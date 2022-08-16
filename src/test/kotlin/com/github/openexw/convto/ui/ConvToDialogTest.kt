package com.github.openexw.convto.ui

import com.intellij.testFramework.fixtures.BasePlatformTestCase

//import org.junit.jupiter.api.Assertions.*

class ConvToDialogTest : BasePlatformTestCase() {
    fun testDialog() {
        val dialog = ConvToDialog("haha")
        dialog.init()
        dialog.isVisible = true
//        dialog.size =
    }
}