package com.banregio.devuapp.starwars.presentation.starships

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.banregio.devuapp.databinding.StarShipItemBinding
import com.banregio.devuapp.starwars.domain.models.SWStarShip

class StarShipsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items: MutableList<SWStarShip> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = StarShipItemBinding.inflate(inflater, parent, false)
        return StarShipsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? StarShipsViewHolder)?.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun setItems(newItems: List<SWStarShip>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }


    inner class StarShipsViewHolder(
        private val binding: StarShipItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SWStarShip) {
            binding.tvSsName.text = item.name
            binding.tvSsModel.text = item.model
            binding.tvSsCost.text = item.costInCredits
            binding.tvSsSpeed.text = item.maxAtmosphereSpeed
            binding.tvSsCrew.text = item.crewLimits
            binding.tvSsClass.text = item.starShipClass
        }

    }

}