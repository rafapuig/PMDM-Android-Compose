package es.rafapuig.pmdm.compose.lifecycle.save_UI_state.viewmodel.parcels.person_upsert.uiState

import android.os.Parcel
import android.os.Parcelable

/** https://developer.android.com/reference/android/os/Parcelable */

data class PersonUpsertUiStateBoilerPlateImpl2(
    override val name: String = "",
    override val age: Int? = null
) : PersonUpsertUiState, Parcelable {

    private constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readValue(Int::class.java.classLoader) as? Int
    )


    companion object CREATOR : Parcelable.Creator<PersonUpsertUiStateBoilerPlateImpl2> {

        override fun createFromParcel(source: Parcel): PersonUpsertUiStateBoilerPlateImpl2 {
            return PersonUpsertUiStateBoilerPlateImpl2(source)
        }

        override fun newArray(size: Int): Array<out PersonUpsertUiStateBoilerPlateImpl2?> {
            return arrayOfNulls(size)
        }
    }

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeValue(age)
    }

}


