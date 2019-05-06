package ai.action

import model.state.NormalState
import model.state.State
import model.state.board.*
import model.state.player.NormalPlayer

class NormalActionResolver : ActionResolver {
    override fun actions(s: State): List<Action> {
        val state = s as NormalState
        var res = mutableListOf<Action>()

        for(i in 0..s.board.rows-1){
            for(j in 0..s.board.cols-1){
                if(state.board.board[i][j].content != CellContent.NOTHING) {
                    res.addAll(availableActions(state, state.board.board[i][j]))
                }
            }
        }

        return res
    }

    // For every direction (up, right, down, left), returns the number of cells a pawn can go
    fun availableActions(state: NormalState, bc: NormalBoardCell): List<Action>{
        var res = mutableListOf<Action>()

        res.addAll(upActions(state, bc)) // TODO1 can do a matrix ([0=up, 1=right, ...][<actions>])
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

        for(i in x-1 downTo 0){ // TODO attenzione non andare fuori dalla board con x-1 (per tutti i casi, con relative x/y(+/-1))
            when(bc.content){
                CellContent.WHITE ->{
                    if(state.board.board[i][bc.coordinate.y].content == CellContent.NOTHING && state.board.board[i][bc.coordinate.y].type == CellType.NORMAL){
                        res.add(NormalTablutAction(NormalCoordinate(bc.coordinate).returnCell(), NormalCoordinate(i, bc.coordinate.y).returnCell(), if(state.player==NormalPlayer.WHITE) "WHITE" else "BLACK")) // TODO this player or other turn player? // TODO1 fare "toString" enum (anche altri casi)
                    }else{
                        // TODO should break (anche altri casi)
                    }
                }
                CellContent.BLACK ->{ // TODO if black stays inside a camp, the cell can be a camp (anche altri casi)
                    if(state.board.board[i][bc.coordinate.y].content == CellContent.NOTHING && state.board.board[i][bc.coordinate.y].type == CellType.NORMAL){
                        res.add(NormalTablutAction(NormalCoordinate(bc.coordinate).returnCell(), NormalCoordinate(i, bc.coordinate.y).returnCell(), if(state.player==NormalPlayer.WHITE) "WHITE" else "BLACK"))
                    }else{
                        // should break
                    }
                }
                CellContent.KING ->{
                    if(state.board.board[i][bc.coordinate.y].content == CellContent.NOTHING && (state.board.board[i][bc.coordinate.y].type == CellType.NORMAL || state.board.board[i][bc.coordinate.y].type == CellType.EXIT)){
                        res.add(NormalTablutAction(NormalCoordinate(bc.coordinate).returnCell(), NormalCoordinate(i, bc.coordinate.y).returnCell(), if(state.player==NormalPlayer.WHITE) "WHITE" else "BLACK"))
                    }else{
                        // should break
                    }
                }
            }
        }

        return res
    }

    fun rightActions(state: NormalState, bc: NormalBoardCell): List<Action>{
        var res = mutableListOf<Action>()
        val y = bc.coordinate.y

        for(j in y+1..state.board.cols){
            when(bc.content){
                CellContent.WHITE ->{
                    if(state.board.board[bc.coordinate.x][j].content == CellContent.NOTHING && state.board.board[bc.coordinate.x][j].type == CellType.NORMAL){
                        res.add(NormalTablutAction(NormalCoordinate(bc.coordinate).returnCell(), NormalCoordinate(bc.coordinate.x, j).returnCell(), if(state.player==NormalPlayer.WHITE) "WHITE" else "BLACK"))
                    }else{
                        // should break
                    }
                }
                CellContent.BLACK ->{
                    if(state.board.board[bc.coordinate.x][j].content == CellContent.NOTHING && state.board.board[bc.coordinate.x][j].type == CellType.NORMAL){
                        res.add(NormalTablutAction(NormalCoordinate(bc.coordinate).returnCell(), NormalCoordinate(bc.coordinate.x, j).returnCell(), if(state.player==NormalPlayer.WHITE) "WHITE" else "BLACK"))
                    }else{
                        // should break
                    }
                }
                CellContent.KING ->{
                    if(state.board.board[bc.coordinate.x][j].content == CellContent.NOTHING && (state.board.board[bc.coordinate.x][j].type == CellType.NORMAL || state.board.board[bc.coordinate.x][j].type == CellType.EXIT)){
                        res.add(NormalTablutAction(NormalCoordinate(bc.coordinate).returnCell(), NormalCoordinate(bc.coordinate.x, j).returnCell(), if(state.player==NormalPlayer.WHITE) "WHITE" else "BLACK"))
                    }else{
                        // should break
                    }
                }
            }
        }

        return res
    }

    fun downActions(state: NormalState, bc: NormalBoardCell): List<Action>{
        var res = mutableListOf<Action>()
        val x = bc.coordinate.x

        for(i in x+1..state.board.rows){
            when(bc.content){
                CellContent.WHITE ->{
                    if(state.board.board[i][bc.coordinate.y].content == CellContent.NOTHING && state.board.board[i][bc.coordinate.y].type == CellType.NORMAL){
                        res.add(NormalTablutAction(NormalCoordinate(bc.coordinate).returnCell(), NormalCoordinate(i, bc.coordinate.y).returnCell(), if(state.player==NormalPlayer.WHITE) "WHITE" else "BLACK"))
                    }else{
                        // should break
                    }
                }
                CellContent.BLACK ->{
                    if(state.board.board[i][bc.coordinate.y].content == CellContent.NOTHING && state.board.board[i][bc.coordinate.y].type == CellType.NORMAL){
                        res.add(NormalTablutAction(NormalCoordinate(bc.coordinate).returnCell(), NormalCoordinate(i, bc.coordinate.y).returnCell(), if(state.player==NormalPlayer.WHITE) "WHITE" else "BLACK"))
                    }else{
                        // should break
                    }
                }
                CellContent.KING ->{
                    if(state.board.board[i][bc.coordinate.y].content == CellContent.NOTHING && (state.board.board[i][bc.coordinate.y].type == CellType.NORMAL || state.board.board[i][bc.coordinate.y].type == CellType.EXIT)){
                        res.add(NormalTablutAction(NormalCoordinate(bc.coordinate).returnCell(), NormalCoordinate(i, bc.coordinate.y).returnCell(), if(state.player==NormalPlayer.WHITE) "WHITE" else "BLACK"))
                    }else{
                        // should break
                    }
                }
            }
        }

        return res
    }

    fun leftActions(state: NormalState, bc: NormalBoardCell): List<Action>{
        var res = mutableListOf<Action>()
        val y = bc.coordinate.y

        for(j in y-1 downTo 0){
            when(bc.content){
                CellContent.WHITE ->{
                    if(state.board.board[bc.coordinate.x][j].content == CellContent.NOTHING && state.board.board[bc.coordinate.x][j].type == CellType.NORMAL){
                        res.add(NormalTablutAction(NormalCoordinate(bc.coordinate).returnCell(), NormalCoordinate(bc.coordinate.x, j).returnCell(), if(state.player==NormalPlayer.WHITE) "WHITE" else "BLACK"))
                    }else{
                        // should break
                    }
                }
                CellContent.BLACK ->{
                    if(state.board.board[bc.coordinate.x][j].content == CellContent.NOTHING && state.board.board[bc.coordinate.x][j].type == CellType.NORMAL){
                        res.add(NormalTablutAction(NormalCoordinate(bc.coordinate).returnCell(), NormalCoordinate(bc.coordinate.x, j).returnCell(), if(state.player==NormalPlayer.WHITE) "WHITE" else "BLACK"))
                    }else{
                        // should break
                    }
                }
                CellContent.KING ->{
                    if(state.board.board[bc.coordinate.x][j].content == CellContent.NOTHING && (state.board.board[bc.coordinate.x][j].type == CellType.NORMAL || state.board.board[bc.coordinate.x][j].type == CellType.EXIT)){
                        res.add(NormalTablutAction(NormalCoordinate(bc.coordinate).returnCell(), NormalCoordinate(bc.coordinate.x, j).returnCell(), if(state.player==NormalPlayer.WHITE) "WHITE" else "BLACK"))
                    }else{
                        // should break
                    }
                }
            }
        }

        return res
    }
}