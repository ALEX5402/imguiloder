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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class injectinto() : ComponentActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val getpackage = intent.getStringExtra("package")
        Log.w("haha",getpackage.toString())
        Log.w("haha",getpackage.toString())

        CoroutineScope(Dispatchers.Default).launch {
            val getfiles = filesDir.listFiles()
            getfiles?.let {
                for (files in getfiles) {
                    val getfile = files.name.endsWith(
                        ".ttf",
                        true
                    )
                    if (getfile){
                        files.delete()
                        Log.w("FILE DELETED", files.toString())
                    }
                }
            }
        }

        setContent {
            selectgametheme{
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                 Injectionview(onclicklaunch = {
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














