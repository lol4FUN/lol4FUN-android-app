package com.github.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.home.R
import com.github.lol4fun.core.model.Match

class HomeAdapter(
    private val context: Context?
) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    private val historyList = mutableListOf<Match>()
    private lateinit var clickListener: ItemClickListener


    fun setOnClickListener(cl: ItemClickListener) {
        clickListener = cl
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_home, parent, false)
        return HomeViewHolder(view)
    }

    override fun getItemCount() = historyList.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(context, historyList[position])
        holder.itemView.setOnClickListener {
            clickListener.onItemClicked(position, it)
        }
    }

    fun addData(data: List<Match>) {
        historyList.addAll(data)
        notifyDataSetChanged()
    }

    class HomeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(context: Context?, data: Match) {
            //TODO()
        }
    }
}