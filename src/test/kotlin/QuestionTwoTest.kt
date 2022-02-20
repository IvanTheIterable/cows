import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.util.concurrent.ConcurrentHashMap
import kotlin.test.assertNotNull

class QuestionTwoTest {

    @Test
    fun `should create cow`() {
        val farm = FarmWithoutCollectionsImpl()

        assertEquals(null, farm.lastCow.nextCow)

        farm.giveBirth("0", "1", "cow1")

        assertTrue(farm.lastCow.cow.cowId.equals("1"))
        assertTrue(farm.lastCow.cow.nickName.equals("cow1"))
        assertTrue(farm.lastCow.prevCow!!.nextCow!!.cow.cowId.equals("1"))
        assertTrue(farm.lastCow.prevCow!!.nextCow!!.cow.nickName.equals("cow1"))

        farm.print()
    }

    @Test
    fun `should remove cow from the middle`() {
        val farm = FarmWithoutCollectionsImpl()

        assertEquals(null, farm.lastCow.nextCow)

        farm.giveBirth("0", "1", "cow1")
        farm.giveBirth("0", "2", "cow2")
        farm.giveBirth("0", "3", "cow3")
        farm.giveBirth("0", "4", "cow4")
        farm.giveBirth("0", "5", "cow5")

        assertTrue(farm.lastCow.prevCow!!.prevCow!!.cow.cowId.equals("3"))

        farm.endLifeSpan("3")
        assertTrue(farm.lastCow.prevCow!!.prevCow!!.cow.cowId.equals("2"))

    }

    @Test
    fun `should remove cow from the end`() {
        val farm = FarmWithoutCollectionsImpl()

        assertEquals(null, farm.lastCow.nextCow)

        farm.giveBirth("0", "1", "cow1")
        farm.giveBirth("0", "2", "cow2")
        farm.giveBirth("0", "3", "cow3")
        farm.giveBirth("0", "4", "cow4")
        farm.giveBirth("0", "5", "cow5")

        assertTrue(farm.lastCow.cow.cowId.equals("5"))

        farm.endLifeSpan("5")
        assertTrue(farm.lastCow.cow.cowId.equals("4"))

    }

    @Test
    fun `should remove cow from the start`() {
        val farm = FarmWithoutCollectionsImpl()

        assertEquals(null, farm.lastCow.nextCow)

        farm.giveBirth("0", "1", "cow1")
        farm.giveBirth("0", "2", "cow2")
        farm.giveBirth("0", "3", "cow3")
        farm.giveBirth("0", "4", "cow4")
        farm.giveBirth("0", "5", "cow5")

        assertTrue(farm.lastCow.prevCow!!.prevCow!!.prevCow!!.prevCow!!.cow.cowId.equals("1"))

        farm.endLifeSpan("1")
        assertTrue(farm.lastCow.prevCow!!.prevCow!!.prevCow!!.prevCow!!.cow.cowId.equals("0"))

    }

}