package Geometry

import Interval

interface Hittable {

    /**
     * Given a ray and an interval of t-values on that ray, returns null if there is no intersection
     * on the interval. Otherwise, returns a Hittables.HitRecord.
     */
    fun hit(r: Ray, tInterval: Interval): HitRecord?
}
