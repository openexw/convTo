package com.github.openexw.convto.services

import com.intellij.openapi.project.Project
import com.github.openexw.convto.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))

//        project.setH
//        System.getenv("CI")
            ?: TODO("Don't forget to remove all non-needed sample code files with their corresponding registration entries in `plugin.xml`.")
    }
}
