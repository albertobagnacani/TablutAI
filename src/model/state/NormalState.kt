package model.state

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import model.state.board.*
import model.state.board.NormalCoordinate.Companion.getThirdCoordinateFromTwo
import model.state.player.NormalPlayer
import model.state.player.Player

//class NormalState(override val board: NormalBoard<NormalBoardCell>, @SerializedName("turn") @Expose override val player: NormalPlayer) : State{
class NormalState(override var board: NormalBoard<NormalBoardCell>, override var player: NormalPlayer) : State{
    //constructor() : this(NormalBoard(0, 0, "", ""), NormalPlayer.WHITE)
    override fun toString(): String {
        return "NormalState(board=$board, player=$player)"
    }
}