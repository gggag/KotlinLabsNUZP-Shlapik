import com.diacht.ktest.compose.startTestUi
import org.example.helloworld.BuildConfig
import kotlin.math.abs
import kotlin.math.ln
import kotlin.math.min
import kotlin.math.cbrt

fun seed(): String = "gggag"

fun labNumber() : Int = BuildConfig.LAB_NUMBER

fun main(args: Array<String>) {
    println("Лабораторна робота №${labNumber()} користувача ${seed()}")
    startTestUi(seed(), labNumber())
}

fun iCalculate(
    x0: Int = 111,
    x1: Int = 76,
    x2: Int = -123
): Double {
    val minValue = min(abs(x0), min(abs(x1), abs(x2)))
    return ln(minValue.toDouble())
}

fun dCalculate(
    x0: Double = 30.94,
    x1: Double = -48.59,
    x2: Double = -133.2,
    x3: Double = 57.95,
    x4: Double = 83.25
): Double {
    val sum = abs(x0) + abs(x1) + abs(x2) + abs(x3)+abs(x4)
    return cbrt(sum)
}

fun strCalculate(
    x0: String,
    x1: String
): Int {
    require(x0.length == x1.length && x0.length % 2 == 0) {
        "Strings must have equal even length"
    }

    var result = 0

    for (i in x0.indices) {
        val a = x0[i]
        val b = x1[i]
        if (a == 'T' || a == 'C') {
            if (a != b) {
                result += if ((a == 'C' && b == 'G') || (a == 'G' && b == 'C')) 2 else 1
            }
        }
    }

    return result
}