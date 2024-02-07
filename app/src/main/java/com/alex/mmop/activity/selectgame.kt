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
import com.alex.mmop.composable.Selectmode
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
                     items(mutablelistfgames()) {

                        Selectmode(
                            version = it.version,
                            packagename = it.packagename,
                            icon = it.icon,
                            apkname = it.apkname,
                            oninstall = {


                            }, onuninstall =
                            {


                            }
                        )
                      }
                    }
                }
            }
        }
    }

    // this funtuion just set data to each veriable
    fun mutablelistfgames () : MutableList<gamedata>{
        val icons = listOf(
            R.drawable.bgmi_icon,
            R.drawable.globalpubg,
            R.drawable.icon_foreground
        )
        val versions = listOf(
            "3.0.0",
            "3.1.1",
            "3.1.0"
        )
        val apknames = listOf(
            "BGMI INDIA",
            "GLOBAL PUBG",
            "PUBG KOREA"
        )
        val packagenames = listOf(
            "com.pubg.imobile",
            "com.tencent.ig",
            "com.pubg.korea"
        )
        val list = mutableListOf<gamedata>()
       return runBlocking {
           for (i in icons.indices) {
               val data = gamedata(
                   packagename = packagenames[i],
                   icon = icons[i],
                   apkname = apknames[i],
                   version = versions[i]
                   )
               list.add(data)
           }
           list
       }
    }
}





