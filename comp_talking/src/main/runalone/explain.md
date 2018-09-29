这个文件夹用于单独运行测试的时候配置一些信息，例如调起目标Activity等，方便调试，在打包工程的时候，这个文件夹下的所有内容将被移除（命名尽量规范，确保能够被依赖和移出）

单独运行配置
1. 新建一个module，类型选择 Phone & Table Module

2. 将本module中的**gradle.properties**中添加 isRunAlone=true
   例如本module： F:\GitFile\github\BeeComponentForAndroid\module_sample\gradle.properties

3. 在本module的 build.gradle 中，将
```groovy
    apply plugin: 'com.android.application'
```
改成
```groovy
if (isRunAlone.toBoolean()) {
    apply plugin: 'com.android.application'
} else {
    apply plugin: 'com.android.library'
}
```
4. 在src/main 下面新建一个package，命名为 runalone

5 一种原理，两种姿势：在本module的build.gradle 中的 android{}，添加代码如下： 
```groovy
       sourceSets {
           main {
               if (isRunAlone.toBoolean()) {
                   manifest.srcFile 'src/main/runalone/AndroidManifest.xml'
               } else {
                   manifest.srcFile 'src/main/AndroidManifest.xml'
                   //集成开发模式下排除runalone文件夹中的所有Java文件
                   java {
                       exclude 'runalone/**'
                   }
               }
               java.srcDirs = ['src/main/java', 'src/main/runalone/java']
               res.srcDirs = ['src/main/res', 'src/main/runalone/res']
           }
       }
   ```
   
6. runalone文件夹下创建 java 和 res 文件<br/>
   正确姿势1: <br/>
   选中runalone -> File -> new -> Folder(Android logo) -> Java fodler(res folder) -> change folder location -> src/main/runalone/java(res) -> finish
   即使是已经创建java文件夹或者是res文件夹也没关系，可以直接覆盖。
   正确姿势2: <br/>
   直接粘贴复制本module中的
      
7. 如果要在 **runalone** 包中新建的自己的 Application，而且必须继承自 BaseApplication
   命名规则（举例）：runalone\java\com.xxxx.xxxx.runalone\application\ApplicationName.java

8. 在 **runalone** 包下新建一个AndroidManifest.xml，作为单独运行时的 app工程的 AndroidManifest.xml
    业务组件的 AndroidManifest.xml 应该具有一个 Android APP 所具有的的所有属性，尤其是声明 Application 和要 launch的Activity
    默认的AndroidManifest.xml 声明需要的权限、Application、Activity、Service、Broadcast等

9. 在build.gradle中移除所有库依赖，并添加module依赖： implementation project(':base')

10. 依赖 annotationProcessor project(':router-anno-compiler') ，后期使用 gradle 插件自动依赖。