package ai.action

import model.state.NormalState
import model.state.State
import model.state.board.CellContent
import model.state.board.CellType
import model.state.board.NormalBoardCell
import model.state.board.NormalCoordinate
import model.state.player.NormalPlayer

class NormalActionResolver : ActionResolver {
    override fun applyAction(state: State, a: Action): State{
        val s = NormalState((state as NormalState).board.copy(), state.player)
        val fromCoord = NormalCoordinate().fromCell((a as NormalTablutAction).from)
        val toCoord = NormalCoordinate().fromCell(a.to)

        var oldCell = s.board.getElement(fromCoord)
        var tmp = oldCell.content
        oldCell.content = CellContent.NOTHING
        var newCell = s.board.getElement(toCoord)
        newCell.content = tmp

        s.player = switchPlayer(s.player)

        //state.board.setElement(fromCoord, oldCell) // Empty the first
        //state.board.setElement(toCoord, newCell) // Fill the second
        return s
    }

    fun switchPlayer(player: NormalPlayer): NormalPlayer{
        if(player == NormalPlayer.WHITE) {
            return NormalPlayer.BLACK
        }
        else{
            return NormalPlayer.WHITE
        }
    }

    override fun actions(s: State): List<Action> {
        val state = s as NormalState
        var res = mutableListOf<Action>()

        for(i in 0..s.board.rows-1){
            for(j in 0..s.board.cols-1){
                if(moveablePawn(state.board.board[i][j], state.player)) {
                //if(state.board.board[i][j].content.name == s.player.name){
                    //val bc = state.board[2,3]
                    var actions = availableActions(state, state.board.board[i][j])
                    res.addAll(actions)
                }
            }
        }

        return res
    }

    fun moveablePawn(c: NormalBoardCell, p: NormalPlayer): Boolean{
        if(p == NormalPlayer.WHITE){
            if(c.content == CellContent.WHITE || c.content == CellContent.KING){
                return true
            }
        }else{
            if(c.content == CellContent.BLACK){
                return true
            }
        }
        return false
    }

    // For every direction (up, right, down, left), returns the number of cells a pawn can go
    fun availableActions(state: NormalState, bc: NormalBoardCell): List<Action>{
        var res = mutableListOf<Action>()

        res.addAll(upActions(state, bc)) // TODO2 can do a matrix ([0=up, 1=right, ...][<actions>])
        res.addAll(rightActions(state, bc))
        res.addAll(downActions(state, bc))
        res.addAll(leftActions(state, bc))

        return res
    }

    // TODO1 the logic is the same -> unify and write a better code
    fun upActions(state: NormalState, bc: NormalBoardCell): List<Action>{
        var res = mutableListOf<Action>()
        // Low the x until is 0
        val x = bc.coordinate.x

        loop@for(i in x-1 downTo 0){
            var cell = state.board.board[i][bc.coordinate.y]
            when(bc.content){
                CellContent.WHITE ->{
                    if(cell.content == CellContent.NOTHING && (cell.type == CellType.NORMAL || cell.type == CellType.EXIT)){
                        res.add(NormalTablutAction(NormalCoordinate(bc.coordinate).returnCell(), NormalCoordinate(i, bc.coordinate.y).returnCell(), if(state.player==NormalPlayer.WHITE) "WHITE" else "BLACK")) // TODO1 fare "toString" enum (anche altri casi)
                    }else{
                        break@loop
                    }
                }
                CellContent.BLACK ->{ // TODOg if black stays inside a camp, the cell can be a camp (anche altri casi)
                    if(cell.content == CellContent.NOTHING && (cell.type == CellType.NORMAL || cell.type == CellType.EXIT)){
                        res.add(NormalTablutAction(NormalCoordinate(bc.coordinate).returnCell(), NormalCoordinate(i, bc.coordinate.y).returnCell(), if(state.player==NormalPlayer.WHITE) "WHITE" else "BLACK"))
                    }else{
                        break@loop
                    }
                }
                CellContent.KING ->{
                    if(cell.content == CellContent.NOTHING && (cell.type == CellType.NORMAL || cell.type == CellType.EXIT)){
                        res.add(NormalTablutAction(NormalCoordinate(bc.coordinate).returnCell(), NormalCoordinate(i, bc.coordinate.y).returnCell(), if(state.player==NormalPlayer.WHITE) "WHITE" else "BLACK"))
                    }else{
                        break@loop
                    }
                }
            }
        }

        return res
    }

    fun rightActions(state: NormalState, bc: NormalBoardCell): List<Action>{
        var res = mutableListOf<Action>()
        val y = bc.coordinate.y

        loop@for(j in y+1..state.board.cols-1){
            var cell = state.board.board[bc.coordinate.x][j]
            when(bc.content){
                CellContent.WHITE ->{
                    if(cell.content == CellContent.NOTHING && (cell.type == CellType.NORMAL || cell.type == CellType.EXIT)){
                        res.add(NormalTablutAction(NormalCoordinate(bc.coordinate).returnCell(), NormalCoordinate(bc.coordinate.x, j).returnCell(), if(state.player==NormalPlayer.WHITE) "WHITE" else "BLACK"))
                    }else{
                        break@loop
                    }
                }
                CellContent.BLACK ->{
                    if(cell.content == CellContent.NOTHING && (cell.type == CellType.NORMAL || cell.type == CellType.EXIT)){
                        res.add(NormalTablutAction(NormalCoordinate(bc.coordinate).returnCell(), NormalCoordinate(bc.coordinate.x, j).returnCell(), if(state.player==NormalPlayer.WHITE) "WHITE" else "BLACK"))
                    }else{
                        break@loop
                    }
                }
                CellContent.KING ->{
                    if(cell.content == CellContent.NOTHING && (cell.type == CellType.NORMAL || cell.type == CellType.EXIT)){
                        res.add(NormalTablutAction(NormalCoordinate(bc.coordinate).returnCell(), NormalCoordinate(bc.coordinate.x, j).returnCell(), if(state.player==NormalPlayer.WHITE) "WHITE" else "BLACK"))
                    }else{
                        break@loop
                    }
                }
            }
        }

        return res
    }

    fun downActions(state: NormalState, bc: NormalBoardCell): List<Action>{
        var res = mutableListOf<Action>()
        val x = bc.coordinate.x

        loop@for(i in x+1..state.board.rows-1){
            var cell = state.board.board[i][bc.coordinate.y]
            when(bc.content){
                CellContent.WHITE ->{
                    if(cell.content == CellContent.NOTHING && (cell.type == CellType.NORMAL || cell.type == CellType.EXIT)){
                        res.add(NormalTablutAction(NormalCoordinate(bc.coordinate).returnCell(), NormalCoordinate(i, bc.coordinate.y).returnCell(), if(state.player==NormalPlayer.WHITE) "WHITE" else "BLACK"))
                    }else{
                        break@loop
                    }
                }
                CellContent.BLACK ->{
                    if(cell.content == CellContent.NOTHING && (cell.type == CellType.NORMAL || cell.type == CellType.EXIT)){
                        res.add(NormalTablutAction(NormalCoordinate(bc.coordinate).returnCell(), NormalCoordinate(i, bc.coordinate.y).returnCell(), if(state.player==NormalPlayer.WHITE) "WHITE" else "BLACK"))
                    }else{
                        break@loop
                    }
                }
                CellContent.KING ->{
                    if(cell.content == CellContent.NOTHING && (cell.type == CellType.NORMAL || cell.type == CellType.EXIT)){
                        res.add(NormalTablutAction(NormalCoordinate(bc.coordinate).returnCell(), NormalCoordinate(i, bc.coordinate.y).returnCell(), if(state.player==NormalPlayer.WHITE) "WHITE" else "BLACK"))
                    }else{
                        break@loop
                    }
                }
            }
        }

        return res
    }

    fun leftActions(state: NormalState, bc: NormalBoardCell): List<Action>{
        var res = mutableListOf<Action>()
        val y = bc.coordinate.y

        loop@for(j in y-1 downTo 0){
            var cell = state.board.board[bc.coordinate.x][j]
            when(bc.content){
                CellContent.WHITE ->{
                    if(cell.content == CellContent.NOTHING && (cell.type == CellType.NORMAL || cell.type == CellType.EXIT)){
                        res.add(NormalTablutAction(NormalCoordinate(bc.coordinate).returnCell(), NormalCoordinate(bc.coordinate.x, j).returnCell(), if(state.player==NormalPlayer.WHITE) "WHITE" else "BLACK"))
                    }else{
                        break@loop
                    }
                }
                CellContent.BLACK ->{
                    if(cell.content == CellContent.NOTHING && (cell.type == CellType.NORMAL || cell.type == CellType.EXIT)){
                        res.add(NormalTablutAction(NormalCoordinate(bc.coordinate).returnCell(), NormalCoordinate(bc.coordinate.x, j).returnCell(), if(state.player==NormalPlayer.WHITE) "WHITE" else "BLACK"))
                    }else{
                        break@loop
                    }
                }
                CellContent.KING ->{
                    if(cell.content == CellContent.NOTHING && (cell.type == CellType.NORMAL || cell.type == CellType.EXIT)){
                        res.add(NormalTablutAction(NormalCoordinate(bc.coordinate).returnCell(), NormalCoordinate(bc.coordinate.x, j).returnCell(), if(state.player==NormalPlayer.WHITE) "WHITE" else "BLACK"))
                    }else{
                        break@loop
                    }
                }
            }
        }

        return res
    }
}