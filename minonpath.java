import java.io.*;
import java.util.*;

import static java.lang.StrictMath.ceil;
import static java.lang.StrictMath.log;

//fun ceil(x: Double): Int = Math.ceil(x).toInt()
//fun log(x: Double): Float = Math.log(x).toFloat()
// ceil(log(x) / log(2) + 1e-10);


//fun reset() {
//    for (i in 0..200006) {
//        lvl.add(0)
//        p.add(0)
//        r.add(0)
//        ans.add(0)
//        up.add(ArrayList<Int>())
//        for (j in 0..25)
//            up[i].add(0)
//    }
//}


public class minonpath {
    FastScanner in;
    PrintWriter out;

    private final int INF = Integer.MAX_VALUE;

    private ArrayList<Integer>[] graph;
    private int n;
    private int log2;
    private int m;
    private int[] d;
    private int[][] dp;
    private int[][] cs;

    private int min (int a,int b){
        if (a<b){
            return a;
        } else {
            return b;
        }
    }

    private int lca(int l, int r) {
        int localCst = INF;
        int v, u;
        if (d[l] < d[r]) {
            v = l;
            u = r;
        } else {
            v = r;
            u = l;
        }
        for (int i = log2; i >= 0; i--) {
            if (d[u] - d[v] >= (1 << i)) {
                localCst = min(localCst, cs[u][i]);
                u = dp[u][i];
            }
        }
        if (v == u) {
            return localCst;
        }
        for (int i = log2; i >= 0; i--) {
            if (dp[v][i] != dp[u][i]) {
                localCst = min(min(cs[v][i], cs[u][i]), localCst);
                v = dp[v][i];
                u = dp[u][i];
            }
        }
        return min(localCst, min(cs[v][0], cs[u][0]));
    }

    private void dfs(int vertex, int depth) {
        d[vertex] = depth;
            if (graph[vertex] != null) {
            for (int v : graph[vertex]) {
                dfs(v, depth + 1);
            }
        }
    }

    private int log2(int x) {
        return (int) ceil(log(x) / log(2) + 1e-10);
    }

    public void solve() throws IOException {

        n = in.nextInt();
        log2 = log2(n);
        graph = new ArrayList[n];

        d = new int[n];
        dp = new int[n][log2 + 1];

        cs = new int[n][log2 + 1];

        for (int i = 1; i < n; i++) {
            int x = in.nextInt() - 1;
            if (graph[x] == null) graph[x] = new ArrayList<>();
            graph[x].add(i);
            dp[i][0] = x;
            cs[i][0] = in.nextInt();
        }

        dfs(0, 0);

        for (int j = 1; j <= log2; j++) {
            for (int i = 0; i < n; i++) {
                dp[i][j] = dp[dp[i][j - 1]][j - 1];
                cs[i][j] = min(cs[dp[i][j - 1]][j - 1], cs[i][j - 1]);
            }
        }

        m = in.nextInt();
        for (int i = 0; i < m; i++) {
            out.println(lca(in.nextInt() - 1, in.nextInt() - 1));
        }

    }

    public void run() {
        try {
            in = new FastScanner(new File("lca.in"));
            out = new PrintWriter(new File("lca.out"));

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(File f) {
            try {
                br = new BufferedReader(new FileReader(f));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

    }

    public static void main(String[] arg) {
        new minonpath().run();
    }
}

