@file:OptIn(ExperimentalMaterial3Api::class)

package es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.ui.screens

import DiceUi
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import es.rafapuig.pmdm.compose.viewmodel.ui.theme.PMDMComposeTheme
import es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.data.DiceRepositoryImpl
import es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.ui.mvi.DiceUiState
import es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.ui.components.DiceFace
import es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.ui.mvi.DiceAction
import es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.ui.viewmodel.DiceViewModel


@Composable
fun DiceScreen(
    uiState: DiceUiState,
    onAction: (DiceAction) -> Unit = {}
) {
    val isLoading = uiState.isRolling
    val dice = uiState.dice

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Column {
                        Text(
                            "Dado",
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.fillMaxWidth(.8f)
                        )
                        Text(
                            "Ejemplo de ViewModel con dependencia",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth(.8f)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            val isLandscape =
                LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE

            val landscapeFraction = .8f
            val portraitFraction = .6f

            val loader = @Composable {
                Box(
                    modifier = Modifier
                            then (
                            if (isLandscape) Modifier.fillMaxHeight(landscapeFraction)
                            else Modifier.fillMaxWidth(portraitFraction))
                        .aspectRatio(1f),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        strokeWidth = 18.dp,
                        modifier = Modifier.fillMaxSize(if (isLandscape) landscapeFraction else portraitFraction)
                    )
                }
            }

            val diceFace = @Composable {
                DiceFace(
                    value = dice.face ?: 0,
                    modifier = Modifier
                            then (
                            if (isLandscape) Modifier.fillMaxHeight(landscapeFraction)
                            else Modifier.fillMaxWidth(portraitFraction))
                        .clickable { onAction(DiceAction.Roll) }
                )
            }

            val diceContent = @Composable {
                if (isLoading) loader() else diceFace()
            }

            val portraitContent = @Composable {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.surfaceVariant),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(48.dp, Alignment.CenterVertically)
                ) {
                    Text("Dado", style = MaterialTheme.typography.headlineMedium)

                    diceContent()

                    Button(onClick = { onAction(DiceAction.Roll) }) {
                        Text("Lanzar dado")
                    }
                }
            }

            val landscapeContent = @Composable {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.surfaceVariant),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(
                        48.dp,
                        Alignment.CenterHorizontally
                    )
                ) {
                    diceContent()

                    /*Button(onClick = { onAction(DiceAction.Roll) }) {
                        Text("Lanzar dado")
                    }*/
                }
            }

            if (!isLandscape) portraitContent() else landscapeContent()
        }
    }
}


@Preview(
    showSystemUi = true,
    device = "spec:parent=pixel_5,orientation=landscape",
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun DiceFaceScreenLandscapePreview() {
    PMDMComposeTheme {
        DiceScreen(
            uiState = DiceUiState(
                DiceUi(face = 5),
                false
            )
        )
    }
}

@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun DiceFaceScreenPortraitPreview() {
    PMDMComposeTheme {
        DiceScreen(
            uiState = DiceUiState(
                DiceUi(face = 5),
                false
            )
        )
    }
}

