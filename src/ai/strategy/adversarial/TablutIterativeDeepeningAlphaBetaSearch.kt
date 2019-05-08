package ai.strategy.adversarial

import ai.action.Action
import ai.heuristic.Heuristic
import ai.heuristic.NormalTablutHeuristic
import aima.core.search.adversarial.IterativeDeepeningAlphaBetaSearch
import aima.core.search.framework.Metrics
import model.state.State
import model.state.player.Player

class TablutIterativeDeepeningAlphaBetaSearch(val game: NormalTablutGame, val utilMin: Double, val utilMax: Double, val time: Int, val heuristc: NormalTablutHeuristic) : IterativeDeepeningAlphaBetaSearch<State, Action, Player>(game, utilMin, utilMax, time) {
    // createFor?
    /**
     * Primitive operation, which estimates the value for (not necessarily terminal) states.
     * This implementation returns the utility value for terminal states and (utilMin + utilMax) / 2 for non-terminal states.
     * When overriding, first call the super implementation!
     */
    override fun eval(state: State, player: Player): Double {
        super.eval(state, player) // MUST CALL super
        return heuristc.getValue(state, player)
    }

    /**
     * Returns some statistic data from the last search.
     */
    override fun getMetrics(): Metrics {
        return super.getMetrics()
    }

    /**
     * Primitive operation which is used to stop iterative deepening search in situations where a safe winner has been identified.
     * This implementation returns true if the given value (for the currently preferred action result) is the highest or
     * lowest utility value possible.
     */
    override fun hasSafeWinner(resultUtility: Double): Boolean {
        return super.hasSafeWinner(resultUtility)
    }

    /**
     * Primitive operation which is called at the beginning of one depth limited search step.
     * This implementation increments the current depth limit by one.
     */
    override fun incrementDepthLimit() {
        super.incrementDepthLimit()
    }

    /**
     * Primitive operation which is used to stop iterative deepening search in situations where a clear best action exists.
     * This implementation returns always false.
     */
    override fun isSignificantlyBetter(newUtility: Double, utility: Double): Boolean {
        return super.isSignificantlyBetter(newUtility, utility)
    }

    /**
     * Template method controlling the search. It is based on iterative deepening and tries to make to a good decision in limited time.
     * Credit goes to Behi Monsio who had the idea of ordering actions by utility in subsequent depth-limited search runs.
     */
    override fun makeDecision(state: State): Action {
        return super.makeDecision(state)
    }

    /**
     * Primitive operation for action ordering. This implementation preserves the original order (provided by the game).
     */
    override fun orderActions(state: State, actions: MutableList<Action>, player: Player, depth: Int): MutableList<Action> {
        return super.orderActions(state, actions, player, depth)
    }
}