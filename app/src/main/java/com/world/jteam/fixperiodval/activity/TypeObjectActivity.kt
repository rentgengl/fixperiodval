package com.world.jteam.fixperiodval.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.world.jteam.fixperiodval.App
import com.world.jteam.fixperiodval.databinding.ActivityTypeListBinding
import com.world.jteam.fixperiodval.databinding.ActivityTypeObjectBinding
import com.world.jteam.fixperiodval.room.Indicator
import com.world.jteam.fixperiodval.type.TypeIndicatorsRecycleViewAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TypeObjectActivity : AppCompatActivity() {
    lateinit private var binding: ActivityTypeObjectBinding
    private var indicatorList:MutableList<Indicator> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTypeObjectBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.typeIndicatorsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.typeIndicatorsRecyclerView.adapter = TypeIndicatorsRecycleViewAdapter(
            indicatorList,
            binding.typeIndicatorsRecyclerView)

        reloadIndicatorList()
    }

    fun reloadIndicatorList() {
        indicatorList.clear()

        lifecycleScope.launch(Dispatchers.Main) {
            val indicators: List<Indicator>? = withContext(Dispatchers.IO){
                val indicatorDao = App.instance.db.IndicatorDao()

                val indicators = indicatorDao.getAllIndicator()

                return@withContext indicators
            }

            indicators?.forEach {i -> indicatorList.add(i) }

            binding.typeIndicatorsRecyclerView.adapter?.notifyDataSetChanged()

        }
    }

    fun checkIndicatorClick(view: View) {
        //TODO
    }
}