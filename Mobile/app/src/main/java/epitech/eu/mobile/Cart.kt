package epitech.eu.mobile

class Cart: ArrayList<Article>() {

    fun computeBill(): String {
        var bill = 0.00
        if (!this.isNullOrEmpty())
            for (anArticle in this)
                bill += anArticle.prix
        return Tools.roundInEuro(bill)
    }

    fun countItemInCart(currentArticle: Article): String{
        var countItem = 0
        if (!this.isNullOrEmpty())
            for (anArticle in this)
                if (currentArticle.id == anArticle.id)
                    countItem++
        return if (countItem > 1) "$countItem items" else "$countItem item"
    }
}