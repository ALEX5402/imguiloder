package com.alex.mmop.composable

import android.content.Context
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp


@Composable
fun Settingsmenu(context: Context, permissonpopup: () -> Unit){
    var checkgms by remember {
        mutableStateOf(false)
    }
    var rootmode by remember {
        mutableStateOf(false)
    }
    var vpnmode by remember {
        mutableStateOf(false)
    }
    var hideroot by remember {
        mutableStateOf(false)
    }
    var killmode by remember {
        mutableStateOf(false)
    }
    var launchanimation by remember {
        mutableStateOf(false)
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
                value = checkgms,
                role = Role.Switch,
                onValueChange = {
                    checkgms = !checkgms
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
                Switch(checked = checkgms, onCheckedChange = {
                    checkgms = it
                })
            }
        }
        Box(modifier = Modifier
            .toggleable(
                value = rootmode,
                role = Role.Switch,
                onValueChange = {
                    rootmode = !rootmode
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
                Switch(checked = rootmode, onCheckedChange = {
                    rootmode = it
                })
            }
        }

        Box(modifier = Modifier
            .toggleable(
                value = vpnmode,
                role = Role.Switch,
                onValueChange = {
                    vpnmode = !vpnmode
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
                Switch(checked = vpnmode, onCheckedChange = {
                    vpnmode = it
                })
            }
        }

        Box(modifier = Modifier
            .toggleable(
                value = hideroot,
                role = Role.Switch,
                onValueChange = {
                    hideroot = !hideroot
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
                Switch(checked = hideroot, onCheckedChange = {
                    hideroot = it
                })
            }

        }

        Box(modifier = Modifier
            .toggleable(
                value = killmode,
                role = Role.Switch,
                onValueChange = {
                    killmode = !killmode
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
                Switch(checked = killmode, onCheckedChange = {
                    killmode = it
                })
            }
        }
        Box(modifier = Modifier
            .toggleable(
                value = launchanimation,
                role = Role.Switch,
                onValueChange = {
                    launchanimation = !launchanimation
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
                Switch(checked = launchanimation, onCheckedChange = {
                    launchanimation = it
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
            permissonpopup()
        },
            modifier = Modifier.padding(5.dp)
        ) {
            Text(text = "Copy Obb With Documents Provider")

        }

        TextButton(onClick = {
            permissonpopup()
        },
            modifier = Modifier.padding(5.dp)
        ) {
            Text(text = "Copy All Obb With Documents Provider")

        }
        TextButton(onClick = {
            permissonpopup()
        },
            modifier = Modifier.padding(5.dp)
        ) {
            Text(text = "Copy Pubg Global Obb With Documents Provider")

        }
        TextButton(onClick = {
            permissonpopup()
        },
            modifier = Modifier.padding(5.dp)
        ) {
            Text(text = "Copy Pubg India Obb With Documents Provider")

        }
        TextButton(onClick = {
            permissonpopup()
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
