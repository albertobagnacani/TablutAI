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
                    // TODO incongruenza: r vs y, c vs x. Sistemare ovunque, anche scambio indici in coordinate
                    "N" -> board[r][c] = NormalBoardCell(NormalCoordinate(c, r), CellType.NORMAL, CellContent.NOTHING)
                    "E" -> board[r][c] = NormalBoardCell(NormalCoordinate(c, r), CellType.EXIT, CellContent.NOTHING)
                    "C" -> board[r][c] = NormalBoardCell(NormalCoordinate(c, r), CellType.CAMP, CellContent.BLACK)
                    "K" -> board[r][c] = NormalBoardCell(NormalCoordinate(c, r), CellType.CASTLE, CellContent.KING)
                }
                // TODO sistemare
                if (NormalCoordinate(c, r) == NormalCoordinate(4, 2)
                    || NormalCoordinate(c, r) == NormalCoordinate(4, 3)
                    || NormalCoordinate(c, r) == NormalCoordinate(4, 5)
                    || NormalCoordinate(c, r) == NormalCoordinate(4, 6)
                    || NormalCoordinate(c, r) == NormalCoordinate(2, 4)
                    || NormalCoordinate(c, r) == NormalCoordinate(3, 4)
                    || NormalCoordinate(c, r) == NormalCoordinate(5, 4)
                    || NormalCoordinate(c, r) == NormalCoordinate(6, 4)
                )
                    board[r][c] = NormalBoardCell(NormalCoordinate(c, r), CellType.NORMAL, CellContent.WHITE)
                c++
            }
            r++
        }
    }

    // TODO1 enum su toShow
    fun printBoard(toShow: Int){
        for(i in 0..rows-1) {
            for (j in 0..cols - 1) {
                // TODO1 bruttino "" per inferire stringa
                when(toShow){
                    0 -> print("" + board[j][i] + " ")
                    1 -> print("" + board[j][i]?.type + " ")
                    2-> print("" + board[j][i]?.content + " ")
                }
            }
            println()
        }
    }

    // TODO mettere .equals invece che == ?
    fun getExitCoordinates(): List<Coordinate>{
        var res = mutableListOf<Coordinate>()

        for(i in 0..rows-1) {
            for (j in 0..cols - 1) {
                if(board[j][i].type==CellType.EXIT) {
                    res.add(NormalCoordinate(j, i))
                }
            }
        }

        return res
    }

    fun getCampCoordinates(): List<Coordinate>{
        var res = mutableListOf<Coordinate>()

        for(i in 0..rows-1) {
            for (j in 0..cols - 1) {
                if(board[j][i].type==CellType.CAMP) {
                    res.add(NormalCoordinate(j, i))
                }
            }
        }

        return res
    }

    fun getKingCoordinate(): NormalCoordinate{
        var res = NormalCoordinate(-1, -1)

        for(i in 0..rows-1) {
            for (j in 0..cols - 1) {
                if(board[j][i].content==CellContent.KING) {
                    res = NormalCoordinate(j, i)
                }
            }
        }

        return res
    }

    fun getBlackCoordinates(): List<Coordinate>{
        var res = mutableListOf<Coordinate>()

        for(i in 0..rows-1) {
            for (j in 0..cols - 1) {
                if(board[j][i].content == CellContent.BLACK) {
                    res.add(NormalCoordinate(j, i))
                }
            }
        }

        return res
    }

    fun getBoardCellFromCoord(c: NormalCoordinate): NormalBoardCell{
        return NormalBoardCell(c, board[c.y][c.x].type, board[c.y][c.x].content)
    }

    // TODO1 can do in a better way
    fun getBoardCellsFromCoords(coordinateList: List<NormalCoordinate>): List<NormalBoardCell>{
        var res = mutableListOf<NormalBoardCell>()

        for(coord in coordinateList){
            res.add(NormalBoardCell(coord, board[coord.y][coord.x].type, board[coord.y][coord.x].content))
        }

        return res
    }

    // TODO change getBlack/Camp/ExitCoordinates in getBlack/Camp/ExitBoardCells calls everywhere, it's more generic and contains coordinates. Remove then the ...Coordinates
    fun getBlackBoardCells(): List<NormalBoardCell>{
        var res = mutableListOf<NormalBoardCell>()

        for(i in 0..rows-1) {
            for (j in 0..cols - 1) {
                if(board[j][i].content == CellContent.BLACK) {
                    res.add(board[j][i])
                }
            }
        }

        return res
    }

    fun getCampBoardCells(): List<NormalBoardCell>{
        var res = mutableListOf<NormalBoardCell>()

        for(i in 0..rows-1) {
            for (j in 0..cols - 1) {
                if(board[j][i].type == CellType.CAMP) {
                    res.add(board[j][i])
                }
            }
        }

        return res
    }

    fun getExitBoardCells(): List<NormalBoardCell>{
        var res = mutableListOf<NormalBoardCell>()

        for(i in 0..rows-1) {
            for (j in 0..cols - 1) {
                if(board[j][i].type == CellType.EXIT) {
                    res.add(board[j][i])
                }
            }
        }

        return res
    }

    fun getBlackAdjKing(): List<NormalBoardCell>{
        val res = mutableListOf<NormalBoardCell>()
        val blackBoardCell = this.getBlackBoardCells()

        for(black in blackBoardCell){
            if(black.coordinate.adjCoordinates().contains(this.getKingCoordinate()))
                res.add(black)
        }

        return res
    }
}