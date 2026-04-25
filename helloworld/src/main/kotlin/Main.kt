import com.diacht.ktest.compose.startTestUi
import kotlinx.coroutines.*
import org.example.helloworld.BuildConfig
import java.net.URL
import kotlin.math.tanh

fun seed(): String = "gggag"
fun labNumber() : Int = BuildConfig.LAB_NUMBER

suspend fun getNumberFromServer(message: String): Int {
    return withContext(Dispatchers.IO) {

        val url = URL("http://diacht.2vsoft.com/api/send-number?message=$message")
        val connection = url.openConnection()
        connection.connect()

        val input = connection.getInputStream()
        val buffer = ByteArray(128)
        val bytesRead = input.read(buffer)

        input.close()

        String(buffer, 0, bytesRead).toInt()
    }
}

suspend fun serverDataCalculate(strList: List<String>): Double = coroutineScope {

    val deferredResults = strList.map { str ->
        async {
            getNumberFromServer(str)
        }
    }

    val results = deferredResults.awaitAll()

  //println("Server values: $results")

    val minValue = results.minOrNull() ?: 0
  //println("Min value: $minValue")

    tanh(minValue.toDouble())
}

fun main(args: Array<String>)= runBlocking{
    println("Лабораторна робота №${labNumber()} користувача ${seed()}")

    val strList = listOf(
        "100", "200", "300", "400", "500", "600", "700"
    )

    val result = serverDataCalculate(strList)

    println("Result: $result")
    startTestUi(seed(), labNumber())
}