package com.alex.mmop.composable

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.airbnb.lottie.RenderMode
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.alex.mmop.R
import com.alex.mmop.api.alexapi
import com.alex.mmop.api.any
import com.alex.mmop.authapi.kuroapi
import com.alex.mmop.authapi.userinfo
import kotlinx.coroutines.runBlocking

fun savestring(context: Context, key: String, value: String) {
    runBlocking {
        val sharedPreferences = context.getSharedPreferences(any.prefskey, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(key,value)
        editor.apply()
    }
}

fun getstring(context: Context, key: String): String? {
    val sharedPreferences = context.getSharedPreferences(any.prefskey, Context.MODE_PRIVATE)
    return sharedPreferences.getString(key,"")
}

@Composable
fun Loginpage(context: Context ,
              onlogindone : () -> Unit,
              onloginfailed : (reason:String) -> Unit,

){
    val localcontext  = LocalContext.current
    val usersave = getstring(localcontext, any.usersafe)

    var userkey by remember {
        mutableStateOf(usersave)
    }
    val progressbarshow = remember {
        mutableStateOf(false)
    }
    if (progressbarshow.value){
        Showprogressbar(progressbarshow = progressbarshow.value,"Please wait")
    }
    
    val compositon by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.userlogin))
    val constrains1 = ConstraintSet {
        val loginbtn = createRefFor(any.loginbtn)
        val registerbtn = createRefFor(any.register)
        constrain(loginbtn)
        {
            bottom.linkTo(registerbtn.top)

        }
        constrain(registerbtn)
        {
            top.linkTo(loginbtn.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
    }

    Box(modifier = Modifier
    ){
        Column {
                LottieAnimation(composition = compositon, isPlaying = true,
                    iterations = LottieConstants.IterateForever,
                    modifier = Modifier
                        .height(500.dp)
                        .fillMaxWidth(),
                    renderMode = RenderMode.HARDWARE,
                    alignment = Alignment.BottomCenter,
                    contentScale = ContentScale.Crop)
            Column {
                val gradientColors = arrayOf(
                    Color.White,
                    Color.Cyan,
                    Color.Red
                )
                val brush = remember {
                    Brush.linearGradient(
                        colors = gradientColors.toList()
                    )
                }
                    OutlinedTextField(value = userkey!!, onValueChange =
                    {
                        userkey = it
                        savestring(localcontext,any.usersafe,it)
                    },
                        label = {
                            Text(text = " Put User key here" )
                        }, modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 30.dp),
                        textStyle = LocalTextStyle.current.copy(
                            textAlign = TextAlign.Left
                        ),
                        trailingIcon = {
                            Icon(imageVector = Icons.Rounded.CheckCircle, contentDescription = null)
                        }
                    )
                ConstraintLayout(constraintSet = constrains1,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 70.dp, vertical = 30.dp)
                ) {
                    TextButton(onClick = { //loginbutton
                        val keyyy = userkey
                        if (keyyy!!.isEmpty())
                            return@TextButton Toast.makeText(context, "please put your key",Toast.LENGTH_LONG).show()
                        if (!alexapi.isInternetAvailable(context))
                            return@TextButton Toast.makeText(context, "Unable to connect to internet",Toast.LENGTH_LONG).show()

                        val userinfoclass = userinfo(
                            userkey!!,
                            alexapi.GetAndroidID(),
                            alexapi.GetDeviceModel(),
                            alexapi.GetDeviceBrand()
                        )
                        val uuid = generateuuid(userinfoclass)
                        val kuroapi = kuroapi(
                            userkey = userkey!!,
                            uuid = uuid,
                            androidid = userinfoclass.androidid,
                            devicemodel = userinfoclass.devicemodel,
                            devicebrand = userinfoclass.devicemodel
                        )
                        progressbarshow.value = true
                        alexapi.loginnnn(kuroapi,context, onsucess = {
                            progressbarshow.value = false
                            onlogindone()
                        } , onfailed = {
                            progressbarshow.value = false
                            onloginfailed(it)
                        },
                            )

                    }, modifier = Modifier
                        .fillMaxWidth()
                        .layoutId(any.loginbtn),
                        border = BorderStroke(
                            2.dp,
                            color = Color.Gray
                        )
                    ) {
                        Text( text = "Login"
                            , fontFamily = FontFamily.SansSerif,
                            color = themetextcolour2()
                        )
                    }
                    Row (modifier = Modifier
                        .layoutId(any.register)
                        .padding(20.dp)
                        ){
                        ClickableText(text = AnnotatedString("! Register"),
                         onClick = {
                                   alexapi.openLink(localcontext,"https://t.me/esp68")
                         } ,
                            style = TextStyle(color = registercolour())
                        )

                    }

                }
            }

        }
    }
}

fun generateuuid(userinfo: userinfo) : String {
    return runBlocking {
        alexapi.getDeviceUniqueIdentifier(
            "${userinfo.userkey}${userinfo.androidid}${userinfo.devicemodel}${userinfo.devicebrand}"
        )
    }
}




