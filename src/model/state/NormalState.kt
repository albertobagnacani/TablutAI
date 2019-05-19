package model.state

import model.state.board.NormalBoard
import model.state.board.NormalBoardCell
import model.state.player.NormalPlayer

//class NormalState(override val board: NormalBoard<NormalBoardCell>, @SerializedName("turn") @Expose override val player: NormalPlayer) : State{
data class NormalState(override var board: NormalBoard<NormalBoardCell>, override var player: NormalPlayer) : State{
    //constructor() : this(NormalBoard(0, 0, "", ""), NormalPlayer.WHITE)
    override fun toString(): String {
        return "NormalState(board=\n$board, player=$player)"
    }
}