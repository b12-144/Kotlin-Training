package entities

data class EUser(
    val id: Long = 0,
    var name: String,
    var phone: String? = null,
    var address: String? = null
)
