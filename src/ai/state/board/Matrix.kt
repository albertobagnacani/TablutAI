package ai.state.board

interface Matrix<T>{
    val rows: Int
    val cols: Int

    fun get(row: Int, col: Int): T
}