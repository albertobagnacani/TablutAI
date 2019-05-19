package model.state.board

data class NormalBoardCell(override var coordinate: NormalCoordinate, override var type: CellType, override var content: CellContent) : BoardCell {
    constructor() : this(NormalCoordinate(0,0), CellType.NORMAL, CellContent.NOTHING)

    override fun toString(): String {
        return "NormalBoardCell(coordinate=$coordinate, type=${type.name}, content=${content.name})"
    }
}