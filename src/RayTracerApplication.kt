import Cameras.Camera
import Cameras.NormalCamera
import Cameras.SimpleDiffuseCamera
import Geometry.HittableList
import Geometry.Shapes.Plane
import Geometry.Shapes.Sphere
import Geometry.Vec3
import PPMstuff.BufferToPPM
import PPMstuff.PPMViewer

fun main() {

    // World
    val world: HittableList = HittableList()
    world.add(Sphere(Vec3(0, 0, -1), 0.5))
    world.add(Sphere(Vec3(1, 0, -1), 0.25))
    world.add(Sphere(Vec3(-1, 0, -1), 0.25))
    world.add(Plane(Vec3(0.0,1.0,0.0),-0.3))

    //Cameras.Camera
    val cam: Camera = SimpleDiffuseCamera(16.0 / 9.0,400,20)

    val buffer =cam.render(world)

    //making the ppm picture
    val filename:String = "3spheres1plane"
    BufferToPPM.convert(buffer,filename)

    //opening ppm file
    TextEditorOpener.openInEditor(filename)
    //showing the picture
    PPMViewer.display("$filename.ppm")





}

