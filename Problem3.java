import java.io.*;
import java.util.*;

public class Problem3 {
    FastScanner in;
    PrintWriter out;

    boolean[] isTerm;
    List<Integer>[] edges;
    int[] was;
    int[] prev;
    int[] d;
    List<Integer>[] revEdges;
    private static final int MOD = 1_000_000_007;
    HashSet<Integer> inCycle;

    void count(int u) {
        was[u] = 1;
        for (int v : revEdges[u]) {
            if (was[v] == 0) {
                count(v);
            }
            d[u] = (d[u] + d[v]) % MOD;
        }
    }

    void check(int u, boolean find) {
        if (was[u] > 0) {
            return;
        }
        was[u] = 1;
        for (int v : edges[u]) {
            if (was[v] == 0) {
                prev[v] = u;
                check(v, find);
            }
            if (was[v] == 1 && find) {
                inCycle.add(v);
            }
        }
        was[u] = 2;
    }

    void solve() {
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        d = new int[n];
        isTerm = new boolean[n];
        int[] term = new int[k];
        for (int i = 0; i < k; ++i) {
            int x = in.nextInt() - 1;
            isTerm[x] = true;
            term[i] = x;
        }

        edges = new ArrayList[n];
        revEdges = new ArrayList[n];
        for (int i = 0; i < n; ++i) {
            edges[i] = new ArrayList<>();
            revEdges[i] = new ArrayList<>();
        }
        was = new int[n];
        prev = new int[n];
        for (int i = 0; i < m; ++i) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            in.next();
            edges[u].add(v);
            revEdges[v].add(u);
        }
        Arrays.fill(prev, -1);
        inCycle = new HashSet<>();
        check(0, true);
        if (inCycle.size() > 0) {
            Arrays.fill(was, 0);
            inCycle.stream().forEach(u -> check(u, false));
            for (int i = 0; i < k; ++i) {
                if (was[term[i]] > 0) {
                    out.println(-1);
                    return;
                }
            }
        }
        Arrays.fill(was, 0);
        Arrays.fill(d, 0);
        int ans = 0;
        d[0] = 1;
        for (int i : term) {
            if (was[i] == 0) {
                count(i);
            }
            ans = (ans + d[i]) % MOD;
        }
        out.println(ans);
    }


    public void run() {
        try {
            in = new FastScanner(new File("problem.in"));
            out = new PrintWriter(new File("problem3.out"));
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

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }
    }

    public static void main(String[] arg) {
        new Problem3().run();
    }
}