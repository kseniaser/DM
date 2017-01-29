import java.util.*;
import java.io.*;

public class E {
    FastScanner in;
    PrintWriter out;

    int n;
    int m;
    int[] used;
    int[] parent;

    int cycleStart = -1;
    int cycleEnd = -1;
    ArrayList<Integer>[] graph;

    boolean dfs(int cur) {
        used[cur] = 1;
        for (int i : graph[cur]) {
            if (used[i] == 0) {
                parent[i] = cur;
                if (dfs(i))
                    return true;
            } else if (used[i] == 1) {
                cycleStart = cur;
                cycleEnd = i;
                return true;
            }
        }
        used[cur] = 2;
        return false;
    }

    List<Integer> getCycle(int start, int end) {
        List<Integer> result = new ArrayList<>();
        for (int i = start; i != end; i = parent[i]){
            result.add(i + 1);
        }
        result.add(end + 1);
        Collections.reverse(result);
        return result;
    }


    public void solve() throws IOException {
        n = in.nextInt();
        m = in.nextInt();
        used = new int[n];
        parent = new int[n];
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            graph[from].add(to);
        }
        List<Integer> result = null;
        for (int i = 0; i < n; i++) {
            dfs(i);
        }
        if (cycleStart == -1 && cycleEnd == -1)
            out.println("NO");
        else {
            out.println("YES");
            result = getCycle(cycleStart, cycleEnd);
            for (int i : result) {
                out.print(i + " ");
            }
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("cycle.in"));
            out = new PrintWriter(new File("cycle.out"));

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
        new E().run();
    }
}