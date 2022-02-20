data class Cow(val cowId: String, val nickName: String)

data class LinkedCow(val cow: Cow, var prevCow: LinkedCow?, var nextCow: LinkedCow?)