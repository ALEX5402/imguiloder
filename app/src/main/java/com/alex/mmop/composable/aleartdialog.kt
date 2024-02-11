package com.alex.mmop.composable


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun Showprogressbar(progressbarshow : Boolean, alertindo: String = "Please wait"){
    if (progressbarshow){
        Dialog(onDismissRequest = {

        }) {
            Card(modifier = Modifier
                .width(100.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column {
                    Progressdialog(text = alertindo)
                }
            }
        }
    }
}


@Composable
fun Progressdialog( text: String ){
        CircularProgressIndicator(
            modifier = Modifier
                .size(100.dp)
        )
      Box(modifier = Modifier.widthIn(200.dp)
          .fillMaxWidth(0.2f)
      ) {

              Text(text = text,
                  modifier = Modifier.padding(5.dp)
                      .fillMaxWidth()
                  ,
                  textAlign = TextAlign.Center
              )
    }
}


/*

@Preview(device = "spec:parent=Nexus S")
@Composable
fun Priviewdawd(){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Showprogressbar(progressbarshow = true,"dddd")

    }
}



*/

