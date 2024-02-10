package com.alex.mmop.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alex.mmop.R
import com.alex.mmop.composable.Loginpage
import com.alex.mmop.ui.theme.selectgametheme

class Login : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            selectgametheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                }
                Column(
                    Modifier
                        .padding(10.dp)
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                        Loginpage(context = this@Login,
                            onlogindone = {
                              startActivity(Intent(this@Login,selectgame::class.java))
                                finish()
                        },
                            onloginfailed = {
                             Toast.makeText(this@Login, "${R.string.login_failed} $it" ,Toast.LENGTH_SHORT)
                                 .show()
                        },)
                }
            }
        }
    }
}