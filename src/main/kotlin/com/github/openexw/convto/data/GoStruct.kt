package com.github.openexw.convto.data

// 表格
class GoStruct(private val name: String, private val comment: String, private val fields: ArrayList<GoField>) {

    override fun toString(): String {
        var str: String = ""
        if (comment.isNotEmpty()) {
            str = "// $name $comment"
        }
        str += "type $name { \n"

        for (col in fields) {
            var tmp: String = col.columnName + " " + col.fieldType + col.tags

            if (col.comment.isNotEmpty()) {
                tmp += " // " + col.comment
            }
            str += tmp + "\n"
        }
        return str
    }

    fun toProto(): String {
        // TODO
        return ""
    }
}

// 字段
data class GoField(val columnName: String, val fieldType: GoType, val comment: String, val tags: String)