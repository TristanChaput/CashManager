package epitech.eu.mobile

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Article(val id: String, val img: String, val name: String, val description: String, val prix: Double) : Parcelable
