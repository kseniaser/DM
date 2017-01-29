import java.util.*;
import java.io.*;

public class G {
    FastScanner in;
    PrintWriter out;

    class Edge {
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    ArrayList<Edge>[] graph;
    ArrayList<Edge>[] inversedGraph;

    boolean[] used;
    ArrayList<Integer> topsort;
    void dfs(int cur) {
        if (used[cur])
            return;
        used[cur] = true;
        for (Edge e : graph[cur]) {
            dfs(e.to);
        }
        topsort.add(cur);
    }

    public void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
        int s = in.nextInt() - 1;
        int t = in.nextInt() - 1;
        graph = new ArrayList[n];
        inversedGraph = new ArrayList[n];
        used = new boolean[n];
        topsort = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            inversedGraph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            int weight = in.nextInt();
            graph[from].add(new Edge(from, to, weight));
            inversedGraph[to].add(new Edge(to, from, weight));
        }

        dfs(s);
        Collections.reverse(topsort);
        if (!used[t]) {
            out.println("Unreachable");
            return;
        }
        Integer[] d = new Integer[n];
        for (int i = 0; i < n; i++) {
            d[i] = null;
        }
        d[s] = 0;
        for (int i : topsort) {
            for (Edge e : inversedGraph[i]) {
                if (d[e.to] != null) {
                    if (d[i] == null) {
                        d[i] = d[e.to] + e.weight;
                    } else {
                        d[i] = Math.min(d[i], d[e.to] + e.weight);
                    }
                }
            }
        }
        out.println(d[t]);
     }

    public void run() {
        try {
            in = new FastScanner(new File("shortpath.in"));
            out = new PrintWriter(new File("shortpath.out"));

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
        new G().run();
    }
}