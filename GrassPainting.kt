import java.io.*
import java.util.*
import kotlin.*

class GrassPainting {

    val NAME = "grassplant"
    val inputFileName = NAME + ".in"
    val outputFileName = NAME + ".out"

    var n =0
    var m =0
    var counter = 0

    var tabl = Array(100002, { index -> ArrayList<Int>() })
    var lca = Array(100002, { index -> IntArray(22) })
    var vert = IntArray(100002)
    var was = IntArray(100002)
    var result = IntArray(100002)
    var connect = IntArray(200004)


    fun dfs(x: Int, y: Int, z: Int) {
        vert[x] = z
        lca[x][0] = y
        counter++
        was[x] = counter
        for (i in 0..tabl[x].size - 1){
            if (tabl[x][i] != y) {
                dfs (tabl[x][i], x, z + 1)
            }
        }
        counter++
        result[x] = counter
    }

    fun match(x: Int, y: Int): Int {
        var x1 = x
        for (i in 18 downTo 0)
            if (vert[lca[x1][i]] >= y)
                x1 = lca[x1][i]
        return x1
    }

    fun getLCA(x: Int, y: Int): Int {
        var x1 = x
        var y1 = y
        var x2 = vert[x1]
        var y2 = vert[y1]
        if (x2 > y2)
            x1 = match (x1, y2)
        else if (y2 > x2)
            y1 = match (y1, x2)
        if (x1 == y1)
            return x1
        for (i in 18 downTo 0) {
            if (lca[x1][i] != lca[y1][i]) {
                x1 = lca[x1][i]
                y1 = lca[y1][i]
            }
        }
        return lca[x1][0];
    }

    fun update(x: Int, y: Int) {
        var x1 = x
        while (x1 <= n * 2) {
            connect[x1] += y
            x1 += (x1 and -x1)
        }
    }

    fun str(x: Int): Int {
        var x1 = x
        var sum = 0
        while (x1 != 0) {
            sum += connect[x1]
            x1 -= (x1 and -x1)
        }
        return sum
    }

    private fun solution(sIn: FastScanner, sOut: PrintWriter) {

        n = sIn.nextInt()
        m = sIn.nextInt()
        for(i in 1..n-1){
            var a = sIn.nextInt()
            var b = sIn.nextInt()
            tabl[a].add(b)
            tabl[b].add(a)
        }
        dfs (1, 1, 0);
        for (i in 1..18) {
            for (j in 1..n) {
                lca[j][i] = lca[lca[j][i - 1]][i - 1]
            }
        }
        for (i in 1..m){
            var comand = sIn.next()
            var a = sIn.nextInt()
            var b = sIn.nextInt()
            if (comand.equals("P")){
                update (was[a], 1)
                update (was[b], 1)
                update (was[getLCA (a, b)], -2)
            }else{
                var t = b
                if (vert[a] > vert[b])
                    t = a
                sOut.println (str (result[t]) - str (was[t] - 1))
            }
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
    GrassPainting().run()
}
