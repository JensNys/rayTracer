/*
    must have a hitPoint, the rest is optional
 */
class HitRecord(val hitPoint:Vec3,val normal:Vec3,val t:Double,val front_face:Boolean) {
    override fun toString(): String {
        return "HitRecord(hitPoint=$hitPoint, normal=$normal, t=$t, front_face=$front_face)"
    }
}