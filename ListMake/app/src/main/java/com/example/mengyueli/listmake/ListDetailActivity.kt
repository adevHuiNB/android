package com.example.mengyueli.listmake

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.InputType
import android.widget.EditText

class ListDetailActivity : AppCompatActivity() {

    lateinit var list: TaskList
    lateinit var listItemsRecyclerView: RecyclerView


    lateinit var addTaskButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_detail)

        list = intent.getParcelableExtra(ListActivity.INTENT_LIST_KEY)
// 2
        title = list.name


        listItemsRecyclerView =
                findViewById<RecyclerView>(R.id.list_items_recyclerview)
// 2
        listItemsRecyclerView.adapter = ListItemsRecyclerViewAdapter(list)
// 3
        listItemsRecyclerView.layoutManager = LinearLayoutManager(this)




        addTaskButton = findViewById<FloatingActionButton>(R.id.add_task_button)
        addTaskButton.setOnClickListener {
            showCreateTaskDialog()
        }




        // 1



    }

    override fun onBackPressed() {
        val bundle = Bundle()
        bundle.putParcelable(ListActivity.INTENT_LIST_KEY, list)
        val intent = Intent()
        intent.putExtras(bundle)
        setResult(Activity.RESULT_OK, intent)
        super.onBackPressed()

    }

    private fun showCreateTaskDialog() {
        //1
        val taskEditText = EditText(this)
        taskEditText.inputType = InputType.TYPE_CLASS_TEXT
//2
        AlertDialog.Builder(this)
                .setTitle(R.string.task_to_add)
                .setView(taskEditText)
                .setPositiveButton(R.string.add_task, { dialog, _ ->
                    // 3
                    val task = taskEditText.text.toString()
                    list.tasks.add(task)
                    // 4
                    val recyclerAdapter = listItemsRecyclerView.adapter as
                            ListItemsRecyclerViewAdapter
                    recyclerAdapter.notifyItemInserted(list.tasks.size)
                    //5
                    dialog.dismiss()
                })
//6
                .create()
                .show() }





}
