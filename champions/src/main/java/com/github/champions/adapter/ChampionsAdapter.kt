package com.github.champions.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.champions.R
import com.github.lol4fun.extensions.toChampionGenericObject
import com.github.lol4fun.util.ConstantsUtil.Api.BASE_URL_SQUARE_ASSET
import com.github.lol4fun.util.GlideApp
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
            context?.let {
                GlideApp
                    .with(it)
                    .load("$BASE_URL_SQUARE_ASSET${champion.image?.full}")
                    .into(itemView.rvChampionItemAvatar)
            }
            itemView.rvChampionItemName.text = champion.name
        }
    }
}