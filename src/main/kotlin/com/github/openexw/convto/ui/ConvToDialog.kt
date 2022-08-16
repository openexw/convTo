package com.github.openexw.convto.ui

import com.intellij.openapi.project.Project
import java.awt.event.*
import javax.swing.*

class ConvToDialog(title: String) : JDialog() {
    private val contentPane: JPanel = JPanel()
    private val buttonOK: JButton = JButton()
    private val buttonCancel: JButton = JButton()
    private val titleFeild: JTextField = JTextField()
    private val JTextArea = JTextArea()

    fun init() {

        titleFeild.text = title

        setContentPane(contentPane)
        contentPane.size.setSize(500, 500)

//        JTextArea.
        isModal = true
        getRootPane().defaultButton = buttonOK
        buttonOK.addActionListener { onOK() }
        buttonCancel.addActionListener { onCancel() }

        // call onCancel() when cross is clicked
        defaultCloseOperation = DO_NOTHING_ON_CLOSE
        addWindowListener(object : WindowAdapter() {
            override fun windowClosing(e: WindowEvent) {
                onCancel()
            }
        })

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(
            { onCancel() },
            KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
            JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT
        )
    }

    private fun onOK() {
        // add your code here
        dispose()
    }

    private fun onCancel() {
        // add your code here if necessary
        dispose()
    }
//    @JvmStatic
//    fun main(args: Array<String>) {
//        val dialog = SQLBox()
//        dialog.pack()
//        dialog.isVisible = true
//        System.exit(0)
//    }
}