package PPMstuff

import ImageBuffer
import Vec3
import java.io.FileWriter

class BufferToPPM {
    companion object {
        fun convert(buffer:ImageBuffer,filename:String) {
            // render
//            val filename = "sphere1rpp"/**/
            val myWriter = FileWriter("$filename.ppm")
            myWriter.write("P3\n")
            myWriter.write("${buffer.width} ${buffer.height}\n255\n")

            for (j in buffer.height - 1 downTo 0) {
                for (i in 0 until buffer.width) {
                    myWriter.write(buffer.get(i,j)?.writeColor() ?: "kleur niet geinstantieerd")
                }
            }
            myWriter.close()
        }
    }

}