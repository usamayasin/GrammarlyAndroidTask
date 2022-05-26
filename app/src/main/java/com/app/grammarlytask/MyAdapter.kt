package com.app.grammarlytask

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.grammarlytask.databinding.RowItemBinding

class MyAdapter(var list:ArrayList<MainActivity.Timer>, val listener:MyClick) :RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    interface MyClick{
        fun click(position: Int)
    }

    fun updateList( newList:ArrayList<MainActivity.Timer>){
        list = newList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RowItemBinding.inflate(
            LayoutInflater.from(parent.context)
        )

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return list.size
    }


    inner class MyViewHolder(
        private val itemViewDataBinding: RowItemBinding,
    ) :
        RecyclerView.ViewHolder(itemViewDataBinding.root) {

        fun bind(position: Int) {

            itemViewDataBinding.tvCounter.text = list[position].counter.toString()

            itemViewDataBinding.tvCounter.setOnClickListener { listener.click(position) }
        }

    }

}