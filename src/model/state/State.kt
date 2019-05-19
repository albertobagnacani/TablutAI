package model.state

import model.state.board.Board
import model.state.board.BoardCell
import model.state.player.Player
import java.io.Serializable

interface State : Serializable {
    val board: Board<BoardCell>
    val player: Player

    //fun isTerminal(): Boolean
}