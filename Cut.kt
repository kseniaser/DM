//import java.io.BufferedReader
//import java.io.File
//import java.io.FileReader
//import java.io.PrintWriter
//import java.util.*
//
//class Cut {
//
//    private val PROBLEM_NAME = "test"
//    val out = PrintWriter(File(PROBLEM_NAME + ".out"))
//
//    val INF = 100000000
//    val MAXN = 500
//    var  n = Int
//    var s = 0
//    var t = Int
//    lateinit var dist : IntArray
//    lateinit var q : IntArray
//    lateinit var ptr : IntArray
//    lateinit var used : BooleanArray
//    lateinit var edges : ArrayList<edge>
//    lateinit var ans : ArrayList<Int>
//    lateinit var g: Array<MutableList<Int>>
//
//    data class edge(var from: Int, var to: Int,var cap: Int, var flow: Int)
//
//    fun add_edge(from:Int,to:Int, cap:Int) {
//        var e1 = edge(from, to, cap, 0)
//        var e2 = edge(to, from, cap, 0)
//        g[from].add( edges.size);
//        g[to].add( edges.size + 1);
//        edges.add(e1);
//        edges.add(e2);
//    }
//
//    fun bfs():Boolean {
//        var c1 = 0;
//        var c2 = 0;
//        q[c2++] = s;
//        memset(dist, -1, n * sizeof(dist[0]));
//        dist[s] = 0;
//        while (c1 < c2 && dist[t] == -1) {
//            int v = q[c1++];
//            for (size_t i = 0; i < g[v].size(); i++) {
//                int curr = g[v][i];
//                int to = edges[curr].to;
//                if (dist[to] == -1 && edges[curr].flow < edges[curr].cap) {
//                    q[c2++] = to;
//                    dist[to] = dist[v] + 1;
//                }
//            }
//        }
//        return dist[t] != -1;
//    }
//
//    int dfs(int v, int flow) {
//        if (!flow) return 0;
//        if (v == t) return flow;
//        for (; ptr[v] < (int) g[v].size(); ptr[v]++) {
//            int curr = g[v][ptr[v]];
//            int to = edges[curr].to;
//            if (dist[to] != dist[v] + 1) continue;
//            int pushed = dfs(to, min(flow, edges[curr].cap - edges[curr].flow));
//            if (pushed) {
//                edges[curr].flow += pushed;
//                // if curr % 2 = 0 then curr + 1 else curr - 1
//                edges[curr^1].flow -= pushed;
//                return pushed;
//            }
//        }
//        return 0;
//    }
//
//
//    void dfs2(int v){
//        if (used[v]) return;
//        //    std::cout << "in " << v << std::endl;
//        ans.push_back(v);
//        used[v] = true;
//        for (; ptr[v] < (int) g[v].size(); ptr[v]++) {
//            int curr = g[v][ptr[v]];
//            int to = edges[curr].to;
//            if (edges[curr].cap > edges[curr].flow) {
//                dfs2(to);
//            }
//        }
//    }
//
//
//
//    private fun solve(inp: Scanner) {
//
//
//
//    }
//
//    private class Scanner(file: File) {
//        val br = BufferedReader(FileReader(file))
//        var st = StringTokenizer("")
//
//        fun hasNext(): Boolean {
//            while (!st.hasMoreTokens()) {
//                val line = br.readLine() ?: return false
//                st = StringTokenizer(line)
//            }
//            return true
//        }
//
//        fun next(): String = if (hasNext()) st.nextToken() else ""
//        fun nextInt(): Int = Integer.parseInt(next())
//        //fun nextLong(): Long = java.lang.Long.parseLong(next())
//        //fun nextDouble(): Double = java.lang.Double.parseDouble(next())
//        fun close() = br.close()
//    }
//
//    fun run(){
//        val `input` = Scanner(File(PROBLEM_NAME + ".in"))
//        solve(`input`)
//        `input`.close()
//        out.close()
//    }
//
//
//}
//fun main(vararg args: String) {
//    Cut().run()
//}
