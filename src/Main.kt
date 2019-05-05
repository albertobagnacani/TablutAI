import ai.action.NormalTablutAction
import ai.strategy.adversarial.NormalTablutGame
import ai.strategy.adversarial.TablutGame
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
import model.state.rules.StandardGameRulesFactory

fun main(args : Array<String>) {
    val boardTypePath = "src/resources/normalBoardType.txt"
    val boardContentPath = "src/resources/normalBoardContent.txt"
    val gameVersion = "Normal"

    val player = if(args[0] == "White") {NormalPlayer.WHITE} else {NormalPlayer.BLACK}
    val seconds = args[1].toInt()-10

    val initialState = StandardStateFactory().createFromGameVersion(gameVersion, boardTypePath, boardContentPath)
    val client = TablutClient(player, "Franco", initialState)

    client.declareName()
    client.read() // Initial state

    if (player == NormalPlayer.BLACK) { // Black's turn
        client.read()
    }

    val utilMin = -1.0
    val utilMax = 1.0

    while(true) {
        // eval action
        var game = NormalTablutGame(client.state, initialState as NormalState, StandardGameRulesFactory().createFromGameVersion(gameVersion, client.state), NormalTablutAction())
        var search = IterativeDeepeningAlphaBetaSearch(game, utilMin, utilMax, seconds)
        var action = search.makeDecision(client.state)
        client.write(action)
        client.read() // Read what my action did
        client.read()
    }

    /*
    val game = NormalTablutGame(state, StandardGameRulesFactory().createFromGameVersion(gameVersion, state), NormalTablutAction())
    //val problem = Problem(initState, MCActionsFunction(), MCResultFunction(), MCGoalTest())

    val current = System.currentTimeMillis()

    val utilMin = -1.0
    val utilMax = 1.0
    val search = IterativeDeepeningAlphaBetaSearch(game, utilMin, utilMax, seconds)
    val action = search.makeDecision(state)
    //val agent = SearchAgent(problem, search)
    //println(agent.actions)
    //println(agent.instrumentation)
    println("ms: ${System.currentTimeMillis() - current}")
    */
}