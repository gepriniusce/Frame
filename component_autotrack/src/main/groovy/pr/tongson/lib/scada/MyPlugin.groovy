package pr.tongson.lib.scada

import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class MyPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        MyExtension extension = project.extensions.create("TongsonAutoTrack", MyExtension)

        AppExtension appExtension = project.extensions.findByType(AppExtension.class)
        appExtension.registerTransform(new MyTransform(project, extension))
    }
}