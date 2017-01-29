import java.util.*;
import java.io.*;

public class C {
    FastScanner in;
    PrintWriter out;
    Random random;
    double A;
    double R;

    class Point {
        private long x;
        private long y;
        int id;
        public Point(int x, int y, int id) {
            this.x = x;
            this.y = y;
            this.id = id;
        }

        public double getDistance(Point o) {
            return Math.sqrt(pow(this.x - o.x) + pow(this.y - o.y));
        }

        private long pow(long a) {
            return a * a;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    ", id=" + id +
                    '}';
        }
    }

    class Edge implements Comparable<Edge> {
        Point p1;
        Point p2;
        double cost;

        public Edge(Point p1, Point p2) {
            this.p1 = p1;
            this.p2 = p2;
            this.cost = p1.getDistance(p2);
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.cost, o.cost);
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "p1=" + p1 +
                    ", p2=" + p2 +
                    ", cost=" + cost +
                    '}';
        }
    }

    class DSU {
        int[] parents;
        int[] weights;

        public DSU(int n) {
            parents = new int[n];
            weights = new int[n];
            for (int i = 0; i < n; i++) {
                parents[i] = i;
            }
        }

        private int get(int x) {
            if (parents[x] != x)
                parents[x] = get(parents[x]);
            return parents[x];
        }

        public boolean inSameUnion(int x, int y) {
            return get(x) == get(y);
        }

        public void squash(int x, int y) {
            x = get(x);
            y = get(y);
            if (x == y)
                return;
            if (weights[x] > weights[y]) {
                parents[y] = x;
            } else if (weights[x] < weights[y]) {
                parents[x] = y;
            } else if (random.nextBoolean()) {
                parents[y] = x;
                weights[x]++;
            } else {
                parents[x] = y;
                weights[y]++;
            }
        }
    }

    public void solve() throws IOException {
        int n = in.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            y[i] = in.nextInt();
        }
        R = in.nextDouble();
        A = in.nextDouble();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            points[i] = new Point(x[i], y[i], i);
        }

        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                Edge e = new Edge(points[i], points[j]);
                edges.add(e);
            }
        }
        DSU dsu = new DSU(n);
        Collections.sort(edges);
        int currentEdge = 0;
        double curAnswer = n * A;
        double answer = n * A;
        int col = 0;
        for (int i = 0; i < n - 1; i++) {
            while (currentEdge < edges.size() && dsu.inSameUnion(edges.get(currentEdge).p1.id, edges.get(currentEdge).p2.id)) {
                currentEdge++;
            }
            if (currentEdge == edges.size())
                break;
            dsu.squash(edges.get(currentEdge).p1.id, edges.get(currentEdge).p2.id);
            curAnswer += edges.get(currentEdge).cost * R;
            curAnswer -= A;
            if (i == n - 2)
                curAnswer -= A;
            answer = Math.min(answer, curAnswer);
        }
        out.printf(Locale.US, "%.7f", answer);
    }

    public void run() {
        try {
            in = new FastScanner(new File("transport.in"));
            out = new PrintWriter(new File("transport.out"));

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

        double nextDouble() { return Double.parseDouble(next()); }
    }

    public static void main(String[] arg) {
        new C().run();
    }
}