import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.PrintWriter
import java.util.*

class Matching {

    private val PROBLEM_NAME = "matching"
    val out = PrintWriter(File(PROBLEM_NAME + ".out"))
    lateinit var used: BooleanArray
    lateinit var chain: IntArray
    lateinit var graph: Array<MutableList<Int>>


    fun kuhn(v: Int): Boolean {
        if (used[v]) {
            return false
        }
        used[v] = true;

        for (w in graph[v]) {
            if (chain[w] == -1 || kuhn(chain[w])) {
                chain[w] = v
                return true
            }
        }
        return false
    }

    private fun solve(inp: Scanner) {


        val n = inp.nextInt()
        val m = inp.nextInt()
        val k = inp.nextInt()

        used = BooleanArray(n, { void -> false})
        chain = IntArray(m, { index -> -1 })
        graph =  Array(n, { index -> ArrayList<Int>() })

        for (i in 0..k-1) {
            var v = inp.nextInt() - 1
            var w = inp.nextInt() - 1
            graph[v].add(w)
        }

        Arrays.fill(chain, -1)
        var maxMatching = 0
        for (v in 0..n-1) {
            Arrays.fill(used, false)
            if (kuhn(v)) {
                maxMatching++
            }
        }
        out.println(maxMatching)

    }

    private class Scanner(file: File) {
        val br = BufferedReader(FileReader(file))
        var st = StringTokenizer("")

        fun hasNext(): Boolean {
            while (!st.hasMoreTokens()) {
                val line = br.readLine() ?: return false
                st = StringTokenizer(line)
            }
            return true
        }

        fun next(): String = if (hasNext()) st.nextToken() else ""
        fun nextInt(): Int = Integer.parseInt(next())
        //fun nextLong(): Long = java.lang.Long.parseLong(next())
        //fun nextDouble(): Double = java.lang.Double.parseDouble(next())
        fun close() = br.close()
    }

    fun run(){
        val `input` = Scanner(File(PROBLEM_NAME + ".in"))
        solve(`input`)
        `input`.close()
        out.close()
    }


}
fun main(vararg args: String) {
    Matching().run()
}