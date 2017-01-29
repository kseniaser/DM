import java.io.*;
import java.util.*;

public class Prefix {
    FastScanner in;
    PrintWriter out;

    public void solve() throws IOException {
        String s = in.next();
        int [] prefix = new int [s.length()];
        for (int i=1;i<s.length();++i){
            int j = prefix[i-1];
            while (j>0 && s.charAt(i) != s.charAt(j))
                j = prefix[j-1];
            if (s.charAt(i) == s.charAt(j))
                j++;
            prefix[i] = j;
        }
        for (int i=0;i<s.length();i++)
            out.print(prefix[i] + " ");
    }

    public void run() {
        try {
            in = new FastScanner(new File("prefix.in"));
            out = new PrintWriter(new File("prefix.out"));

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
        new Prefix().run();
    }
}

