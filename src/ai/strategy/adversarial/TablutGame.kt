package ai.strategy.adversarial

import ai.action.Action
import aima.core.search.adversarial.Game
import model.state.State
import model.state.player.Player

interface TablutGame : Game<State, Action, Player>{
}