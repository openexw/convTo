package com.github.openexw.convto.actions

import com.github.openexw.convto.ui.ConvToDialogx
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent


class ConvToStructAction : AnAction() {
    override fun actionPerformed(evnet: AnActionEvent) {
//        Messages.showDialog(project, "Hello World!", "First Plugin")
//        Messages.showInfoMessage("Hello World!", "First Plugin")
//        Messages.showInfoMessage(evnet.project, "Struct Fields From SQL", "Struct Fields From SQL")
        val d = ConvToDialogx()
        d.show()
    }
}