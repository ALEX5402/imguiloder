package com.alex.mmop.activity

import android.content.Context
import android.os.Bundle
import android.widget.Toast
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
        val getlink = intent.getStringExtra("libs")
        val getpackage = intent.getStringExtra("packagename")
        setContent {
            selectgametheme{
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Injectionview(onclicklaunch = {


                    })
                }

            }
        }

    }
    fun launchgame(packagename: String ,
                   context: Context,
                   libs:String

    ){

        val check = FCore.get().isInstalled(packagename,0)
        if (check){


        }else{
          val reasult = FCore.get().installPackageAsUser(packagename,0)

            Toast.makeText(context,reasult.toString(),Toast.LENGTH_SHORT).show()



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














