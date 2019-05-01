package ai.adversarial

import model.state.player.NormalPlayer
import model.state.player.Player
import aima.core.agent.Action
import aima.core.agent.State
import aima.core.search.adversarial.Game

class TablutGame : Game<State, Action, Player>{
    override fun getUtility(p0: State?, p1: Player?): Double {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getInitialState(): State {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getResult(p0: State?, p1: Action?): State {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPlayer(p0: State?): NormalPlayer {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPlayers(): Array<NormalPlayer> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getActions(p0: State?): MutableList<Action> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isTerminal(p0: State?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}