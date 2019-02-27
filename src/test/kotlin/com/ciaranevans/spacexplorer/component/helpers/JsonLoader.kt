package com.ciaranevans.spacexplorer.component.helpers

class JsonLoader {
    fun readJsonFileAsString(fileName: String): String {
        return JsonLoader::class.java.getResource("/component/data/$fileName").readText(Charsets.UTF_8)
    }
}