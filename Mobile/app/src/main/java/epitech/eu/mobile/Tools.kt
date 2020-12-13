package epitech.eu.mobile

import java.math.RoundingMode
import java.text.DecimalFormat

class Tools {
    companion object {
        const val INTENT_PARCELABLE = "OBJECT_INTENT"
        const val ARRAY_INTENT_PARCELABLE = "ARRAY_INTENT"

        fun roundInEuro(nbDouble: Double): String {
            val df = DecimalFormat("#.##")
            df.roundingMode = RoundingMode.CEILING
            return df.format(nbDouble).plus("€")
        }

    }
}