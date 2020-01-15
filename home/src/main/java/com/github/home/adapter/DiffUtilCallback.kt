package com.github.home.adapter

import androidx.recyclerview.widget.DiffUtil
import com.github.lol4fun.core.model.Match

class DiffUtilCallback : DiffUtil.ItemCallback<Match>() {
    override fun areItemsTheSame(oldItem: Match, newItem: Match): Boolean {
        return oldItem.gameId == newItem.gameId
    }

    override fun areContentsTheSame(oldItem: Match, newItem: Match): Boolean {
        return oldItem.gameDuration == newItem.gameDuration
                && oldItem.gameMode == newItem.gameMode
                && oldItem.gameType == newItem.gameType
                && oldItem.participant == newItem.participant
                && oldItem.participantStatsList == newItem.participantStatsList
                && oldItem.teams == newItem.teams
    }

}