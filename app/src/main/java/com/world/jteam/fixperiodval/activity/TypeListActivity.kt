package com.world.jteam.fixperiodval.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
    val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(),
        { result: ActivityResult ->
            if(result.resultCode == Activity.RESULT_OK) {
                val intent = result.data

                binding.typeRecyclerView.adapter?.notifyItemChanged(intent!!.getIntExtra("id",0))
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTypeListBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.typeRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.typeRecyclerView.adapter = TypeListRecycleViewAdapter(typeList,binding.typeRecyclerView)

        reloadTypeList()
    }

    fun reloadTypeList(){
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
        val intent = Intent(this,TypeObjectActivity::class.java)
        intent.putExtra("id",0)

        resultLauncher.launch(intent)
    }

    fun removeTypeClick(view: View) {

    }

}