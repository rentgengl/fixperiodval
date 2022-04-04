package com.world.jteam.fixperiodval.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.world.jteam.fixperiodval.App
import com.world.jteam.fixperiodval.R
import com.world.jteam.fixperiodval.databinding.ActivityIndicatorBinding
import com.world.jteam.fixperiodval.indicator.IndicatorRecycleViewAdapter
import com.world.jteam.fixperiodval.room.Indicator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class IndicatorActivity : AppCompatActivity() {
    lateinit private var binding: ActivityIndicatorBinding
    private var indicatorList:MutableList<Indicator> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityIndicatorBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.indicatorRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.indicatorRecyclerView.adapter = IndicatorRecycleViewAdapter(indicatorList)

        reloadIndicatorList()
    }

    fun reloadIndicatorList(){
        indicatorList.clear()

        binding.addIndicatorButton.isEnabled = false
        binding.removeIndicatorButton.isEnabled = false

        lifecycleScope.launch(Dispatchers.Main) {
            val indicators: List<Indicator>? = withContext(Dispatchers.IO){
                val indicatorDao = App.instance.db.IndicatorDao()

                val indicators = indicatorDao.getAllIndicator()

                return@withContext indicators
            }

            indicators?.forEach {i -> indicatorList.add(i) }

            binding.indicatorRecyclerView.adapter?.notifyDataSetChanged()

            binding.addIndicatorButton.isEnabled = true
            binding.removeIndicatorButton.isEnabled = true

        }
    }

    fun addIndicatorClick(view: View) {
        lifecycleScope.launch(Dispatchers.Main) {
            val indicator = withContext(Dispatchers.IO) {
                val indicatorDao = App.instance.db.IndicatorDao()

                val indicator = Indicator(0, "", "")
                val id = indicatorDao.insert(indicator)
                indicator.id = id.toInt()

                return@withContext indicator
            }

            indicatorList.add(indicator)

            binding.indicatorRecyclerView.adapter?.notifyItemInserted(indicatorList.size)
        }
    }

    fun removeIndicatorClick(view: View) {
        val focusedViewIndicator = binding.indicatorRecyclerView.layoutManager?.focusedChild

        focusedViewIndicator?.let { focusedView ->
            val indicatorViewHolder =
                binding.indicatorRecyclerView.findContainingViewHolder(focusedView) as? IndicatorRecycleViewAdapter.IndicatorViewHolder

            indicatorViewHolder?.let {viewHolder ->

                indicatorViewHolder.currentIndicator?.let { indicator ->
                    indicatorList.remove(indicator)
                    binding.indicatorRecyclerView.adapter?.notifyItemRemoved(viewHolder.adapterPosition)

                    lifecycleScope.launch(Dispatchers.IO) {
                        val indicatorDao = App.instance.db.IndicatorDao()
                        indicatorDao.delete(indicator)
                    }
                }

            }
        }

    }
}