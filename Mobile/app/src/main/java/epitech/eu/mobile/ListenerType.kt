package epitech.eu.mobile

import android.widget.Button
import android.widget.TextView

sealed class ListenerType {
    class OnArticleClickListener(val position: Int) : ListenerType()
    object AddOnClickButtonListener : ListenerType()
    object BackToArticlesOnClickButtonListener : ListenerType()
    object CartOnClickButtonListener: ListenerType()
    object PaymentOnClickButtonListener: ListenerType()
}
