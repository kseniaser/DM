import java.io.*;
import java.util.*;

public class Substr {
    FastScanner in;
    PrintWriter out;

    public void solve() throws IOException {
        int a, b;
        int t = in.nextInt();
        int r = in.nextInt();
        String s = in.next();
        long [] hash1  = new long [s.length()+1];
        long [] pow = new long [s.length()+1];
        pow[0] = 1;
        pow[1] = t;
        hash1[0]=s.charAt(0)%r;
        for (int i=1;i<s.length();i++){
            if (i>1)
                pow[i] = (pow[i-1]*t)%r;
            hash1[i]= (((hash1[i-1]*t)%r)+s.charAt(i))%r;
        }
        int m = in.nextInt();
        for (int i = 0; i<m;i++ ){
            long str = 0;
            a = in.nextInt();
            b = in.nextInt();
            if (a == 0){
                out.println(hash1[b]);
            } else {
                if ((hash1[b]-(hash1[a-1]*pow[b-a+1])%r) < 0) {
                    out.println(hash1[b] - (hash1[a - 1] * pow[b - a + 1]) % r + r);
                } else {
                    out.println(hash1[b]-(hash1[a-1]*pow[b-a+1])%r);
                }
            }
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("fullham.in"));
            out = new PrintWriter(new File("fullham.out"));

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
        new Substr().run();
    }
}