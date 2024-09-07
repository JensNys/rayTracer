class Sphere(val center:Vec3,val radius:Double) : Hittable{

    override fun hit(r: Ray, tInterval: Interval): HitRecord? {
        val oc:Vec3 = center-r.origin//vector from origin to the center

        val a:Double = r.direction.squaredLength
        val h:Double = (r.direction * oc)
        val c:Double = oc.squaredLength - radius*radius

        val discriminant_div4 = h*h-a*c;
        if  (discriminant_div4<0.0){
            return null
        }
        val sqrtDiscriminant = Math.sqrt(discriminant_div4)
        var t:Double = (h-sqrtDiscriminant)/a //smallest intersection

        if (!tInterval.contains(t)){
            t = (h+sqrtDiscriminant)/a//furthest intersection
            if (!tInterval.contains(t)){
                return null
            }
        }
        val hitPoint = r.point_at_parameter(t);

        var outward_normal:Vec3 = (hitPoint-center).unitVector;

        val front_face: Boolean
        val normal:Vec3
        if ((r.direction * outward_normal) > 0.0) {
            // ray is inside the sphere
            normal = -outward_normal
            front_face = false
        } else {
            // ray is outside the sphere
            normal = outward_normal
            front_face = true
        }
        return HitRecord(hitPoint,normal,t,front_face)


    }


}