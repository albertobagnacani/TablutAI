package model.state

import model.state.board.Board
import model.state.board.BoardCell
import model.state.player.Player

class NormalState : State {
    override val board: Board<BoardCell>
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override val player: Player
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
}