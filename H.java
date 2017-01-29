import java.util.*;
import java.io.*;

public class H
{
    FastScanner in;
    PrintWriter out;

    List<Integer>[] graph;

    class Edge {
        int from;
        int to;

        public Edge(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Edge edge = (Edge) o;

            if (from != edge.from) return false;
            return to == edge.to;

        }

        @Override
        public int hashCode() {
            int result = from;
            result = 31 * result + to;
            return result;
        }
    }

    List<Integer> topsort;
    boolean[] used;

    void dfs(int cur) {
        if (used[cur])
            return;
        used[cur] = true;
        for (int i : graph[cur]) {
            if (!used[i]) {
                dfs(i);
            }
        }
        topsort.add(cur);
    }

    public void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
        topsort = new ArrayList<>();
        used = new boolean[n];
        Set<Edge> set = new HashSet<Edge>();
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            graph[from].add(to);
            set.add(new Edge(from, to));
        }

        for (int i = 0; i < n; i++) {
            dfs(i);
        }
        Collections.reverse(topsort);
        for (int i = 0; i < topsort.size() - 1; i++) {
            if (!set.contains(new Edge(topsort.get(i), topsort.get(i + 1)))) {
                out.println("NO");
                return;
            }
        }
        out.println("YES");
    }

    public void run() {
        try {
            in = new FastScanner(new File("hamiltonian.in"));
            out = new PrintWriter(new File("hamiltonian.out"));

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
        new H().run();
    }
}