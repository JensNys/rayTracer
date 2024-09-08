package Hittables

import Interval
import Ray

class HittableList : Hittable {
    val scene: ArrayList<Hittable> = ArrayList()


    fun add(new: Hittable) {
        scene.add(new);
    }

    override fun hit(r: Ray, tInterval: Interval): HitRecord? {
        var tInterval = tInterval
        var closestRecord: HitRecord? = null;
        for (geometricObject in scene) {
            val tmpRecord: HitRecord? = geometricObject.hit(r, tInterval)

            //if there was a hit
            if (tmpRecord != null) {
                closestRecord = tmpRecord;
                tInterval = Interval(tInterval.min, closestRecord.t);

            }
        }
        return closestRecord
    }


}