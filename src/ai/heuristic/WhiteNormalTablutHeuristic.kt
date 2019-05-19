package ai.heuristic

import model.state.NormalState
import model.state.State
import model.state.board.CellContent
import model.state.board.NormalBoardCell
import model.state.player.NormalPlayer
import model.state.player.Player
import kotlin.math.floor
import kotlin.math.pow
import kotlin.math.sqrt

class WhiteNormalTablutHeuristic : NormalTablutHeuristic {

    override fun getValue(state: State, player: Player): Double { // Distanza dalle azzurre
        return distanceFromNearestExit(state as NormalState, player as NormalPlayer)
    }

    fun distanceFromNearestExit(state: NormalState, player: NormalPlayer): Double{
        var min = 20.0
        var res = 0.0
        var dist: Double

        val exits = state.board.getExitBoardCells()
        for(exit in exits) {
            if (exit.content == CellContent.NOTHING) {
                dist = calculateDist(state.board.getKingBoardCell(), exit)
                if (dist < min) {
                    min = dist
                }
            }
        }

        val m = floor(min).toInt()
        when(m){
            4 -> res = -0.9
            3 -> res = -0.5
            2 -> res = 0.0
            1 -> res = 0.5
            0 -> res = 0.9
        }

        // if (res != -0.9)
        //    println(""+res+"\n"+state.board)

        return res
    }

    fun calculateDist(king: NormalBoardCell, exit: NormalBoardCell): Double{
        return sqrt((king.coordinate.x-exit.coordinate.x).toDouble().pow(2)+(king.coordinate.y-exit.coordinate.y).toDouble().pow(2))
    }
}