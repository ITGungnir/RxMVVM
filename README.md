# RxMVVM

[![](https://jitpack.io/v/ITGungnir/RxMVVM.svg)](https://jitpack.io/#ITGungnir/RxMVVM)
![License](https://img.shields.io/badge/License-Apache2.0-blue.svg)
![](https://img.shields.io/badge/Email-itgungnir@163.com-ff69b4.svg)

A basic framework that integrates both MVVM and Redux.

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

#### 3）在Activity中使用
新版本的`RxMvvm`中不再封装`BaseActivity`、`BaseFragment`和`LazyFragment`，因此只需要继承`AppCompatActivity`或`Fragment`即可。
在`Activity`中通过`buildActivityViewModel()`方法绑定`VM`，从而可以调用`VM`层的方法或监听数据变化。
```kotlin
class AppActivity1 : AppCompatActivity() {

    private val viewModel by lazy {
        buildActivityViewModel(
            activity = this,
            viewModelClass = AppViewModel1::class.java
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app1)

        initComponent()
        observeVM()
    }

    private fun initComponent() {
        button.setOnClickListener {
            viewModel.generateRandomNumber()
        }
    }

    private fun observeVM() {

        viewModel.pick(AppState1::randomNum)
            .observe(this, Observer { randomNum ->
                randomNum?.a?.let {
                    number.text = it.toString()
                }
            })

        viewModel.pick(AppState1::error)
            .observe(this, Observer { error ->
                error?.a?.message?.let {
                    Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                }
            })
    }
}
```

#### 4）在Fragment中使用
`Fragment`的使用与`Activity`的使用相似，它的`VM`绑定方法有两种，即`buildActivityViewModel()`和`buildFragmentViewModel()`。
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

#### 5）Fragment懒加载的使用
新版本的`RxMvvm`不再提供`LazyFragment`的`API`，因为`Google`已经废弃了`setUserVisibleHint()`方法，并提供了新的`setMaxLifecycle()`方法，其使用方法分为以下两步。

第一步，在创建`FragmentPagerAdapter`或`FragmentStatePagerAdapter`时，调用两个方法的构造函数，代码如下：
```kotlin
adapter = object : FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment = FragChild.newInstance(position)
    override fun getCount(): Int = pageCount
}
```

第二步，在子`Fragment`的`onResume()`方法中进行页面的初始化：
```kotlin
class FragChild : Fragment() {

    private var isInitialized = false

    override fun onResume() {
        super.onResume()
        if (!isInitialized) {
            // perform lazy loading
            isInitialized = true
        }
    }
}
```

#### 6）ViewModel传参
想要通过`ViewModel`传参，需要通过`ViewModelProvider.Factory`创建`ViewModel`的实例。新版本的`RxMvvm`提供的`buildActivityViewModel()`和`buildFragmentViewModel()`方法中新加了`factory`参数，
但需要先在具体的`ViewModel`类中创建工厂：
```kotlin
class AppViewModel7 constructor() : BaseViewModel<AppState7>(initialState = AppState7()) {

    constructor(initialData: Int) : this() {
        setState {
            copy(
                data = initialData
            )
        }
    }

    class Factory(private val initialData: Int) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T = AppViewModel7(initialData = initialData) as T
    }

    fun changeData() {
        var newData: Int = (Math.random() * 1000 + 10).toInt()
        while (newData == getState().data) {
            newData = (Math.random() * 1000 + 10).toInt()
        }
        setState {
            copy(
                data = newData
            )
        }
    }
}
```

在`Activity`中创建`ViewModel`的代码如下：
```kotlin
private val viewModel by lazy {
    buildActivityViewModel(
        activity = this,
        viewModelClass = AppViewModel7::class.java,
        factory = AppViewModel7.Factory(9999)
    )
}
```

## 3、使用Redux
`Redux`是一种前端的全局状态管理框架，它不仅可以存储全局的状态信息，还可以在系统各个组件中监听全局的状态的变化。

参考：
[Redux入门一](http://www.ruanyifeng.com/blog/2016/09/redux_tutorial_part_one_basic_usages.html)、
[Redux入门二](http://www.ruanyifeng.com/blog/2016/09/redux_tutorial_part_two_async_operations.html)、
[Redux入门三](http://www.ruanyifeng.com/blog/2016/09/redux_tutorial_part_three_react-redux.html)

本项目中的`Redux`部分旨在提供一个轻量级的全局状态机和事件总线功能。

使用`Redux`时需要自定义`Redux`子类、`AppState`、`Action`、`Reducer`和`Middleware`。

#### 1）创建State
`State`中可以存储应用中的全局状态，在变量前面加上`@DoPersist`注解，可以将这个变量的值持久化到`SharedPreferences`中，如果不加这个注解，则不会做持久化操作。
```kotlin
data class AppState(
    val result: Int = 0,
    val loginFail: Unit? = null,
    val backPressureNum: Long = 0L,
    @DoPersist val username: String = ""
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

    override fun apply(state: AppState, action: Action, dispatch: (Action) -> Unit): Action = when (action) {
        is ChangeNum -> {
            Observable.just(action.newNum + 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    dispatch(MultiTwo(it))
                }
            NullAction
        }
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
    reducer = MyReducer()
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
MyRedux.instance.dispatch(ChangeNum(currNum), listOf(PlusMiddleware(), MultipleMiddleware()))
```
```kotlin
// 监听State
disposable = MyRedux.instance.pick(AppState::result).subscribe {
    if (currNum == 1) {
        tvResult.text = "(1 + 1) * 2 = 4"
    } else {
        tvResult.text = "($currNum + 1) * 2 = ${it.a}"
    }
    currNum++
}
```
如果本次`dispatch`的事件不需要中间件处理，则可以不传这个参数：
```kotlin
MyRedux.instance.dispatch(Logout)
```
发送Action的过程可以同步完成，也可以异步完成：
```kotlin
Observable.just(1)
    .subscribeOn(Schedulers.io())
    .subscribe {
        MyRedux.instance.dispatch(ToastEvent, listOf())
    }
```
除此之外，也可以通过`currState`对象获取到当前全局状态对象：
```kotlin
currentUserName.text = "当前用户名：${MyRedux.instance.currState.username}"
```

#### 8）注意内存泄漏
**注意：在使用RxMVVM中的Redux功能时，请注意Disposable的取消订阅操作，否则会导致内存泄漏。**
```kotlin
private var disposable: Disposable? = null
disposable = MyRedux.instance.pick(AppState::toastEvent).subscribe {
    it.a?.let {
        Toast.makeText(this, "消费ToastEvent事件", Toast.LENGTH_SHORT).show()
    }
}
disposable?.takeIf { !it.isDisposed }?.dispose()
```

## Change Logs
#### v1.6.5
* Redux核心从LiveData切换到RxJava
* Redux支持背压
* Redux部分API更改

#### v1.6.4
* ViewModel中支持从非主线程中推送数据

#### v1.6.3
* ViewModel支持传入参数

## License
```text
Copyright 2019 ITGungnir

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```