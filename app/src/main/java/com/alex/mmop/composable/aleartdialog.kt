package com.alex.mmop.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Showprogressbar(progressbarshow : MutableState<Boolean>){
    if (progressbarshow.value){
        AlertDialog(onDismissRequest = {



        }

        ) {
            Box{


            }

        }

    }

}







