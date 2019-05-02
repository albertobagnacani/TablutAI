package model.state

import model.state.board.NormalBoard
import model.state.board.NormalBoardCell
import model.state.player.NormalPlayer

class StandardStateFactory : StateFactory {
    override fun createFromGameVersion(version: String, boardTypePath: String): State {
        return when(version){
            "Normal" -> NormalState(NormalBoard<NormalBoardCell>(9, 9, boardTypePath), NormalPlayer.WHITE)
            // "Brandubh" -> BrandubhState()
            // ...
            else -> throw Exception("State not found")
        }
    }

    // TODO remove?
    override fun createInitialState(): State {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}