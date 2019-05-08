package ai.heuristic

import model.state.State
import model.state.player.Player

interface NormalTablutHeuristic : TablutHeuristic {
    fun getValue(state: State, player: Player): Double
}