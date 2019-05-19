package model.state.board

interface BoardCell{
    val coordinate: NormalCoordinate
    val type: CellType
    val content: CellContent
}