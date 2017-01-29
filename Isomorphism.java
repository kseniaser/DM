import java.io.*;
import java.util.*;

public class Isomorphism {
    FastScanner in;
    PrintWriter out;

    boolean[] term1;
    boolean[] term2;
    int[][] g1;
    int[][] g2;
    boolean[] used;

    boolean possibility1(int x) {
        boolean possible = false;

        for (int i = 0; i < 26; i++) {
            possible |= (g1[x][i] != 0);
        }

        return possible;
    }

    boolean possibility2(int y) {
        boolean possible = false;

        for (int i = 0; i < 26; i++) {
            possible |= (g2[y][i] != 0);
        }

        return possible;
    }

    boolean check(int x, int y) {
        used[x] = true;

        if (term1[x] != term2[y]) {
            return false;
        }

        boolean alright = true;

        for (int i = 0; i < 26; i++) {
            int one = g1[x][i];
            int two = g2[y][i];

            if (possibility1(one) != possibility2(two)) {
                return false;
            }

            if (!used[one]) {
                alright &= check(one, two);
            }
        }

        return alright;
    }

    public void solve() throws IOException {
        int n1 = in.nextInt();
        int m1 = in.nextInt();
        int k1 = in.nextInt();
        g1 = new int[n1 + 1][30];
        term1 = new boolean[n1 + 1];

        for (int i = 0; i < k1; i++) {
            term1[in.nextInt()] = true;
        }

        for (int i = 0; i < m1; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            int c = (int) (in.next().charAt(0) - 'a');

            g1[a][c] = b;
        }

        int n2 = in.nextInt();
        int m2 = in.nextInt();
        int k2 = in.nextInt();
        g2 = new int[n2 + 1][30];
        term2 = new boolean[n2 + 1];

        for (int i = 0; i < k2; i++) {
            term2[in.nextInt()] = true;
        }

        for (int i = 0; i < m2; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            int c = (int) (in.next().charAt(0) - 'a');

            g2[a][c] = b;
        }

        if (n1 != n2 || m1 != m2 || k1 != k2) {
            out.println("NO");
            return;
        }

        used = new boolean[Math.max(n1, n2) + 1];

        if (check(1, 1)) {
            out.println("YES");
        } else {
            out.println("NO");
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("isomorphism.in"));
            out = new PrintWriter(new File("isomorphism.out"));

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
        new Isomorphism().run();
    }
}