@file:OptIn(ExperimentalMaterial3Api::class)

package es.rafapuig.pmdm.compose.sensormotion.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import es.rafapuig.pmdm.compose.sensormotion.ui.components.MovingBallCanvas
import es.rafapuig.pmdm.compose.sensormotion.ui.theme.PMDMComposeTheme
import es.rafapuig.pmdm.compose.sensormotion.ui.utils.format

@Composable
fun BallScreen(
    normalized: Pair<Float, Float> = Pair(0f, 0f)
) {

    val ballRadiusDp = 24.dp
    val paddingDp = 24.dp

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Mueve el dispositivo",
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        }

    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF101010))
                .padding(innerPadding)
                .padding(paddingDp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                with(normalized) {
                    Text(
                        "X: ${first.format(2)} " +
                                "Y: ${second.format(2)}",
                        color = Color.LightGray
                    )
                }
                Spacer(Modifier.height(20.dp))

                // Área de dibujo
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    MovingBallCanvas(
                        normalized,
                        ballRadiusDp
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun BallScreenPreview() {
    PMDMComposeTheme {
        BallScreen()
    }
}