package com.alex.mmop.composable


import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alex.mmop.api.Filesapi
import com.alex.mmop.api.any
import com.alex.mmop.api.gmsapi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


@SuppressLint("CommitPrefEdits", "CoroutineCreationDuringComposition")
@Composable
fun Settingsmenu(context: Context, permissonpopup: () -> Unit){
    val settingsmenu =  context.getSharedPreferences(any.settings,MODE_PRIVATE)
    val prefseditor = settingsmenu.edit()
    val gmsbutton : Boolean = settingsmenu.getBoolean(any.gmsmode,false)
    val rootbutton : Boolean = settingsmenu.getBoolean(any.rootmode,false)
    val hiderootd : Boolean = settingsmenu.getBoolean(any.hideroot,false)
    val vpnbtn : Boolean = settingsmenu.getBoolean(any.vpnmode,false)
    val crashmode : Boolean = settingsmenu.getBoolean(any.crashmode,false)
    val splash : Boolean = settingsmenu.getBoolean(any.animation,false)


    val checkgms = remember {
        mutableStateOf(gmsbutton)
    }

    val rootmode = remember {
        mutableStateOf(rootbutton)
    }

    val vpnmode = remember {
        mutableStateOf(vpnbtn)
    }
    val hideroot = remember {
        mutableStateOf(hiderootd)
    }
    val killmode = remember {
        mutableStateOf(crashmode)
    }
    val launchanimation = remember {
        mutableStateOf(splash)
    }
    var alertdialog by remember {
        mutableStateOf(false)
    }

    Column(
        Modifier
            .padding(10.dp)
            .verticalScroll(rememberScrollState(), true)
            .horizontalScroll(rememberScrollState(), true)
    )

    {
        Box(modifier = Modifier
            .toggleable(
                value = checkgms.value,
                role = Role.Switch,
                onValueChange = {
                    checkgms.value = it
                    prefseditor.putBoolean(any.gmsmode, it)
                    prefseditor.apply()
                    if (it){
                        alertdialog = true
                        gmsapi.installgms(
                            onsucess = {
                                Log.i("alex","install sucess")
                                CoroutineScope(Dispatchers.Main).launch {
                                    alertdialog = false
                                }
                            },
                            onfail = {
                                CoroutineScope(Dispatchers.Main).launch {
                                    alertdialog = false
                                    Toast.makeText(context,"Error $it",Toast.LENGTH_LONG).show()
                                }

                            }
                        )
                    }else{
                        alertdialog = true
                        gmsapi.removegms(
                            onsucess = {
                                Log.i("alex","remove sucess")
                                CoroutineScope(Dispatchers.Main).launch {
                                    alertdialog = false
                                }
                            },
                            onfail = {
                                CoroutineScope(Dispatchers.Main).launch {
                                    alertdialog = false
                                    Toast.makeText(context,"Error $it",Toast.LENGTH_LONG).show()
                                }
                            }
                        )

                    }
                }
            )
            .padding(10.dp)
        ) {
            Row(Modifier.padding(10.dp)) {
                Box {
                    Icon(
                        Icons.Filled.MailOutline, contentDescription = null,
                        modifier = Modifier.padding(10.dp)
                    )
                }

                Column(Modifier.padding(10.dp)) {
                    Text(
                        text = "Google Play Services",
                        color = themetextcolour2(),
                        modifier = Modifier.requiredWidth(150.dp)
                    )
                }


                Switch(checked = checkgms.value, onCheckedChange = {
                        checkgms.value = it
                        prefseditor.putBoolean(any.gmsmode,it)
                        prefseditor.apply()
                    if (it){
                        alertdialog = true
                        gmsapi.installgms(
                            onsucess = {
                                CoroutineScope(Dispatchers.Main).launch {
                                    alertdialog = false
                                    Toast.makeText(context,"Sucess",Toast.LENGTH_LONG).show()
                                }
                            },
                            onfail = {
                                CoroutineScope(Dispatchers.Main).launch {
                                    alertdialog = false
                                    Toast.makeText(context,"Error $it",Toast.LENGTH_LONG).show()
                                }
                            }
                        )
                    }else{
                        alertdialog = true
                        gmsapi.removegms(
                            onsucess = {
                                CoroutineScope(Dispatchers.Main).launch {
                                    alertdialog = false
                                    Toast.makeText(context,"Sucess",Toast.LENGTH_LONG).show()
                                }
                            },
                            onfail = {
                                CoroutineScope(Dispatchers.Main).launch {
                                    alertdialog = false
                                    Toast.makeText(context,"Error $it",Toast.LENGTH_LONG).show()
                                }
                            }
                        )

                    }
                })



                if (alertdialog){
                    Showprogressbar(progressbarshow = alertdialog, alertindo = "Please Wait")
                }else{
                    Showprogressbar(progressbarshow = alertdialog, alertindo = "Please Wait")
                }
            }
        }
        Box(modifier = Modifier
            .toggleable(
                value = rootmode.value,
                role = Role.Switch,
                onValueChange = {
                    rootmode.value = it
                    prefseditor.putBoolean(any.rootmode, it)
                    prefseditor.apply()
                    restarttoast(context)
                }
            )
            .padding(10.dp)
        ) {
            Row(Modifier.padding(10.dp)) {
                Box {
                    Icon(
                        Icons.Filled.Edit, contentDescription = null,
                        modifier = Modifier.padding(10.dp)
                    )
                }

                Column(Modifier.padding(10.dp)) {
                    Text(
                        text = "Hide Root Path",
                        modifier = Modifier.requiredWidth(150.dp),
                        color = themetextcolour2()

                    )
                }
                Switch(checked = rootmode.value, onCheckedChange = {
                    rootmode.value = it
                    prefseditor.putBoolean(any.rootmode,it)
                    prefseditor.apply()
                    restarttoast(context)
                })
            }
        }
        Box(modifier = Modifier
            .toggleable(
                value = vpnmode.value,
                role = Role.Switch,
                onValueChange = {
                    vpnmode.value = it
                    prefseditor.putBoolean(any.vpnmode, it)
                    prefseditor.apply()
                    restarttoast(context)
                }
            )
            .padding(10.dp)

                ) {
            Row(Modifier.padding(10.dp)) {
                Box {
                    Icon(
                        Icons.Filled.Call, contentDescription = null,
                        modifier = Modifier.padding(10.dp)
                    )
                }
                Column(Modifier.padding(10.dp)) {
                    Text(
                        text = "Hide Vpn",
                        modifier = Modifier.requiredWidth(150.dp),
                        color = themetextcolour2()

                    )
                }
                Switch(checked = vpnmode.value, onCheckedChange = {
                    vpnmode.value = it
                    prefseditor.putBoolean(any.vpnmode,it)
                    prefseditor.apply()
                    restarttoast(context)
                })
            }
        }

        Box(modifier = Modifier
            .toggleable(
                value = hideroot.value,
                role = Role.Switch,
                onValueChange = {
                    hideroot.value = it
                    prefseditor.putBoolean(any.hideroot, it)
                    prefseditor.apply()
                    restarttoast(context)
                }
            )
            .padding(10.dp)

        ) {
            Row(Modifier.padding(10.dp)) {
                Box {
                    Icon(
                        Icons.Filled.Build, contentDescription = null,
                        modifier = Modifier.padding(10.dp)
                    )
                }

                Column(Modifier.padding(10.dp)) {
                    Text(
                        text = "Hide Root",
                        modifier = Modifier.requiredWidth(150.dp),
                        color = themetextcolour2()
                    )
                }
                Switch(checked = hideroot.value, onCheckedChange = {
                    hideroot.value = it
                    prefseditor.putBoolean(any.hideroot,it)
                    prefseditor.apply()
                    restarttoast(context)
                })
            }

        }

        Box(modifier = Modifier
            .toggleable(
                value = killmode.value,
                role = Role.Switch,
                onValueChange = {
                    killmode.value = it
                    prefseditor.putBoolean(any.crashmode, it)
                    prefseditor.apply()
                    restarttoast(context)
                }
            )
            .padding(10.dp)

        ) {
            Row(Modifier.padding(10.dp)) {
                Box {
                    Icon(
                        Icons.Filled.Settings, contentDescription = null,
                        modifier = Modifier.padding(10.dp)
                    )
                }

                Column(Modifier.padding(10.dp)) {
                    Text(
                        text = "Kill Apk",
                        modifier = Modifier.requiredWidth(150.dp),
                        color = themetextcolour2()
                    )
                }
                Switch(checked = killmode.value, onCheckedChange = {
                    killmode.value = it
                    prefseditor.putBoolean(any.crashmode,it)
                    prefseditor.apply()
                    restarttoast(context)
                })
            }
        }
        Box(modifier = Modifier
            .toggleable(
                value = launchanimation.value,
                role = Role.Switch,
                onValueChange = {
                    launchanimation.value = it
                    prefseditor.putBoolean(any.animation, it)
                    prefseditor.apply()
                    restarttoast(context)
                }
            )
            .padding(10.dp)

        ) {
            Row(Modifier.padding(10.dp)) {
                Box {
                    Icon(
                        Icons.Filled.Settings, contentDescription = null,
                        modifier = Modifier.padding(10.dp)
                    )
                }
                Column(Modifier.padding(10.dp)) {
                    Text(
                        text = "Launch Animation",
                        modifier = Modifier.requiredWidth(150.dp),
                        color = themetextcolour2()
                    )
                }
                Switch(checked = launchanimation.value, onCheckedChange = {
                    launchanimation.value = it
                    prefseditor.putBoolean(any.animation,it)
                    prefseditor.apply()
                    restarttoast(context)
                })
            }
        }
        TextButton(onClick = {
            permissonpopup()
        },
            modifier = Modifier.padding(5.dp)
        ) {
            Text(text = "Give Permission Unknown apps")

        }


    }

}

fun restarttoast(context: Context){
    Toast.makeText(context,"Restart the app to take effect",Toast.LENGTH_LONG).show()
}