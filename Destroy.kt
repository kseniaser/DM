import java.io.*
import java.util.*
import kotlin.*

class Destroy {

    val NAME = "destroy"
    val inputFileName = NAME + ".in"
    val outputFileName = NAME + ".out"

    lateinit var disj: Array<Int>
    lateinit var connect: Array<Int>

    class Edge {
        var weight: Long = 0
        var id: Int = 0
        var u: Int = 0
        var v: Int = 0

        constructor(id: Int, u: Int, v: Int, weight: Long) {
            this.weight = weight
            this.id = id
            this.u = u
            this.v = v
        }
    }

    fun Recur(x: Int): Int {
        if (connect[x] == x) {
            return x;
        } else {
            connect[x] = Recur(connect[x])
            return connect[x]
        }
    }

    private fun solution(sIn: FastScanner, sOut: PrintWriter) {

        val n = sIn.nextInt()
        val m = sIn.nextInt()
        var s = sIn.nextLong()

        connect = Array(n+1, { index -> index })
        disj = Array(n+1, { index -> 0 })
        var edges = ArrayList<Edge>()

        for (i in 0..m - 1) {
            edges.add(Edge(i, sIn.nextInt() - 1, sIn.nextInt() - 1, sIn.nextLong()))
        }

        Collections.sort(edges) {
            x, y ->
            y.weight.compareTo(x.weight)
            //(y.weight - x.weight).toInt()
        }
        var heft = ArrayList<Edge>()

        for (i in edges) {
            if (Recur(i.u) != Recur(i.v)) {
                var a = Recur(i.u)
                var b = Recur(i.v)
                if (a != b) {
                    if (disj[a] == disj[b]) {
                        disj[a]++
                    }
                    if (disj[a] < disj[b]) {
                        connect[a] = b
                    } else {
                        connect[b] = a
                    }
                }
            } else {
                heft.add(i)
            }
        }
        heft.reverse()
        var result = ArrayList<Int>()
        for (i in heft) {
            if (i.weight > s) {
                break;
            } else {
                s -= i.weight
                result.add(i.id)
            }
        }
        Collections.sort(result) {
            x, y ->
            x - y
        }
        sOut.println(result.size)
        for (i in result) {
            sOut.print(i+1)
            sOut.print(" ")
        }


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
    Destroy().run()
}
