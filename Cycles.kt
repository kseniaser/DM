import java.io.*
import java.util.*
import kotlin.*

class Cycles {

    val NAME = "cycles"
    val inputFileName = NAME + ".in"
    val outputFileName = NAME + ".out"

    var heft = IntArray(20)
    val point = BooleanArray(1048579) //2^20+3

    fun max (a:Int,b:Int):Int{
        if(a<b)
            return b
        else
            return a
    }

    fun recur(n: Int, u: Int) {
        point[u] = true;
        if (u == ((1 shl n) - 1))
            return;
        for (i in 0..n - 1) {
            if ((u and (1 shl i)) == 0 && !point[u + (1 shl i)]) {
                recur(n, u + (1 shl i))
            }
        }
    }

    private fun solution(sIn: FastScanner, sOut: PrintWriter) {

        val n = sIn.nextInt()
        val m = sIn.nextInt()
        for (i in 0..n - 1) {
            heft[i] = sIn.nextInt()
        }
        var p = 0
        while (p < (1 shl n)) {
            point[p] = false
            p++
        }
        for (i in 0..m - 1) {
            var v = sIn.nextInt()
            var q = 0
            for (j in 0..v - 1) {
                var k = sIn.nextInt()-1
                q += (1 shl k)
            }
            recur(n, q)
        }
        var r = 0
        var h = 0
        var t = 0
        while (t < (1 shl n)) {
            if (!point[t]) {
                var sum = 0
                var g = 0
                for (k in 0..n-1)
                    if ((t and (1 shl k)) > 0) {
                        sum += heft[k]
                        g++
                    }
                if (g > h) {
                    h = g
                    r = 0
                }
                r = max(r, sum)
            }
            t++
        }
        sOut.print(r)

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
        }*/

        fun nextLong(): Long {
            return next()?.toLong()!!
        }
    }
}


fun main(vararg args: String) {
    Cycles().run()
}
