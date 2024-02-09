package com.alex.mmop.activity

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.alex.mmop.api.any
import com.alex.mmop.api.downloderapi
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
        val getlink = intent.getStringExtra("libs")
        val getpackage = intent.getStringExtra("packagename")
        setContent {
            val showprogressbar = remember {
                mutableStateOf(false)
            }
            selectgametheme{
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (showprogressbar.value){
                        Showprogressbar(progressbarshow = showprogressbar.value, alertindo = "Downloading")
                    }else{
                        Showprogressbar(progressbarshow = showprogressbar.value, alertindo = "Downloading")
                    }

                    Injectionview(onclicklaunch = {
                        showprogressbar.value = true
                        launchgame(
                            packagename = getpackage!!,
                            context = this,
                            libs = getlink!!,
                            zippassword = any.zippass,
                            onsucess = {
                                CoroutineScope(Dispatchers.Main).launch {
                                    showprogressbar.value = false
                                    delay(1000)
                                    FCore.get().launchApk(getpackage,0)
                                }
                            },
                            onfailed = {reason->
                                CoroutineScope(Dispatchers.Main).launch {
                                    showprogressbar.value = false
                                    Toast.makeText(this@injectinto,"Something Went Wrong $reason",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }

                        )

                    })
                }

            }
        }
    }
    fun launchgame(packagename: String ,
                   context: Context,
                   libs:String,
                   zippassword:String,
                   onsucess:()->Unit,
                   onfailed:(reason:String)->Unit
    ){
        val check = FCore.get().isInstalled(packagename,0)
        if (check){
            downloderapi.downloadlib(
                ondownloadsucess = {
                    onsucess()
                },
                ondownloadfailed = {
                    onfailed(it)
                },
                zippassword = zippassword,
                librl = libs,
                context = context)
        }else{
            val reasult = FCore.get().installPackageAsUser(packagename,0)
            Toast.makeText(context,reasult.toString(),Toast.LENGTH_SHORT).show()
            downloderapi.downloadlib(
                ondownloadsucess = {
                    onsucess()
                },
                ondownloadfailed = {
                    onfailed(it)
                },
                zippassword = zippassword,
                librl = libs,
                context = context)
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














