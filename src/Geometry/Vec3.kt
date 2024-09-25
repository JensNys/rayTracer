package Geometry

import MyRandom

data class Vec3(val x: Double, val y: Double, val z: Double) {

    constructor(x: Int, y: Int, z: Int) : this(x.toDouble(), y.toDouble(), z.toDouble())

    fun X() = x
    fun Y() = y
    fun Z() = z

    fun R() = x
    fun G() = y
    fun B() = z

    operator fun plus(v2: Vec3): Vec3 {
        return Vec3(x + v2.x, y + v2.y, z + v2.z)
    }

    operator fun minus(v2: Vec3): Vec3 {
        return Vec3(x - v2.x, y - v2.y, z - v2.z)
    }

    operator fun times(value: Double): Vec3 {
        return Vec3(x * value, y * value, z * value)
    }

    operator fun Double.times(vector: Vec3): Vec3 {
        return Vec3(this * vector.X(), this * vector.Y(), this * vector.Z())
    }

    operator fun div(value: Double): Vec3 {
        return Vec3(x / value, y / value, z / value)
    }

    val length: Double by lazy {
        kotlin.math.sqrt(squaredLength)
    }

    val squaredLength: Double by lazy{
        x * x + y * y + z * z
    }



    val unitVector: Vec3 by lazy{
        val k = 1.0 / length;
        k * this;
    }

    val isUnitVector: Boolean by lazy{
        squaredLength == 1.0
    }

    fun dot(v: Vec3): Double {
        return x * v.x + y * v.y + z * v.z
    }
    operator fun times(v: Vec3): Double {
        return x * v.x + y * v.y + z * v.z
    }
    operator fun unaryMinus(): Vec3 {
        return (-1.0)*this
    }

    fun cross(v: Vec3): Vec3 {
        return Vec3(
            (y * v.z - z * v.y),
            (z * v.x - x * v.z),
            (x * v.y - y * v.x)
        )
    }

    private fun clamp(x: Double, min: Double, max: Double): Double {
        return when {
            x < min -> min
            x > max -> max
            else -> x
        }
    }

    override fun toString(): String {
        return "( $x, $y, $z)"
    }

    /**
     * @return the translated [.-255]rgb value from the [0-1] vector
     */
    fun writeColor(): String {
        return "${(255.99 * x).toInt()} ${(255.99 * y).toInt()} ${(255.99 * z).toInt()}\n"
    }

    fun writeColor(samplesPerPixel: Int): String {
        val scale = 1.0 / samplesPerPixel
        val r = x * scale
        val g = y * scale
        val b = z * scale
        return "${(255.99 * clamp(r, 0.0, 0.999)).toInt()} ${(255.99 * clamp(g, 0.0, 0.999)).toInt()} ${(255.99 * clamp(b, 0.0, 0.999)).toInt()}\n"
    }

    companion object{
        fun random():Vec3{
            return Vec3(MyRandom.randomDouble(),MyRandom.randomDouble(),MyRandom.randomDouble())
        }

        fun random(min:Double,max:Double):Vec3{
            return Vec3(MyRandom.randomDouble(min,max),MyRandom.randomDouble(min,max),MyRandom.randomDouble(min,max))
        }

        /**
         * returns random vector of length 1
         */
        fun randomUnit():Vec3{
            while (true){
                var r:Vec3= random(-1.0,1.0)
                if (1E-160< r.squaredLength && r.squaredLength<1.0){
                    return r.unitVector
                }
            }
        }

        /**
         * returns a vector on the hemisphere around the given normal Vector
         */
        fun randomOnHemisphere(normal:Vec3):Vec3{
            val random=randomUnit()
            if (normal * random>0.0){
                return random
            }
            else{
                return -random
            }
        }
    }


}
// Extension function to allow Double * Vec3 scaling
operator fun Double.times(v: Vec3): Vec3 {
    return Vec3(this * v.x, this * v.y, this * v.z)
}