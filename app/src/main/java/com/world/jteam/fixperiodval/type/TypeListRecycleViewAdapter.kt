package com.world.jteam.fixperiodval.type

import android.text.Editable
import android.text.InputType
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
        private val editableFactory = Editable.Factory.getInstance()

        fun bind(type: Type) {
            binding.typeName.text = editableFactory.newEditable(type!!.name)
            binding.typeName.inputType = InputType.TYPE_NULL
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