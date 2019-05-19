package model.state.board

data class NormalCoordinate(override val x: Int, override val y: Int) : Coordinate{
    companion object {
        val arr = arrayOf("a", "b", "c", "d", "e", "f", "g", "h", "i")

        fun getMiddleCoordinate() : Coordinate = NormalCoordinate(4, 4)
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
        return returnCell()
    }

    // Coordinate(3, 0)
    // res: "a4"
    fun returnCell(): String{
        val resY = arr[this.y]
        val resX = this.x+1
        return resY+""+resX  // x+1 because TablutCompetition's server board starts from 1 (and not 0)
    }

    // s: a4
    // result: Coordinate(3, 0)
    fun fromCell(s: String): Coordinate{
        val y = arr.indexOf(s.substring(0, 1))
        var x = s.substring(1, 2).toInt()
        x = x-1

        return NormalCoordinate(x, y) // x-1 like before
    }

    //fun moveLeft(c: Coordinate): Coordinate{}
}