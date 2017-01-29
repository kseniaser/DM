import java.util.*;
import java.io.*;

public class B {
    FastScanner in;
    PrintWriter out;
    Random random;

    interface DSU {
        int get(int x);
        void union(int x, int y);
    }


    class Edge implements Comparable<Edge> {
        int from;
        int to;
        int distance;

        public Edge(int from, int to,int distance) {
            this.from = from;
            this.to = to;
            this.distance = distance;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.distance, o.distance);
        }
    }

    class DSUImplementation implements DSU {
        int[] parent;
        int[] weight;

        public DSUImplementation(int n) {
            parent = new int[n];
            weight = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                weight[i] = 1;
            }
        }

        @Override
        public int get(int x) {
            if (parent[x] == x)
                return x;
            parent[x] = get(parent[x]);
            return parent[x];
        }

        @Override
        public void union(int x, int y) {
            x = get(x);
            y = get(y);
            if (weight[x] > weight[y])
                parent[y] = x;
            else if (weight[x] < weight[y])
                parent[x] = y;
            else if (random.nextBoolean()) {
                parent[y] = x;
                weight[x]++;
            } else {
                parent[x] = y;
                weight[y]++;
            }
        }
    }

    public void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            edges.add(new Edge(in.nextInt() - 1, in.nextInt() - 1, in.nextInt()));
        }
        edges.sort(null);
        DSU dsu = new DSUImplementation(n);
        int answer = 0;
        int currentEdge = 0;
        for (int i = 0; i < n - 1; i++) {
            while (dsu.get(edges.get(currentEdge).from) == dsu.get(edges.get(currentEdge).to)) {
                currentEdge++;
            }
            answer+=edges.get(currentEdge).distance;
            dsu.union(edges.get(currentEdge).from, edges.get(currentEdge).to);
        }
        out.println(answer);
    }

    public void run() {
        try {
            in = new FastScanner(new File("spantree2.in"));
            out = new PrintWriter(new File("spantree2.out"));
            random = new Random(1488L);
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
        new B().run();
    }
}