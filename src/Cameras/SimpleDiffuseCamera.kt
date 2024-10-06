package Cameras

import Geometry.*
import Interval

class SimpleDiffuseCamera(aspect_ratio: Double, image_width: Int, samplesPerPixel: Int) : Camera(aspect_ratio, image_width,
    samplesPerPixel
) {

    override fun ray_color(r: Ray, world: Hittable): Vec3{
        return ray_color(r,world,3);
    }
     fun ray_color(r: Ray, world: Hittable,recursionDepth:Int): Vec3 {

         if (recursionDepth==0){
             return Vec3(0,0,0)
         }

         val rec: HitRecord? = world.hit(r, Interval(0.0, Double.POSITIVE_INFINITY));

        if (rec != null) {// als de ray iets geraakt heeft
            val target: Vec3 = rec.normal + rec.normal + Vec3.randomOnHemisphere(rec.normal)
            return 0.5 * ray_color(Ray(rec.hitPoint,target-rec.hitPoint),world,recursionDepth-1)
        } else{
            val unitDirection = r.direction.unitVector
            val t = 0.5 * (unitDirection.y + 1)
            return ((1 - t) * Vec3(1, 1, 1)) + (t * Vec3(0.5, 0.7, 1.0))
        }

     }
}