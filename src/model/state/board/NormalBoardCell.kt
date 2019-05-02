package model.state.board

// TODO1 factory?
data class NormalBoardCell(val coordinates: Coordinates, val type: CellType, val content: CellContent) : BoardCell {
    constructor() : this(Coordinates(0,0), CellType.NORMAL, CellContent.NOTHING)
}