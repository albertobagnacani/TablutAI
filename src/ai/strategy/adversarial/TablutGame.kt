package ai.strategy.adversarial

import model.state.player.NormalPlayer
import model.state.player.Player
import aima.core.agent.Action
import aima.core.search.adversarial.Game
import model.state.State

interface TablutGame : Game<State, Action, Player>{
}