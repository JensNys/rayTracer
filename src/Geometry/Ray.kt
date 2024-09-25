package Geometry

class Ray(var origin: Vec3, var direction: Vec3) {

    fun point_at_parameter(t:Double): Vec3 {
        return origin + t*direction
    }

}