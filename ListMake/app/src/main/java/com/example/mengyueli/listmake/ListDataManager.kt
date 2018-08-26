package com.example.mengyueli.listmake

import android.content.Context
import android.preference.PreferenceManager

class ListDataManager(val context: Context) {
    fun saveList(list: TaskList) {
// 1
        val sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(context).edit()
// 2
        sharedPreferences.putStringSet(list.name, list.tasks.toHashSet())
// 3
        sharedPreferences.apply()
    }

    fun readLists(): ArrayList<TaskList> {
        // 1
        val sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(context)
// 2
        val sharedPreferenceContents = sharedPreferences.all
        // 3
        val taskLists = ArrayList<TaskList>()
// 4
        for (taskList in sharedPreferenceContents) {
            val itemsHashSet = taskList.value as HashSet<String>
            val list = TaskList(taskList.key, ArrayList(itemsHashSet))
            // 5
            taskLists.add(list)
        }
        return taskLists
    }
}