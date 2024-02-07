package com.alex.mmop.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.alex.mmop.R
import com.alex.mmop.authapi.gamedata
import com.alex.mmop.ui.theme.selectgametheme
import kotlinx.coroutines.runBlocking

class selectgame : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)





        setContent {


            selectgametheme{
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LazyColumn(modifier = Modifier.fillMaxSize()
                    ){
                     items(mutablelistfgames()){






                      }
                    }
                }
            }
        }
    }
    fun mutablelistfgames () : MutableList<gamedata>{
        val icons = listOf(
            R.drawable.bgmi_icon,
            R.drawable.icon_round,
            R.drawable.icon_foreground
        )
        val packagenames = listOf(
            "com.pubg.imobile",
            "com.tencent.ig",
            "com.pubg.korea"
        )
        val list = mutableListOf<gamedata>()
       return runBlocking {
           for (i in icons.indices) {
               val data = gamedata(packagenames[i], icons[i])
               list.add(data)
           }
           list
       }
    }
}





