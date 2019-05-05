package model.state.board

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

/*
This also allows you to create 2d arrays in a similar manner to 1d arrays, e.g. something like
val array2D = Array2D<String>(5, 5) { x, y -> "$x $y" }

and access/set contents with the indexing operator:
val xy = array2D[1, 2]
*/
open class ArrayMatrix<T>(override val rows: Int, override val cols: Int, var array: Array<Array<T>>): Matrix<T>{
    companion object {
        inline operator fun <reified T> invoke() =
            ArrayMatrix(0, 0, Array(0) { emptyArray<T>() })

        inline operator fun <reified T> invoke(xWidth: Int, yWidth: Int) =
            ArrayMatrix(xWidth, yWidth, Array(xWidth) { arrayOfNulls<T>(yWidth) })

        inline operator fun <reified T> invoke(xWidth: Int, yWidth: Int, operator: (Int, Int) -> (T)): ArrayMatrix<T> {
            val array = Array(xWidth) {
                val x = it
                Array(yWidth) {operator(x, it)}
            }
            return ArrayMatrix(xWidth, yWidth, array)
        }
    }

    override operator fun get(i: Int, j: Int): T {
        return array[i][j]
    }

    inline fun <reified R> map(transform: (T) -> R): ArrayMatrix<R> =
        ArrayMatrix(
            rows,
            cols,
            array.map { row -> row.map { transform(it) }.toTypedArray() }.toTypedArray()
        )

    inline fun forEach(operation: (T) -> Unit) {
        array.forEach { it.forEach { operation.invoke(it) } }
    }

    inline fun forEachIndexed(operation: (x: Int, y: Int, T) -> Unit) {
        array.forEachIndexed { x, p -> p.forEachIndexed { y, t -> operation.invoke(x, y, t) } }
    }

    override fun toString(): String {
        return "ArrayMatrix(rows=$rows, cols=$cols, array=${Arrays.toString(array)})"
    }
}