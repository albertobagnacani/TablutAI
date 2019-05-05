package model.state

import model.state.board.Board
import model.state.board.BoardCell
import model.state.player.Player
import aima.core.agent.State
import com.google.gson.annotations.SerializedName
import model.state.board.NormalBoard
import model.state.board.NormalBoardCell
import java.io.Serializable

interface State : Serializable {
    val board: Board<BoardCell>
    val player: Player

    //fun isTerminal(): Boolean
}