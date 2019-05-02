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
                    // TODO incongruenza: r vs y, c vs x
                    "N" -> board[r][c] = NormalBoardCell(Coordinates(c, r), CellType.NORMAL, CellContent.NOTHING)
                    "E" -> board[r][c] = NormalBoardCell(Coordinates(c, r), CellType.EXIT, CellContent.NOTHING)
                    "C" -> board[r][c] = NormalBoardCell(Coordinates(c, r), CellType.CAMP, CellContent.BLACK)
                    "K" -> board[r][c] = NormalBoardCell(Coordinates(c, r), CellType.CASTLE, CellContent.KING)
                }
                // TODO sistemare
                if (Coordinates(c, r) == Coordinates(4, 2)
                    || Coordinates(c, r) == Coordinates(4, 3)
                    || Coordinates(c, r) == Coordinates(4, 5)
                    || Coordinates(c, r) == Coordinates(4, 6)
                    || Coordinates(c, r) == Coordinates(2, 4)
                    || Coordinates(c, r) == Coordinates(3, 4)
                    || Coordinates(c, r) == Coordinates(5, 4)
                    || Coordinates(c, r) == Coordinates(6, 4)
                )
                    board[r][c] = NormalBoardCell(Coordinates(c, r), CellType.NORMAL, CellContent.WHITE)
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
}