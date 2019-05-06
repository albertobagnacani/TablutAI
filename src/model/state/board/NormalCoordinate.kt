package model.state.board

data class NormalCoordinate(override val x: Int, override val y: Int) : Coordinate{
    companion object {
        val arr = arrayOf("a", "b", "c", "d", "e", "f", "g", "h", "i")

        fun getMiddleCoordinate() : Coordinate = NormalCoordinate(4, 4)
        // TODO attenzione: potrei andare fuori dalla board. Exception quando errori?
        // TODO1 fare come metodo non statico?
        fun getThirdCoordinateFromTwo(mainCoordinate: Coordinate, secondaryCoordinate: Coordinate): NormalCoordinate{
            if(mainCoordinate.x==secondaryCoordinate.x) {
                return if (mainCoordinate.y < secondaryCoordinate.y) NormalCoordinate(mainCoordinate.x, mainCoordinate.y-1) else NormalCoordinate(mainCoordinate.x, mainCoordinate.y+1)
            }else if(mainCoordinate.y==secondaryCoordinate.y){
                return if (mainCoordinate.x < secondaryCoordinate.x) NormalCoordinate(mainCoordinate.x-1, mainCoordinate.y) else NormalCoordinate(mainCoordinate.x+1, mainCoordinate.y)
            }else{ // Error for this type of operation
                return NormalCoordinate(0, 0)
            }
        }
    }

    constructor(c: Coordinate) : this(c.x, c.y)
    constructor() : this(0, 0) {}

    fun adjCoordinates(): List<NormalCoordinate>{
        val res = mutableListOf<NormalCoordinate>()

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

    override fun toString(): String {
        return "NormalCoordinate(x=$x, y=$y)"
    }

    fun returnCell(): String{
        return arr[y]+x
    }

    fun fromCell(s: String): Coordinate{
        val y = arr.indexOf(s.substring(0, 1))
        val x = s.substring(0, 1).toInt()

        return NormalCoordinate(x, y)
    }

    //fun moveLeft(c: Coordinate): Coordinate{}
}