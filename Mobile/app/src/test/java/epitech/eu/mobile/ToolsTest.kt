package epitech.eu.mobile

import org.junit.Assert.assertEquals
import org.junit.Test

class ToolsTest {

    private val anArticle = Article("", "", "", "", 0.00)
    private val cartListTest = ArrayList<Article>()

    @Test
    fun `round in euro`() {
        assertEquals("0,99€", Tools.roundInEuro(0.99))
    }

    @Test
    fun `compute bill when no item in cart`() {
        assertEquals("0€", Tools.computeBill(cartListTest))
    }

    @Test
    fun `compute bill when items in cart`() {
        anArticle.prix = 10.99
        cartListTest.add(anArticle)
        assertEquals("10,99€", Tools.computeBill(cartListTest))
    }

    @Test
    fun `count an item when it's not in cart`() {
        assertEquals("0 item", Tools.countItemInCart(cartListTest, anArticle))
    }

    @Test
    fun `count an item in cart`() {
        cartListTest.add(anArticle)
        assertEquals("1 item", Tools.countItemInCart(cartListTest, anArticle))
    }

    @Test
    fun `count items in cart`() {
        cartListTest.add(anArticle)
        cartListTest.add(anArticle)
        assertEquals("2 items", Tools.countItemInCart(cartListTest, anArticle))
    }
}