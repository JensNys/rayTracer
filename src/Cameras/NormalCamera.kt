package Cameras

import Hittables.HitRecord
import Hittables.Hittable
import Interval
import Ray
import Vec3
import times

/**
 * this camera shows for each object the surface normal at the hitpoint. It's purpose is debugging
 */
class NormalCamera(aspect_ratio: Double, image_width: Int, samplesPerPixel: Int) : Camera(aspect_ratio, image_width,
    samplesPerPixel
) {


    override fun ray_color(r: Ray, world: Hittable): Vec3 {
        val rec: HitRecord? = world.hit(r, Interval(0.0, Double.POSITIVE_INFINITY));

        if (rec != null) {
            val percentages: Vec3 = (rec.normal + (Vec3(1, 1, 1))) / 2.0
            return percentages
        }

        val unitDirection = r.direction.unitVector
        val t = 0.5 * (unitDirection.y + 1)
        return ((1 - t) * Vec3(1, 1, 1)) + (t * Vec3(0.5, 0.7, 1.0)) //(1.0-t)*Vec3(1.0, 1.0, 1.0) + t*Vec3(0.5, 0.7, 1.0)
    }
}
