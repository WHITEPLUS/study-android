package com.example.nakamigawa.myapplication.model

/**
 * Created by nakamigawa on 2017/04/15.
 */
import io.realm.RealmObject

/**
 * Created by nakamigawa on 2017/04/14.
 */

open class Todo(
        open var name: String = "",
        open var status: Int = 0 // 0=none, -1=delete
) : RealmObject() {}