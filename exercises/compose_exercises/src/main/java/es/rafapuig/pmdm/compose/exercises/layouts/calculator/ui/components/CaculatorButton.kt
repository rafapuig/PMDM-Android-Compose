package es.rafapuig.pmdm.compose.exercises.layouts.calculator.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import es.rafapuig.pmdm.compose.exercises.layouts.calculator.ui.theme.Orange


@Composable
fun CalculatorButton(
    symbol : String,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
    color: Color = Color.White,
    backgroundColor: Color = Color.DarkGray
) {
    CalculatorButton(
        modifier = modifier,
        backgroundColor = backgroundColor,
        onClick = onClick
    ) {
        Text(
            text = symbol,
            fontSize = 36.sp,
            color = color,
        )
    }
}

@Composable
fun CalculatorButton(
    imageVector: ImageVector,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
    color: Color = Color.White,
    backgroundColor: Color = Color.DarkGray
) {
    CalculatorButton(
        modifier = modifier,
        backgroundColor = backgroundColor,
        onClick = onClick
    )
     {
        Icon(
            imageVector = imageVector,
            contentDescription = null,
            tint = color
        )
    }
}

@Composable
fun CalculatorButton(
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.DarkGray,
    content: @Composable () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(RoundedCornerShape(20))
            .background(backgroundColor)
            .clickable(onClick = {})
            //.then(modifier)

    ) {
        content()
    }
}

class SymbolPreviewProvider : PreviewParameterProvider<String> {
    override val values: Sequence<String>
        get() = sequenceOf("AC","+","-","*","/","0")
}

@Preview(showBackground = true, widthDp = 80)
@Composable
fun CalculatorButtonPreview(
    @PreviewParameter(SymbolPreviewProvider::class) symbol: String
) {
    CalculatorButton(symbol = symbol, color = Orange)
}
