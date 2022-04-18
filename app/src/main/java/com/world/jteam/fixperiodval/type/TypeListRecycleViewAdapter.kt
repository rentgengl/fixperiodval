package com.world.jteam.fixperiodval.type

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.world.jteam.fixperiodval.databinding.ActivityTypeListRecyclerviewItemBinding
import com.world.jteam.fixperiodval.room.Type

class TypeListRecycleViewAdapter(private val typeList:MutableList<Type>, private val typeRecyclerView: RecyclerView)
    : RecyclerView.Adapter<TypeListRecycleViewAdapter.TypeViewHolder>(){

    class TypeViewHolder(typeItemBinding: ActivityTypeListRecyclerviewItemBinding)
        : RecyclerView.ViewHolder(typeItemBinding.root) {

        private val binding = typeItemBinding

        fun bind(type: Type) {
            binding.typeName.text = type.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeListRecycleViewAdapter.TypeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ActivityTypeListRecyclerviewItemBinding.inflate(layoutInflater,parent,false)

        return TypeListRecycleViewAdapter.TypeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TypeListRecycleViewAdapter.TypeViewHolder, position: Int) {
        val type = typeList[position]
        holder.bind(type)
    }

    override fun getItemCount(): Int {
        return typeList.count()
    }
}