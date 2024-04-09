package com.alex.mmop.composable

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.alex.mmop.R
import com.alex.mmop.api.LOGS
import com.alex.mmop.api.any
import com.fvbox.lib.FCore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun Zarchiver (
    onimportclick : () -> Unit,
){

    val packagename = "ru.zdevs.zarchiver"
    val constraints = ConstraintSet {
        val uninstall_button = createRefFor(any.uninstallbtn)
        val install_button = createRefFor(any.installbtn)
        constrain(uninstall_button){
            start.linkTo(parent.start)
            end.linkTo(install_button.start)
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
        }
        constrain(install_button){
            start.linkTo(uninstall_button.end)
            end.linkTo(parent.end)
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
        }
    }

    val context = LocalContext.current

    val getplaybutton = FCore.get().isInstalled(packagename,0)

    var playbuttontext by remember {
        mutableStateOf(getplaybutton)
    }


    Box (modifier = Modifier
        .padding(10.dp)
        .border(
            BorderStroke(
                width = 2.dp,
                color = Color(200, 200, 200, 255)
            ),
            shape = MaterialTheme.shapes.medium
        )
        .fillMaxSize()

    ) {
        Column {
            Row {
                Box (contentAlignment = Alignment.Center,
                    modifier = Modifier.padding(vertical = 20.dp)
                ){
                    Image(
                        painter = painterResource(id = R.drawable.zarchiver),
                        contentDescription = "image",
                        modifier = Modifier
                            .padding(10.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .height(80.dp)
                            .width(80.dp)
                        ,
                        contentScale = ContentScale.Fit

                    )
                }
                Box (modifier= Modifier
                    .fillMaxSize()){
                    Column(Modifier.padding(15.dp)) {
                        Text(text = "version : Latest",
                            modifier = Modifier.padding(2.dp),
                            fontFamily = FontFamily.Monospace
                        )
                        Row {
                                Text(text = "status : ",
                                    modifier = Modifier.padding(2.dp),
                                    fontFamily = FontFamily.Monospace
                                )
                                Text(text = "online",
                                    color = Color.Green,
                                    modifier = Modifier,
                                    fontFamily = FontFamily.Monospace
                                )
                        }
                        Text(text = "info : Zarchiver",
                            modifier = Modifier.padding(2.dp),
                            fontFamily = FontFamily.Monospace
                        )
                    }
                }
            }
            ConstraintLayout(modifier = Modifier
                .fillMaxSize()
                , constraintSet = constraints
            ) {
                Button(
                    modifier = Modifier
                        .padding(10.dp)
                        .layoutId(any.uninstallbtn),
                    shape = RoundedCornerShape(10.dp),
                    onClick = {
                        onimportclick()




                    }) {
                    Text(text = "  Import Files  ")
                }
                    Button(
                        modifier = Modifier
                            .padding(10.dp)
                            .layoutId(any.installbtn) ,
                        shape = RoundedCornerShape(10.dp),
                        onClick = {
                            LOGS.warn(" here ")
                            if (!isAppInstalled(context,packagename))
                                return@Button Toast.makeText(context,"Please Install $packagename",
                                    Toast.LENGTH_LONG).show()
                            if (playbuttontext){
                                CoroutineScope(Dispatchers.Main).launch {
                                    FCore.get().launchApk(packagename,0)
                                }
                                LOGS.warn(" here ")
                            }else{
                                try {
                                    FCore.get().installPackageAsUser(packagename,0)
                                    playbuttontext = true
                                } catch (err : Exception){
                                    err.printStackTrace()
                                }
                            }
                        }) {
                        AnimatedVisibility(visible = !playbuttontext) {
                            Text(text = "install")
                        }
                        AnimatedVisibility(visible = playbuttontext) {
                            Text(text = "open")
                        }
                    }

            }
        }
    }

}


/*
@Preview
@Composable
fun priview ( ){
zarchiver()
}*/
