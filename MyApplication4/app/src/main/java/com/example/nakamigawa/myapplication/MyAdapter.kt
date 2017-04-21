package com.example.nakamigawa.myapplication

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import com.example.nakamigawa.myapplication.model.Todo
import io.realm.RealmResults


class MyAdapter
(private val mDataset: RealmResults<Todo>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {


    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var iv = v.findViewById(R.id.todoIconView) as ImageView
        var tv = v.findViewById(R.id.text_view) as TextView
        var sw = v.findViewById(R.id.switch1) as Switch
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MyAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.my_text_view, parent, false)

        val vh = ViewHolder(v)
        return vh
    }



    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tv.text = (mDataset[position].name + if (mDataset[position].status == 1) " 完了" else " 未")

        holder.sw.setOnCheckedChangeListener { buttonView, isChecked ->
            holder.tv.text = (mDataset[position].name + if (isChecked) "fix" else "")
            holder.iv.setImageResource(if (isChecked) R.drawable.bat else R.drawable.hasa)
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return mDataset.size
    }
}
