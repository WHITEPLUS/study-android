package com.example.nakamigawa.myapplication

import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.*
import com.example.nakamigawa.myapplication.model.Todo
import io.realm.Realm
import io.realm.RealmConfiguration
import android.databinding.DataBindingUtil
import com.example.nakamigawa.myapplication.databinding.ActivityMainBinding
import com.example.nakamigawa.myapplication.model.User


class MainActivity() : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val tv = findViewById(R.id.text1) as TextView
//        tv.text = "こんにちは、せかい"

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val user = User("Test", "User")
        binding.setUser(user)

        val realmConfig = RealmConfiguration.Builder(baseContext)
                .name("hoge.realm")
                .build()
        val realm = Realm.getInstance(realmConfig)
        Log.d("realm-path", "path: " + realm.path)

        this.render(realm)

        val button = findViewById(R.id.button) as Button
        button.setOnClickListener{ onClickAdd(realm) }

    }

    val onClickAdd =  {
        realm: Realm ->
        val et = findViewById(R.id.editText) as EditText

        this.addTodo(realm, et.text.toString())
        this.render(realm)

        Toast.makeText(baseContext, "[" + et.text.toString() + "]追加", Toast.LENGTH_LONG).show()
    }

    fun addTodo(realm: Realm, todo: String) {
        realm.executeTransaction {
            val todoModel = realm.createObject(Todo::class.java)
            todoModel.name = todo
            todoModel.status = 0
            realm.copyToRealm(todoModel)
        }
    }

    fun render(realm: Realm) {

        val mRecyclerView = findViewById(R.id.rView) as RecyclerView;
        mRecyclerView.setHasFixedSize(true)

        mRecyclerView.layoutManager = LinearLayoutManager(this)

        val props = realm.where(Todo::class.java).findAll()

        val mAdapter = MyAdapter(props)
        mRecyclerView.adapter = mAdapter

    }

}
