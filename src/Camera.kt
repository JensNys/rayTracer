class Camera {
    var origin: Vec3
    var horizontal: Vec3
    var vertical: Vec3
    var lower_left_corner: Vec3


    init {
        val aspect_ratio = 16.0 / 9.0
        val viewport_height = 2.0
        val viewport_width = aspect_ratio * viewport_height
        val focal_length = 1.0

        origin = Vec3(0, 0, 0)
        horizontal = Vec3(viewport_width, 0.0, 0.0)
        vertical = Vec3(0.0, viewport_height, 0.0)
        lower_left_corner =
            origin -(horizontal / 2.0)- (vertical / 2.0) - (Vec3(0.0, 0.0, focal_length))
    }

    fun getRay(u: Double, v: Double): Ray {
        return Ray(origin, lower_left_corner+ (u*horizontal)+ (v*vertical) - origin)
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
}