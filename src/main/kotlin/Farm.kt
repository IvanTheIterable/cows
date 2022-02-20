import java.util.concurrent.ConcurrentHashMap

interface Farm {

    fun giveBirth(parentCowId: String, childCowId: String, childNickName: String): Cow?

    fun endLifeSpan(cowId: String): Cow?

}

class FarmImpl(
    private val cows: ConcurrentHashMap<String, Cow> = ConcurrentHashMap()
) : Farm {

    fun getSize() = cows.size

    override fun giveBirth(parentCowId: String, childCowId: String, childNickName: String): Cow? =
        cows.put(childCowId, Cow(childCowId, childNickName))

    override fun endLifeSpan(cowId: String): Cow? =
        cows.remove(cowId)
}

class FarmWithoutCollectionsImpl : Farm {

    var lastCow = LinkedCow(Cow("0", "cow0"), null, null)

    override fun giveBirth(parentCowId: String, childCowId: String, childNickName: String): Cow {
        val newCow = Cow(childCowId, childNickName)

        lastCow.nextCow = LinkedCow(newCow, lastCow, null)

        lastCow = lastCow.nextCow!!

        return newCow
    }

    override fun endLifeSpan(cowId: String): Cow {
        if (cowId == lastCow.cow.cowId) {
            if (lastCow.prevCow != null) {
                lastCow = lastCow.prevCow!!
            }
            return lastCow.cow
        }

        var current = lastCow

        while (current.prevCow != null) {
            if (current.cow.cowId == cowId) {
                current.prevCow!!.nextCow = current.nextCow
                current.nextCow?.let {
                    it.prevCow = current.prevCow
                }
                break
            }
            current = current.prevCow!!
        }

        return current.cow

    }

}