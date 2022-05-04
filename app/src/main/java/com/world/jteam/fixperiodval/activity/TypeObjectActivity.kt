package com.world.jteam.fixperiodval.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.CheckBox
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.world.jteam.fixperiodval.App
import com.world.jteam.fixperiodval.databinding.ActivityTypeListBinding
import com.world.jteam.fixperiodval.databinding.ActivityTypeObjectBinding
import com.world.jteam.fixperiodval.room.*
import com.world.jteam.fixperiodval.type.TypeIndicatorsRecycleViewAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TypeObjectActivity : AppCompatActivity() {
    lateinit private var binding: ActivityTypeObjectBinding
    private var indicatorList:MutableList<Indicator> = mutableListOf()
    private var typeObject: TypeObject? = null
    private var typeId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //binding
        binding = ActivityTypeObjectBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.typeIndicatorsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.typeIndicatorsRecyclerView.adapter = TypeIndicatorsRecycleViewAdapter(
            indicatorList,
            binding.typeIndicatorsRecyclerView)

        binding.typeObjectName.setOnFocusChangeListener({
                view: View, focused: Boolean ->
            if (!focused){
                typeObject!!.type.name = binding.typeObjectName.text.toString()
            }
        })

        //data
        typeId = intent.getIntExtra("typeId",0)
        reloadData()
    }

    fun reloadData() {
        indicatorList.clear()
        typeObject = null

        lifecycleScope.launch(Dispatchers.Main) {
            //indicators
            val indicators: List<Indicator>? = withContext(Dispatchers.IO){
                val indicatorDao = App.instance.db.IndicatorDao()

                val indicators = indicatorDao.getAllIndicator()

                return@withContext indicators
            }

            indicators?.forEach {i -> indicatorList.add(i) }

            binding.typeIndicatorsRecyclerView.adapter?.notifyDataSetChanged()

            //type object
            if (typeId != 0) {
                typeObject = withContext(Dispatchers.IO) {
                    val typeDao = App.instance.db.TypeDao()

                    val typeObject = typeDao.typeObjectById(typeId)

                    return@withContext typeObject
                }
            } else {
                val type = Type(0,"")
                val typeIndicatorList :MutableList<TypeIndicator> = mutableListOf()
                typeObject = TypeObject(type,typeIndicatorList)
            }

            binding.typeObjectName.text = Editable.Factory.getInstance().newEditable(typeObject!!.type.name) //TODO заменить на toEditable
        }
    }

    fun checkIndicatorClick(view: View) {
        val indicatorViewHolder =
            binding.typeIndicatorsRecyclerView.findContainingViewHolder(view) as? TypeIndicatorsRecycleViewAdapter.TypeIndicatorsViewHolder

        //if checked then add to object table else remove
        indicatorViewHolder?.let { viewHolder ->
            if ((view as CheckBox).isChecked) {
                val typeIndicator = TypeIndicator(0,typeObject!!.type.id,viewHolder.currentIndicator!!.id)
                typeObject!!.typeIndicators.add(typeIndicator)
            } else {
                for (i in typeObject!!.typeIndicators) {
                    if (i.indicatorId == viewHolder.currentIndicator!!.id){
                        typeObject!!.typeIndicators.remove(i)
                        break
                    }
                }
            }
        }
    }

    fun okClick(view: View) {
        //TODO
    }
}