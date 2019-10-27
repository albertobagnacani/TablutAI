import ai.action.NormalActionResolver
import ai.heuristic.BlackNormalTablutHeuristic
import ai.heuristic.WhiteNormalTablutHeuristic
import ai.strategy.adversarial.NormalTablutGame
import ai.strategy.adversarial.TablutIterativeDeepeningAlphaBetaSearch
import client.TablutClient
import model.state.NormalState
import model.state.StandardStateFactory
import model.state.player.NormalPlayer
import model.state.rules.NormalGameRules
import model.state.rules.StandardGameRulesFactory

fun main(args : Array<String>) {
    val boardTypePath = "resources/normalBoardType.txt"
    val boardContentPath = "resources/normalBoardContent.txt"
    val gameVersion = "Normal"

    val player = if(args[0] == "White") {NormalPlayer.WHITE} else {NormalPlayer.BLACK}
    val seconds = args[1].toInt()-2

    val heuristic = if(player == NormalPlayer.WHITE) WhiteNormalTablutHeuristic() else BlackNormalTablutHeuristic()
    val initialState = StandardStateFactory().createFromGameVersion(gameVersion, boardTypePath, boardContentPath)
    val client = TablutClient(player, "Franco", StandardStateFactory().createFromGameVersion(gameVersion, boardTypePath, boardContentPath) as NormalState)

    client.declareName()
    client.read() // Initial state

    if (player == NormalPlayer.BLACK) { // Black's turn
        client.read()
    }

    val utilMin = -1.0
    val utilMax = 1.0

    while(true) {
        // eval action
        val game = NormalTablutGame(client.state, initialState as NormalState, StandardGameRulesFactory().createFromGameVersion(gameVersion, client.state) as NormalGameRules, NormalActionResolver())
        val search = TablutIterativeDeepeningAlphaBetaSearch(game, utilMin, utilMax, seconds, heuristic)
        //var search = IterativeDeepeningAlphaBetaSearch(game, utilMin, utilMax, seconds)
        val action = search.makeDecision(client.state)
        //println(action)
        //val metrics = search.metrics
        //println(metrics)
        client.write(action)
        client.read() // Read what my action did
        client.read() // Read the other action
    }
}