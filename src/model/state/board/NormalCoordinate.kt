package model.state.board

// TODO Specificare le coordinate degli accampamenti, del castello, delle uscite invece che in Board?
data class NormalCoordinate(override val x: Int, override val y: Int) : Coordinate{
    companion object {
        fun getMiddleCoordinate() : Coordinate = NormalCoordinate(4, 4)
        // TODO attenzione: potrei andare fuori dalla board
        // TODO fare come metodo non statico?
        fun getThirdCoordinateFromTwo(mainCoordinate: Coordinate, secondaryCoordinate: Coordinate): Coordinate{
            if(mainCoordinate.x==secondaryCoordinate.x) {
                return if (mainCoordinate.y < secondaryCoordinate.y) NormalCoordinate(mainCoordinate.x, mainCoordinate.y-1) else NormalCoordinate(mainCoordinate.x, mainCoordinate.y+1)
            }else if(mainCoordinate.y==secondaryCoordinate.y){
                return if (mainCoordinate.x < secondaryCoordinate.x) NormalCoordinate(mainCoordinate.x-1, mainCoordinate.y) else NormalCoordinate(mainCoordinate.x+1, mainCoordinate.y)
            }else{ // Error for this type of operation
                // TODO exception?
                return NormalCoordinate(0, 0)
            }
        }
    }

    constructor(c: Coordinate) : this(c.x, c.y) {}

    fun adjCoordinates(): List<Coordinate>{
        val res = mutableListOf<Coordinate>()

        // TODO sistemare
        if(x+1<=8){ // Right
            res.add(NormalCoordinate(x+1, y))
        }else if(y+1<=8) { // Down
            res.add(NormalCoordinate(x, y+1))
        }else if(x-1>=0){ // Left
            res.add(NormalCoordinate(x-1, y))
        }else if(y-1>=0){ // Up
            res.add(NormalCoordinate(x, y-1))
        }

        return res
    }

    // TODO ?
    //fun moveLeft(c: Coordinate): Coordinate{}
}