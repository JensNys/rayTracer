class Plane : Hittable {
    //class represents the plane ax+by+cz=d;
    var normal: Vec3
    var d: Double

    constructor(a: Double, b: Double, c: Double, d: Double) {
        normal = Vec3(a, b, c)
        this.d = d
    }

    constructor(normal: Vec3, d: Double) {
        this.normal = normal
        this.d = d
    }

    override fun hit(r: Ray, tInterval: Interval): HitRecord? {
        val t = (d - (r.origin.dot(normal))) / (r.direction.dot(normal))
        if (!tInterval.contains(t)) {
            return null
        }
        val hitPoint = r.point_at_parameter(t)

        rec.set_face_normal(r, normal)
        return true
    }
}