import java.io.*;
import java.util.*;

public class Problem2 {
    FastScanner in;
    PrintWriter out;

    boolean[] terminal;
    int[][] gone;

    class Edge {
        int u;
        int v;
        char c;
        Edge(int u, int v, char c) {
            this.u = u;
            this.v = v;
            this.c = c;
        }
    }

    List<Edge>[] edges;

    private void recur(int u, int ind, String s) {
        if (gone[u][ind] != 0) {
            return;
        }
        if (ind == s.length()) {
            /*if(terminal[u]==true)
                gone[u][id]=2;
            else gone[u][id] =1;*/
            gone[u][ind] = terminal[u] ? 2 : 1;
            return;
        }
        gone[u][ind] = 1;
        for (Edge e : edges[u]) {  //run through all elements of edge[u]
            if (e.c == s.charAt(ind)) {
                recur(e.v, ind + 1, s);
                if (gone[e.v][ind + 1] == 2) {
                    gone[u][ind] = 2;
                    break;
                }
            }
        }
    }

    public void solve() throws IOException {
        String s = in.next();
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        terminal = new boolean[n ];
        for (int i = 0; i < k; ++i) {
            terminal[in.nextInt() - 1] = true;
        }
        edges = new ArrayList[n];
        for (int i = 0; i < n; ++i) {
            edges[i] = new ArrayList<>();
        }
        gone = new int[n][s.length() + 1];
        for (int i = 0; i < m; ++i) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            char c = in.next().charAt(0);
            edges[u].add(new Edge(u, v, c));
        }
        recur(0, 0, s);
        out.println(gone[0][0] == 2 ? "Accepts" : "Rejects");
    }

    public void run() {
        try {
            in = new FastScanner(new File("problem2.in"));
            out = new PrintWriter(new File("problem2.out"));

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
        new Problem2().run();
    }
}