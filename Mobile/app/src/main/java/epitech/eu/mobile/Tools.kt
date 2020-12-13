package epitech.eu.mobile

import java.math.RoundingMode
import java.text.DecimalFormat

class Tools {
    companion object {
        const val INTENT_PARCELABLE = "OBJECT_INTENT"
        const val ARRAY_INTENT_PARCELABLE = "ARRAY_INTENT"

        private fun roundInEuro(nbDouble: Double): String {
            val df = DecimalFormat("#.##")
            df.roundingMode = RoundingMode.CEILING
            return df.format(nbDouble).plus("€")
        }

        fun computeBill(cartList: ArrayList<Article>): String {
            var bill = 0.00
            if (!cartList.isNullOrEmpty())
                for (anArticle in cartList)
                    bill += anArticle.prix
            return roundInEuro(bill)
        }

        fun countItemInCart(cartList: ArrayList<Article>, currentArticle: Article): String{
            var countItem = 0
            if (!cartList.isNullOrEmpty())
                for (anArticle in cartList)
                    if (currentArticle.id == anArticle.id)
                        countItem++
            return if (countItem > 1) "$countItem items" else "$countItem item"
        }

    }
}