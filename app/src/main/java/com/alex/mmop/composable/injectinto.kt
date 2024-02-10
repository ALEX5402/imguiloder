package com.alex.mmop.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.alex.mmop.R
import com.alex.mmop.api.any
import com.fvbox.lib.FCore


@Composable
fun Injectionview(onclicklaunch:() -> Unit) {
    Box(
        modifier = Modifier
            .size(150.dp)
            .fillMaxSize()
            .blur(10.dp, edgeTreatment = BlurredEdgeTreatment.Unbounded)
            .clip(RoundedCornerShape(30.dp))
            .background(Color.Transparent),
    ) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
    val constraintsettt = ConstraintSet {

        val textview = createRefFor("text11")
        val card = createRefFor("card11")
        val boxmodname = createRefFor("modname")
        constrain(textview) {
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(card) {
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(parent.top)

        }
        constrain(boxmodname) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(parent.top)
        }
    }

    ConstraintLayout(
        constraintSet = constraintsettt,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Box(modifier = Modifier.layoutId("text11")) {
            Text(
                text = "Ui Desined By ALEX5402", color = Color.Red,
                fontFamily = FontFamily.Monospace

            )
        }
        Box(
            modifier = Modifier.padding(30.dp)
                .layoutId("modname")

        ) {
            Text(
                text = any.modownername,
                color = registercolour(),
                fontSize = 30.sp
            )
        }
        Box(
            Modifier
                .layoutId("card11")
                .border(
                    BorderStroke(
                        width = 2.dp,
                        color = Color(255, 255, 255, 255)
                    ),
                    shape = MaterialTheme.shapes.medium
                )
                .padding(vertical = 20.dp)
        ) {
            Box(
                modifier = Modifier
                    .padding()
                    .heightIn(200.dp)
                    .width(300.dp)
                    .padding(horizontal = 20.dp)

            ) {
                Column {


                    Row(
                        Modifier.padding(10.dp)
                    ) {

                        Image(painter = painterResource(id = R.drawable.facebook),
                            contentDescription = null,
                            modifier = Modifier
                                .height(50.dp)
                                .padding(10.dp)

                                .clickable(
                                    role = Role.Image,
                                    onClick = {
                                        launchsocial(any.facebook)
                                    }
                                )

                        )

                        Image(painter = painterResource(id = R.drawable.twitter),
                            contentDescription = null,
                            modifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                                .height(50.dp)
                                .clickable(
                                    role = Role.Image,
                                    onClick = {
                                        launchsocial(any.twitter)
                                    }
                                )


                        )
                        Image(painter = painterResource(id = R.drawable.facebook),
                            contentDescription = null,
                            modifier = Modifier
                                .height(50.dp)
                                .padding(10.dp)

                                .clickable(
                                    role = Role.Image,
                                    onClick = {
                                        launchsocial(any.facebook)
                                    }
                                )


                        )

                        Image(painter = painterResource(id = R.drawable.twitter),
                            contentDescription = null,
                            modifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                                .height(50.dp)
                                .clickable(
                                    role = Role.Image,
                                    onClick = {
                                        launchsocial(any.twitter)
                                    }
                                )

                        )
                        Image(painter = painterResource(id = R.drawable.facebook),
                            contentDescription = null,
                            modifier = Modifier
                                .height(50.dp)
                                .padding(10.dp)
                                .clickable(
                                    role = Role.Image,
                                    onClick = {
                                        launchsocial(any.facebook)
                                    }
                                )


                        )

                        Image(painter = painterResource(id = R.drawable.twitter),
                            contentDescription = null,
                            modifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                                .height(50.dp)
                                .clickable(
                                    role = Role.Image,
                                    onClick = {
                                        launchsocial(any.twitter)
                                    }
                                )
                        )
                    }

                    Row(
                        Modifier.padding(10.dp)
                    ) {

                        Image(painter = painterResource(id = R.drawable.facebook),
                            contentDescription = null,
                            modifier = Modifier
                                .height(50.dp)
                                .padding(10.dp)

                                .clickable(
                                    role = Role.Image,
                                    onClick = {
                                        launchsocial(any.facebook)
                                    }
                                )
                        )
                        Image(painter = painterResource(id = R.drawable.twitter),
                            contentDescription = null,
                            modifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                                .height(50.dp)
                                .clickable(
                                    role = Role.Image,
                                    onClick = {
                                        launchsocial(any.twitter)
                                    }
                                )


                        )
                        Image(painter = painterResource(id = R.drawable.facebook),
                            contentDescription = null,
                            modifier = Modifier
                                .height(50.dp)
                                .padding(10.dp)

                                .clickable(
                                    role = Role.Image,
                                    onClick = {
                                        launchsocial(any.facebook)
                                    }
                                )


                        )

                        Image(painter = painterResource(id = R.drawable.twitter),
                            contentDescription = null,
                            modifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                                .height(50.dp)
                                .clickable(
                                    role = Role.Image,
                                    onClick = {
                                        launchsocial(any.twitter)
                                    }
                                )

                        )
                        Image(painter = painterResource(id = R.drawable.facebook),
                            contentDescription = null,
                            modifier = Modifier
                                .height(50.dp)
                                .padding(10.dp)
                                .clickable(
                                    role = Role.Image,
                                    onClick = {
                                        launchsocial(any.facebook)
                                    }
                                )


                        )

                        Image(painter = painterResource(id = R.drawable.twitter),
                            contentDescription = null,
                            modifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                                .height(50.dp)
                                .clickable(
                                    role = Role.Image,
                                    onClick = {
                                        launchsocial(any.twitter)
                                    }
                                )
                        )
                    }
                    Row(
                        Modifier.padding(10.dp)
                    ) {

                        Image(painter = painterResource(id = R.drawable.facebook),
                            contentDescription = null,
                            modifier = Modifier
                                .height(50.dp)
                                .padding(10.dp)

                                .clickable(
                                    role = Role.Image,
                                    onClick = {
                                        launchsocial(any.facebook)
                                    }
                                )
                        )

                        Image(painter = painterResource(id = R.drawable.twitter),
                            contentDescription = null,
                            modifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                                .height(50.dp)
                                .clickable(
                                    role = Role.Image,
                                    onClick = {
                                        launchsocial(any.twitter)
                                    }
                                )


                        )
                        Image(painter = painterResource(id = R.drawable.facebook),
                            contentDescription = null,
                            modifier = Modifier
                                .height(50.dp)
                                .padding(10.dp)

                                .clickable(
                                    role = Role.Image,
                                    onClick = {
                                        launchsocial(any.facebook)
                                    }
                                )


                        )

                        Image(painter = painterResource(id = R.drawable.twitter),
                            contentDescription = null,
                            modifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                                .height(50.dp)
                                .clickable(
                                    role = Role.Image,
                                    onClick = {
                                        launchsocial(any.twitter)
                                    }
                                )

                        )
                        Image(painter = painterResource(id = R.drawable.facebook),
                            contentDescription = null,
                            modifier = Modifier
                                .height(50.dp)
                                .padding(10.dp)
                                .clickable(
                                    role = Role.Image,
                                    onClick = {
                                        launchsocial(any.facebook)
                                    }
                                )


                        )

                        Image(painter = painterResource(id = R.drawable.twitter),
                            contentDescription = null,
                            modifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                                .height(50.dp)
                                .clickable(
                                    role = Role.Image,
                                    onClick = {
                                        launchsocial(any.twitter)
                                    }
                                )
                        )
                    }
                    Row(
                        Modifier.padding(10.dp)
                    ) {

                        Image(painter = painterResource(id = R.drawable.facebook),
                            contentDescription = null,
                            modifier = Modifier
                                .height(50.dp)
                                .padding(10.dp)

                                .clickable(
                                    role = Role.Image,
                                    onClick = {
                                        launchsocial(any.facebook)
                                    }
                                )


                        )

                        Image(painter = painterResource(id = R.drawable.twitter),
                            contentDescription = null,
                            modifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                                .height(50.dp)
                                .clickable(
                                    role = Role.Image,
                                    onClick = {
                                        launchsocial(any.twitter)
                                    }
                                )


                        )
                        Image(painter = painterResource(id = R.drawable.facebook),
                            contentDescription = null,
                            modifier = Modifier
                                .height(50.dp)
                                .padding(10.dp)

                                .clickable(
                                    role = Role.Image,
                                    onClick = {
                                        launchsocial(any.facebook)
                                    }
                                )


                        )

                        Image(painter = painterResource(id = R.drawable.twitter),
                            contentDescription = null,
                            modifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                                .height(50.dp)
                                .clickable(
                                    role = Role.Image,
                                    onClick = {
                                        launchsocial(any.twitter)
                                    }
                                )

                        )
                        Image(painter = painterResource(id = R.drawable.facebook),
                            contentDescription = null,
                            modifier = Modifier
                                .height(50.dp)
                                .padding(10.dp)
                                .clickable(
                                    role = Role.Image,
                                    onClick = {
                                        launchsocial(any.facebook)
                                    }
                                )


                        )

                        Image(painter = painterResource(id = R.drawable.twitter),
                            contentDescription = null,
                            modifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                                .height(50.dp)
                                .clickable(
                                    role = Role.Image,
                                    onClick = {
                                        launchsocial(any.twitter)
                                    }
                                )
                        )
                    }
                    Box(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Button(
                            onClick =
                            {
                                onclicklaunch()
                            },
                            modifier = Modifier,
                        ) {
                            Text(text = "Launch Game")
                        }

                    }

                }

            }

        }

    }
}
fun launchsocial(packagename: String) {
    val check = FCore.get().isInstalled(packagename,0)
    if (check){
        FCore.get().launchApk(packagename,0)
    }else{
        val reasult =  FCore.get().installPackageAsUser(packagename,0)
        if (reasult != null){
            FCore.get().launchApk(packagename,0)
        }
    }
}


/*

@Preview(device = "spec:parent=Nexus 5")
@Composable
fun Priview(){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Injectionview()

    }
}





*/


