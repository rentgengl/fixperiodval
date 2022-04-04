package com.world.jteam.fixperiodval.indicator

import android.content.Context
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.world.jteam.fixperiodval.App
import com.world.jteam.fixperiodval.databinding.ActivityIndicatorRecyclerviewItemBinding
import com.world.jteam.fixperiodval.room.Indicator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class IndicatorRecycleViewAdapter(private val indicatorList:MutableList<Indicator>)
        : RecyclerView.Adapter<IndicatorRecycleViewAdapter.IndicatorViewHolder>(){

    class IndicatorViewHolder(indicatorItemBinding: ActivityIndicatorRecyclerviewItemBinding)
        : RecyclerView.ViewHolder(indicatorItemBinding.root) {

        private val binding = indicatorItemBinding
        var currentIndicator: Indicator? = null
            get
            private set
        private var focusedViewChange = false

        private val viewFocusChangedLambda = { view: View, focused: Boolean ->

            if (!focused && focusedViewChange) {

                binding.root.findViewTreeLifecycleOwner()!!.lifecycleScope.launch(Dispatchers.IO) {
                    val indicatorDao = App.instance.db.IndicatorDao()

                    currentIndicator!!.name = binding.indicatorName.text.toString()
                    currentIndicator!!.unit = binding.indicatorUnit.text.toString()

                    indicatorDao.update(currentIndicator!!)
                }
            }

            focusedViewChange = false
        }

        private val viewAfterTextChangedLambda = { text: Editable? ->
            focusedViewChange = true
        }

        init {

            binding.indicatorName.setOnFocusChangeListener(viewFocusChangedLambda)
            binding.indicatorName.doAfterTextChanged (viewAfterTextChangedLambda)

            binding.indicatorUnit.setOnFocusChangeListener(viewFocusChangedLambda)
            binding.indicatorUnit.doAfterTextChanged (viewAfterTextChangedLambda)
        }

        private val editableFactory = Editable.Factory.getInstance()

        fun bind(indicator: Indicator) {
            currentIndicator = indicator
            binding.indicatorName.text = editableFactory.newEditable(currentIndicator!!.name)
            binding.indicatorUnit.text =  editableFactory.newEditable(currentIndicator!!.unit)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IndicatorViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ActivityIndicatorRecyclerviewItemBinding.inflate(layoutInflater,parent,false)

        return IndicatorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IndicatorViewHolder, position: Int) {
        val indicator = indicatorList[position]
        holder.bind(indicator)
    }

    override fun getItemCount(): Int {
        return indicatorList.count()
    }
}