package epitech.eu.mobile

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Article(val img: Int, val name: String, val description: String, val prix: Double, var nb: Int) : Parcelable {
}

/*
            val intent = Intent(this, Articles::class.java)
            startActivity(intent)
 */
