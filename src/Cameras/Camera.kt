package Cameras

import Geometry.Hittable
import ImageBuffer
import MyRandom
import Geometry.Ray
import Geometry.Vec3
import Geometry.times

abstract class Camera(val aspect_ratio :Double, val image_width :Int, val samplesPerPixel:Int) {
    //location of the camera in the world
    var origin: Vec3

    //vector that "points" horizontal bound of the viewport
    var horizontal: Vec3
    //vector that "points" vertical bound of the viewport
    var vertical: Vec3
    //lower left corner of the viewport
    var lower_left_corner: Vec3
    var image_height:Int




    init {


        //determine viewport dimensions
        val focal_length = 1.0
        val viewport_height = 2.0
        val viewport_width = aspect_ratio * viewport_height

        image_height =  (image_width/aspect_ratio).toInt()


        origin = Vec3(0, 0, 0)
        horizontal = Vec3(viewport_width, 0.0, 0.0)
        vertical = Vec3(0.0, viewport_height, 0.0)
        lower_left_corner =
            origin -(horizontal / 2.0)- (vertical / 2.0) - (Vec3(0.0, 0.0, focal_length))
    }

    fun getRay(u: Double, v: Double): Ray {
        return Ray(origin, lower_left_corner + (u * horizontal) + (v * vertical) - origin)
    }

    fun render(world: Hittable):ImageBuffer {
        // render
        var buffer :ImageBuffer= ImageBuffer(image_width,image_height)
        for (j in image_height - 1 downTo 0) {
            print("lines remaining: $j\n")
            for (i in 0 until image_width) {
                var pixel_color = Vec3(0, 0, 0)
                for (s in 0 until samplesPerPixel) {
                    val u: Double = (i.toDouble() + MyRandom.randomDouble()) / (image_width - 1)
                    val v: Double = (j.toDouble() + MyRandom.randomDouble()) / (image_height - 1)

                    val r = getRay(u, v)

                    pixel_color = ray_color(r, world).plus(pixel_color)
                }
                buffer.addAtIndex(i,j,pixel_color/(samplesPerPixel.toDouble()))
            }
        }




        return buffer

    }
    /**
     * @returns rgb vector with Vec3 percentage that lerps between white and blue vertically
     */
    abstract fun ray_color(r: Ray, world: Hittable): Vec3
}