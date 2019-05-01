package client

import ai.adversarial.TablutGame
import aima.core.search.adversarial.IterativeDeepeningAlphaBetaSearch

/**
 * The main, communicating with the server.
 */
fun main(args : Array<String>) {
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
    val game = TablutGame()

    val search = IterativeDeepeningAlphaBetaSearch(game, utilMin, utilMax, seconds)

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
}