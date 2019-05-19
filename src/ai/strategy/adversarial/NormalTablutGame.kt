package ai.strategy.adversarial

import ai.action.Action
import ai.action.NormalActionResolver
import ai.action.NormalTablutAction
import model.state.NormalState
import model.state.State
import model.state.player.NormalPlayer
import model.state.player.Player
import model.state.rules.NormalGameRules

class NormalTablutGame(val state: State, val initialState: NormalState, val gameRules: NormalGameRules, val actionResolver: NormalActionResolver) : TablutGame {
    /**
     * The initial state, which specifies how the game is set up at the start
     */
    override fun getInitialState(): State = initialState

    /**
     * The transition model, which defines the result of a move.
     */
    override fun getResult(state: State, action: Action): State {
        val res = actionResolver.applyAction(state as NormalState, action as NormalTablutAction)
        return res
    }

    /**
     * Defines which player has the move in a state.
     */
    override fun getPlayer(state: State): Player {
        val res = state.player
        return res
    }

    override fun getPlayers(): Array<Player> {
        val res = NormalPlayer.values() as Array<Player>
        return res
    }

    /**
     * Returns the set of legal moves in a state.
     */
    override fun getActions(state: State): List<Action> {
        val res = actionResolver.actions(state)
        return res
    }

    /**
     * A utility function (also called an objective function or
     * payoff function), defines the final numeric value for a game that ends in
     * terminal state s for a player p. In chess, the outcome is a win, loss, or
     * draw, with values +1, 0, or 1/2 . Some games have a wider variety of possible
     * outcomes; the payoffs in backgammon range from 0 to +192. A zero-sum game is
     * (confusingly) defined as one where the total payoff to all players is the
     * same for every instance of the game. Chess is zero-sum because every game has
     * payoff of either 0 + 1, 1 + 0 or 1/2 + 1/2 . "Constant-sum" would have been a
     * better term, but zero-sum is traditional and makes sense if you imagine each
     * player is charged an entry fee of 1/2.
     */
    // TODOg sistemare NormalGameRules(state).isTerminal()
    // TODO2 hardcoded utils
    // TODOg patta come hash dello stato
    override fun getUtility(state: State, player: Player): Double {
        var res: Double

        if(NormalGameRules(state as NormalState).isTerminal()){
            if(state.player == player) {
                res = 1.0
            }else{
                res = -1.0
            }
        }else{
            res = 0.5
        }
        return res
    }

    /**
     * A terminal test (as goal test as in the informed), which is true when the game is over
     * and false TERMINAL STATES otherwise.
     * States where the game has ended are called terminal states
     */
    // TODO1 why these cast? Everywhere
    override fun isTerminal(state: State): Boolean {
        val res = NormalGameRules(state as NormalState).isTerminal()

        return res
    }
}