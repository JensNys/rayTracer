class Interval(var min: Double, var max: Double) {

    // Default constructor: empty interval
    constructor() : this(Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY)

    // Function to calculate the size of the interval
    val size: Double by lazy {
        max - min
    }

    // Check if the interval contains a value x
    fun contains(x: Double): Boolean {
        return min <= x && x <= max
    }

    // Check if the interval surrounds a value x
    fun surrounds(x: Double): Boolean {
        return min < x && x < max
    }

    companion object {
        // Static instances for empty and universe intervals
        val empty = Interval(Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY)
        val universe = Interval(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY)
    }
}
