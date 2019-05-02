package model.state.rules

interface GameRulesFactory {
    // TODO1 use enum insted of String?
    fun createFromGameVersion(version: String): GameRules
}