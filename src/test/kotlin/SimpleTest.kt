import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SimpleTest {

    private lateinit var simpleClass: SimpleClass

    @BeforeTest
    fun setup() {
        simpleClass = SimpleClass()
    }

    @Test
    fun `Four test`() {
        assertEquals(4, SimpleClass.Companion.four)
    }

    @Test
    fun `Random Test`() {
        simpleClass.random().forEach {
            assertTrue(it <= SimpleClass.Companion.size)
        }
    }
}
