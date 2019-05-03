package model.state.board

import java.io.File

// TODO1 factory?
data class NormalBoard<T>(override val rows: Int, override val cols: Int, val boardTypePath: String) : Board<NormalBoardCell>, ArrayMatrix<NormalBoardCell>(rows, cols, Array(rows){Array(cols){NormalBoardCell()}}){
    val board = this.array

    init{
        var r = 0
        var c = 0

        File(boardTypePath).forEachLine { item ->
            c = 0
            item.split(" ").forEach {
                when (it) {
                    "N" -> board[r][c] = NormalBoardCell(NormalCoordinate(r, c), CellType.NORMAL, CellContent.NOTHING)
                    "E" -> board[r][c] = NormalBoardCell(NormalCoordinate(r, c), CellType.EXIT, CellContent.NOTHING)
                    "C" -> board[r][c] = NormalBoardCell(NormalCoordinate(r, c), CellType.CAMP, CellContent.BLACK)
                    "K" -> board[r][c] = NormalBoardCell(NormalCoordinate(r, c), CellType.CASTLE, CellContent.KING)
                }
                c++
            }
            r++
        }
        for(i in 0..rows-1){
            if(board[i][4].content == CellContent.NOTHING)
                board[i][4] = NormalBoardCell(NormalCoordinate(r, 4), CellType.NORMAL, CellContent.WHITE)
        }

        for(j in 0..cols-1){
            if(board[4][j].content == CellContent.NOTHING)
                board[4][j] = NormalBoardCell(NormalCoordinate(4, j), CellType.NORMAL, CellContent.WHITE)
        }
    }

    fun init(){

    }

    // TODO1 enum su toShow
    fun printBoard(toShow: Int){ // TODO check scambio indici in questi 3 files (no main)
        for(i in 0..rows-1) {
            for (j in 0..cols - 1) {
                // TODO1 bruttino "" per inferire stringa
                when(toShow){
                    0 -> print("" + board[i][j] + " ")
                    1 -> print("" + board[i][j]?.type + " ")
                    2 -> print("" + board[i][j]?.content + " ")
                }
            }
            println()
        }
    }

    fun getKingBoardCell(): NormalBoardCell{
        var res = NormalBoardCell()

        for(i in 0..rows-1) {
            for (j in 0..cols - 1) {
                if(board[i][j].content==CellContent.KING) {
                    res = board[i][j]
                    break
                }
            }
        }

        return res
    }

    fun getBoardCellFromCoord(c: NormalCoordinate): NormalBoardCell{
        return NormalBoardCell(c, board[c.x][c.y].type, board[c.x][c.y].content)
    }

    // TODO1 can do in a better way
    fun getBoardCellsFromCoords(coordinateList: List<NormalCoordinate>): List<NormalBoardCell>{
        var res = mutableListOf<NormalBoardCell>()

        for(coord in coordinateList){
            res.add(NormalBoardCell(coord, board[coord.x][coord.y].type, board[coord.x][coord.y].content))
        }

        return res
    }

    fun getBlackBoardCells(): List<NormalBoardCell>{
        var res = mutableListOf<NormalBoardCell>()

        for(i in 0..rows-1) {
            for (j in 0..cols - 1) {
                if(board[i][j].content == CellContent.BLACK) {
                    res.add(board[i][j])
                }
            }
        }

        return res
    }

    fun getCampBoardCells(): List<NormalBoardCell>{
        var res = mutableListOf<NormalBoardCell>()

        for(i in 0..rows-1) {
            for (j in 0..cols - 1) {
                if(board[i][j].type == CellType.CAMP) {
                    res.add(board[i][j])
                }
            }
        }

        return res
    }

    fun getExitBoardCells(): List<NormalBoardCell>{
        var res = mutableListOf<NormalBoardCell>()

        for(i in 0..rows-1) {
            for (j in 0..cols - 1) {
                if(board[i][j].type == CellType.EXIT) {
                    res.add(board[i][j])
                }
            }
        }

        return res
    }

    fun getBlackBoardCellAdjKing(): List<NormalBoardCell>{
        val res = mutableListOf<NormalBoardCell>()
        val blackBoardCell = this.getBlackBoardCells()

        for(black in blackBoardCell){
            if(black.coordinate.adjCoordinates().contains(this.getKingBoardCell().coordinate))
                res.add(black)
        }

        return res
    }

    fun getCampBoardCellsAdjKing(): List<NormalBoardCell>{
        val res = mutableListOf<NormalBoardCell>()

        for(black in this.getBlackBoardCells()){
            if(black.coordinate.adjCoordinates().contains(this.getKingBoardCell().coordinate))
                res.add(black)
        }

        return res
    }
}