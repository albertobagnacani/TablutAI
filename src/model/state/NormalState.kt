package model.state

import model.state.board.Board
import model.state.board.BoardCell
import model.state.board.NormalBoard
import model.state.board.NormalBoardCell
import model.state.player.NormalPlayer
import model.state.player.Player

class NormalState(override val board: Board<BoardCell>, override val player: Player) : State{
}