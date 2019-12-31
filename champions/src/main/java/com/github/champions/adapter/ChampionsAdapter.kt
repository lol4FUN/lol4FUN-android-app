package com.github.champions.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.champions.R
import com.github.lol4fun.extensions.toChampionGenericObject
import kotlinx.android.synthetic.main.item_champion.view.*

class ChampionsAdapter(
    private val context: Context?,
    private val championsList: ArrayList<Any>
) : RecyclerView.Adapter<ChampionsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_champion, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return championsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(context, championsList[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(context: Context?, anyChampion: Any?) = with(itemView) {
            val champion = anyChampion.toChampionGenericObject()
            itemView.rvChampionItemName.text = champion.name
        }
    }
}