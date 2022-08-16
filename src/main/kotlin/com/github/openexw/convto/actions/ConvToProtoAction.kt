package com.github.openexw.convto.actions

import com.github.openexw.convto.ui.ConvToDialog
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.Messages;


class ConvToProtoAction : AnAction() {
    override fun actionPerformed(evnet: AnActionEvent) {
//        val project = evnet.project
//        Messages.showDialog(project, "Hello World!", "First Plugin")
//        Messages.showInfoMessage("Hello World!", "First Plugin")
        Messages.showInfoMessage(evnet.project, "Proto Fields From SQL", "Proto Fields From SQL")
        val d = ConvToDialog("Struct Fields From SQL")
        d.init()
//        var box = SQLBox.main()
//        Messages.showDialog(evnet.project, null, "Generate Go Struct From SQL", noteRowInTheDialog())
//        box.
    }
}