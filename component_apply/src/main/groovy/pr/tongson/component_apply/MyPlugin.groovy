package pr.tongson.component_apply

import org.gradle.api.Plugin
import org.gradle.api.Project


/**
 * 仿得到组件化的自定义Plugin实现一键
 */
class MyPlugin implements Plugin<Project> {
    /**
     * 默认是app，直接运行assembleRelease的时候，等同于运行app:assembleRelease
     */
    String apiModuleName = "app"
    String mainModuleKey = "mainModuleName"
    String currentModuleName
    String mainModuleName
    List<AssembleTask> taskNames
    boolean isRunAlone

    void apply(Project project) {
        println("##########TongsonDIYBuildGradleHelper##########->Start")
        project.extensions.create('TongsonBuildGradle', MyExtension)

        //判断gradle.properties中是否含有isRunAlone
        if (!project.hasProperty("isRunAlone")) {
            throw new RuntimeException("you should set isRunAlone in " + currentModuleName + "'s gradle.properties")
        }

        //获取所有 taskNames
        taskNames = project.gradle.startParameter.taskNames
        //获取 rootProject gradle.properties 中 mainModuleKey
        mainModuleName = project.rootProject.property(mainModuleKey)
        //获取 gradle.properties 中 isRunAlone
        isRunAlone = Boolean.parseBoolean(project.properties.get("isRunAlone"))
        //获取当前 module name
        currentModuleName = project.path.replace(":", "")

        println("##########taskNames:" + taskNames.toString())
        println("##########mainModuleName:" + mainModuleName)
        println("##########isRunAlone:" + isRunAlone)
        println("##########currentModuleName:" + currentModuleName)


        //得到全部的 assembleTasks
//        List<AssembleTask> assembleTasks = getTaskInfos(taskNames)

        //获取当前module的AssembleTask
//        AssembleTask assembleTask = fetchCurrentModuleName(project, assembleTasks)


//        List<AssembleTask> assembleTasks = getTaskInfos(project)
//
//        for (AssembleTask a : assembleTasks) {
//            println("=====" + a.toString())
//            if (a.isAssemble) {
//                currentModuleName = apiModuleName
//            }
//        }

        AssembleTask assembleTask = getTaskInfo(taskNames)
        if (assembleTask.isAssemble) {
            fetchMainModuleName(project, assembleTask)
        }
        println("apiModule is:" + apiModuleName)


        //获取rootProject gradle.properties中isRunAlone
        if (isRunAlone && assembleTask.isAssemble) {
            //对于要编译的组件和主项目，isRunAlone修改为true，其他组件都强制修改为false
            //这就意味着组件不能引用主项目，这在层级结构里面也是这么规定的
            if (currentModuleName == mainModuleName) {
                isRunAlone = true
            } else if (currentModuleName == apiModuleName) {
                isRunAlone = true
            } else {
                isRunAlone = false
            }
        }
        project.setProperty("isRunAlone", isRunAlone)
        //根据配置添加各种组件依赖，并且自动化生成组件加载代码
        if (isRunAlone) {
            project.apply plugin: 'com.android.application'
            if (currentModuleName != mainModuleName) {
                project.android.sourceSets {
                    main {
                        manifest.srcFile 'src/main/runAlone/AndroidManifest.xml'
                        java.srcDirs = ['src/main/java', 'src/main/runAlone/java']
                        res.srcDirs = ['src/main/res', 'src/main/runAlone/res']
                        assets.srcDirs = ['src/main/assets', 'src/main/runAlone/assets']
                        jniLibs.srcDirs = ['src/main/jniLibs', 'src/main/runAlone/jniLibs']
                    }
                }
            }
            println("============================================")
            println("current module is " + currentModuleName)
            println("apply plugin is " + 'com.android.application')
            println("============================================")
            if (assembleTask.isAssemble) {
                // && currentModuleName == mainModuleName
                compileComponents(assembleTask, project)
//                project.android.registerTransform(new ComCodeTransform(project))
            }
        } else {
            project.apply plugin: 'com.android.library'
            println("============================================")
            println("current module is " + currentModuleName)
            println("apply plugin is " + 'com.android.library')
            println("============================================")
            project.android.sourceSets {
                main {
                    //release時debug目錄下文件不需要合併到主工程
                    java {
                        exclude 'src/main/runAlone/AndroidManifest.xml'
                        exclude 'src/main/runAlone/java'
                    }
                    res {
                        exclude 'src/main/runAlone/res'
                    }
                    assets {
                        exclude 'src/main/runAlone/assets'
                    }
                    jniLibs {
                        exclude 'src/main/runAlone/jniLibs'
                    }
                }
            }
        }
        println("##########TongsonDIYBuildGradleHelper##########->End")
    }

    private List<AssembleTask> getTaskInfos(List<String> taskNames) {
        List<AssembleTask> assembleTasks = new ArrayList<>()
        for (String taskName : taskNames) {
            AssembleTask assembleTask = new AssembleTask()
            println("taskName:" + taskName)
            if (taskName.toUpperCase().contains("ASSEMBLE")) {

                assembleTask.isAssemble = true
                if (taskName.toUpperCase().contains("DEBUG")) {
                    assembleTask.isDebug = true
                }
                String[] split = taskName.split(":")
                assembleTask.modules.add(split.length > 1 ? split[split.length - 2] : "all")
            }
            assembleTasks.add(assembleTask)
        }
        return assembleTasks
    }

    private List<AssembleTask> getTaskInfos(Project project) {
        List<AssembleTask> assembleTasks = new ArrayList<>()
        for (String taskName : taskNames) {
            AssembleTask assembleTask = new AssembleTask()
            println("getTaskInfos-taskName:" + taskName)
            if (taskName.toUpperCase().contains("ASSEMBLE")) {
                String components
                if (taskName.toUpperCase().contains("DEBUG")) {
                    assembleTask.isDebug = true
                    components = (String) project.properties.get("debugComponent")
                } else {
                    components = (String) project.properties.get("compileComponent")
                }

                if (components == null || components.length() == 0) {
                    assembleTask.isAssemble = false
                } else {
                    assembleTask.isAssemble = true
                }

                String[] split = taskName.split(":")
                assembleTask.modules.add(split.length > 1 ? split[split.length - 2] : "all")
            }
            assembleTasks.add(assembleTask)
        }
        return assembleTasks
    }

    private AssembleTask getTaskInfo(List<String> taskNames) {
        AssembleTask assembleTask = new AssembleTask()
        //[:module_plalyer:assembleDebug, :demo_autotrack:assembleDebug, :app:assembleRelease, :module_im:assembleDebug, :module_welcome:assembleDebug]
        for (String task : taskNames) {
            println("task:" + task)
            if (task.toUpperCase().contains("ASSEMBLE")
                    || task.contains("aR")
                    || task.contains("asR")
                    || task.contains("asD")
                    || task.toUpperCase().contains("TINKER")
                    || task.toUpperCase().contains("INSTALL")
                    || task.toUpperCase().contains("RESGUARD")) {
                if (task.toUpperCase().contains("DEBUG")) {
                    assembleTask.isDebug = true
                }
                assembleTask.isAssemble = true
                String[] strs = task.split(":")
                assembleTask.modules.add(strs.length > 1 ? strs[strs.length - 2] : "all")
                continue
            }
        }
        return assembleTask
    }

    private class AssembleTask {
        /**
         * 是否需要组装
         */
        boolean isAssemble = false
        /**
         * 是否是debug
         */
        boolean isDebug = false
        /**
         * task的
         */
        List<String> modules = new ArrayList<>()


        @Override
        public String toString() {
            return "AssembleTask{" +
                    "isAssemble=" + isAssemble +
                    ", isDebug=" + isDebug +
                    ", modules=" + modules.toString() +
                    '}';
        }
    }

    /**
     * 根据当前的task，获取要运行的组件，规则如下：
     * assembleRelease ---app
     * app:assembleRelease :app:assembleRelease ---app
     * sharecomponent:assembleRelease :sharecomponent:assembleRelease ---sharecomponent
     * @param assembleTask
     */
    private AssembleTask fetchCurrentModuleName(Project project, List<AssembleTask> assembleTasks) {
        //判断rootProject 中的 gradle.properties 中是否含有 mainModuleKey
        if (!project.rootProject.hasProperty(mainModuleKey)) {
            throw new RuntimeException("you should set " + mainModuleKey + " in rootProject's gradle.properties")
        }
        AssembleTask resultAssembleTask = new AssembleTask()
        for (AssembleTask assembleTask : assembleTasks) {
            println("assembleTask==>" + assembleTask.modules.toString())
            if (assembleTask.modules.size() > 0
                    && assembleTask.modules.get(0) != null
                    && assembleTask.modules.get(0).trim().length() > 0
                    && assembleTask.modules.get(0) != "all"
                    && currentModuleName == assembleTask.modules.get(0)) {
                apiModuleName = assembleTask.modules.get(0)
                resultAssembleTask = assembleTask
                break
            } else {
                apiModuleName = mainModuleName
            }
            resultAssembleTask = assembleTask
        }
        if (apiModuleName == null || apiModuleName.trim().length() <= 0) {
            apiModuleName = "app"
        }
        return resultAssembleTask
    }

    private void fetchMainModuleName(Project project, AssembleTask assembleTask) {
        //判断rootProject 中的 gradle.properties 中是否含有 mainModuleKey
        if (!project.rootProject.hasProperty(mainModuleKey)) {
            throw new RuntimeException("you should set " + mainModuleKey + " in rootProject's gradle.properties")
        }
        println("666666666666666" + assembleTask.modules.toString())
        if (assembleTask.modules.size() > 0
                && assembleTask.modules.get(0) != null
                && assembleTask.modules.get(0).trim().length() > 0
                && assembleTask.modules.get(0) != "all") {
            apiModuleName = assembleTask.modules.get(0)
        } else {
            apiModuleName = project.rootProject.property(mainModuleKey)
        }
        if (apiModuleName == null || apiModuleName.trim().length() <= 0) {
            apiModuleName = "app"
        }
    }

    /**
     * 自动添加依赖，只在运行assemble任务的才会添加依赖，因此在开发期间组件之间是完全感知不到的，这是做到完全隔离的关键
     * 支持两种语法：module或者groupId:artifactId:version(@aar),前者之间引用module工程，后者使用maven中已经发布的aar
     * @param assembleTask
     * @param project
     */
    private void compileComponents(AssembleTask assembleTask, Project project) {

        println("添加依赖")
        String components
        println("assembleTask.isDebug:" + assembleTask.isDebug)
        if (assembleTask.isDebug) {
            components = (String) project.properties.get("debugComponent")
        } else {
            components = (String) project.properties.get("compileComponent")
        }

        if (components == null || components.length() == 0) {
            println("there is no add dependencies ")
            return
        }
        String[] compileComponents = components.split(",")
        if (compileComponents == null || compileComponents.length == 0) {
            println("there is no add dependencies ")
            return
        }
        for (String str : compileComponents) {
            println("need dependencies api :" + str)
            str = str.trim()
            if (str.startsWith(":")) {
                str = str.substring(1)
            }
            // 是否是maven 坐标
            if (MyUtils.isMavenArtifact(str)) {
                /**
                 * 示例语法:groupId:artifactId:version(@aar)
                 * compileComponent=com.luojilab.reader:readercomponent:1.0.0
                 * 注意，前提是已经将组件aar文件发布到maven上，并配置了相应的repositories
                 */
                project.dependencies.add("api", str)
                println("add dependencies lib  : " + str)
            } else {
                /**
                 * 示例语法:module
                 * compileComponent=module_welcome,module_webview
                 */
                project.dependencies.add("api", project.project(':' + str))
                println("add dependencies project : " + str)
            }
        }
    }

}