package ai.action

import aima.core.agent.Action
import aima.core.search.framework.problem.ActionsFunction
import model.state.NormalState
import model.state.State
import model.state.board.BoardCell
import model.state.board.CellContent
import model.state.board.CellType
import model.state.board.NormalBoardCell

// TODO1 factory?
/*
    {
        "from":"e4",
        "to":"f4",
        "turn":"WHITE"
    }
    // e4: column e (4), row 4
    // f4: column f (5), row 4
*/
data class NormalTablutAction(val from: String, val to: String, val turn: String) : TablutAction {
}