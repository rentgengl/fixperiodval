package com.world.jteam.fixperiodval.type

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.world.jteam.fixperiodval.databinding.ActivityTypeIndicatorsRecyclerviewItemBinding
import com.world.jteam.fixperiodval.databinding.ActivityTypeListRecyclerviewItemBinding
import com.world.jteam.fixperiodval.room.Indicator
import com.world.jteam.fixperiodval.room.Type

class TypeIndicatorsRecycleViewAdapter(private val indicatorList:MutableList<Indicator>, private val typeIndicatorsRecyclerView: RecyclerView)
    : RecyclerView.Adapter<TypeIndicatorsRecycleViewAdapter.TypeIndicatorsViewHolder>(){

    class TypeIndicatorsViewHolder(typeIndicatorsItemBinding: ActivityTypeIndicatorsRecyclerviewItemBinding)
        : RecyclerView.ViewHolder(typeIndicatorsItemBinding.root) {

        private val binding = typeIndicatorsItemBinding

        fun bind(type: Indicator) {
            binding.checkTypeIndicator.text = type.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeIndicatorsRecycleViewAdapter.TypeIndicatorsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ActivityTypeIndicatorsRecyclerviewItemBinding.inflate(layoutInflater,parent,false)

        return TypeIndicatorsRecycleViewAdapter.TypeIndicatorsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TypeIndicatorsRecycleViewAdapter.TypeIndicatorsViewHolder, position: Int) {
        val indicator = indicatorList[position]
        holder.bind(indicator)
    }

    override fun getItemCount(): Int {
        return indicatorList.count()
    }
}