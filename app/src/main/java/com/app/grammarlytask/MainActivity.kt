package com.app.grammarlytask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity(), MyAdapter.MyClick {

    private var list = arrayListOf<Timer>()
    private val adapter = MyAdapter(list, this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (x in 0..20) {
            list.add(Timer(0, true))
        }

        rv_timers.layoutManager = LinearLayoutManager(this)
        rv_timers.adapter = adapter

        CoroutineScope(Dispatchers.Main).launch {
            while (true) {
                delay(1000)
                for (x in 0..20) {
                    if (list[x].status)
                        list[x].counter = list[x].counter + 1
                }
                adapter.updateList(list)
            }
        }

    }

    override fun click(position: Int) {
        list[position].status = list[position].status.not()
        adapter.notifyDataSetChanged()

    }

    data class Timer(var counter: Int, var status: Boolean)
}

