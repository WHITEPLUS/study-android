package com.wh_plus.realmdatabindingsample

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.wh_plus.realmdatabindingsample.databinding.ActivityMainBinding
import com.wh_plus.realmdatabindingsample.model.Todo
import com.wh_plus.realmdatabindingsample.model.TodoForm
import com.wh_plus.realmdatabindingsample.model.User
import io.realm.Realm
import io.realm.RealmConfiguration

class MainActivity() : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        binding.user = User("Test", "User")
        binding.todoForm = TodoForm()

        val realmConfig = RealmConfiguration.Builder(baseContext).build()
        val realm = Realm.getInstance(realmConfig)
        Log.d("realm-path", "path: " + realm.path)

        this.render(realm)

        val button = findViewById(R.id.button) as Button
        button.setOnClickListener{ onClickAdd(realm, binding) }

    }

    val onClickAdd =  {
        realm: Realm ,binding: ActivityMainBinding ->

        this.addTodo(realm, binding.todoForm.todoText)

        this.render(realm)

        Toast.makeText(baseContext, "[" + binding.todoForm.todoText + "]追加", Toast.LENGTH_LONG).show()
        binding.todoForm = TodoForm()
    }

    fun addTodo(realm: Realm, todo: String) {
        realm.executeTransaction {
            val todoModel = realm.createObject(Todo::class.java)
            todoModel.name = todo
            todoModel.status = 0
            todoModel.iconId = R.drawable.pen
            realm.copyToRealm(todoModel)
        }
    }

    fun render(realm: Realm) {

        val mRecyclerView = findViewById(R.id.rView) as RecyclerView;
        mRecyclerView.setHasFixedSize(true)

        mRecyclerView.layoutManager = LinearLayoutManager(this)

        val props = realm.where(Todo::class.java).findAll()

        val mAdapter = TodoAdapter(props)
        mRecyclerView.adapter = mAdapter

    }

}
