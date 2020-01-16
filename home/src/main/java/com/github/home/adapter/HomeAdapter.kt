package com.github.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.home.R
import com.github.lol4fun.core.model.Match
import com.github.lol4fun.util.ConstantsUtil.Api.BASE_URL_SQUARE_ASSET
import com.github.lol4fun.util.GlideApp
import kotlinx.android.synthetic.main.adapter_home.view.*

class HomeAdapter(
    private val context: Context?
) : PagedListAdapter<Match, HomeAdapter.HomeViewHolder>(DiffUtilCallback()) {

    var itemClicked: ((Match) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_home, parent, false)
        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        getItem(position)?.let { match ->
            holder.bind(context, match)
            holder.itemView.setOnClickListener {
                itemClicked?.let { it(match) }
            }
        }
    }

    class HomeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(context: Context?, data: Match) {
            itemView.apply {
                data.champion?.let { champion ->
                    context?.let {
                        ivPosition.background = ContextCompat.getDrawable(it, data.lane)
                        GlideApp
                            .with(it)
                            .load("${BASE_URL_SQUARE_ASSET}${champion.image?.full}")
                            .into(ivChampion)
                    }
                }

                val dataPlayer = data.participant.find { it.player.summonerName == "" }
                data.participantStatsList.find { it.id == dataPlayer?.id }?.let { stats ->
                    val kda = "${stats.kills}/${stats.deaths}/${stats.assists}"
                    tvKDA.text = kda
                }
            }
        }
    }
}