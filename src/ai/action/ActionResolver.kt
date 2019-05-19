package ai.action

import model.state.State

interface ActionResolver {
    fun actions(s: State): List<Action>
    fun applyAction(state: State, a: Action): State
}