package ai.action

import aima.core.agent.Action
import aima.core.search.framework.problem.ActionsFunction
import model.state.NormalState
import model.state.State

// TODO1 factory?
class NormalTablutAction() : TablutAction() {
    override fun actions(s: Any?): Set<Action> {
        val state = s as NormalState
        val res = mutableSetOf<Action>() // Use CellWorldAction?

        return res
    }
}

/*
companion object {
    val MC = DynamicAction("MC") // equals to CM
    val MM = DynamicAction("MM")
    val CC = DynamicAction("CC")
    val M = DynamicAction("M")
    val C = DynamicAction("C")
}

override fun actions(state: Any): Set<Action> {
    var currState = state as MCState
    var otherState = state.otherSide()

    val result = HashSet<Action>()

    if (!currState.boat){
        val temp = otherState
        otherState = currState
        currState = temp
    }

    for (chosenMiss in 0 .. currState.nMiss){
        for (chosenCann in 0 .. currState.nCann){

            val future = currState.copy(nMiss = currState.nMiss - chosenMiss, nCann = currState.nCann - chosenCann, boat = !currState.boat)
            val otherFuture = otherState.copy(nMiss = otherState.nMiss + chosenMiss, nCann = otherState.nCann + chosenCann, boat = !otherState.boat)

            if (otherFuture.nMiss != 0){
                if ((future.nMiss >= future.nCann && otherFuture.nMiss >= otherFuture.nCann) || future.nMiss == 0)
                    when (Pair(chosenMiss, chosenCann)) {
                        Pair(0, 1) -> result.add(C)
                        Pair(1, 1) -> result.add(MC)
                        Pair(1, 0) -> result.add(M)
                        Pair(0, 2) -> result.add(CC)
                        Pair(2, 0) -> result.add(MM)
                    }
            }else if (future.nMiss >= future.nCann)
                when (Pair(chosenMiss, chosenCann)){
                    Pair(0, 1) -> result.add(C)
                    Pair(1, 1) -> result.add(MC)
                    Pair(1, 0) -> result.add(M)
                    Pair(0, 2) -> result.add(CC)
                    Pair(2, 0) -> result.add(MM)
                }

        }
    }

    return result
}
*/