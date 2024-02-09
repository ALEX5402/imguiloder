package com.alex.mmop.composable


import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.MODE_PRIVATE
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.alex.mmop.api.any


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

    Column(
        Modifier
            .padding(10.dp)
            .verticalScroll(rememberScrollState(),true)
            .horizontalScroll(rememberScrollState(),true)
    )
    {
        Box(modifier = Modifier
            .toggleable(
                value = checkgms.value,
                role = Role.Switch,
                onValueChange = {
                    checkgms.value = it
                    prefseditor.putBoolean(any.gmsmode,it)
                    prefseditor.apply()
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

                })
            }
        }
        Box(modifier = Modifier
            .toggleable(
                value = rootmode.value,
                role = Role.Switch,
                onValueChange = {
                    rootmode.value = it
                    prefseditor.putBoolean(any.rootmode,it)
                    prefseditor.apply()
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
                })
            }
        }
        Box(modifier = Modifier
            .toggleable(
                value = vpnmode.value,
                role = Role.Switch,
                onValueChange = {
                    vpnmode.value = it
                    prefseditor.putBoolean(any.vpnmode,it)
                    prefseditor.apply()
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
                })
            }
        }

        Box(modifier = Modifier
            .toggleable(
                value = hideroot.value,
                role = Role.Switch,
                onValueChange = {
                    hideroot.value = it
                    prefseditor.putBoolean(any.hideroot,it)
                    prefseditor.apply()
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
                })
            }

        }

        Box(modifier = Modifier
            .toggleable(
                value = killmode.value,
                role = Role.Switch,
                onValueChange = {
                    killmode.value = it
                    prefseditor.putBoolean(any.crashmode,it)
                    prefseditor.apply()
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
                })
            }
        }
        Box(modifier = Modifier
            .toggleable(
                value = launchanimation.value,
                role = Role.Switch,
                onValueChange = {
                    launchanimation.value = it
                    prefseditor.putBoolean(any.animation,it)
                    prefseditor.apply()
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
        TextButton(onClick = {
            Toast.makeText(context,"Soon",Toast.LENGTH_SHORT).show()
        },
            modifier = Modifier.padding(5.dp)
        ) {
            Text(text = "Copy Obb With Documents Provider")

        }
        TextButton(onClick = {
            Toast.makeText(context,"Soon",Toast.LENGTH_SHORT).show()
        },
            modifier = Modifier.padding(5.dp)
        ) {
            Text(text = "Copy All Obb With Documents Provider")

        }
        TextButton(onClick = {
            Toast.makeText(context,"Soon",Toast.LENGTH_SHORT).show()
        },
            modifier = Modifier.padding(5.dp)
        ) {
            Text(text = "Copy Pubg Global Obb With Documents Provider")

        }
        TextButton(onClick = {
            Toast.makeText(context,"Soon",Toast.LENGTH_SHORT).show()
        },
            modifier = Modifier.padding(5.dp)
        ) {
            Text(text = "Copy Pubg India Obb With Documents Provider")
        }
        TextButton(onClick = {
            Toast.makeText(context,"Soon",Toast.LENGTH_SHORT).show()
        },
            modifier = Modifier.padding(5.dp)
        ) {
            Text(text = "Copy Pubg Korea Obb With Documents Provider")

        }
    }
}



/*
@Preview
@Composable
fun showpriview()
{
    Settingsmenu()
}
*/
