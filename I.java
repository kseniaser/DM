import java.util.*;
import java.io.*;

public class I {
    FastScanner in;
    PrintWriter out;

    int[] d;

    ArrayList<Integer>[] graph;

    int dfs(int cur) {
        d[cur] = -1;
        for (int i : graph[cur]) {
            if (d[i] == 0) {
                dfs(i);
            }
            if (d[i] == -1) {
                d[cur] = 1;
                break;
            }

        }
        return d[cur];
    }

    public void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
        int s = in.nextInt() - 1;
        graph = new ArrayList[n];
        d = new int[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            graph[from].add(to);
        }

        if (dfs(s) == 1) {
            out.println("First player wins");
        } else {
            out.println("Second player wins");
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("game.in"));
            out = new PrintWriter(new File("game.out"));

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
        new I().run();
    }
}