package es.rafapuig.pmdm.compose.exercises.layouts.calculator.ui

import android.app.Activity
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Backspace
import androidx.compose.material.icons.automirrored.outlined.Backspace
import androidx.compose.material.icons.filled.Backspace
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import es.rafapuig.pmdm.compose.exercises.layouts.calculator.ui.components.CalculatorButton
import es.rafapuig.pmdm.compose.exercises.layouts.calculator.ui.theme.DarkColorScheme
import es.rafapuig.pmdm.compose.exercises.layouts.calculator.ui.theme.LightOrange
import es.rafapuig.pmdm.compose.exercises.layouts.calculator.ui.theme.Orange


@Composable
fun Calculator(state: CalculatorState) {
    val buttonSpacing = 8.dp
    val darkBackground = Color(0xFF121212)
    Scaffold { innerPadding ->
        /*Surface(
            modifier = Modifier
                .fillMaxSize()
                //.padding(innerPadding)
                .background(Color.Red)
        ) {*/
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalArrangement = Arrangement
                    .spacedBy(
                        space = buttonSpacing,
                        alignment = Alignment.Bottom
                    )
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement
                        .spacedBy(buttonSpacing)
                ) {
                    CalculatorButton(
                        symbol = "AC",
                        color = Orange,
                        modifier = Modifier
                            .weight(2f)
                            .aspectRatio(2f)
                    )
                    CalculatorButton(
                        imageVector = Icons.AutoMirrored.Outlined.Backspace,
                        color = Orange,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                    )
                    /*CalculatorButton(
                symbol = "%",
                color = Orange,
                modifier = Modifier
                    .weight(1f)
            )*/
                    CalculatorButton(
                        symbol = "/",
                        color = Orange,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement
                        .spacedBy(buttonSpacing)
                ) {
                    CalculatorButton(
                        symbol = "7",
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                    )
                    CalculatorButton(
                        symbol = "8",
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                    )
                    CalculatorButton(
                        symbol = "9",
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                    )
                    CalculatorButton(
                        symbol = "x",
                        color = Orange,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement
                        .spacedBy(buttonSpacing)
                ) {
                    CalculatorButton(
                        symbol = "4",
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                    )
                    CalculatorButton(
                        symbol = "5",
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                    )
                    CalculatorButton(
                        symbol = "6",
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                    )
                    CalculatorButton(
                        symbol = "-",
                        color = Orange,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement
                        .spacedBy(buttonSpacing)
                ) {
                    CalculatorButton(
                        symbol = "1",
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                    )
                    CalculatorButton(
                        symbol = "2",
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                    )
                    CalculatorButton(
                        symbol = "3",
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                    )
                    CalculatorButton(
                        symbol = "+",
                        color = Orange,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement
                        .spacedBy(buttonSpacing)
                ) {
                    CalculatorButton(
                        symbol = "0",
                        modifier = Modifier
                            .aspectRatio(2f)
                            .weight(2f)
                    )

                    CalculatorButton(
                        symbol = ".",
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                    )
                    CalculatorButton(
                        symbol = "=",
                        color = Color.White,
                        backgroundColor = LightOrange,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .weight(1f)
                    )
                }
            }
        }
   // }
}


@Preview(
    showBackground = true, showSystemUi = false,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun CalculatorPreview() {

    val state = rememberCalculatorState()


    val darkColor = Color(0xFF121212).toArgb()

    MaterialTheme(
        colorScheme = if (isSystemInDarkTheme()) darkColorScheme() else lightColorScheme()
    ) {
        val view = LocalView.current
        if (!view.isInEditMode) {
            val window = (view.context as Activity).window
            //window.statusBarColor = darkColor // MaterialTheme.colorScheme.background.toArgb()
            window.navigationBarColor = MaterialTheme.colorScheme.background.toArgb()

            //val insetsController = WindowCompat.getInsetsController(window, view)
            //WindowCompat.setDecorFitsSystemWindows(window, false)
            //insetsController.isAppearanceLightNavigationBars = false
            //insetsController.isAppearanceLightStatusBars = false
        }

        Surface {
            Calculator(state.value)
        }


    }
}
