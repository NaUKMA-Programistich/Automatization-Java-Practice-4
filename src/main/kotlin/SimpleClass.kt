import kotlin.random.Random

class SimpleClass {

    val size = 100

    fun four() = 4

    fun random() = List(100) { Random.nextInt(0, size) }
}
