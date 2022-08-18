package com.github.openexw.convto.ui

import com.intellij.testFramework.fixtures.BasePlatformTestCase
import com.intellij.openapi.project.Project
//import org.junit.jupiter.api.Assertions.*

class ConvToDialogTest : BasePlatformTestCase() {
    fun testDialog() {
//        Project()
        val dialog = ConvToDialog(project, "haha")
        dialog.show()
//        panel.width = 100

//        dialog.isVisible = true
//        dialog.size =
    }
}