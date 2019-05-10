package model.state.board

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.File
import java.util.*

// TODO1 factory?
data class NormalBoard<out T>(override val rows: Int, override val cols: Int, val boardTypePath: String, val boardContentPath: String) : Board<NormalBoardCell>, ArrayMatrix<NormalBoardCell>(rows, cols, Array(rows){Array(cols){NormalBoardCell()}}){
    //@SerializedName("board")
    //@Expose
    var board = this.array

    init{
        initializeCoords()
        initializeType()
        initializeContent()
    }

    fun initializeCoords(){
        for(i in 0..rows-1){
            for(j in 0..cols-1){
                board[i][j] = NormalBoardCell(NormalCoordinate(i, j), CellType.NORMAL, CellContent.WHITE)
            }
        }
    }

    fun initializeType(){
        var r = 0
        var c: Int
        File(boardTypePath).forEachLine { item ->
            c = 0
            item.split(" ").forEach {
                when (it) {
                    "N" -> board[r][c].type = CellType.NORMAL
                    "E" -> board[r][c].type = CellType.EXIT
                    "C" -> board[r][c].type = CellType.CAMP
                    "K" -> board[r][c].type = CellType.CASTLE
                }
                c++
            }
            r++
        }
    }

    fun initializeContent(){
        var r = 0
        var c: Int
        File(boardContentPath).forEachLine { item ->
            c = 0
            item.split(" ").forEach {
                when (it) {
                    "N" -> board[r][c].content = CellContent.NOTHING
                    "W" -> board[r][c].content = CellContent.WHITE
                    "B" -> board[r][c].content = CellContent.BLACK
                    "K" -> board[r][c].content = CellContent.KING
                }
                c++
            }
            r++
        }
    }

    fun initializeContent(f: File){
        var r = 0
        var c: Int
        File(f.name).forEachLine { item ->
            c = 0
            item.split(" ").forEach {
                when (it) {
                    "N" -> board[r][c].content = CellContent.NOTHING
                    "W" -> board[r][c].content = CellContent.WHITE
                    "B" -> board[r][c].content = CellContent.BLACK
                    "K" -> board[r][c].content = CellContent.KING
                }
                c++
            }
            r++
        }
    }

    fun emptyContent(){
        for(i in 0..rows-1)
            for(j in 0..cols-1)
                board[i][j].content = CellContent.NOTHING
    }

    // TODO1 enum su toShow
    fun printBoard(toShow: Int){
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

    fun getElement(c: Coordinate) : NormalBoardCell{
        return board[c.x][c.y]
    }

    fun setElement(c: Coordinate, n: NormalBoardCell) {
        board[c.x][c.y] = n
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

    override fun toString(): String {
        return "NormalBoard(rows=$rows, cols=$cols, boardTypePath='$boardTypePath', board=${Arrays.toString(board)})"
    }
}