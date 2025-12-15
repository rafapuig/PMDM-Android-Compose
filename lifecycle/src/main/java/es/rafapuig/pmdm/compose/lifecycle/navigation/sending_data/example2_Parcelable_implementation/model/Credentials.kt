package es.rafapuig.pmdm.compose.lifecycle.navigation.sending_data.example2_Parcelable_implementation.model

import android.os.Parcel
import android.os.Parcelable

import es.rafapuig.pmdm.compose.lifecycle.navigation.sending_data.ui.models.Credentials as CredentialsInterface

/**
 * Implementación para que sea empaquetable en un Bundle
 * Para que se pueda empaquetar (parcelize) en un Bundle
 * la clase implementa la interface Parcelable
 * y sobreescribe los metodos describeContents() y writeToParcel()
 *
 * La clase debe declarar un CREATOR de la siguiente manera:
 * companion object CREATOR : Parcelable.Creator<Credentials>
 * es decir, que implemente la interface Parcelable.Creator
 */

data class Credentials(
    override val username: String = "",
    override val password: String = ""
) : CredentialsInterface, Parcelable {

    private constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(username)
        dest.writeString(password)
    }

    companion object CREATOR : Parcelable.Creator<Credentials> {
        override fun createFromParcel(parcel: Parcel): Credentials {
            return Credentials(parcel)
        }

        override fun newArray(size: Int): Array<Credentials?> {
            return arrayOfNulls(size)
        }
    }
}
