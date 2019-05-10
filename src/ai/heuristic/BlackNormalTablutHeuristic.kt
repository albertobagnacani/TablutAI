package ai.heuristic

import model.state.NormalState
import model.state.State
import model.state.board.NormalCoordinate
import model.state.player.NormalPlayer
import model.state.player.Player

class BlackNormalTablutHeuristic : NormalTablutHeuristic {
    override fun getValue(state: State, player: Player): Double{ // Numero di pedine mancanti allo stato attuale per catturare re
        return numberOfPawnsToCaptureKing(state as NormalState, player as NormalPlayer)
    }

    fun numberOfPawnsToCaptureKing(state: NormalState, player: NormalPlayer): Double{
        val surrounding = state.board.getBlackBoardCellAdjKing().size
        var res = 0.0

        if(state.board.getKingBoardCell().coordinate.equals(NormalCoordinate.getMiddleCoordinate())){
            when(surrounding){
                4 -> res = 1.0
                3 -> res = 0.5
                2 -> res = 0.0
                1 -> res = -0.5
                0 -> res = -1.0
            }
        }else if(NormalCoordinate(NormalCoordinate.getMiddleCoordinate()).adjCoordinates().contains(state.board.getKingBoardCell().coordinate)){
            when(surrounding){
                3 -> res = 1.0
                2 -> res = (1.toDouble()/3.toDouble())
                1 -> res = -(1.toDouble()/3.toDouble())
                0 -> res = -1.0
            }
        }else{
            when(surrounding){
                2 -> res = 1.0
                1 -> res = 0.0
                0 -> res = -1.0
            }
        }

        return res
    }
}