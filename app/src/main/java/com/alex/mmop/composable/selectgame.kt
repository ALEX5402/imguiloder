package com.alex.mmop.composable

import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.alex.mmop.R
import com.alex.mmop.api.Filesapi
import com.alex.mmop.api.any
import com.alex.mmop.ui.theme.selectgametheme


@Composable
fun Selectmode(version : String,
             packagename : String,
             icon : Int ,
             apkname : String ,
             pkgstatus : Boolean = false,
             oninstall: () -> Unit,
             onuninstall: () -> Unit


){
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
    val playbuttontext by remember {
        mutableStateOf(Filesapi.isobb())
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
                        painter = painterResource(id = icon),
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
                        Text(text = "version : $version",
                            modifier = Modifier.padding(2.dp),
                            fontFamily = FontFamily.Monospace
                        )
                        Row {
                            if (pkgstatus){
                                Text(text = "status : ",
                                    modifier = Modifier.padding(2.dp),
                                    fontFamily = FontFamily.Monospace
                                )
                                Text(text = "online",
                                    color = Color.Green,
                                    modifier = Modifier,
                                    fontFamily = FontFamily.Monospace
                                )
                            } else{
                                Text(text = "status : ",
                                    modifier = Modifier.padding(2.dp),
                                    fontFamily = FontFamily.Monospace
                                )
                                Text(text = "Offline",
                                    color = Color.Red,
                                    modifier = Modifier,
                                    fontFamily = FontFamily.Monospace
                                )
                            }

                        }
                        Text(text = "info : $apkname",
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
                            .layoutId(any.uninstallbtn)
                        ,
                        shape = RoundedCornerShape(10.dp),
                        onClick = {
                            onuninstall()
                        }

                    ) {
                        Text(text = "  Uninstall  ")
                    }
                    if (pkgstatus){
                        Button(
                            modifier = Modifier
                                .padding(10.dp)
                                .layoutId(any.installbtn) ,
                            shape = RoundedCornerShape(10.dp),
                            onClick = {
                                oninstall()
                            }) {

                            Text(text = "Setup Files" , modifier = Modifier.animateVisibility(
                               ! playbuttontext
                            ))

                            Text(text = "Play" , modifier = Modifier.animateVisibility(
                                playbuttontext
                            ))

                        }
                    }else{
                        Button(
                            modifier = Modifier
                                .padding(10.dp)
                                .layoutId(any.installbtn) ,
                            enabled = false,
                            shape = RoundedCornerShape(10.dp),
                            onClick = {
                            }) {
                            Text(text = "Not Enabled")
                        }


                    }


                }


        }
    }
}
@SuppressLint("ComposableModifierFactory")
@Composable
private fun Modifier.animateVisibility(visible: Boolean): Modifier {
    val transition = updateTransition(targetState = visible, label = "")

    val opacity = transition.animateFloat(
        transitionSpec = {
            tween(
                durationMillis = 500,
                easing = LinearOutSlowInEasing
            )
        }, label = ""
    ) { state ->
        if (state) 1f else 0f
    }
    return this
        .then(animateContentSize())
        .then(
            Modifier.alpha(opacity.value)
        )
}



@Preview(showBackground = true, device = "id:Nexus 5", name = "da")
@Composable
fun GreetingPreview() {
    selectgametheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {

            Box(
                modifier = Modifier
                    .padding()
                    .fillMaxSize()
            ) {

                LazyColumn(
                    modifier = Modifier.padding(horizontal = 10.dp)
                ) {
                    item {


                        Selectmode(
                            version = "it.version",
                            packagename = "it.packagename",
                            icon = R.drawable.bgmi_icon,
                            apkname = "it.apkname",
                            oninstall = {

                            }, onuninstall =
                            {

                            }
                        )


                    }
                }
            }
        }

    }
}

