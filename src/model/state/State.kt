package model.state

import model.state.board.Board
import model.state.board.BoardCell
import model.state.player.Player
import aima.core.agent.State
import model.state.board.NormalBoard
import model.state.board.NormalBoardCell

interface State : State{
    val board: Board<BoardCell>
    val player: Player

    fun isTerminal(): Boolean
}