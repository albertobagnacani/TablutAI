package model.state

import model.state.board.Board
import model.state.board.BoardCell
import model.state.player.Player

interface State{
    val board: Board<BoardCell>
    val player: Player
}