import java.io.*;
import java.util.*;

public class Problem4 {
    FastScanner in;
    PrintWriter out;
    int n, m, k, l;
    ArrayList<ArrayList<Integer>> reversedGo;
    int[][] ans;
    boolean[][] used;
    public final static int MOD = 1000000007;

    int calc(int t, int l) {
        if (l < 0) {
            return 0;
        }

        if (used[t][l]) {
            return ans[t][l];
        }

        for (int i = 0; i < reversedGo.get(t).size(); i++) {
            int v = reversedGo.get(t).get(i);

            ans[t][l] += calc(v, l - 1);
            ans[t][l] %= MOD;
        }

        used[t][l] = true;
        return ans[t][l];
    }

    public void solve() throws IOException {
        n = in.nextInt();
        m = in.nextInt();
        k = in.nextInt();
        l = in.nextInt();
        HashSet<Integer> term = new HashSet<>();
        reversedGo = new ArrayList<>();
        ans = new int[n + 1][l + 2];
        used = new boolean[n + 1][l + 2];

        for (int i = 0; i < n ; i++) {
            reversedGo.add(new ArrayList<>());
        }

        for (int i = 0; i < k; i++) {
            term.add(in.nextInt());
        }

        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            in.next();
            reversedGo.get(y).add(x);
        }

        int final_ans = 0;

        for (int t : term) {
            ans[1][0] = 1;
            used[1][0] = true;
            final_ans += calc(t, l);
            final_ans %= MOD;
        }

        out.println(final_ans);
    }

    public void run() {
        try {
            in = new FastScanner(new File("problem4.in"));
            out = new PrintWriter(new File("problem4.out"));

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
