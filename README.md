# RxMVVM

[![](https://jitpack.io/v/ITGungnir/RxMVVM.svg)](https://jitpack.io/#ITGungnir/RxMVVM)

A basic framework that integrates MVVM and Redux.

## 1、引入
```groovy
implementation "com.github.ITGungnir:RxMVVM:$rxmvvm_version"
```

## 2、使用MVVM

#### 1）State的使用
`State`用于保存当前页面中的所有必要保存的状态，是一个`data class`，需要实现`my.itgungnir.rxmvvm.core.mvvm.State`接口。
```kotlin
data class AppState1(
    val randomNum: Int = 0,
    val error: Throwable? = null
) : State
```

#### 2）BaseViewModel的使用
`BaseViewModel`是`MVVM`中`VM`层的基类，提供方法给`V`层调用，并提供给`V`层一个监听器，监听数据变化。
```kotlin
class AppViewModel1 : BaseViewModel<AppState1>(initialState = AppState1()) {

    @SuppressLint("CheckResult")
    fun generateRandomNumber() {
        Single.just((1..100).random())
            .subscribe({
                setState {
                    copy(
                        randomNum = it,
                        error = null
                    )
                }
            }, {
                setState {
                    copy(
                        error = Throwable(message = "生成随机数失败！")
                    )
                }
            })
    }
}
```

#### 3）BaseActivity的使用
`BaseActivity`是`V`层中`Activity`的基类，通过`buildActivityViewModel()`方法绑定`VM`，从而可以调用`VM`层的方法或监听数据变化。
```kotlin
class AppActivity1 : BaseActivity() {

    private val viewModel by lazy {
        buildActivityViewModel(
            activity = this,
            viewModelClass = AppViewModel1::class.java
        )
    }

    override fun layoutId(): Int = R.layout.activity_app1

    override fun initComponent() {
        button.setOnClickListener {
            viewModel.generateRandomNumber()
        }
    }

    override fun observeVM() {

        viewModel.pick(AppState1::randomNum)
            .observe(this, Observer { randomNum ->
                randomNum?.a?.let {
                    number.text = it.toString()
                }
            })

        viewModel.pick(AppState1::error)
            .observe(this, Observer { error ->
                error?.a?.message?.let {
                    toast(it)
                }
            })
    }
}
```

#### 4）BaseFragment的使用
`BaseFragment`是`Fragment`的基类，用法与`BaseActivity`大同小异。
```kotlin
class FragBottom : BaseFragment() {

    private val viewModel by lazy {
        buildActivityViewModel(
            activity = activity!!,
            viewModelClass = AppViewModel2::class.java
        )
    }

    override fun layoutId(): Int = R.layout.fragment_app2_bottom

    override fun initComponent() {}

    override fun observeVM() {

        viewModel.pick(AppState2::randomNum)
            .observe(this, Observer { num ->
                num?.a?.let {
                    randomNum.text = it.toString()
                }
            })

        viewModel.pick(AppState2::error)
            .observe(this, Observer { error ->
                error?.a?.message?.let {
                    toast(it)
                }
            })
    }
}
```
`BaseFragment`的`VM`绑定方法有两种，即`buildActivityViewModel()`和`buildFragmentViewModel()`。
前者可以与其他`Fragment`共享同一个`VM`，而后者则只是使用自己的`VM`。
```kotlin
private val innerViewModel by lazy {
    buildFragmentViewModel(
        fragment = this,
        viewModelClass = ChildViewModel::class.java
    )
}
private val outerViewModel by lazy {
    buildActivityViewModel(
        activity = activity!!,
        viewModelClass = AppViewModel4::class.java
    )
}
```

#### 4）LazyFragment的使用
`LazyFragment`是一种支持懒加载的`Fragment`，即仅当当前`Fragment`被用户可见且之前没有被加载过时才加载数据。
通过回调`onLazyLoad()`方法处理懒加载事件。
```kotlin
class FragChild : LazyFragment() {

    private val innerViewModel by lazy {
        buildFragmentViewModel(
            fragment = this,
            viewModelClass = ChildViewModel::class.java
        )
    }

    private val outerViewModel by lazy {
        buildActivityViewModel(
            activity = activity!!,
            viewModelClass = AppViewModel4::class.java
        )
    }

    private var flag = 0

    companion object {
        fun newInstance(flag: Int) = FragChild().apply { this.flag = flag }
    }

    override fun layoutId(): Int = R.layout.fragment_app4_child

    @SuppressLint("SetTextI18n")
    override fun initComponent() {
        title.text = "App4 Fragment $flag"

        button.setOnClickListener {
            innerViewModel.generateRandomNumber()
        }
    }

    override fun onLazyLoad() {
        outerViewModel.appendLog("Fragment$flag 懒加载成功！")
    }

    override fun observeVM() {

        innerViewModel.pick(ChildState::randomNum)
            .observe(this, Observer { randomNum ->
                randomNum?.a?.let {
                    number.text = it.toString()
                }
            })

        innerViewModel.pick(ChildState::error)
            .observe(this, Observer { error ->
                error?.a?.message?.let {
                    toast(it)
                }
            })
    }
}
```

## 3、使用Redux
使用`Redux`时需要自定义`Redux`子类、`AppState`、`Action`、`Reducer`和`Middleware`。

#### 1）创建State
`State`中可以存储应用中的状态。
```kotlin
data class AppState(
    val result: Int = 0
)
```

#### 2）创建Action
每个`Action`表示一个动作，需要实现`Action`接口。
```kotlin
data class GetResult(val result: Int) : Action
```

#### 3）创建Reducer
`Reducer`用来处理`Action`，将`Action`中的数据更新到`State`中。自定义`Reducer`需要实现`Reducer`接口。
```kotlin
class MyReducer : Reducer<AppState> {

    override fun reduce(state: AppState, action: Action): AppState = when (action) {
        is GetResult ->
            state.copy(result = action.result)
        else ->
            state
    }
}
```

#### 4）创建Middleware
`Middleware`用于将一个`Action`转换成另一个`Action`，需要实现`Middleware`接口。
```kotlin
class PlusMiddleware : Middleware<AppState> {

    override fun apply(state: AppState, action: Action): Action = when (action) {
        is ChangeNum ->
            MultiTwo(action.newNum + 1)
        else ->
            action
    }
}
```

#### 5）创建Redux子类
`Redux`子类需要继承`BaseRedux`类，并配置好其中的初始化数据，包括`Reducer`、`Middleware`等。
```kotlin
class MyRedux(context: Application) : BaseRedux<AppState>(
    context = context,
    initialState = AppState(),
    reducer = MyReducer(),
    middlewareList = listOf(PlusMiddleware(), MultipleMiddleware())
) {

    companion object {

        lateinit var instance: MyRedux

        fun init(context: Application) {
            instance = MyRedux(context)
        }
    }

    override fun deserializeToCurrState(json: String): AppState? =
        Gson().fromJson(json, AppState::class.java)
}
```
**注意：** `BaseRedux`的子类需要重写`deserializeToCurrState`方法，这个方法用于提供Json字符串向全局状态的映射规则。

#### 6）初始化Redux
建议在`Application`类中初始化`Redux`，初始化时需要传入上下文。
```kotlin
MyRedux.init(this)
```

#### 7）使用Redux
`Redux`的使用包括发送`Action`和监听`State`两个步骤。
```kotlin
// 发送Action
MyRedux.instance.dispatch(ChangeNum(number), false)
```
```kotlin
// 监听State
MyRedux.instance.pick(AppState::result).observe(this, Observer {
    it?.a?.let { num ->
        if (number > 1) {
            reduxText.text = "($number + 1) * 2 = $num"
        }
        number++
    }
})
```
发送Action的过程可以同步完成，也可以异步完成：
```kotlin
Single.just(ChangeNum(number))
    .subscribeOn(Schedulers.io())
//    .observeOn(AndroidSchedulers.mainThread())
    .observeOn(Schedulers.io())
    .subscribe({
        MyRedux.instance.dispatch(it, true)
    }, {
        println("------>>error: ${it.message}")
    })
```
除此之外，也可以通过`currState()`方法获取到当前全局状态对象：
```kotlin
println("------>>${MyRedux.instance.currState().result}")
```