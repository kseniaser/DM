import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by Oktai on 04.11.2016.
 */
public class HateLabs {


    private static int n, dop;
    private static boolean[][] graph;
    private static ArrayList<Integer> hamPath;
    private static final String PROBLEM = "lca";

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new File(PROBLEM + ".in"));
        PrintWriter out = new PrintWriter(new File(PROBLEM + ".out"));

        solve(in, out);

        out.close();
        in.close();
    }

    private static void solve(Scanner in, PrintWriter out) throws Exception {
        int flag = 0;
        dop = n = in.nextInt();
        graph = new boolean[n + 1][n + 1];
        hamPath = new ArrayList<>();

        for (int i = 2; i <= n; i++) {
            String current = in.next();
            for (int jj = 0; jj < current.length(); jj++) {
                graph[i][jj + 1] = current.charAt(jj) == '1';
                graph[jj + 1][i] = !graph[i][jj + 1];
            }
        }

        hamPath.add(1);
        n--;


        int currentVertex = 1;

        while (n > 0) {
            insert(currentVertex + 1);
            currentVertex++;
            n--;
        }


        int current = dop - 1;
        while(current > 0) {
            if (graph[hamPath.get(current)][hamPath.get(0)]) break;
            current--;
        }

        while(flag == 0) {
            flag = 1;
            for (int i = current + 1; i < dop; ++i) {
                for (int j = 1; j <= current; ++j) {

                    if (graph[hamPath.get(i)][hamPath.get(j)]) {

                        int[] dopArray = new int[i - current];

                        for (int k = current + 1; k < i + 1; k++) {
                            dopArray[k - current - 1] = hamPath.get(k);
                        }

                        for (int k = i; k >= j + i - current; k--) {
                            hamPath.set(k, hamPath.get(k - i + current));
                        }

                        for (int k = j; k < j + i - current; k++) {
                            hamPath.set(k, dopArray[k - j]);
                        }

                        flag = 0;
                        current = i;
                        break;
                    }
                }
                if (flag == 0) break;
            }
        }


        for (Integer k : hamPath) {
            out.print(k + " ");
        }
    }


    private static void insert(int currentVertex) {
        int low = 0;
        int high = hamPath.size();
        int mid = 0;
        boolean currentAnswer = false;

        while (low < high) {
            mid = (low + high) / 2;
            currentAnswer = graph[hamPath.get(mid)][currentVertex];
            if (currentAnswer) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        if (currentAnswer) {
            hamPath.add(mid + 1, currentVertex);
        } else {
            hamPath.add(mid, currentVertex);
        }
        /*System.out.println("Current array: ");
        for (Integer i : hamPath) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.flush();*/
    }

    static class Scanner {
        BufferedReader br;
        StringTokenizer t;

        Scanner(File file) throws Exception {
            br = new BufferedReader(new FileReader(file));
            t = new StringTokenizer("");
        }

        boolean hasNext() throws Exception {
            while (!t.hasMoreTokens()) {
                String line = br.readLine();
                if (line == null)
                    return false;
                t = new StringTokenizer(line);
            }
            return true;
        }

        String next() throws Exception {
            if (hasNext()) {
                return t.nextToken();
            }
            return null;
        }

        int nextInt() throws Exception {
            return Integer.parseInt(next());
        }

        long nextLong() throws Exception {
            return Long.parseLong(next());
        }

        double nextDouble() throws Exception {
            return Double.parseDouble(next());
        }

        void close() throws Exception {
            br.close();
        }
    }
}

/*
import java.io.*
import java.util.*

private val PROBLEM_NAME = "lca"
val out = PrintWriter(File(PROBLEM_NAME + ".out"))
val N1 = 1003
var matrix = ArrayList<ArrayList<Int>>()
var n = 0
var nSize = 0
var path = ArrayList<Int>()
var pSize = 0
var vertex = 2
var m: Int = 0


fun reset() {
    for (i in 0..N1) {
        matrix.add(ArrayList<Int>())
        path.add(0)
        for (j in 0..N1)
            matrix[i].add(0)
    }
}

private fun buildingMatrix(inp: Scanner) {

    for (i in 2..n) {
        val str = `inp`.next()
        for (jj in 0..str.length - 1) {
            when (str[jj]) {
                '1' -> {
                    matrix[i][jj + 1] = 1
                    matrix[jj + 1][i] = 0
                }
                '0' -> {
                    matrix[i][jj + 1] = 0
                    matrix[jj + 1][i] = 1
                }
            }
        }
    }
}

private fun bin() {
    var l = 0
    var r = path.size
    m = 0
    while (l < r) {
        m = (l + r) / 2
        when (matrix[path[m]][vertex]) {
            1 -> l = m + 1
            0 -> r = m
        }
    }
    cont()
}

private fun cont() {
    when (matrix[path[m]][vertex]) {
        1 -> {
            //println("" +(m+1) + " " + path.size)
            //path[m+1]=vertex+1
            path.add(m + 1, vertex)
            //            if (m + 1 > pSize) {
            //                //pSize=m-1
            //            }
        }
        0 -> {
            //path[m]=vertex+1
            path.add(m, vertex)
            //            if (m > pSize) {
            //                //pSize=m
            //            }
        }
    }
    //pSize++
}


fun kindOfMagic(a: Int, b: Int, c: Int) {

    var mas = ArrayList<Int>()
    for (i in 0..c - b - 1) {
        mas.add(0)
    }
    var k = b + 1
    while (k <= c) {
        mas[k - b - 1] = path[k]
        k++
    }
    while (k >= a + c - b) {
        path[k] = path[k - c + b]
        k--
    }
    k = a
    while (k <= a + c - b - 1) {
        path[k] = mas[k - a]
        k++
    }
}

private fun solve(inp: Scanner) {
    reset()
    //println("-- " + path.size + " " + matrix.size)
    var ffffff = false
    var kkkkkk = false
    n = `inp`.nextInt()
    nSize = n - 1

    when (n) {
        1 -> {
            out.print("1 ")
            kkkkkk = true
        }
    //2 -> kkkkkk = true
        else -> kkkkkk = false
    }

    buildingMatrix(inp)

    path.add(1)
    pSize++
    n--

    while (n > 0) {
        bin()
        vertex++
        n--
    }

    var h = nSize
    val num = path[0]

    while (h > 0) {
        if (matrix[path[h]][num] == 0)
            h--
        else
            break
    }

    while (!ffffff) {
        ffffff = true
        for (i in h + 1..nSize) {
            for (j in 1..h) {
                if (matrix[path[i]][path[j]] == 1) {
                    kindOfMagic(j, h, i)
                    ffffff = false
                    h = i
                    break
                }
            }
            if (!ffffff)
                break
        }
    }

    if (!kkkkkk) {
        //out.print("" + path[nSize] + " ")
        for (t in 0..nSize) {
            out.print("" + path[t] + " ")
        }
        //
    }

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
 */