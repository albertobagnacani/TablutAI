package ai.state.rules

interface GameRulesFactory {
    // TODO use enum insted of String?
    fun createFromGameVersion(version: String): GameRules
}