import Geometry.Ray
import Geometry.Shapes.Sphere
import Geometry.Vec3
import org.junit.jupiter.api.Test

class CameraTest{
    @Test
    fun zero(){


        val origin: Vec3 = Vec3(0,0,0)
        val sphere: Sphere = Sphere(Vec3(0,0,-1),0.5)
        val ray: Ray = Ray(origin, Vec3(0,0,-1))
        println(sphere.hit(ray,Interval.universe))
    }
}