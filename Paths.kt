import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.PrintWriter
import java.util.*

class Paths {

    private val PROBLEM_NAME = "paths"
//    private val PROBLEM_NAME = "test"
    val out = PrintWriter(File(PROBLEM_NAME + ".out"))
    lateinit var used: IntArray
    lateinit var match: IntArray
    lateinit var ancestor: Array<MutableList<Int>>

    fun kun(v: Int,currentUsed: Int): Boolean {
        if (used[v] == currentUsed) {
            return false
        }
        used[v] = currentUsed
        for (w in ancestor[v]) {
            if (match[w] == -1 || kun(match[w],currentUsed)) {
                match[w] = v
                return true
            }
        }
        return false
    }

    private fun solve(inp: Scanner) {
        val n = inp.nextInt()
        val m = inp.nextInt()
        used = IntArray(n, { void -> 0})
        match = IntArray(n, { index -> -1 })
        ancestor =  Array(n, { index -> ArrayList<Int>() })

        for (i in 0..m-1) {
            var a = inp.nextInt()-1
            var b = inp.nextInt()-1
            ancestor[a].add(b)
        }
        var matching = 0
        for (v in 0..n-1) {
            if (kun(v,v+1)) {
                matching++
            }
        }

        out.println(n - (matching + 1) + 1);



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
    Paths().run()
}