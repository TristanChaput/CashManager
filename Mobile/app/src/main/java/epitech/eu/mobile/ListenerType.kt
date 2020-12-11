package epitech.eu.mobile

import android.widget.Button

sealed class ListenerType{
    class OnArticleClickListener(val position: Int): ListenerType()
    class AddOnClickButtonListener(val button: Button): ListenerType()
}
