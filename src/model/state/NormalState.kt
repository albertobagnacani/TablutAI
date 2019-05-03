package model.state

import model.state.board.*
import model.state.board.NormalCoordinate.Companion.getThirdCoordinateFromTwo
import model.state.player.NormalPlayer
import model.state.player.Player

class NormalState(override val board: NormalBoard<NormalBoardCell>, override val player: Player) : State{
}