package com.alex.mmop.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun Settingsmenu(){
    var checked by remember {
        mutableStateOf(false)
    }
    Column(Modifier.padding(10.dp))
    {
        Box(modifier = Modifier
            .toggleable(
                value = checked,
                role = Role.Switch,
                onValueChange = {
                    checked = !checked
                }
            ).padding(10.dp)
            .border( // Apply the border modifier
                BorderStroke(width = 2.dp, color = Color.White),
                shape = RoundedCornerShape(10.dp)
            )
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
                Switch(checked = checked, onCheckedChange = {
                    checked = it
                })
            }
        }

        Box(modifier = Modifier
            .toggleable(
                value = checked,
                role = Role.Switch,
                onValueChange = {
                    checked = !checked
                }
            ).padding(10.dp)
            .border( // Apply the border modifier
                BorderStroke(width = 2.dp, color = Color.White),
                shape = RoundedCornerShape(10.dp)
            )
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
                Switch(checked = checked, onCheckedChange = {
                    checked = it
                })
            }
        }
        Box(modifier = Modifier
            .toggleable(
                value = checked,
                role = Role.Switch,
                onValueChange = {
                    checked = !checked
                }
            ).padding(10.dp)
            .border( // Apply the border modifier
                BorderStroke(width = 2.dp, color = Color.White),
                shape = RoundedCornerShape(10.dp)
            )
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
                Switch(checked = checked, onCheckedChange = {
                    checked = it
                })
            }
        }
        Box(modifier = Modifier
            .toggleable(
                value = checked,
                role = Role.Switch,
                onValueChange = {
                    checked = !checked
                }
            )
            .padding(10.dp)
            .border( // Apply the border modifier
                BorderStroke(width = 2.dp, color = Color.White),
                shape = RoundedCornerShape(10.dp)
            )
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
                Switch(checked = checked, onCheckedChange = {
                    checked = it
                })
            }
        }
        Box(modifier = Modifier
            .toggleable(
                value = checked,
                role = Role.Switch,
                onValueChange = {
                    checked = !checked
                }
            )
            .padding(10.dp)
            .border( // Apply the border modifier
                BorderStroke(width = 2.dp, color = Color.White),
                shape = RoundedCornerShape(10.dp)
            )
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
                Switch(checked = checked, onCheckedChange = {
                    checked = it
                })
            }
        }

        Box(modifier = Modifier
            .toggleable(
                value = checked,
                role = Role.Switch,
                onValueChange = {
                    checked = !checked
                }
            )
            .padding(10.dp)
            .border( // Apply the border modifier
                BorderStroke(width = 2.dp, color = Color.White),
                shape = RoundedCornerShape(10.dp)
            )
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
                Switch(checked = checked, onCheckedChange = {
                    checked = it
                })
            }
        }

    }
}



@Preview
@Composable
fun showpriview()
{
    Settingsmenu()
}

