# WanAndroid-Compose
> 该项目使用 Jetpack Compose 开发并通过 WanAndroid 开放 API 实现。

# 效果
<img src="https://github.com/binZai-ComeOn/WanAndroid-Compose/blob/master/img/%E9%A6%96%E9%A1%B5.png" width="180" height="360"> <img src="https://github.com/binZai-ComeOn/WanAndroid-Compose/blob/master/img/%E4%B8%AA%E4%BA%BA.png" width="180" height="360"> <img src="https://github.com/binZai-ComeOn/WanAndroid-Compose/blob/master/img/%E4%BD%93%E7%B3%BB-%E5%AF%BC%E8%88%AA.png" width="180" height="360"> <img src="https://github.com/binZai-ComeOn/WanAndroid-Compose/blob/master/img/%E4%BD%93%E7%B3%BB.png" width="180" height="360"> <img src="https://github.com/binZai-ComeOn/WanAndroid-Compose/blob/master/img/%E5%B9%BF%E5%9C%BA.png" width="180" height="360"> <img src="https://github.com/binZai-ComeOn/WanAndroid-Compose/blob/master/img/%E6%90%9C%E7%B4%A2.png" width="180" height="360"> <img src="https://github.com/binZai-ComeOn/WanAndroid-Compose/blob/master/img/%E9%A1%B9%E7%9B%AE.png" width="180" height="360">

# 使用依赖
 - [Paging](https://developer.android.google.cn/jetpack/androidx/releases/paging?hl=zh_cn#groovy)：数据自动分页加载.
 - [Activity](https://developer.android.google.cn/jetpack/androidx/releases/activity?hl=zh-cn)：访问基于 Activity 构建的可组合 API.
 - [Logger](https://github.com/orhanobut/logger)：简单、漂亮且功能强大的 Android 记录器.
 - [Coroutines](https://github.com/Kotlin/kotlinx.coroutines)：对具有多平台支持的 Kotlin 协程的库支持.
 - [Retrofit](https://github.com/square/retrofit)：适用于 Android 和 JVM 的类型安全 HTTP 客户端.
 - [Core](https://developer.android.google.cn/jetpack/androidx/releases/core?hl=zh-cn)：针对最新的平台功能和 API 调整应用，同时还支持旧设备.
 - [Coil](https://coil-kt.github.io/coil/README-zh/)：Coil 是一个 Android 图片加载库，通过 Kotlin 协程的方式加载图片.
 - [Gson](https://github.com/google/gson)：一个 Java 序列化/反序列化库，用于将 Java 对象转换为 JSON 并返回.
 - [Accompanist](https://github.com/google/accompanist)：通过开发人员通常需要但尚未提供的功能来补充Jetpack Compose.
 - [Compose UI](https://developer.android.google.cn/jetpack/androidx/releases/compose-ui?hl=zh-cn)：与设备互动所需的 Compose UI 的基本组件，包括布局、绘图和输入.
 - [Compose Material](https://developer.android.google.cn/jetpack/androidx/releases/compose-material?hl=zh-cn)：使用现成可用的 Material Design 组件构建 Jetpack Compose UI.
 - [DataStore](https://developer.android.google.cn/jetpack/androidx/releases/datastore?hl=zh_cn)：其以异步、一致的事务方式存储数据，克服了 SharedPreferences 的一些缺点.
 - [ConstraintLayout](https://developer.android.google.cn/jetpack/compose/layouts/constraintlayout?hl=zh-cn)：ConstraintLayout 是一种布局，让您可以相对于屏幕上的其他可组合项来放置可组合项.
 - [Lifecycle](https://developer.android.google.cn/jetpack/androidx/releases/lifecycle?hl=zh-cn)：生命周期感知型组件可执行操作来响应另一个组件（如 activity 和 fragment）的生命周期状态的变化.
 - [Navigation](https://developer.android.google.cn/jetpack/androidx/releases/navigation?hl=zh-cn)：用于在 Android 应用中的“目的地”之间导航，该框架提供一致的 API，无论目的地是作为 Fragment、Activity 还是其他组件实现.
 - [Room](https://developer.android.google.cn/jetpack/androidx/releases/room?hl=zh_cn)：Room 持久性库在 SQLite 的基础上提供了一个抽象层，让用户能够在充分利用 SQLite 的强大功能的同时，获享更强健的数据库访问机制.

# 参考网址
 - [Material Design](https://m3.material.io/)：Material 3是谷歌开源设计系统的最新版本，这里有着Material 3组件的相关使用教程。 

# 功能列表
 - 首页
    - 轮播图 √
    - 文章列表 √
 - 公众号
    - 公众号列表 √
    - 公众号文章列表 √
 - 体系
    - 体系与导航 √
    - 体系列表 √
    - 导航侧边栏 √
    - 导航列表 √
 - 广场
    - 广场列表 √
    - 添加分享文章 
 - 项目
    - 项目列表 √
 - 设置
    - 登录 √
    - 注册 √
    - 我的收藏 √
    - 系统设置 √
    - 我的积分 √
    - 积分规则 √
    - 积分排行 √
    - 主题切换
    - 我的分享 √
 - 搜索
   - 搜索 √
   - 热门搜索 √
   - 历史搜索 √
 - 其它
   - 上架Google商店 
   - 列表可下拉刷新
   - 适配Android横竖屏 √
   - App整体卡顿、性能优化
   - 可打包成 Web 网页应用
   - 可打包成Desktop电脑桌面应用
   - 历史的标签触发长按事件，右上角显示删除该标签的按钮
   - 上拉隐藏顶部标题栏与底部导航栏，下拉显示顶部标题栏和底部导航栏

# 待优化
 - 我的分享数据获取应使用paging3获取分页数据
 - 从其它页面切换到首页页面，数据会重新渲染两次
 - InputText暂时无法在首次输入文本时出现清除的小图标
 - 搜索测试两个字，会出现带html的标题，无法将这些html标题解析
 - 使用Android Studio自带的Android模拟器使用DataStore无法获取数据
