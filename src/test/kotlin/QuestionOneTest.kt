import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.concurrent.ConcurrentHashMap

class QuestionOneTest {

    @Test
    fun `should create cow`() {
        val farm = FarmImpl()

        assertEquals(0, farm.getSize())

        farm.giveBirth("0", "1", "cow1")

        assertEquals(1, farm.getSize())

        farm.print()
    }

    @Test
    fun `should remove cow`() {
        val cows = ConcurrentHashMap<String, Cow>()
        cows["0"] = Cow("0", "cow0")
        val farm = FarmImpl(cows)

        assertEquals(1, farm.getSize())

        farm.endLifeSpan("0")

        assertEquals(0, farm.getSize())
    }

}