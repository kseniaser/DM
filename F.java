import java.util.*;
import java.io.*;

public class F {
    FastScanner in;
    PrintWriter out;

    ArrayList<Integer> topsort;
    ArrayList<Integer>[] graph;
    int n;
    int m;
    int[] used;
    boolean dfs(int cur) {
        if (used[cur] == 2)
            return true;
        used[cur] = 1;
        for (int i : graph[cur]) {
            if (used[i] == 1) {
                return false;
            }
            if (!dfs(i))
                return false;
        }

        used[cur] = 2;
        topsort.add(cur);
        return true;
    }
    public void solve() throws IOException {
        n = in.nextInt();
        m = in.nextInt();
        used = new int[n];
        graph = new ArrayList[n];
        topsort = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            graph[from].add(to);
        }

        for (int i = 0; i < n; i++) {
            if (!dfs(i)) {
                out.println(-1);
                return;
            }
        }
        Collections.reverse(topsort);
        for (int i : topsort) {
            out.print((i + 1) + " ");
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("topsort.in"));
            out = new PrintWriter(new File("topsort.out"));

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
        new F().run();
    }
}