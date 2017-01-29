import com.sun.javafx.collections.MappingChange
import java.io.*
import java.util.*

private val PROBLEM_NAME = "guyaury"
val out = PrintWriter(File(PROBLEM_NAME + ".out"))
//ar sizeOfGraph = 0
var matrix = ArrayList<ArrayList<Int>>()
var n = 0
var nSize = 0
var path = ArrayList<Int>()
var pSize = 0
var vertex = 2
var m: Int = 0
//var masKey = ArrayList<Pair>()


fun reset(size: Int) {
    val N = size
    for (i in 0..N) {
        matrix.add(ArrayList<Int>())
        for (j in 0..N)
            matrix[i].add(0)
    }
}

public class Pair {
    private var key: Int = 0
    private var y: Int = 0

    constructor(key: Int, y: Int) {
        this.key = key
        this.y = y
    }

    fun gexKey(): Int {
        return key
    }

    fun getY(): Int {
        return y
    }
}

private fun solve(inp: Scanner) {

    var a = inp.nextInt()
    var b = inp.nextInt()
    var numb = 0
    for (i in 0..b-1){
        var str = inp.next()
        for (j in 0..a-1) {
            if (str[j] == '#') {
                matrix [i][j] = 1
                numb++
            } else {
                matrix[i][j] = 0
            }
        }
    }
    mapOf<Int,Int>()
    



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

fun main(args: Array<String>) {
    val `input` = Scanner(File(PROBLEM_NAME + ".in"))
    solve(`input`)
    `input`.close()
    out.close()
}