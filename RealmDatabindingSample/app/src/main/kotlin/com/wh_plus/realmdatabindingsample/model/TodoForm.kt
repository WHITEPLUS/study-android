package com.wh_plus.realmdatabindingsample.model

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.wh_plus.realmdatabindingsample.BR

/**
 * Created by nakamigawa on 2017/04/24.
 */

//class TodoForm(var todoText: String = "")

class TodoForm(): BaseObservable() {

    @get:Bindable
    var todoText: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.todoForm)
        }
}