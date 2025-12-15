package es.rafapuig.pmdm.compose.lifecycle.save_UI_state.viewmodel.parcels.person_upsert.uiState

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi


/** https://developer.android.com/reference/android/os/Parcelable */

data class PersonUpsertUiStateBoilerPlateImpl(
    override val name: String = "",
    override val age: Int? = null
) : PersonUpsertUiState, Parcelable {

    private constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readBoolean().let { hasAge ->
            if (hasAge) parcel.readInt() else null
        }
    )


    companion object CREATOR : Parcelable.Creator<PersonUpsertUiStateBoilerPlateImpl> {

        override fun createFromParcel(source: Parcel): PersonUpsertUiStateBoilerPlateImpl? {
            return PersonUpsertUiStateBoilerPlateImpl(source)
        }

        override fun newArray(size: Int): Array<out PersonUpsertUiStateBoilerPlateImpl?> {
            return arrayOfNulls(size)
        }
    }

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeBoolean(age != null)
        age?.let { dest.writeInt(it) }
    }

}


