package Geometry.Shapes

import Geometry.HitRecord
import Geometry.Hittable
import Interval
import Geometry.Ray
import Geometry.Vec3

class Plane : Hittable {

    var normal: Vec3
    var d: Double

    /**
     * generates the plane ax+by+cy=d
     */
    constructor(a: Double, b: Double, c: Double, d: Double) {
        normal = Vec3(a, b, c)
        this.d = d
    }

    constructor(normal: Vec3, d: Double) {
        this.normal = normal
        this.d = d
    }

    override fun hit(r: Ray, tInterval: Interval): HitRecord? {
        val t = (d - (r.origin.dot(normal))) / (r.direction.dot(normal))
        if (!tInterval.contains(t)) {
            return null
        }
        return HitRecord(r,normal,t);
    }
}