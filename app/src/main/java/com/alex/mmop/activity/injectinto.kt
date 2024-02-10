package com.alex.mmop.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.alex.mmop.composable.Injectionview
import com.alex.mmop.ui.theme.selectgametheme
import com.fvbox.lib.FCore

class injectinto() : ComponentActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val getpackage = intent.getStringExtra("package")
        Log.w("haha",getpackage.toString())
        Log.w("haha",getpackage.toString())
        setContent {
            selectgametheme{
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                 Injectionview(onclicklaunch = {
                    // FCore.get().init(this@injectinto,true)
                      getpackage?.let {
                         FCore.get().launchApk(it,0)
                     }
                    })
                }

            }
        }
    }
}


/*
@Preview(device = "spec:parent=Nexus S")
@Composable
fun Priview(){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

    }
}

*/














