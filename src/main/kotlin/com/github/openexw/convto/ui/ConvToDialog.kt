package com.github.openexw.convto.ui

import com.intellij.microservices.utils.D
import com.intellij.openapi.editor.impl.EditorImpl
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogPanel
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.components.ActionLink
import com.intellij.ui.components.JBTabbedPane
import com.intellij.ui.dsl.builder.Cell
import com.intellij.ui.dsl.builder.panel
import org.apache.commons.lang3.StringUtils.right
import java.awt.Dimension
import javax.swing.JComponent
import com.intellij.ui.dsl.builder.BottomGap


class ConvToDialog(val project: Project, title: String) :
    DialogWrapper(project, null, true, IdeModalityType.MODELESS, false) {
    //    fun init (){
////        init()
//    }
    override fun createCenterPanel(): JComponent? {
//        val editorImpl = EditorImpl()
//        editorImpl.
        val pane = JBTabbedPane()
        pane.minimumSize = Dimension(400, 300)
        pane.preferredSize = Dimension(800, 600)

        val ctx = panel {
            row {
                label("<html>Description:")
            }
            row {
                link("View source") {
//                if (!openInIdeaProject(fileName)) {
//                    BrowserUtil.browse(BASE_URL + fileName)
                }
            }
        }
        pane.add("asdefsdf", ctx)
        return pane
    }
}

//    @P(title=title)
//    fun init(): DialogPanel {
//        return panel {
//            row("Row1") {
//                textField()
//                label("text 1")
//            }
//        }
//    }


//    private val contentPane: JPanel = JPanel()
//    private val buttonOK: JButton = JButton()
//    private val buttonCancel: JButton = JButton()
//    private val titleFeild: JTextField = JTextField()
//    private val JTextArea = JTextArea()
//
//    fun init() {
//
//        titleFeild.text = title
//
//        setContentPane(contentPane)
//        contentPane.size.setSize(500, 500)
//
////        JTextArea.
//        isModal = true
//        getRootPane().defaultButton = buttonOK
//        buttonOK.addActionListener { onOK() }
//        buttonCancel.addActionListener { onCancel() }
//
//        // call onCancel() when cross is clicked
//        defaultCloseOperation = DO_NOTHING_ON_CLOSE
//        addWindowListener(object : WindowAdapter() {
//            override fun windowClosing(e: WindowEvent) {
//                onCancel()
//            }
//        })
//
//        // call onCancel() on ESCAPE
//        contentPane.registerKeyboardAction(
//            { onCancel() },
//            KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
//            JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT
//        )
//    }
//
//    private fun onOK() {
//        // add your code here
//        dispose()
//    }
//
//    private fun onCancel() {
//        // add your code here if necessary
//        dispose()
//    }
//    @JvmStatic
//    fun main(args: Array<String>) {
//        val dialog = SQLBox()
//        dialog.pack()
//        dialog.isVisible = true
//        System.exit(0)
//    }
//}