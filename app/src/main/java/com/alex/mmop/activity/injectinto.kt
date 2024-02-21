package com.alex.mmop.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.alex.mmop.composable.Injectionview
import com.alex.mmop.composable.Showprogressbar
import com.alex.mmop.ui.theme.selectgametheme
import com.fvbox.lib.FCore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class injectinto() : ComponentActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val getpackage = intent.getStringExtra("package")

        setContent {

            var showdialog by remember{
                mutableStateOf(false)
            }

            selectgametheme{
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                 Injectionview(onclicklaunch = {
                     CoroutineScope(Dispatchers.Main).launch {
                         showdialog = true
                         delay(5000)
                         showdialog = false
                         getpackage?.let {
                             FCore.get().launchApk(it,0)
                         }
                      }
                    })
                    if (showdialog){
                        Showprogressbar(showdialog)
                    }else{
                        Showprogressbar(showdialog)
                    }
                }

            }
        }



    }

}











