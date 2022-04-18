package com.world.jteam.fixperiodval.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.world.jteam.fixperiodval.databinding.ActivityTypeListBinding
import com.world.jteam.fixperiodval.databinding.ActivityTypeObjectBinding

class TypeObjectActivity : AppCompatActivity() {
    lateinit private var binding: ActivityTypeObjectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTypeObjectBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }
}