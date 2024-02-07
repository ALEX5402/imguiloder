package com.alex.mmop.composable

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.alex.mmop.R



@SuppressLint("ComposableNaming", "UnrememberedMutableState")
@Composable
fun splashscreen( ){
    val isplayin by remember {
        mutableStateOf(true)
    }

    val compisition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animate))

    val state by animateLottieCompositionAsState(composition =compisition,
        isPlaying = isplayin,
        iterations = LottieConstants.IterateForever
    )
    Box {
    }
    LottieAnimation(composition = compisition, progress = state)

}

