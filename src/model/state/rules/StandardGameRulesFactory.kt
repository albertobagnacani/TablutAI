package model.state.rules

import model.state.NormalState
import model.state.State

class StandardGameRulesFactory : GameRulesFactory {
    override fun createFromGameVersion(version: String, s: State): GameRules {
        return when(version){
            // TODO1 why this cast?
            "Normal" -> NormalGameRules(s as NormalState)
            // "Brandubh" -> BrandubhGameRules()
            // ...
            else -> throw Exception("Game rules not found")
        }
    }
}