package model.state

import model.state.board.NormalBoard
import model.state.board.NormalBoardCell
import model.state.player.NormalPlayer

class StandardStateFactory : StateFactory {
    override fun createFromGameVersion(version: String, boardTypePath: String, boardContentPath: String): State {
        return when(version){
            "Normal" -> NormalState(NormalBoard<NormalBoardCell>(9, 9, boardTypePath, boardContentPath), NormalPlayer.WHITE) // Il giocatore nello stato iniziale è il bianco
            // "Brandubh" -> BrandubhState()
            // ...
            else -> throw Exception("State not found")
        }
    }
}