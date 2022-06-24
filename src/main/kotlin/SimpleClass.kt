import kotlin.random.Random

class SimpleClass {

    companion object {
        const val size = 100
        const val four = 4
    }
    fun random() = List(size) { Random.nextInt(0, size) }
}
