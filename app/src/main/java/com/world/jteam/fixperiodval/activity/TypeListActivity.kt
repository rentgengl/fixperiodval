package com.world.jteam.fixperiodval.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.world.jteam.fixperiodval.App
import com.world.jteam.fixperiodval.databinding.ActivityTypeListBinding
import com.world.jteam.fixperiodval.room.Type
import com.world.jteam.fixperiodval.type.TypeListRecycleViewAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TypeListActivity : AppCompatActivity() {
    lateinit private var binding: ActivityTypeListBinding
    private var typeList:MutableList<Type> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTypeListBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.typeRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.typeRecyclerView.adapter = TypeListRecycleViewAdapter(typeList,binding.typeRecyclerView)

        reloadIndicatorList()
    }

    fun reloadIndicatorList(){
        typeList.clear()

        binding.addTypeButton.isEnabled = false
        binding.removeTypeButton.isEnabled = false

        lifecycleScope.launch(Dispatchers.Main) {
            val types: List<Type>? = withContext(Dispatchers.IO){
                val typeDao = App.instance.db.TypeDao()

                val types = typeDao.getAllType()

                return@withContext types
            }

            types?.forEach {i -> typeList.add(i) }

            if (typeList.size == 0){
                val emptyType = Type(0, "")
                typeList.add(emptyType)
            }

            binding.typeRecyclerView.adapter?.notifyDataSetChanged()

            binding.addTypeButton.isEnabled = true
            binding.removeTypeButton.isEnabled = true

        }
    }

    fun addTypeClick(view: View) {

    }

    fun removeTypeClick(view: View) {

    }

}