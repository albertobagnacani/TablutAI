package ai.state

import ai.state.board.Board
import ai.state.board.BoardCell
import ai.state.player.Player

interface State{
    val board: Board<BoardCell>
    val player: Player
}