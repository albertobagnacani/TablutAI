package model.state.rules

import model.state.State

interface GameRulesFactory {
    // TODO1 use enum insted of String?
    fun createFromGameVersion(version: String, s: State): GameRules
}