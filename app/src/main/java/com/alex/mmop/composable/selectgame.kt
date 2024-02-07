package com.alex.mmop.composable

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.alex.mmop.api.any


@SuppressLint("ComposableNaming")
@Composable
fun showgame(version : String,
             packagename : String,
             icon : Int ,
             apkname : String ,
             pkgstatus : Boolean = false,
             onuninstall : () -> Unit ,
             oninstall : () -> Unit ,
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
            Row(Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(id = icon),
                    contentDescription = "image",
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                )
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

                    Button(
                        modifier = Modifier
                            .padding(10.dp)
                            .layoutId(any.installbtn) ,
                        shape = RoundedCornerShape(10.dp),
                        onClick = {
                            oninstall()
                        }) {
                        Text(text = "Setup Files")
                    }

                }


        }
    }
}


/*
@Preview(showBackground = true, device = "id:Nexus 5", name = "da")
@Composable
fun GreetingPreview() {
    selectgametheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {

  Box(modifier = Modifier
                  .padding()
                  .fillMaxSize() ) {
                  Image(painter = painterResource(id = R.drawable.backgr) ,
                      contentDescription ="null" ,
                      modifier = Modifier.fillMaxSize(),
                      contentScale = ContentScale.FillBounds
                  )
              }

            LazyColumn(modifier = Modifier.padding(horizontal = 10.dp)
            ){
                item {
                    indiapubg()

                }
            }
        }
    }
}
*/

