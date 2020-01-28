package com.github.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.home.R
import com.github.lol4fun.core.model.Item
import com.github.lol4fun.core.model.Match
import com.github.lol4fun.core.model.ParticipantStats
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
                    val path = "${BASE_URL_SQUARE_ASSET}${user.champion?.image?.full}"
                    ivChampion.setImageDownload(context, path)
                    setImage(context, stats.item0, ivItem01)
                    setImage(context, stats.item1, ivItem02)
                    setImage(context, stats.item2, ivItem03)
                    setImage(context, stats.item3, ivItem04)
                    setImage(context, stats.item4, ivItem05)
                    setImage(context, stats.item5, ivItem06)
                }
            }
        }

        private fun setImage(context: Context?, item: Item?, view: ImageView) {
            item?.let {
                val path = "${BASE_URL_ITEM_ASSET}${it.image.full}"
                view.setImageDownload(context, path)
            } ?: run { view.setImageResource(R.drawable.ic_image_default) }
        }
    }
}