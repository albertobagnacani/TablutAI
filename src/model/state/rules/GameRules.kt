package model.state.rules

interface GameRules {
    fun isTerminal(): Boolean
}