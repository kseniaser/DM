import java.io.*;
import java.util.*;
import javafx.util.Pair;

public class Equivalence {
    FastScanner in;
    PrintWriter out;

    class Automaton {
        int n;
        boolean[] term;
        int[][] e;

        Automaton(int n, boolean[] term, int[][] e) {
            this.n = n;
            this.term = term;
            this.e = e;
        }
    }

    boolean checkForEquivalence(Automaton[] a) {
        Queue<Pair<Integer, Integer>> q = new ArrayDeque<>();
        q.add(new Pair<>(0, 0));
        boolean[][] us = new boolean[2][Math.max(a[0].n, a[1].n)];
        us[0][0] = us[1][0] = true;
        boolean[][] add = new boolean[2][Math.max(a[0].n, a[1].n)];
        while (!q.isEmpty()) {
            Pair<Integer, Integer> p = q.remove();
            int u = p.getKey();
            int v = p.getValue();
            if (a[0].term[u] != a[1].term[v]) {
                return false;
            }
            for (int i = 0; i < 26; ++i) {
                int d1 = a[0].e[u][i];
                int d2 = a[1].e[v][i];
                if (d1 < 0 && d2 > -1){
                    if (dfs(d2, a[1], add[1])){
                        return false;
                    }
                }
                if (d2 < 0 && d1 > -1){
                    if (dfs(d1, a[0], add[0])){
                        return false;
                    }
                }
                if (d1 > -1 && d2 > -1 && !us[0][d1]) {
                    q.add(new Pair<>(d1, d2));
                    us[0][d1] = us[1][d2] = true;
                }
            }
        }
        return true;
    }

    private boolean dfs(int u, Automaton a, boolean[] us) {
        if (a.term[u]){
            return true;
        }
        us[u] = true;
        for (int i = 0; i < 26; ++i){
            if (a.e[u][i] > -1 && dfs(a.e[u][i], a, us)){
                return true;
            }
        }
        return false;
    }

    private void solve() {
        Automaton[] a = new Automaton[2];
        for (int i = 0; i < 2; ++i) {
            int n = in.nextInt();
            int m = in.nextInt();
            int k = in.nextInt();
            boolean[] t = new boolean[n];
            int[][] e = new int[n][26];
            for (int j = 0; j < n; ++j) {
                Arrays.fill(e[j], -1);
            }
            for (int j = 0; j < k; ++j) {
                t[in.nextInt() - 1] = true;
            }
            for (int j = 0; j < m; ++j) {
                int u = in.nextInt() - 1;
                int v = in.nextInt() - 1;
                int c = in.next().charAt(0) - 'a';
                e[u][c] = v;
            }
            a[i] = new Automaton(n, t, e);
        }

        out.println(checkForEquivalence(a) ? "YES" : "NO");
    }

    public void run() {
        try {
            in = new FastScanner(new File("equivalence.in"));
            out = new PrintWriter(new File("equivalence.out"));

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
        new Equivalence().run();
    }

}