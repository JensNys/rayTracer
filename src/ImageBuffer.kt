class ImageBuffer {
    val matrix:Array<Array<Vec3?>>

    constructor(width:Int,height:Int){
        matrix =Array(width) { Array<Vec3?>(height) { null } }
    }

    fun addAtIndex(row:Int,column:Int,color:Vec3){
        matrix[row][column] = color
    }
}