import android.os.Parcelable
import es.rafapuig.pmdm.compose.viewmodel.with_dependecies.dice.domain.model.Dice
import kotlinx.parcelize.Parcelize

@JvmInline
@Parcelize
value class DiceUi(val face: Int? = null) : Parcelable

