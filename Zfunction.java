import java.io.*;
import java.util.*;

public class Zfunction {
    FastScanner in;
    PrintWriter out;

    public void solve() throws IOException {
        String s = in.next();
        int [] z = new int [s.length()];
        for (int i=1, l=0, r=0; i<s.length(); ++i) {
            if (i <= r)
                z[i] = Math.min(r-i+1, z[i-l]);
            while (i+z[i] < s.length() && s.charAt(z[i]) == s.charAt(i+z[i]))
                ++z[i];
            if (i+z[i]-1 > r) {
                l = i;
                r = i + z[i] - 1;
            }
        }
        for (int i=1;i<s.length();i++)
            out.print(z[i] + " ");
    }

    public void run() {
        try {
            in = new FastScanner(new File("z.in"));
            out = new PrintWriter(new File("z.out"));

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
        new Zfunction().run();
    }
}
