import java.io.*
import java.util.*
import kotlin.*

class Rainbow {

    val NAME = "test"
    val inputFileName = NAME + ".in"
    val outputFileName = NAME + ".out"

    class Trio(var from: Int, var to: Int, var color: Int)

    var n = 0
    var m = 0

    // set works faster than array
    var mult = HashSet<Int>()
    var copyMult = HashSet<Int>()

    var arrOfTrio = ArrayList<Trio>()

    var tabl = Array(5005, { index -> ArrayList<Int>() })

    var deg = IntArray(105, { index -> -1 })
    var ancest = IntArray(105, { index -> -1 })
    var before = IntArray(5005, { index -> -2 })

    var shade = BooleanArray(105, { index -> false })
    var first = BooleanArray(5005, { index -> false })
    var second = BooleanArray(5005, { index -> false })
    var was = BooleanArray(5005, { index -> false })


    fun recur(a: Int): Int {
        when (ancest[a]) {
            a -> return a
        }
        ancest[a] = recur(ancest[a])
        return ancest[a]
    }

    fun cr(a: Int, b: Int) {
        when (a == b) {
            false -> {
                when (deg[a]) {
                    deg[b] -> deg[a]++
                }
                when (deg[a] < deg[b]) {
                    true -> ancest[a] = b
                    false -> ancest[b] = a
                }
            }
        }
    }

    fun cond(): Boolean {
        val queue: Queue<Int> = ArrayDeque<Int>()
        for (i in 0..m - 1) {
            was[i] = false
            when (first[i]) {
                true -> {
                    queue.add(i)
                    before[i] = -2
                    was[i] = true
                }
            }
        }
        while (queue.isNotEmpty()) {
            val vertex = queue.poll()
            when (second[vertex]) {
                true -> {
                    var end = vertex
                    while (end != -2) {
                        when (copyMult.contains(end)) {
                            true -> {
                                shade[arrOfTrio[end].color] = true
                                mult.add(end)
                                copyMult.remove(end)
                            }
                            false -> {
                                shade[arrOfTrio[end].color] = false
                                mult.remove(end)
                                copyMult.add(end)
                            }
                        }
                        end = before[end]
                    }
                    return true
                }
            }
            for (i in tabl[vertex]) {
                when (was[i]) {
                    false -> {
                        was[i] = true
                        before[i] = vertex
                        queue.add(i)
                    }
                }
            }
        }
        return false
    }

    private fun solution(sIn: FastScanner, sOut: PrintWriter) {
        n = sIn.nextInt()
        m = sIn.nextInt()
        for (i in 0..m - 1) {
            arrOfTrio.add(Trio(sIn.nextInt() - 1, sIn.nextInt() - 1, sIn.nextInt()))
            copyMult.add(i)
        }
        while (true) {
            for (i in 0..m - 1) {
                tabl[i].clear()
            }
            for (i in mult) {
                deg = IntArray(n+1, { index -> 0 })
                ancest = IntArray(n+1, { index -> index })
                for (j in mult) {
                    if (j == i) {
                        continue
                    }
                    val a = recur(arrOfTrio[j].from)
                    val b = recur(arrOfTrio[j].to)
                    cr(a, b)
                }
                for (j in copyMult) {
                    when (arrOfTrio[i].color == arrOfTrio[j].color || !shade[arrOfTrio[j].color]) {
                        true -> tabl[j].add(i)
                    }
                    when (recur(arrOfTrio[j].from) == recur(arrOfTrio[j].to)) {
                        false -> tabl[i].add(j)
                    }
                }
            }
            //without that cycling
            //wtf why
            first = BooleanArray(m, { index -> false })
            second = BooleanArray(m, { index -> false })

            deg = IntArray(n+1, { index -> 0 })
            ancest = IntArray(n+1, { index -> index })
            for (i in mult) {
                val a = recur(arrOfTrio[i].from)
                val b = recur(arrOfTrio[i].to)
                cr(a, b)
            }
            for (i in copyMult) {
                when (shade[arrOfTrio[i].color]) {
                    false -> second[i] = true
                }
                when (recur(arrOfTrio[i].from) == recur(arrOfTrio[i].to)) {
                    false -> first[i] = true
                }
            }
            if (!cond()) {
                break
            }
        }
        sOut.println(mult.size)
        for (i in mult) {
            sOut.print("" + (i + 1) + " ")
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
    Rainbow().run()
}