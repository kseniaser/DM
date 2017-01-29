import java.io.*
import java.util.*
import kotlin.*

class HeavyLight {

    val NAME = "test"
    val inputFileName = NAME + ".in"
    val outputFileName = NAME + ".out"


    private fun solution(sIn: FastScanner, sOut: PrintWriter) {

    }

    fun run() {
        FastScanner(inputFileName).use { sIn ->
            PrintWriter(outputFileName).use { sOut ->
                solution(sIn, sOut)
            }
        }
    }

    private class FastScanner : Closeable {
        private val bufferedReader: BufferedReader
        private var stringTokenizer: StringTokenizer

        constructor(inputStream: InputStream) {
            bufferedReader = BufferedReader(InputStreamReader(inputStream))
            stringTokenizer = StringTokenizer(bufferedReader.readLine())
        }

        constructor(file: File) : this(FileInputStream(file))

        constructor(fileName: String) : this(File(fileName))

        override fun close() {
            bufferedReader.close()
        }

        fun hasNext(): Boolean {
            while (!stringTokenizer.hasMoreTokens()) {
                stringTokenizer = StringTokenizer(bufferedReader.readLine())

            }
            return stringTokenizer.hasMoreTokens()
        }

        fun next(): String? {
            if (hasNext())
                return stringTokenizer.nextToken()
            return null
        }

        fun nextInt(): Int {
            return next()?.toInt()!!
        }
        /*fun nextDouble(): Double {
            return next()?.toDouble()!!
        }

        fun nextLong(): Long {
            return next()?.toLong()!!
        }*/
    }
}

fun main(vararg args: String) {
    HeavyLight().run()
}
