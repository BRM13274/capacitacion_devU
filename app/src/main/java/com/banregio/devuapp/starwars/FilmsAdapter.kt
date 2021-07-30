package com.banregio.devuapp.starwars

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.banregio.devuapp.databinding.FilmItemBinding

class FilmsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items: MutableList<SWFilm> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FilmItemBinding.inflate(inflater, parent, false)
        return SWFilmsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? SWFilmsViewHolder)?.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun setItems(newItems: List<SWFilm>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }


    inner class SWFilmsViewHolder(
        private val binding: FilmItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SWFilm) {
            binding.tvFilmTitle.text = item.title
            binding.tvFilmId.text = item.episodeId.toString()
            binding.tvFilmDirector.text = item.directorName
            binding.tvFilmProducer.text = item.producerName
            binding.tvFilmReleaseDate.text = item.releaseDate
        }

    }

}