package com.wh_plus.realmdatabindingsample

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wh_plus.realmdatabindingsample.databinding.TodoViewBinding
import com.wh_plus.realmdatabindingsample.model.Todo
import io.realm.RealmResults


class TodoAdapter
(private val todoDataSet: RealmResults<Todo>) : RecyclerView.Adapter<TodoAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var todoBindingView: TodoViewBinding = DataBindingUtil.bind<TodoViewBinding>(v)
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): TodoAdapter.ViewHolder {

        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.todo_view, parent, false)
        return ViewHolder(view)

    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.todoBindingView.todo = todoDataSet[position]

        holder.todoBindingView.todoIconView.setImageResource(todoDataSet[position].iconId)


        holder.todoBindingView.switch1.setOnCheckedChangeListener { buttonView, isChecked ->
            holder.todoBindingView.textView.text = (todoDataSet[position].name + if (isChecked) "fix" else "")
            holder.todoBindingView.todoIconView.setImageResource(if (isChecked) R.drawable.close else R.drawable.pen)
        }



    }

    override fun getItemCount(): Int {
        return todoDataSet.size
    }
}