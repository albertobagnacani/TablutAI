package model.state

import ai.action.Action
import ai.action.NormalTablutAction
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import model.state.board.*
import model.state.board.NormalCoordinate.Companion.getThirdCoordinateFromTwo
import model.state.player.NormalPlayer
import model.state.player.Player

//class NormalState(override val board: NormalBoard<NormalBoardCell>, @SerializedName("turn") @Expose override val player: NormalPlayer) : State{
data class NormalState(override var board: NormalBoard<NormalBoardCell>, override var player: NormalPlayer) : State{
    //constructor() : this(NormalBoard(0, 0, "", ""), NormalPlayer.WHITE)
    override fun toString(): String {
        return "NormalState(board=$board, player=$player)"
    }

    fun applyAction(a: NormalTablutAction): State{
        var state = this.copy()
        val fromCoord = NormalCoordinate().fromCell(a.from)
        val toCoord = NormalCoordinate().fromCell(a.to)

        var oldCell = state.board.getElement(fromCoord)
        var tmp = oldCell.content
        oldCell.content = CellContent.NOTHING
        var newCell = state.board.getElement(toCoord)
        newCell.content = tmp

        //state.board.setElement(fromCoord, oldCell) // Empty the first
        //state.board.setElement(toCoord, newCell) // Fill the second
        return state
    }
}