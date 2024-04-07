package com.alex.mmop.composable

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.alex.mmop.R
import com.alex.mmop.api.any
import com.fvbox.lib.FCore
import org.checkerframework.checker.i18n.qual.LocalizableKey
import top.canyie.pine.Pine


@Composable
fun Injectionview(onclicklaunch:() -> Unit) {
    Box(
        modifier = Modifier
            .size(150.dp)
            .fillMaxSize()
            .blur(5.dp, edgeTreatment = BlurredEdgeTreatment.Unbounded)
            .clip(RoundedCornerShape(30.dp))
            .background(Color.Transparent),
    ) {

   Image(painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }

/*
    Column(
        Modifier
            .layoutId("borderbox")
            .fillMaxSize()
            .border(
                BorderStroke(
                    width = 2.dp,
                    color = Color(255, 255, 255, 255)
                ),
                shape = MaterialTheme.shapes.medium
            )
            .padding(vertical = 20.dp)
        , horizontalAlignment =  Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {}
*/

    val constraintsettt = ConstraintSet {

        val modname = createRefFor("modname")
        val launchbutton = createRefFor("launchbutton")
        val socialmedia = createRefFor("socialmedia")
        val bottomcredit = createRefFor("bottomcredit")

        constrain(modname){
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(parent.top)
        }
        constrain(launchbutton){
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(socialmedia){
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(bottomcredit){
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
        }

        createVerticalChain(
            modname,
            launchbutton,
            socialmedia,
            bottomcredit
        )

    }

    ConstraintLayout(
        constraintSet = constraintsettt,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {

        Box(
            modifier = Modifier
                .padding(30.dp)
                .layoutId("modname")

        ) {
            Text(
                text = any.modownername,
                color = registercolour(),
                fontSize = 15.sp,
                textAlign = TextAlign.Center
            )
        }

        Column(modifier = Modifier.layoutId("launchbutton")) {
            Image(painter = painterResource(id = R.drawable.start),
                contentDescription = null,
                modifier = Modifier.height(50.dp)
                    .width(50.dp)
                    .padding(10.dp)
                    .clickable(
                        role = Role.Image,
                        onClick = {
                            onclicklaunch()
                        }
                    )
            )
            Text(text = "Start" ,
                modifier = Modifier.size(50.dp),
                textAlign = TextAlign.Center
            )
        }
        val context = LocalContext.current

        Row(modifier = Modifier.layoutId("socialmedia")) {
            Image(painter = painterResource(id = R.drawable.facebook),
                contentDescription = null,
                modifier = Modifier
                    .height(50.dp)
                    .padding(10.dp)
                    .clickable(
                        role = Role.Image,
                        onClick = {
                            launchsocial(any.facebook,context)
                        }
                    )

            )
            Spacer(modifier = Modifier.size(20.dp))
            Image(painterResource(id = R.drawable.twitter), null, Modifier
                .height(50.dp)
                .width(50.dp)
                .height(50.dp)
                .clickable(
                    role = Role.Image,
                    onClick = {
                        launchsocial(any.twitter,context)
                    }
                )
            )
        }

        Box(modifier = Modifier.layoutId("bottomcredit")) {
            Text(
                text = "Ui Desined By ALEX5402", color = Color.Red,
                fontFamily = FontFamily.Monospace

            )
        }

    }
}
fun launchsocial(packagename: String, context: Context) {
    val check = FCore.get().isInstalled(packagename,0)
    if (check){
        FCore.get().launchApk(packagename,0)
    }else{
        FCore.get().installPackageAsUser(packagename,0)
        Toast.makeText(context , "the package $packagename is installing please click again after that",Toast.LENGTH_LONG).show()

    }
}





