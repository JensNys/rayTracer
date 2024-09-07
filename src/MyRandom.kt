import java.util.*

class MyRandom {
    companion object {
        /**
         * returns random double in [0,1]
         */
        fun randomDouble(): Double {
            val r = Random()
            return r.nextDouble()
        }

        /**
         * returns random double in [min,max]
         */
        fun randomDouble(min: Double, max: Double): Double {
            val r = Random()
            return min + (max - min) * r.nextDouble()
        }

    }


}