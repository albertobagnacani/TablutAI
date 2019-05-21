package model.state.board

import java.io.File

data class NormalBoard<out T>(override val rows: Int, override val cols: Int, val boardTypePath: String, val boardContentPath: String) : Board<NormalBoardCell>, ArrayMatrix<NormalBoardCell>(rows, cols, Array(rows){Array(cols){NormalBoardCell()}}){
    //@SerializedName("board")
    //@Expose
    var board = this.array

    init{
        initializeCoords()
        initializeTypeNoFile()
        initializeContentNoFile()
    }

    fun initializeCoords(){
        for(i in 0..rows-1){
            for(j in 0..cols-1){
                board[i][j] = NormalBoardCell(NormalCoordinate(i, j), CellType.NORMAL, CellContent.WHITE)
            }
        }
    }

    fun initializeTypeNoFile(){
        var r = 0
        var c: Int
        val type = "N E E C C C E E N\n" +
                "E N N N C N N N E\n" +
                "E N N N N N N N E\n" +
                "C N N N N N N N C\n" +
                "C C N N K N N C C\n" +
                "C N N N N N N N C\n" +
                "E N N N N N N N E\n" +
                "E N N N C N N N E\n" +
                "N E E C C C E E N"
        type.split("\n").forEach{ item ->
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

    fun initializeContentNoFile(){
        var r = 0
        var c: Int
        val content = "N N N B B B N N N\n" +
                "N N N N B N N N N\n" +
                "N N N N W N N N N\n" +
                "B N N N W N N N B\n" +
                "B B W W K W W B B\n" +
                "B N N N W N N N B\n" +
                "N N N N W N N N N\n" +
                "N N N N B N N N N\n" +
                "N N N B B B N N N"
        content.split("\n").forEach{ item ->
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

    fun printBoard(toShow: Int){
        for(i in 0..rows-1) {
            for (j in 0..cols - 1) {
                when(toShow){
                    0 -> print("" + board[i][j] + " ")
                    1 -> print("" + board[i][j].type + " ")
                    2 -> print("" + board[i][j].content + " ")
                    3 -> {
                        when(board[i][j].content){
                            CellContent.NOTHING -> print("" + "_" + " ")
                            CellContent.BLACK -> print("" + "B" + " ")
                            CellContent.WHITE -> print("" + "W" + " ")
                            CellContent.KING -> print("" + "K" + " ")
                        }
                    }
                }
            }
            println()
        }
    }

    fun printBoardString(): String{
        var res = ""

        for(i in 0..rows-1) {
            for (j in 0..cols - 1) {
                when(board[i][j].content){
                    CellContent.NOTHING -> res = res.plus("" + "_" + " ")
                    CellContent.BLACK -> res = res.plus("" + "B" + " ")
                    CellContent.WHITE -> res = res.plus("" + "W" + " ")
                    CellContent.KING -> res = res.plus("" + "K" + " ")
                }
            }
            res = res.plus("\n")
        }

        return res
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

    // TODO2 can do in a better way
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
        return printBoardString()
    }
}