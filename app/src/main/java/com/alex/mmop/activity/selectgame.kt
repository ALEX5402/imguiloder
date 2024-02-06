package com.alex.mmop.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.alex.mmop.composable.indiapubg
import com.alex.mmop.ui.theme.selectgametheme

class selectgame : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            selectgametheme{
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LazyColumn(modifier = Modifier.fillMaxSize()
                    ){
                        item {
                            indiapubg()
                        }
                    }
                }
            }
        }
    }
}





