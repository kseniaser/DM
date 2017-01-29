import java.io.*;
import java.util.*;

public class Hash {
    FastScanner in;
    PrintWriter out;

    public void solve() throws IOException {
        int t = in.nextInt();
        int r = in.nextInt();
        String s = in.next();
        long answer = 0;
        for (int i = 0;i<s.length();i++){
            answer = (answer * t % r + (s.charAt(i) % r)) % r;
            out.println(answer);
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("hash.in"));
            out = new PrintWriter(new File("hash.out"));

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
        new Hash().run();
    }
}

