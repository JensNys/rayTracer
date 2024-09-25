import Geometry.Vec3

class ImageBuffer {
    val matrix:Array<Array<Vec3?>>
    val width:Int
    val height:Int
    constructor(width:Int,height:Int){
        matrix =Array(width) { Array<Vec3?>(height) { null } }
        this.width = width
        this.height = height
    }

    fun addAtIndex(row:Int,column:Int,color: Vec3){
        matrix[row][column] = color
    }
    fun get(row:Int,column:Int): Vec3?{
        return matrix[row][column]
    }
}