package Hittables

import Interval
import Ray
import Vec3

class Sphere(val center: Vec3, val radius:Double) : Hittable {

    override fun hit(r: Ray, tInterval: Interval): HitRecord? {
        val oc: Vec3 = center-r.origin//vector from origin to the center

        val a:Double = r.direction.squaredLength
        val h:Double = (r.direction * oc)
        val c:Double = oc.squaredLength - radius*radius

        val discriminant_div4 = h*h-a*c;
        if  (discriminant_div4<0.0){
            return null
        }
        val sqrtDiscriminant = Math.sqrt(discriminant_div4)
        var t:Double = (h-sqrtDiscriminant)/a //smallest intersection

        if (!tInterval.contains(t)){
            t = (h+sqrtDiscriminant)/a//furthest intersection
            if (!tInterval.contains(t)){
                return null
            }
        }
        val hitPoint = r.point_at_parameter(t);

        var outward_normal: Vec3 = (hitPoint-center).unitVector;

        val front_face: Boolean
        val normal: Vec3
        if ((r.direction * outward_normal) > 0.0) {
            // ray is inside the sphere
            normal = -outward_normal
            front_face = false
        } else {
            // ray is outside the sphere
            normal = outward_normal
            front_face = true
        }
        return HitRecord(hitPoint,normal,t,front_face)
    }

    fun alternativeHit(r: Ray, tInterval: Interval): HitRecord? {
        val oc: Vec3 = center-r.origin//vector from origin to the center

        val a:Double = r.direction.squaredLength
        val h:Double = (r.direction * oc)
        val c:Double = oc.squaredLength - radius*radius

        val discriminant_div4 = h*h-a*c;
        if  (discriminant_div4<0.0){
            return null
        }
        val sqrtDiscriminant = Math.sqrt(discriminant_div4)
        var t:Double = (h-sqrtDiscriminant)/a //smallest intersection

        if (!tInterval.contains(t)){
            t = (h+sqrtDiscriminant)/a//furthest intersection
            if (!tInterval.contains(t)){
                return null
            }
        }
        val hitPoint = r.point_at_parameter(t);

        var outward_normal: Vec3 = (hitPoint-center).unitVector;

        return HitRecord(r,outward_normal,t)

    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Sphere

        if (center != other.center) return false
        if (radius != other.radius) return false

        return true
    }

    override fun hashCode(): Int {
        var result = center.hashCode()
        result = 31 * result + radius.hashCode()
        return result
    }


}