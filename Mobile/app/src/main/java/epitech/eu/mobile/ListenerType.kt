package epitech.eu.mobile

/**
 * Contains different type of listeners for multiple type of buttons
 */
sealed class ListenerType {
    class OnArticleClickListener(val position: Int) : ListenerType()
    object AddOnClickButtonListener : ListenerType()
    object BackToArticlesOnClickButtonListener : ListenerType()
    object CartOnClickButtonListener: ListenerType()
    object PaymentOnClickButtonListener: ListenerType()
}
