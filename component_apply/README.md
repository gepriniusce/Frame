# 2020-04-19
高仿得到组件化，自定义BuildGradle

## 接入

rootProject build.gradle 的依赖

    dependencies {
    	classpath 'pr.tongson:build-gradle-plugin:1.0.0-SNAPSHOT'
    	}

module build.gradle 的依赖

    apply plugin: 'pr.tongson.BuildGradle'

module 创建gradle.properties

    isRunAlone=true

module 创建runAlone

    src/main/runAlone/AndroidManifest.xml