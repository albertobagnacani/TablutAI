package model.state.rules

class StandardGameRulesFactory : GameRulesFactory {
    override fun createFromGameVersion(version: String): GameRules {
        return when(version){
            "Normal" -> NormalGameRules()
            // "Brandubh" -> BrandubhGameRules()
            // ...
            else -> throw Exception("Game rules not found")
        }
    }
}