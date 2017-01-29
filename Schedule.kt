import java.io.*
import java.util.*
import kotlin.*

class Schedule {

    val NAME = "schedule"
    val inputFileName = NAME + ".in"
    val outputFileName = NAME + ".out"

    private fun solution(sIn: FastScanner, sOut: PrintWriter) {
        val list = ArrayList<kotlin.Pair<Int,Int>>()
        val n = sIn.nextInt()
        for (i in 0..n-1)
            list.add(kotlin.Pair(sIn.nextInt(), sIn.nextInt()))
        Collections.sort(list) {
            x, y ->
            if (x.first == y.first )
                x.second - y.second
            else
                x.first - y.first
        }

        val queue = PriorityQueue<Int>()
        var ans: Long = 0L
        var counter = 1
        for (i in list) {
            val d = i.first
            val w = i.second
            ans += w
            queue.add(w)
            if (d >= counter)
                counter++
            else
                queue.poll()
        }
        while (!queue.isEmpty()) {
            ans -= queue.poll()
        }
        sOut.print(ans)
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
    Schedule().run()
}