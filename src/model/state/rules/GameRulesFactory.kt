package model.state.rules

import model.state.State

interface GameRulesFactory {
    fun createFromGameVersion(version: String, s: State): GameRules
}