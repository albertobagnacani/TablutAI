package model.state

class StandardStateFactory : StateFactory {
    override fun createFromGameVersion(version: String): State {
        return when(version){
            "Normal" -> NormalState()
            // "Brandubh" -> BrandubhState()
            // ...
            else -> throw Exception("State not found")
        }
    }
}