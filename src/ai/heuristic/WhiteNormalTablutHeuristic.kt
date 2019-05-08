package ai.heuristic

import model.state.NormalState
import model.state.State
import model.state.board.NormalBoardCell
import model.state.player.NormalPlayer
import model.state.player.Player
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.sqrt

class WhiteNormalTablutHeuristic : NormalTablutHeuristic {
    override fun getValue(state: State, player: Player): Double { // Distanza dalle azzurre
        return distanceFromNearestExit(state as NormalState, player as NormalPlayer)
    }

    fun distanceFromNearestExit(state: NormalState, player: NormalPlayer): Double{
        var min = 20.0 // TODO1 brutto
        var res = 0.0

        for(exit in state.board.getExitBoardCells()){
            var dist = calculateDist(state.board.getKingBoardCell(), exit)
            if(dist<min){
                min = dist
            }
        }

        when(min.roundToInt()){
            5 -> res = -1.0
            4 -> res = -0.6
            3 -> res = 0.2
            2 -> res = 0.6
            1 -> res = 1.0
        }

        return res
    }

    fun calculateDist(king: NormalBoardCell, exit: NormalBoardCell): Double{
        return sqrt((king.coordinate.x-exit.coordinate.x).toDouble().pow(2)+(king.coordinate.y-exit.coordinate.y).toDouble().pow(2))
    }
}