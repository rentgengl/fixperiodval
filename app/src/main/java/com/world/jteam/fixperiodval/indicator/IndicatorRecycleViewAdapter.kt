package com.world.jteam.fixperiodval.indicator

import android.content.Context
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.world.jteam.fixperiodval.databinding.ActivityIndicatorRecyclerviewItemBinding
import com.world.jteam.fixperiodval.room.Indicator

class IndicatorRecycleViewAdapter(private val context: Context, private val indicatorList:MutableList<Indicator>)
        : RecyclerView.Adapter<IndicatorRecycleViewAdapter.IndicatorViewHolder>(){

    class IndicatorViewHolder(indicatorItemBinding: ActivityIndicatorRecyclerviewItemBinding)
        : RecyclerView.ViewHolder(indicatorItemBinding.root) {

        private val binding = indicatorItemBinding
        private val editableFactory = Editable.Factory.getInstance()

        fun bind(indicator: Indicator) {
            binding.indicatorName.text = editableFactory.newEditable(indicator.name)
            binding.indicatorUnit.text =  editableFactory.newEditable(indicator.unit)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IndicatorViewHolder {
        val binding = ActivityIndicatorRecyclerviewItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return IndicatorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IndicatorViewHolder, position: Int) {
        val indicator = indicatorList[position]
        holder.bind(indicator)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}