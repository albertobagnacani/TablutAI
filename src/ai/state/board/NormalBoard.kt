package ai.state.board

// TODO factory?
interface NormalBoard<T> : Board<T> {
    /**
     * Type of the cell
     */
    enum class CellType{
        NORMAL,
        CAMP,
        CASTLE,
        EXIT
    }

    /**
     * Content of the cell
     */
    enum class CellContent{
        NOTHING,
        KING,
        BLACK,
        WHITE
    }
}