package com.alex.mmop.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun Showprogressbar(progressbarshow : Boolean){
    if (progressbarshow){
        Dialog(onDismissRequest = {  }) {
            Card(
                modifier = Modifier
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
            ) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(100.dp)
                                .padding(20.dp),
                            color = MaterialTheme.colorScheme.secondary,
                            trackColor = MaterialTheme.colorScheme.surfaceVariant,
                            )
                 Text(
                    text = "Please wait",
                    modifier = Modifier.padding(5.dp),
                    textAlign = TextAlign.Center,

                    )

            }
        }
    }
}

/*

@Preview(showBackground = true,
    device = "spec:width=411dp,height=891dp"
)
@Composable
fun showpreviewlogin() {
    selectgametheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {

            Showprogressbar(progressbarshow = true)

        }
    }
}


*/
