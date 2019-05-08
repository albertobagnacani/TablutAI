import ai.action.ActionResolver
import ai.action.NormalActionResolver
import ai.action.NormalTablutAction
import ai.heuristic.BlackNormalTablutHeuristic
import ai.heuristic.WhiteNormalTablutHeuristic
import ai.strategy.adversarial.NormalTablutGame
import ai.strategy.adversarial.TablutGame
import ai.strategy.adversarial.TablutIterativeDeepeningAlphaBetaSearch
import aima.core.search.adversarial.IterativeDeepeningAlphaBetaSearch
import aima.core.search.framework.SearchAgent
import aima.core.search.framework.problem.Problem
import client.TablutClient
import model.state.NormalState
import model.state.StandardStateFactory
import model.state.StateFactory
import model.state.board.*
import model.state.player.NormalPlayer
import model.state.player.Player
import model.state.rules.GameRulesFactory
import model.state.rules.NormalGameRules
import model.state.rules.StandardGameRulesFactory

// TODO1 move functions to interfaces
fun main(args : Array<String>) {
    val boardTypePath = "src/resources/normalBoardType.txt"
    val boardContentPath = "src/resources/normalBoardContent.txt"
    val gameVersion = "Normal"

    val player = if(args[0] == "White") {NormalPlayer.WHITE} else {NormalPlayer.BLACK}
    val seconds = args[1].toInt()-10

    val heuristic = if(player == NormalPlayer.WHITE) WhiteNormalTablutHeuristic() else BlackNormalTablutHeuristic()
    val initialState = StandardStateFactory().createFromGameVersion(gameVersion, boardTypePath, boardContentPath)
    val client = TablutClient(player, "Franco", StandardStateFactory().createFromGameVersion(gameVersion, boardTypePath, boardContentPath))

    client.declareName()
    client.read() // Initial state

    if (player == NormalPlayer.BLACK) { // Black's turn
        client.read()
    }

    val utilMin = -1.0
    val utilMax = 1.0

    while(true) {
        // eval action
        var game = NormalTablutGame(client.state, initialState as NormalState, StandardGameRulesFactory().createFromGameVersion(gameVersion, client.state) as NormalGameRules, NormalActionResolver())
        //var search = TablutIterativeDeepeningAlphaBetaSearch(game, utilMin, utilMax, seconds, heuristic)
        var search = IterativeDeepeningAlphaBetaSearch(game, utilMin, utilMax, seconds)
        var action = search.makeDecision(client.state) // TODO + perchè non genera bene l'albero? Goal test errato? Mosse errate?
        val metrics = search.metrics
        println(metrics)
        client.write(action)
        client.read() // Read what my action did
        client.read()
    }
}