import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by oktai on 23.10.16.
 */
public class guyaury {


    private static int n;
    private static boolean[][] graph;
    private static ArrayList<Integer> queue;
    private static final String PROBLEM = "fullham";

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new File(PROBLEM + ".in"));
        PrintWriter out = new PrintWriter(new File(PROBLEM + ".out"));

        solve(in, out);

        out.close();
        in.close();
    }

    private static void solve(Scanner in, PrintWriter out) throws Exception {
        int flag = 0;
        n = in.nextInt();
        graph = new boolean[n + 1][n + 1];
        queue = new ArrayList<>();

        for (int i = 2; i <= n; i++) {
            String current = in.next();
            for (int jj = 0; jj < current.length(); jj++) {
                graph[i][jj + 1] = current.charAt(jj) == '1';
                graph[jj + 1][i] = graph[i][jj + 1];
            }
        }

        for (int i = 1; i <= n; i++) {
            queue.add(i);
        }

        for (int i = 0; i < n * (n - 1); i++) {
            if (!graph[queue.get(0)][queue.get(1)]) {
                flag = 0;
                int count = 2;
                while ((count < n - 1) && (!graph[queue.get(0)][queue.get(count)] ||
                        !graph[queue.get(1)][queue.get(count + 1)])) {
                    count++;
                }

                if (count == n - 1) {
                    count = 2;
                    while (!graph[queue.get(0)][queue.get(count)]) {
                        count++;
                    }

                    for (int j = 1; j <= count; j++) {
                        int current = queue.get(j);
                        queue.set(j, queue.get(count));
                        queue.set(count, current);
                    }

                } else {
                    for (int j = 1; j <= count; j++) {
                        int current = queue.get(j);
                        queue.set(j, queue.get(count));
                        queue.set(count, current);
                    }
                }
            } else {
                flag++;
                if (flag > n) break;
            }
            queue.add(queue.get(0));
            queue.remove(0);
        }

        for (Integer k : queue) {
            out.print(k + " ");
        }
    }

    static class Scanner {
        BufferedReader br;
        StringTokenizer t;

        Scanner(File file) throws Exception {
            br = new BufferedReader(new FileReader(file));
            t = new StringTokenizer("");
        }

        boolean hasNext() throws Exception {
            while (!t.hasMoreTokens()) {
                String line = br.readLine();
                if (line == null)
                    return false;
                t = new StringTokenizer(line);
            }
            return true;
        }

        String next() throws Exception {
            if (hasNext()) {
                return t.nextToken();
            }
            return null;
        }

        int nextInt() throws Exception {
            return Integer.parseInt(next());
        }

        long nextLong() throws Exception {
            return Long.parseLong(next());
        }

        double nextDouble() throws Exception {
            return Double.parseDouble(next());
        }

        void close() throws Exception {
            br.close();
        }
    }
}