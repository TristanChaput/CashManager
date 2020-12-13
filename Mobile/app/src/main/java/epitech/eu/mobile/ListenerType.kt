package epitech.eu.mobile

import android.widget.Button
import android.widget.TextView

sealed class ListenerType {
    class OnArticleClickListener(val position: Int) : ListenerType()
    object AddOnClickButtonListener : ListenerType()
    object UpdateBillOnClickButtonListener : ListenerType()
    object BackToArticlesOnClickButtonListener : ListenerType()
}
