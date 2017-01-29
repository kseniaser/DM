import java.io.*;
import java.util.*;

public class Minimization {
    FastScanner in;
    PrintWriter out;

    int n, m, k;
    boolean[] used;
    boolean[] t;
    int[][] a;
    List<Integer>[][] b;
    int M = 'z' - 'a' + 1;
    int[] c;
    boolean[] nt;
    Set<Integer> $t, $nt;
    List<Set<Integer>> p, s;
    int[] cl;
    List<Pair> q;
    int cnt;
    int cn;
    int mm;
    int[][] na;

    class Pair {
        int first;
        int second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    void dfs(int v) {
        used[v] = true;
        for (int i = 0; i < M; i++)
            if (!used[a[v][i]])
                dfs(a[v][i]);
    }

    void find() {
        dfs(1);
        for (int i = 0; i < n; i++)
            if (t[i]) {
                $t.add(i);
                cl[i] = 0;
            }
            else {
                $nt.add(i);
                cl[i] = 1;
            }
        p.add($t);
        p.add($t);
        s.add($t);
        s.add($nt);
        for (int i = 0; i < M; i++) {
            q.add(new Pair(0, i));
            q.add(new Pair(1, i));
        }
        while (!q.isEmpty()) {
            Pair val = q.get(0);
            q.remove(0);

            HashMap<Integer, List<Integer>> temp = new HashMap<>();
            for (int i : s.get(val.first)) {
                for (int j : b[i][val.second]) {
                    if (!temp.containsKey(cl[j]))
                        temp.put(cl[j], new ArrayList<>());
                    temp.get(cl[j]).add(j);
                }
            }
            for (Map.Entry<Integer, List<Integer>> i : temp.entrySet()) {
                if (i.getValue().size() > 0) {
                    int t1 = i.getKey();
                    if (temp.get(t1).size() < p.get(t1).size()) {
                        Set<Integer> tmp = new HashSet<>();
                        p.add(tmp);
                        int t2 = p.size() - 1;
                        for (int j : temp.get(t1)) {
                            p.get(t1).remove(j);
                            p.get(t2).add(j);
                        }
                        if (p.get(t2).size() > p.get(t1).size()) {
                            Set<Integer> asdf = p.get(t1);
                            p.set(t1, p.get(t2));
                            p.set(t2, asdf);
                        }
                        for (int j : p.get(t2)) {
                            cl[j] = t2;
                        }
                        s.add(p.get(t2));
                        for (int j = 0; j < M; j++) {
                            q.add(new Pair(s.size() - 1, j));
                        }
                    }
                }
            }
        }
    }

    void buildDKA() {
        for (Set<Integer> i : p){
            if (i.contains(0))
                for (int j : i)
                    c[j] = 0;
            if (i.contains(1) && c[1] == -1) {
                ++cnt;
                for (int j : i)
                    c[j] = 1;
            }
        }
        for (Set<Integer> i : p){
            int tt = i.iterator().next();
            if (!used[tt]) continue;
            if (c[tt] != -1) continue;
            ++cnt;
            c[tt] = cnt;
            for (int j : i)
                c[j] = cnt;
        }
        for (int i = 0; i < n; i++) {
            if (t[i] && c[i] != -1 && !nt[c[i]]) {
                nt[c[i]] = true;
                ++cn;
            }
        }
        if (nt[0]) --cn;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < M; j++)
                if (c[i] > 0 && c[a[i][j]] > 0 && na[c[i]][j] == 0) {
                    na[c[i]][j] = c[a[i][j]];
                    ++mm;
                }
    }


    private void solve() {

        n = in.nextInt() + 1;
        m = in.nextInt();
        k = in.nextInt();

        b = new List[n + 1][M];
        used = new boolean[n];
        $t = new HashSet<>();
        $nt = new HashSet<>();
        cl = new int[n];
        p = new ArrayList<>();
        s = new ArrayList<>();
        q = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < M; j++) {
                b[i][j] = new ArrayList<>();
            }
        }

        nt = new boolean[n];

        c = new int[n];
        Arrays.fill(c, -1);

        a = new int[n][M];
        for (int i = 0; i < a.length; i++) {
            Arrays.fill(a[i], 0);
        }

        t = new boolean[M];
        for (int i = 0; i < k; i++) {
            t[in.nextInt() - 1] = true;
        }

        for (int i = 0; i < m; i++) {
            int x = in.nextInt() - 1;
            int y = in.nextInt() - 1;
            char z = in.nextChar();
            a[x][z - 'a'] = y;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < M; j++) {
                b[a[i][j]][j].add(j);
            }
        }

        find();
        buildDKA();

        out.println("yay");

        out.println(cnt + " " + mm + " " + cn);
        for (int i = 1; i < n; i++) {
            if (nt[i])
                out.print(i);
        }
        out.println();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < M; j++) {
                if (na[i][j] != 0) {
                    out.println((i + 1) + " " + na[i][j] + " " + ((char)('a' + j)));
                }
            }
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("minimization.in"));
            out = new PrintWriter(new File("minimization.out"));
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


        public char nextChar() {
            return next().charAt(0);
        }
    }

    public static void main(String[] arg) {
        new Minimization().run();
    }
}