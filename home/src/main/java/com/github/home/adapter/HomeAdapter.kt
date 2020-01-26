package com.github.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.home.R
import com.github.lol4fun.core.model.Match
import com.github.lol4fun.extensions.setImageDownload
import com.github.lol4fun.util.ConstantsUtil.Api.BASE_URL_ITEM_ASSET
import com.github.lol4fun.util.ConstantsUtil.Api.BASE_URL_SQUARE_ASSET
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
                val dataPlayer = data.participant.find { it.player.user }
                data.participants.find { it.participantId == dataPlayer?.id }?.let { user ->
                    val stats = user.stats
                    val kda = "${stats.kills}/${stats.deaths}/${stats.assists}"

                    tvKDA.text = kda

                    var path = "${BASE_URL_SQUARE_ASSET}${user.champion?.image?.full}"
                    ivChampion.setImageDownload(context, path)
                    stats.item0?.let {
                        path = "${BASE_URL_ITEM_ASSET}${it.image.full}"
                        ivItem01.setImageDownload(context, path)
                    }
                    stats.item1?.let {
                        path = "${BASE_URL_ITEM_ASSET}${it.image.full}"
                        ivItem02.setImageDownload(context, path)
                    }
                    stats.item2?.let {
                        path = "${BASE_URL_ITEM_ASSET}${it.image.full}"
                        ivItem03.setImageDownload(context, path)
                    }
                    stats.item3?.let {
                        path = "${BASE_URL_ITEM_ASSET}${it.image.full}"
                        ivItem04.setImageDownload(context, path)
                    }
                    stats.item4?.let {
                        path = "${BASE_URL_ITEM_ASSET}${it.image.full}"
                        ivItem05.setImageDownload(context, path)
                    }
                    stats.item5?.let {
                        path = "${BASE_URL_ITEM_ASSET}${it.image.full}"
                        ivItem06.setImageDownload(context, path)
                    }
                }
            }
        }
    }
}