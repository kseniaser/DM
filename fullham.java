import java.io.*;
import java.util.StringTokenizer;

public class fullham {
    FastScanner in;
    PrintWriter out;

    int n;
    int var = 0;
    int flag = 0;
    int matrix[][];
    int vertex[];

    public void shift() {
        int lol = vertex[0];
        for (int k = 0; k < n - 1; k++) {
            vertex[k] = vertex[k + 1];
        }
        vertex[n - 1] = lol;
    }

    public void swap(int i, int i1) {
        int tmp = vertex[i];
        vertex[i] = vertex[i1];
        vertex[i1] = tmp;
    }

    public void findWay() {
        for (int j = 0; j < n * (n - 1); j++) {
            if (matrix[vertex[var]][vertex[var + 1]] != 1) {
                int i = var + 2;
                int param1 = vertex[var];
                int param2 = vertex[var + 1];
                while ((i < n - 1) && (matrix[param1][vertex[i % n]] != 1 || matrix[param2][vertex[(i + 1) % n]] != 1))
                    i++;
                if (i == n - 1) {
                    i = 2;
                    while (matrix[param1][vertex[i % n]] != 1) {
//                        System.out.println("lol");
                        i++;
                    }
                }
                for (int k = var + 1; k <= i; k++) {
                    swap(k % n, i % n);
                }
                flag = 0;
            } else {
                if (flag + 1 > n) {
                    break;
                } else {
                    flag++;
                }
            }
            shift();
        }
    }

    public void writeWay() {
        for (int i = 0; i < n; i++) {
            out.print((vertex[i] + 1) + " ");
        }
    }

    void solve() throws IOException {
        n = in.nextInt();
        matrix = new int[n][n];
        vertex = new int[n];

        vertex[var] = 0;
        matrix[var][var] = 0;

        for (int i = 1; i < n; i++) {
            String s = in.next();
            vertex[i] = i;
            matrix[var][i] = 0;
            for (int j = 0; j < i; j++) {
                matrix[i][j] = matrix[j][i] = (int) s.charAt(j) - (int) '0';
            }
        }
        findWay();
        writeWay();
    }

    public void run() {
        try {
            in = new FastScanner(new File("chvatal.in"));
            out = new PrintWriter(new File("chvatal.out"));

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
        new fullham().run();
    }
}
