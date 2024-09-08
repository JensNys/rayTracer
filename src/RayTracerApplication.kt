import java.io.File
import java.io.FileWriter

fun main() {


    //Image
    val aspect_ratio: Double = 16.0 / 9.0
    val image_width: Int = 400
    val image_height: Int = (image_width / aspect_ratio).toInt()
    val samples_per_pixel: Int = 20


    // World
    val world: HittableList = HittableList()
    world.add(Sphere(Vec3(0, 0, -1), 0.5))
    world.add(Sphere(Vec3(1, 0, -1), 0.25))
    world.add(Sphere(Vec3(-1, 0, -1), 0.25))
    world.add(Plane(Vec3(0.0,1.0,0.0),-0.3))

    //Camera
    val cam: Camera = Camera()

    //file
    // render
    val filename = "sphere1rpp"
    val myWriter = FileWriter("$filename.ppm")
    myWriter.write("P3\n")
    myWriter.write("$image_width $image_height\n255\n")

    for (j in image_height - 1 downTo 0) {
        print("lines remaining: $j\n")
        for (i in 0 until image_width) {
            var pixel_color = Vec3(0, 0, 0)
            for (s in 0 until samples_per_pixel) {
                val u: Double = (i.toDouble() + MyRandom.randomDouble()) / (image_width - 1)
                val v: Double = (j.toDouble() + MyRandom.randomDouble()) / (image_height - 1)

                val r = cam.getRay(u, v)

                pixel_color = ray_color(r, world).plus(pixel_color)
            }
            myWriter.write(pixel_color.writeColor(samples_per_pixel))
        }
    }
    myWriter.close()


    // show ppm picture
    val currentPath = File(".").canonicalPath
    val picturePath = "\'$currentPath\\$filename.ppm\'"

    println(picturePath)
    val k = Runtime.getRuntime().exec("notepad $filename.ppm")
    PPMViewer.display("$filename.ppm")


}

/**
 * @returns rgb vector with Vec3 percentage that lerps between white and blue vertically
 */
fun ray_color(r: Ray, world: Hittable): Vec3 {
    val rec: HitRecord? = world.hit(r, Interval(0.0, Double.POSITIVE_INFINITY));

    if (rec != null) {
        val percentages: Vec3 = (rec.normal + (Vec3(1, 1, 1))) / 2.0
        return percentages
    }

    val unitDirection = r.direction.unitVector
    val t = 0.5 * (unitDirection.y + 1)
    return ((1 - t) * Vec3(1, 1, 1)) + (t * Vec3(0.5, 0.7, 1.0)) //(1.0-t)*Vec3(1.0, 1.0, 1.0) + t*Vec3(0.5, 0.7, 1.0)
}
