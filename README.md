# WanAndroid-Compose

# 代码解释
 - by remember
    
# 组件替代方案
ModalDrawer 可使用 NavigationDrawer 替代，后者可用于适配响应式设备

# 组件使用情况
 - ModalDrawer：用于实现侧边栏等功能。
 - HorizontalPager：用于实现轮播图等功能。

# 依赖
 - [Core](https://developer.android.google.cn/jetpack/androidx/releases/core?hl=zh-cn)：针对最新的平台功能和 API 调整应用，同时还支持旧设备.
 - [Compose UI](https://developer.android.google.cn/jetpack/androidx/releases/compose-ui?hl=zh-cn)：与设备互动所需的 Compose UI 的基本组件，包括布局、绘图和输入.
 - [Lifecycle](https://developer.android.google.cn/jetpack/androidx/releases/lifecycle?hl=zh-cn)：生命周期感知型组件可执行操作来响应另一个组件（如 activity 和 fragment）的生命周期状态的变化.
 - [Activity](https://developer.android.google.cn/jetpack/androidx/releases/activity?hl=zh-cn)：访问基于 Activity 构建的可组合 API.
 - [Compose Material](https://developer.android.google.cn/jetpack/androidx/releases/compose-material?hl=zh-cn)：使用现成可用的 Material Design 组件构建 Jetpack Compose UI.
 - [Navigation](https://developer.android.google.cn/jetpack/androidx/releases/navigation?hl=zh-cn)：用于在 Android 应用中的“目的地”之间导航，该框架提供一致的 API，无论目的地是作为 Fragment、Activity 还是其他组件实现.
 - [Accompanist](https://github.com/google/accompanist)：通过开发人员通常需要但尚未提供的功能来补充Jetpack Compose.
 - [Coroutines](https://github.com/Kotlin/kotlinx.coroutines)：对具有多平台支持的 Kotlin 协程的库支持.
 - [Gson](https://github.com/google/gson)：一个 Java 序列化/反序列化库，用于将 Java 对象转换为 JSON 并返回.
 - [Retrofit](https://github.com/square/retrofit)：适用于 Android 和 JVM 的类型安全 HTTP 客户端.
 - [Logger](https://github.com/orhanobut/logger)：简单、漂亮且功能强大的 Android 记录器.

# 参考网址
 - [Material Design](https://m3.material.io/)：Material 3是谷歌开源设计系统的最新版本，这里有着Material 3组件的相关使用教程。 

# 后期需求
 - 首页
    - 轮播图
    - 文章列表
 - 公众号
    - 公众号列表
    - 公众号文章列表
 - 体系
    - 体系与导航
    - 体系列表与导航栏列表
 - 广场
    - 广场列表
 - 项目
    - 项目列表
 - 设置
    - 登录
    - 我的收藏、系统设置、我的积分、积分排行、主题切换、我的分享等功能
 - 其它
    - 上拉隐藏顶部标题栏与底部导航栏，下拉显示顶部标题栏和底部导航栏
    - 适配Android横竖屏及Desktop