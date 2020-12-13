package epitech.eu.mobile

/**
 * Interface about listener on products
 */
interface ArticleListener {
    /**
     * Used to call a [ListenerType] that execute the function corresponding to a specific event.
     */
    fun articleEvent(clicked: ListenerType)
}