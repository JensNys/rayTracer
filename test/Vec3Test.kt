import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.math.sqrt

class Vec3Test {

    private lateinit var v1: Vec3
    private lateinit var v2: Vec3

    @BeforeEach
    fun init() {
        v1 = Vec3(1, 2, 3)
        v2 = Vec3(4, 5, 6)
    }

    @Test
    fun getters() {
        assertEquals(1.0, v1.x)
        assertEquals(2.0, v1.y)
        assertEquals(3.00, v1.z)
        assertEquals(1.0, v1.R())
        assertEquals(2.0, v1.G())
        assertEquals(3.0, v1.B())
    }

    @Test
    fun plus() {
        assertEquals(Vec3(5, 7, 9), v1 + v2)
    }

    @Test
    fun minus() {
        assertEquals(Vec3(3, 3, 3), v2 - v1)
    }

    @Test
    fun scale() {
        assertEquals(Vec3(3, 6, 9), v1*3.0)
    }

    @Test
    fun length() {
        val v3 = Vec3(3, 4, 0)
        assertEquals(sqrt(14.0), v1.length)
        assertEquals(sqrt(77.0), v2.length)
        assertEquals(5.0, v3.length)
    }

    @Test
    fun squaredLength() {
        val v1 = Vec3(1.0, 2.0, 3.0)
        val v2 = Vec3(4.0, 5.0, 6.0)
        assertEquals(14.0, v1.squaredLength)
        assertEquals(77.0, v2.squaredLength)
    }

    @Test
    fun makeUnitVector() {
        val unit = v1.unitVector
        assertEquals(1.0, unit.length)
        assertTrue(unit.isUnitVector)
    }

    @Test
    fun getUnitVector() {
        assertTrue(v1.unitVector.isUnitVector)
    }

    @Test
    fun dot() {
        assertEquals(32.0, v1.dot(v2))
        val v3 = Vec3(2.0, 7.0, 1.0)
        val v4 = Vec3(8.0, 2.0, 8.0)
        assertEquals(38.0, v3.dot(v4))
    }

    @Test
    fun cross() {
        val v3 = Vec3(1.0, 0.0, 0.0)
        val v4 = Vec3(0, 1, 0)
        assertEquals(Vec3(0, 0, 1), v3.cross(v4))
        assertEquals(Vec3(0, 0, -1), v4.cross(v3))
        assertEquals(Vec3(-3, 6, -3), v1.cross(v2))
        assertEquals(Vec3(3, -6, 3), v2.cross(v1))
    }

    @Test
    fun testToString() {
        val v3 = 2.0 * v1
        println(v3.toString())
    }

    @Test
    fun immutable() {

    }


}
