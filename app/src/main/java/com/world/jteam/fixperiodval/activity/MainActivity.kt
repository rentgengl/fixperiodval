package com.world.jteam.fixperiodval.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.world.jteam.fixperiodval.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
/*
    fun saveButtonClick(view: View) {
        lifecycleScope.launch(Dispatchers.IO){
            val userDao = App.instance.db.userDao()

            val firstName = findViewById<TextView>(R.id.editFirstName).text.toString()
            val lastName = findViewById<TextView>(R.id.editLastName).text.toString()

            val user = User(0, firstName, lastName)

            userDao.insert(user)
        }
    }

    fun getLastNameButtonClick(view: View) {
        lifecycleScope.launch(Dispatchers.Main){

            val user: User? = withContext(Dispatchers.IO){
                val userDao = App.instance.db.userDao()

                val firstName = findViewById<TextView>(R.id.editFirstName).text.toString()

                val user = userDao.getUserLastName(firstName)

                return@withContext user
            }

            user.let {
                findViewById<TextView>(R.id.idUser).text = user?.id.toString()
                findViewById<TextView>(R.id.editLastName).text = user?.lastName
            }

        }


    }

   */
}