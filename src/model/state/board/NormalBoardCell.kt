package model.state.board

// TODO1 factory?
data class NormalBoardCell(var coordinate: NormalCoordinate, var type: CellType, var content: CellContent) : BoardCell {
    constructor() : this(NormalCoordinate(0,0), CellType.NORMAL, CellContent.NOTHING)

    override fun toString(): String {
        return "NormalBoardCell(coordinate=$coordinate, type=$type, content=$content)"
    }
}