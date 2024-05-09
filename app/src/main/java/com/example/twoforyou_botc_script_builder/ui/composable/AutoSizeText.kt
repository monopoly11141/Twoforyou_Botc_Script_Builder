import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun AutoSizeText(
    modifier : Modifier = Modifier,
    text : String,
    maxLines : Int = 1,
) {

    var multiplier by remember { mutableStateOf(1f) }

    val CONSTANT_MULTIPLIER = 0.98f

    Text(
        text = text,
        maxLines = maxLines, // modify to fit your need
        overflow = TextOverflow.Visible,
        style = LocalTextStyle.current.copy(
            fontSize = LocalTextStyle.current.fontSize * multiplier
        ),
        onTextLayout = {
            if (it.hasVisualOverflow) {
                multiplier *= CONSTANT_MULTIPLIER // you can tune this constant
            }
        },
        modifier = modifier
    )
}