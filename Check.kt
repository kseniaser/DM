import java.io.*
import java.util.*
import kotlin.*

class Check {

    val NAME = "check"
    val inputFileName = NAME + ".in"
    val outputFileName = NAME + ".out"

    var elements = BooleanArray(1 shl 10)
    var exit = false
    lateinit var matr: Array<MutableList<Int>>


    fun pow(x: Int, y: Int): Long {
        var xx = x.toLong()
        for (i in 2..y)
            xx *= x
        return xx
    }

    fun axiom1(x: Int, y: Int, n: Int): Boolean {
        var bitX = 0
        var bitY = 0
        var i = 0
        while (i <= n) {
            bitX += (x and (1 shl i)) shr i
            bitY += (y and (1 shl i)) shr i
            i++
        }
        return (bitX > bitY)
    }

    fun axiom2(n: Int, m: Int) {
        val limit = (1 shl n) - 1
        var i = 0
        while (i <= m) {
            var p = 0
            var j = 0
            while (j < matr[i].size) {
                p = p or (1 shl (matr[i][j] - 1))
                j++
            }
            elements[p] = true
            i++
        }
        i = 0
        while (i <= limit) {
            if (elements[i]) {
                check@ for (j in 0..limit) {
                    var k = 0
                    while (k < n) {
                        var f = 1 shl k
                        if ((((i and f) == 0) && (f == (j and f))) || !axiom1(i, j, n - 1)) {
                            continue@check
                        }
                        k++
                    }
                    if (!elements[j]) {
                        exit = true
                    }
                }
            }
            i++
        }
    }

    fun axiom3(n: Int) {
        val limit = (1 shl n) - 1
        var i = 1
        while (i <= limit) {
            check@ for (j in 0..limit) {
                if (elements[i] && elements[j] && axiom1(i, j, n - 1)) {
                    var t = i xor j
                    var k = 0
                    while (k  < n) {
                        var f = 1 shl k
                        if (!((t and f).equals(0))) {
                            if (elements[(j or f)] && ((j or f) != j)) {
                                continue@check
                            }
                        }
                        k++
                    }
                    exit = true
                }
            }
            i++
        }
    }


    private fun solution(sIn: FastScanner, sOut: PrintWriter) {

        var pointer = 0
        var n = sIn.nextInt()
        var m = sIn.nextInt() - 1

        matr = Array(m + 1, { index -> ArrayList<Int>() })

        var i =0
        while (i <= m) {
            var p = sIn.nextInt()
            if (p == 0) {
                pointer++
            } else {
                var j = 0
                while (j < p) {
                    matr[i].add(sIn.nextInt())
                    j++
                }
            }
            i++
        }
        if (pointer == 0) {
            exit = true
        }
        if (!exit) {
            axiom2(n, m)
        }
        if (!exit) {
            axiom3(n)
        }
        if (exit) {
            sOut.print("NO")
        } else {
            sOut.print("YES")
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
        }

        fun nextLong(): Long {
            return next()?.toLong()!!
        }*/

    }
}

fun main(vararg args: String) {
    Check().run()
}