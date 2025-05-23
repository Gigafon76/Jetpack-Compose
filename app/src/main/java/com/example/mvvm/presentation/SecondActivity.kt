package com.example.mvvm.presentation

import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.mvvm.databinding.ActivitySecondBinding
import com.example.mvvm.api.EpisodeAdapter
import com.example.mvvm.api.Episodes
import com.example.mvvm.api.URL
import com.example.mvvm.api.episodesCount
import com.example.mvvm.data.NotificationsViewModel
import kotlinx.coroutines.launch

class SecondActivity : AppCompatActivity() {
    private val viewModel: NotificationsViewModel by viewModels()
    private val episodeAdapter = EpisodeAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val lengthURL = URL.length + 13
        val intent = intent

        binding.name.text = intent.getStringExtra("name")
        binding.photo.load(intent.getStringExtra("img"))
        binding.lastLocation.text = intent.getStringExtra("lastLocation")
        binding.live.text = intent.getStringExtra("liveStatus")
        binding.species.text = intent.getStringExtra("species")
        if (binding.live.text == "Alive") binding.statusOfLive.setColorFilter(Color.GREEN)
        if (binding.live.text == "unknown") binding.statusOfLive.setColorFilter(Color.LTGRAY)
        if (binding.live.text == "Dead") binding.statusOfLive.setColorFilter(Color.RED)
        val episodes: List<String>? =
            intent.getStringExtra("episodes")?.replace("]", "")
                ?.replace("[", "")?.split(", ")?.toList()
        val episodesSize = episodes?.size

        binding.recyclerView.adapter = episodeAdapter
        lifecycleScope.launch {
            val data: MutableList<Episodes> = emptyList<Episodes>().toMutableList()
            if (episodesSize != null) {
                for (i in 0..episodesSize - 1) {
                    val getNumber =
                        episodes?.get(i)?.subSequence(lengthURL, episodes[i].length).toString()
                    val number = getNumber.toInt()
                    data.add(i, viewModel.load(number))
                }
            }
            if (episodesSize != null) {
                episodesCount = episodesSize
            }
            episodeAdapter.setData(data)
        }
    }
}