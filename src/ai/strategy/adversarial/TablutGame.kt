package ai.strategy.adversarial

import ai.action.Action
import ai.action.TablutAction
import model.state.player.NormalPlayer
import model.state.player.Player
import aima.core.search.adversarial.Game
import model.state.State

interface TablutGame : Game<State, Action, Player>{
}