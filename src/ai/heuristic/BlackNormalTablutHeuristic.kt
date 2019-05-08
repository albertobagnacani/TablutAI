package ai.heuristic

import model.state.NormalState
import model.state.State
import model.state.player.NormalPlayer
import model.state.player.Player

class BlackNormalTablutHeuristic : NormalTablutHeuristic {
    override fun getValue(state: State, player: Player): Double{ // Numero di pedine mancanti allo stato attuale per catturare re
        return numberOfPawnsToCaptureKing(state as NormalState, player as NormalPlayer)
    }

    fun numberOfPawnsToCaptureKing(state: NormalState, player: NormalPlayer): Double{
        TODO()
    }
}