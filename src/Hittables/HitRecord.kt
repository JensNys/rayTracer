package Hittables

import Ray
import Vec3

/**
 * a hitrecord records data involving when a ray has hit a surface
 *
 * the hitpoint is the location where a ray has hit the surface
 *
 * ray is the Ray that hit the point
 *
 * normal is the surface normal on the point where the ray hit
 *
 * t is the value at which the ray hit the hitpoint
 *
 * front_face indicates whether the normal points inwards(false) or outwards(true) of the object
 *
 *
 */
class HitRecord {
    val hitPoint: Vec3
    val normal: Vec3
    val t: Double
    val frontFace: Boolean

    constructor(hitPoint: Vec3, normal: Vec3, t: Double, frontFace: Boolean) {
        this.hitPoint = hitPoint
        this.normal = normal
        this.t = t
        this.frontFace = frontFace
    }



//    val hitPoint:Vec3 by lazy {
//        ray.point_at_parameter(t)
//    }

    /**
     * outwardNormal is the normal that points outside of the object at the point of intersection
     * t is the value where the ray hits the object
     */
    constructor(ray: Ray, outwardNormal: Vec3, t:Double){
        val hitPoint: Vec3 = ray.point_at_parameter(t)

        //calculating frontFace and normal
        var frontFace:Boolean
        var normal: Vec3
        if ((ray.direction * outwardNormal) > 0.0) {
            // ray is inside the sphere
            normal = -outwardNormal
            frontFace = false
        } else {
            // ray is outside the sphere
            normal = outwardNormal
            frontFace = true
        }

        this.hitPoint = hitPoint
        this.normal = normal
        this.t = t
        this.frontFace = frontFace

        
        

    }




    override fun toString(): String {
        return "Hittables.HitRecord(hitPoint=$hitPoint, normal=$normal, t=$t, front_face=$frontFace)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HitRecord

        if (hitPoint != other.hitPoint) return false
        if (normal != other.normal) return false
        if (t != other.t) return false
        if (frontFace != other.frontFace) return false

        return true
    }

    override fun hashCode(): Int {
        var result = hitPoint.hashCode()
        result = 31 * result + normal.hashCode()
        result = 31 * result + t.hashCode()
        result = 31 * result + frontFace.hashCode()
        return result
    }

}