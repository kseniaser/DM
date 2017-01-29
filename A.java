import java.util.*;
import java.io.*;

public class A {
    FastScanner in;
    PrintWriter out;

    int pow(int a) {
        return a * a;
    }
    public void solve() throws IOException {
        int n = in.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = in.nextInt();
            y[i] = in.nextInt();
        }

        int[] d = new int[n];
        Arrays.fill(d, Integer.MAX_VALUE);
        boolean[] used = new boolean[n];
        used[0] = true;
        int currentVertex = 0;
        double answer = 0;
        for (int i = 0; i < n - 1; i++) {
            int minId = -1;
            for (int j = 0; j < n; j++) {
                if (used[j])
                    continue;
                d[j] = Math.min(d[j], pow(x[currentVertex] - x[j]) + pow(y[currentVertex] - y[j]));
                if (minId == -1 || d[minId] > d[j]) {
                    minId = j;
                }
            }
            used[minId] = true;
            answer += Math.sqrt(d[minId]);
            currentVertex = minId;
        }
        out.println(answer);
    }

    public void run() {
        try {
            in = new FastScanner(new File("spantree.in"));
            out = new PrintWriter(new File("spantree.out"));
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
        new A().run();
    }
}