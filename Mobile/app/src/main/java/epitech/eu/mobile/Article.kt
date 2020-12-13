package epitech.eu.mobile

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * The [Article] class represents products that users can buy.
 * Article implements Parcelable interface
 */
@Parcelize
data class Article(val id: String, val img: String, val name: String, val description: String, var prix: Double) : Parcelable
