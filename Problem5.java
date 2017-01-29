import java.io.*;
import java.util.*;

public class Problem5 {
    FastScanner in;
    PrintWriter out;
    int n, m, k, l;
    ArrayList<ArrayList<ArrayList<Integer>>> go;
    int[][] ans;
    boolean[][] used;
    public final static int MOD = 1000000007;

    public void solve() throws IOException {
        n = in.nextInt();
        m = in.nextInt();
        k = in.nextInt();
        l = in.nextInt();
        boolean[] term = new boolean[n + 1];
        BitSet set = new BitSet(n + 1);
        HashMap<BitSet, Integer> num = new HashMap<>();
        ArrayList<Boolean> termNum = new ArrayList<>();
        ArrayList<Integer> canFrom = new ArrayList<>();
        ArrayList<Integer> canTo = new ArrayList<>();
        int q = 1;
        go = new ArrayList<>();
        ans = new int[n + 1][l + 2];
        used = new boolean[n + 1][l + 2];

        for (int i = 0; i < n + 1; i++) {
            go.add(new ArrayList<>());

            for (int j = 0; j < 26; j++) {
                go.get(i).add(new ArrayList<>());
            }
        }

        for (int i = 0; i < k; i++) {
            term[in.nextInt()] = true;
        }

        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            int z = in.next().charAt(0) - 'a';
            go.get(x).get(z).add(y);
        }

        termNum.add(false);
        termNum.add(term[1]);
        set.set(1);
        num.put(set, 1);
        LinkedList<BitSet> queue = new LinkedList<>();
        queue.add(set);

        while (!queue.isEmpty()) {
            BitSet curr = queue.poll();

            for (int c = 0; c < 26; c++) {
                BitSet can = new BitSet(n + 1);
                boolean reachable = false;

                for (int i = 1; i <= n; i++) {
                    if (!curr.get(i)) {
                        continue;
                    }

                    for (int j = 0; j < go.get(i).get(c).size(); j++) {
                        int v = go.get(i).get(c).get(j);
                        can.set(v);

                        if (term[v]) {
                            reachable = true;
                        }
                    }
                }

                canFrom.add(num.get(curr));

                if (!num.containsKey(can)) {
                    num.put(can, ++q);
                    termNum.add(reachable);
                    queue.add(can);
                }

                canTo.add(num.get(can));
            }
        }

        int[] ans = new int[q + 1];

        for (int i = 0; i < q; i++) {
            if (termNum.get(i + 1)) {
                ans[i + 1] = 1;
            }
        }

        for (int i = 0; i < l; i++) {
            int[] res = new int[q + 1];

            for (int j = 0; j < canFrom.size(); j++) {
                res[canFrom.get(j)] += ans[canTo.get(j)];
                res[canFrom.get(j)] %= MOD;
            }

            System.arraycopy(res, 0, ans, 0, q + 1);
        }

        out.println(ans[num.get(set)]);
    }

    public void run() {
        try {
            in = new FastScanner(new File("problem5.in"));
            out = new PrintWriter(new File("problem5.out"));

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
        new Problem5().run();
    }
}
