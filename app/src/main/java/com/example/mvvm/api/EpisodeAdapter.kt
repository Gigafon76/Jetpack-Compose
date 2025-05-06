package com.example.mvvm.api

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.databinding.SampleEpisodesBinding


var episodesCount = 1
class EpisodeAdapter : RecyclerView.Adapter<MyViewHolders>() {
    private var data: List<Episodes> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolders {
        return MyViewHolders(
            SampleEpisodesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    fun setData(data: List<Episodes>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = episodesCount

    override fun onBindViewHolder(holder: MyViewHolders, position: Int) {
        val item = data.getOrNull(position)
        with(holder.binding) {
            name.text = item?.name
            episode.text = item?.episode
            date.text = item?.air_date
        }
    }
}

class MyViewHolders(val binding: SampleEpisodesBinding) : RecyclerView.ViewHolder(binding.root)