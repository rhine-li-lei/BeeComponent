# 勤奋蜂 Android 组件化技术思考
## 整体功能
- 支持分模块调试
- 支持组件间相互调用
- 编译时自动注册组件，无需手动维护组件注册表

## 预期功能
- 编译时自动切换（app or library）
- 编译时自动指定 AndroidManifest.xml 文件（debug 时 or library 时）
- 编译时自动依赖 debug 时所需组件或运行时组件
- 登陆状态校验
- module 内组件回调加速
- 使用责任链模式来优化，使用响应式编程来指导
- 支持超时设置
- 支持组件间相互调用（不只是Activity跳转，支持任意指令的调用/回调）
- 支持app间调用的开关及权限设置（满足不同级别的安全需求，默认打开状态且不需要权限）
- 支持同步/异步方式调用
- 支持动态注册/反注册组件(IDynamicComponent)

## 使用
[新建module指南 - 模块单独调试](./comp_talking/src/main/runalone/explain.md)

## 参考文章
- 组件化思想及源码
1. [Android组件化方案](https://blog.csdn.net/guiying712/article/details/55213884)
1. [得到app组件化源码 作者版：JIMU ](https://github.com/mqzhangw/JIMU)
1. [得到app组件化：Android彻底组件化方案实践](https://www.jianshu.com/p/1b1d77f58e84)
1. [AndroidModulePattern github 源码](https://github.com/guiying712/AndroidModulePattern)

- Gradle插件、Maven
1. [Gradle for Android 翻译版 ](https://segmentfault.com/a/1190000004229002)
1. [Gradle插件开发](https://www.jianshu.com/p/3c59eded8155)
1. [Gradle User Guide](https://docs.gradle.org/current/userguide/userguide_single.html#custom_plugins)
1. [Writing Custom Plugins](https://docs.gradle.org/current/userguide/custom_plugins.html)
1. [在AndroidStudio中自定义Gradle插件](https://www.jianshu.com/p/d53399cd507b)
1. [如何使用Android Studio开发Gradle插件](https://blog.csdn.net/sbsujjbcy/article/details/50782830)
1. [拥抱 Android Studio 之五：Gradle 插件开发](http://geek.csdn.net/news/detail/64058)
1. [AndroidStudio本地化配置gradle的buildToolsVersion和gradleBuildTools](https://blog.csdn.net/guiying712/article/details/72629948)
1. [Gradle for Android（二）全局设置、自定义BuildConfig](https://www.cnblogs.com/xinmengwuheng/p/5797048.html)
1. [RUNTIME CLASSPATH VS COMPILE-TIME CLASSPATH](http://techblog.bozho.net/runtime-classpath-vs-compile-time-classpath/)
1. [Dependency Scope](http://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html#Dependency_Scope)
1. [creating and managing module](https://www.jetbrains.com/help/idea/creating-and-managing-modules.html)
1. [发布Android studio项目到本地Maven仓库](https://www.jianshu.com/p/8d7d0cc8fcc3)
1. [Android studio User Guide The Top-level Build File](https://developer.android.com/studio/build/index.html#top-level)
1. [ Gradle Docs 4.7 xtra properties](https://docs.gradle.org/current/userguide/writing_build_scripts.html#sec:extra_properties)

- 注解相关技术
1. [知乎专栏 聊聊Apt/Annotation Processor](https://zhuanlan.zhihu.com/p/38433630)
1. [Android 学习使用annotationprocessor自动生成java文件](https://blog.csdn.net/msn465780/article/details/78888668)
1. [Android编译时注解APT实战（AbstractProcessor）](https://www.jianshu.com/p/07ef8ba80562)
1. [ javapoet 官方github地址 （注意看REAGME）](https://github.com/square/javapoet)

- 路由技术
1. [ARouter](https://github.com/alibaba/ARouter)
1. [EasyRouter](https://github.com/Zane96/EasyRouter)

- MVP架构
1. [MVP 模式](http://kaedea.com/2015/10/11/android-mvp-pattern/)
1. [Android MVP 详解 (上) ](http://www.jianshu.com/p/9a6845b26856)
1. [Android MVP 详解（下）](https://www.jianshu.com/p/0590f530c617)

- 相关 app 参考
1. [微阅 app](https://github.com/Will-Ls/WeiYue)
1. [[Android]如何做一个崩溃率少于千分之三噶应用app--章节列表 苍王](https://www.jianshu.com/p/94a05b996d78)
