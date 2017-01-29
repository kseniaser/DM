import java.io.*;
import java.util.Locale;
import java.util.StringTokenizer;


public class Markchain {
    FastScanner in;
    PrintWriter out;

    static double[][] Mult(double[][] left, double[][] right) {
        int row = left[0].length;
        int column = right[0].length;
        double[][] newMatrix = new double[left.length][column];
        for (int x = 0; x < left.length; x++) {
            for (int y = 0; y < column; y++) {
                for (int z = 0; z < row; z++) {
                    newMatrix[x][y] += left[x][z] * right[z][y];
                }
            }
        }
        return newMatrix;
    }

    public void solve() throws IOException {
        int n = in.nextInt();
        double templMatrix [][] = new double[n][n];
        double ansMatrix [][] = new double[1][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                templMatrix[i][j] = in.nextDouble();
            }
            ansMatrix[0][i] = (double) 1 / n;
        }
        for (int i = 0; i < 100; i++) {
            ansMatrix = Mult(ansMatrix, templMatrix);
        }
        for (int i = 0; i < n; i++) {
            out.printf(Locale.US,"%.8f%n",ansMatrix[0][i]);
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("markchain.in"));
            out = new PrintWriter(new File("markchain.out"));
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
        new Markchain().run();
    }
}
