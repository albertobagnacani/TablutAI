package ai.state

// TODO add matrix
/**
 * Class representing the board
 */
class Board{
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