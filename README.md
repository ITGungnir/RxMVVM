# RxMVVM

[![](https://jitpack.io/v/ITGungnir/RxMVVM.svg)](https://jitpack.io/#ITGungnir/RxMVVM)

A basic framework that integrates MVVM and Redux.

## 1、引入
```groovy
implementation "com.github.ITGungnir:RxMVVM:$rxmvvm_version"
```

## 2、使用MVVM
#### 1）BaseVM的使用
`BaseVM`是`MVVM`中`VM`层的基类，是`V`层和`M`层的桥梁，提供方法给`V`层调用，方法中调用`M`层加载数据或其他操作；提供给`V`层一个监听器，监听数据变化。
```kotlin
class AppVM1 : BaseVM() {

    val randomNumberState = MutableLiveData<Int>()

    fun generateRandomNumber() {
        this.randomNumberState.value = (1..100).random()
    }
}
```

#### 2）BaseActivity的使用
`BaseActivity`是`V`层中`Activity`的基类，通过`createVM()`方法绑定`VM`，通过`vm?`调用`VM`层的方法或监听数据变化。
```kotlin
class AppActivity1 : BaseActivity<AppVM1>() {

    override fun contentView(): Int = R.layout.activity_app1

    override fun obtainVM(): AppVM1 = createVM()

    override fun initComponent() {
        button.setOnClickListener {
            vm?.generateRandomNumber()
        }
    }

    override fun observeVM() {
        // 监听VM中状态的变化
        vm?.randomNumberState?.observe(this, Observer {
            it?.let { num -> toast(num.toString()) }
        })
    }
}
```

#### 3）BaseFragment的使用
`BaseFragment`是`Fragment`的基类，用法与`BaseActivity`大同小异。
```kotlin
class AppFragment3 : BaseFragment<AppFragVM3>() {

    override fun contentView(): AppFragUI3 = AppFragUI3()

    override fun shouldBindLifecycle(): Boolean = true

    override fun obtainVM(): AppFragVM3 = createVM()

    override fun initComponent() {}

    fun generateRandomNumber() {
        vm?.generateRandomNumber()
    }

    override fun observeVM() {
        // 监听VM中状态的变化
        vm?.randomNumberState?.observe(this, Observer {
            it?.let { num -> toast(num.toString()) }
        })
    }
}
```
BaseFragment的VM绑定方法有两种，即`createVM()`和`shareVM()`。前者只是使用自己的VM，而后者则可以与其他Fragment共享同一个VM。
```kotlin
override fun obtainVM(): AppVM4 = shareVM()
```

#### 4）BaseUI的使用
`BaseUI`是基于`Kotlin-Anko`的一个`UI`基类，可以使用`Anko`的特性编写界面，摒弃`XML`界面。
```kotlin
class AppUI2 : BaseUI() {

    private lateinit var uiOwner: AppActivity2

    @SuppressLint("SetTextI18n")
    override fun createView(ui: AnkoContext<LifecycleOwner>): View = with(ui) {
        uiOwner = ui.owner as AppActivity2
        verticalLayout {
            button {
                text = "点击弹出随机数Toast"
            }.lparams(wrapContent, wrapContent).apply {
                setOnClickListener {
                    uiOwner.generateRandomNumber()
                }
            }
        }.apply {
            gravity = Gravity.CENTER
        }
    }
}
```

## 3、使用Redux
使用Redux时需要自定义AppState、Action、Reducer和Middleware。

#### 1）创建State
State中可以存储应用中的状态。
```kotlin
data class AppState(
    val result: Int = 0
)
```

#### 2）创建Action
每个Action表示一个动作，需要实现Action接口。
```kotlin
data class GetResult(val result: Int) : Action
```

#### 3）创建Reducer
Reducer用来处理Action，将Action中的数据更新到State中。自定义Reducer需要实现Reducer接口。
```kotlin
class MyReducer : Reducer {

    override fun reduce(state: Any, action: Action): Any {

        if (state !is AppState) {
            return AppState()
        }

        return when (action) {
            is GetResult -> state.copy(result = action.result)
            else -> state
        }
    }
}
```

#### 4）创建Middleware
Middleware用于将一个Action转换成另一个Action，需要实现Middleware接口。
```kotlin
class PlusMiddleware : Middleware {

    override fun apply(state: Any, action: Action): Action {

        if (state !is AppState) {
            return action
        }

        return when (action) {
            is ChangeNum -> MultiTwo(action.newNum + 1)
            else -> action
        }
    }
}
```

#### 5）初始化Redux
建议在Application类中初始化Redux，初始化时需要传入上下文、State类型、初始状态、Reducer对象和Middleware列表。
```kotlin
Redux.init(
    this,
    AppState::class.java,
    AppState(),
    MyReducer(),
    listOf(PlusMiddleware(), MultipleMiddleware())
)
```

#### 6）使用Redux
Redux的使用包括发送Action和监听State两个步骤。
```kotlin
// 发送Action
Redux.instance?.dispatch(ChangeNum(number), false)
```
```kotlin
// 监听State
Redux.instance?.pick { (it as AppState).result }?.observe(this, Observer {
    it?.let { num ->
        if (number > 1) {
            reduxText.text = "($number + 1) * 2 = $num"
        }
        number++
    }
})
```