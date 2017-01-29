import java.io.*;
import java.util.StringTokenizer;

public class Problem1 {
    FastScanner in;
    PrintWriter out;

    public void solve() throws IOException {
        String s = in.next();
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        int[] term = new int[k];
        int[][] matr = new int[n + 1][26];

        for (int i = 0; i < k; i++) {
            term[i] = in.nextInt();
        }

        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            char c = in.next().charAt(0);
            int z = c - 'a';
            matr[x][z] = y;
        }

        int current = 1;
        boolean exist = true;
        boolean OK = false;

        for (int i = 0; i < s.length(); i++) {
            if (matr[current][s.charAt(i) - 'a'] == 0) {
                exist = false;
                break;
            }

            current = matr[current][s.charAt(i) - 'a'];
        }

        for (int i = 0; i < k; i++) {
            if (term[i] == current) {
                OK = true;
                break;
            }
        }

        if (exist && OK) {
            out.println("Accepts");
        } else {
            out.println("Rejects");
        }

    }

    public void run() {
        try {
            in = new FastScanner(new File("problem2.in"));
            out = new PrintWriter(new File("problem1.out"));
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
        double nextDouble() {
            return Double.parseDouble(next());
        }
    }

    public static void main(String[] arg) {
        new Problem1().run();
    }
}
