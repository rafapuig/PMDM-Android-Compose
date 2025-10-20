package es.rafapuig.pmdm.compose.learning.components.text

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextMotion
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun AnimatedTextMotionStyle() {
    var big by remember { mutableStateOf(false) }

    val textStyle = TextStyle(
        fontSize = if (big) 60.sp else 30.sp,
        color = if(big) Color.Red else Color.Blue,
        fontWeight = if(big) FontWeight.ExtraBold else FontWeight.ExtraLight,
        textMotion = TextMotion.Animated // <-- aqui
    )

    Scaffold { innerPadding ->
        Box(Modifier.padding(innerPadding)) {

            Button(
                onClick = { big = !big },
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {
                Text("Change Text Style")
            }

            Text(
                "Compose !!!",
                style = textStyle,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight(Alignment.CenterVertically)
            )
        }
    }
}