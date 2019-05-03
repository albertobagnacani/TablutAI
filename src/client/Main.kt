package client

import ai.strategy.adversarial.NormalTablutGame
import ai.strategy.adversarial.TablutGame
import aima.core.search.adversarial.IterativeDeepeningAlphaBetaSearch
import model.state.NormalState
import model.state.StandardStateFactory
import model.state.StateFactory
import model.state.board.*
import model.state.player.NormalPlayer
import model.state.player.Player
import model.state.rules.GameRulesFactory
import model.state.rules.StandardGameRulesFactory

fun main(args : Array<String>) {
    val boardTypePath = "src/resources/normalBoardType.txt"
    val gameVersion = "Normal"
    /**
     * The utility's minimum
     */
    val utilMin = -1.0
    /**
     * The utility's maximum
     */
    val utilMax = 1.0
    /**
     * Time used by IterativeDeepeningAlphaBetaSearch
     */
    val seconds = 45
    val state = StandardStateFactory().createFromGameVersion(gameVersion, boardTypePath)
    val game = NormalTablutGame(state, StandardGameRulesFactory().createFromGameVersion(gameVersion, state))

    val search = IterativeDeepeningAlphaBetaSearch(game, utilMin, utilMax, seconds)

    /*
    val nb = NormalBoard<NormalBoardCell>(9, 9, boardTypePath)
    nb.printBoard(1)
    */

    /*
    // TODO
    Actions to do for every interaction with the server
    Loop
    - Receive from server
    - Create state from json
    - Elaborate new state
    - Create json
    - Send to server
     */
    /*
    Piro
    1) Connetti al server attraverso socket (bianco: ServerSocket(5800), nero ServerSocket(5801))
    2) Dichiarare nome in formato stringa (es: "Franco")
    3) Inizia partita
    3.1) Server scrive in formato JSON lo stato della partita e turno (bianco/nero/bianco vince/nero vince)
    Se mio turno: leggo stato e scrivo mossa (e leggo stato per vedere risultato mossa)
    Se turno avversario: leggo stato e aspetto di leggerne un altro nel mio turno

    Classe: tablutClient che implementa già tutto, estendendola è già tutto pronto (posso chiamare write/read)
     */
}