package model.state.board

// TODO1 factory?
data class NormalBoardCell(val coordinate: NormalCoordinate, val type: CellType, val content: CellContent) : BoardCell {
    constructor() : this(NormalCoordinate(0,0), CellType.NORMAL, CellContent.NOTHING)
}