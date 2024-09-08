import Hittables.Sphere
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SphereTest{
    @Test
    fun zero(){
        val origin:Vec3 = Vec3(0,0,0)
        val sphere: Sphere = Sphere(Vec3(0,0,-1),0.5)
        val ray:Ray= Ray(origin,Vec3(0,0,-1))

        var hitRecord1 = (sphere.hit(ray, Interval.universe))
        var hitRecord2 = (sphere.alternativeHit(ray, Interval.universe))
        assertEquals(hitRecord1,hitRecord2)
        assertEquals(0.5,hitRecord1?.t)

    }
}