import java.io.File
import java.io.FileWriter

fun main() {





    // World
    val world: HittableList = HittableList()
    world.add(Sphere(Vec3(0, 0, -1), 0.5))
    world.add(Sphere(Vec3(1, 0, -1), 0.25))
    world.add(Sphere(Vec3(-1, 0, -1), 0.25))
    world.add(Plane(Vec3(0.0,1.0,0.0),-0.3))

    //Camera
    val cam: Camera = NormalCamera(16.0 / 9.0,4t 00,20)

    cam.render(world)
    //file
    // render
//    val filename = "sphere1rpp"
//    val myWriter = FileWriter("$filename.ppm")
//    myWriter.write("P3\n")
//    myWriter.write("$image_width $image_height\n255\n")
//
//    for (j in image_height - 1 downTo 0) {
//        print("lines remaining: $j\n")
//        for (i in 0 until image_width) {
//            var pixel_color = Vec3(0, 0, 0)
//            for (s in 0 until samples_per_pixel) {
//                val u: Double = (i.toDouble() + MyRandom.randomDouble()) / (image_width - 1)
//                val v: Double = (j.toDouble() + MyRandom.randomDouble()) / (image_height - 1)
//
//                val r = cam.getRay(u, v)
//
//                pixel_color = cam.ray_color(r, world).plus(pixel_color)
//            }
//            myWriter.write(pixel_color.writeColor(samples_per_pixel))
//        }
//    }
//    myWriter.close()
//
//
//    // show ppm picture
//    val currentPath = File(".").canonicalPath
//    val picturePath = "\'$currentPath\\$filename.ppm\'"
//
//    println(System.getProperty("os.name"))
//    if (System.getProperty("os.name").startsWith("Windows")) {
//        val k = Runtime.getRuntime().exec("notepad $filename.ppm")
//    }else{
//        val k = Runtime.getRuntime().exec("gedit $filename.ppm")
//    }
//
//    PPMViewer.display("$filename.ppm")


}

