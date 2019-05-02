package model.state.board

interface Matrix<out T>{
    val rows: Int
    val cols: Int

    fun get(row: Int, col: Int): T
}