import java.io.*;
import java.util.*;

public class Fastminimization {

    FastScanner in;
    PrintWriter out;


    class Pair {
        int set, chain;
        public Pair(int set, int ch) {
            this.set = set;
            this.chain = ch;
        }
    }

    class Matrix extends AbstractList<Integer> {
        Strct head, tail;
        int size;

        public Matrix() {
            head = new Strct(null, null, null);
            tail = new Strct(null, head, null);
            head.next = tail;
        }

        public boolean isEmpty() {
            return head.next == tail;
        }

        class Strct {
            Strct next, prev;
            Integer curr;

            public boolean hasNext() {
                return next != null && next != tail;
            }

            public Strct(Strct next, Strct prev, Integer data) {
                this.curr = data;
                if (prev != null) {
                    prev.next = this;
                }
                if (next != null) {
                    next.prev = this;
                }
                this.next = next;
                this.prev = prev;
                Matrix.this.size++;
            }

            public void remove() {
                prev.next = next;
                next.prev = prev;
                Matrix.this.size--;
            }
        }

        public boolean add (Integer e) {
            new Strct(tail, tail.prev, e);
            return true;
        }

        public Strct addNew (Integer e) {
            return new Strct(tail, tail.prev, e);
        }

        public Iterator<Integer> iterator() {
            return new Iterator<Integer>() {
                Strct elem = head;

                public boolean hasNext() {
                    return elem.hasNext();
                }

                public Integer next() {
                    elem = elem.next;
                    return elem.curr;
                }

                public void remove() {
                    if (elem != head && elem != tail) {
                        elem.remove();
                    } else {
                        throw (new IllegalStateException());
                    }
                }
            };
        }

        public Integer remove(int index) {
            if (index < size) {
                Strct cur = head;
                index++;
                while (index -->= 0) {
                    cur = cur.next;
                }
                cur.remove();
                return cur.curr;
            } else {
                return null;
            }
        }

        public Integer get(int arg0) {
            if (arg0 < size) {
                Strct cur = head;
                for (; arg0 >= 0; arg0--) {
                    cur = cur.next;
                }
                return cur.curr;
            } else {
                return null;
            }
        }

        @Override
        public int size() {
            return size;
        }

    }

    public void solve() throws IOException {
        int n = in.nextInt() + 1;
        int m = in.nextInt();
        int k = in.nextInt();

        LinkedList<Integer> que = new LinkedList<>();
        LinkedList<Pair> queue = new LinkedList<>();
        List<Integer>[][] deltaEps = new Matrix[n][26];
        List<Integer> moving = new LinkedList<>();
        Matrix[] firstSet = new Matrix[n + 1];
        Matrix[] changePos = new Matrix[n + 1];
        Matrix[] secondSet = new Matrix[n + 1];
        Matrix.Strct[] toFirst = new Matrix.Strct[n];
        Matrix.Strct[] toSecond = new Matrix.Strct[n];
        int[] place = new int[n];
        int[][] delta = new int[n][26];
        boolean[] terminalPos = new boolean[n];
        boolean[] needChange = new boolean[n];
        boolean[] needSet = new boolean[n];
        boolean inQ[][] = new boolean[n + 1][26];
        int counter = 2;

        for (int i = 0; i < k; i++) {
            terminalPos[in.nextInt() - 1] = true;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 26; j++) {
                delta[i][j] = n - 1;
            }
        }
        List<Integer>[] matrix = new Matrix[n];
        for (int i = 0; i < m; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            int c = in.next().charAt(0) - 'a';
            delta[u][c] = v;
            if (matrix[v] == null) {
                matrix[v] = new Matrix();
            }
            matrix[v].add(u);
        }
        boolean[] acceptable = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (acceptable[i] || !terminalPos[i]) {
                continue;
            }
            que.clear();
            que.add(i);
            while (!que.isEmpty()) {
                int state = que.poll();
                acceptable[state] = true;
                if (matrix[state] != null) {
                    for (Integer v : matrix[state]) {
                        if (!acceptable[v]) {
                            acceptable[v] = true;
                            que.add(v);
                        }
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 26; j++) {
                if (!acceptable[delta[i][j]]) {
                    delta[i][j] = n - 1;
                }
                if (deltaEps[delta[i][j]][j] == null) {
                    deltaEps[delta[i][j]][j] = new Matrix();
                }
                deltaEps[delta[i][j]][j].add(i);

            }
        }
        for (int i = 0; i < 2; i++) {
            secondSet[i] = new Matrix();
            firstSet[i] = new Matrix();
            changePos[i] = new Matrix();
        }
        for (int i = 0; i < n; i++) {
            int b = terminalPos[i] ? 0 : 1;
            toSecond[i] = secondSet[b].addNew(i);
            place[i] = b;
            if ((matrix[i] != null && !matrix[i].isEmpty()) || i == n - 1) {
                toFirst[i] = firstSet[b].addNew(i);
            }
        }
        for (int i = 0; i < 26; i++) {
            if ((firstSet[1].size() < firstSet[0].size() && place[n - 1] != 1)) {
                queue.add(new Pair(1, i));
                inQ[1][i] = true;
            } else {
                queue.add(new Pair(0, i));
                inQ[0][i] = true;
            }
        }
        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            int set = p.set;
            int ch = p.chain;
            inQ[set][ch] = false;
            for (Integer i1 : firstSet[set]) {
                if (deltaEps[i1][ch] != null && !deltaEps[i1][ch].isEmpty()) {
                    for (Integer i : deltaEps[i1][ch]) {
                        if (!needChange[i]) {
                            changePos[place[i]].add(i);
                            needChange[i] = true;
                        }
                        if (!needSet[place[i]]) {
                            moving.add(place[i]);
                            needSet[place[i]] = true;
                        }
                    }
                }
            }
            for (Integer i : moving) {
                if (changePos[i].size() == secondSet[i].size()) {
                    for (Integer j : changePos[i]) {
                        needChange[j] = false;
                    }
                    changePos[i] = new Matrix();
                    needSet[i] = false;
                }
            }
            Iterator<Integer> iterator = moving.iterator();
            while (iterator.hasNext()) {
                int i = iterator.next();
                if (!needSet[i]) {
                    iterator.remove();
                    continue;
                }
                Iterator<Integer> j = changePos[i].iterator();
                secondSet[counter] = new Matrix();
                firstSet[counter] = new Matrix();
                changePos[counter] = new Matrix();
                while (j.hasNext()) {
                    int j1 = j.next();
                    j.remove();
                    needChange[j1] = false;
                    Matrix.Strct b = toSecond[j1];
                    b.remove();
                    toSecond[j1] = secondSet[counter].addNew(j1);
                    place[j1] = counter;
                    if (toFirst[j1] != null) {
                        b = toFirst[j1];
                        b.remove();
                        toFirst[j1] = firstSet[counter].addNew(j1);
                    }
                }
                for (int c = 0; c < 26; c++) {
                    if (inQ[i][c] || (firstSet[counter].size() < firstSet[i].size() && place[n - 1] != counter) || place[n - 1] == i) {
                        queue.add(new Pair(counter, c));
                        inQ[counter][c] = true;
                    } else {
                        queue.add(new Pair(i, c));
                        inQ[i][c] = true;
                    }
                }
                counter++;
                needSet[i] = false;
                iterator.remove();
            }
        }
        Integer[] map = new Integer[counter];
        map[place[0]] = 0;
        int minStates = 1;
        List<String> transitions = new LinkedList<>();
        List<Integer> minTerminal = new LinkedList<>();
        if (terminalPos[0]) {
            minTerminal.add(0);
        }
        que.clear();
        que.add(0);
        while (!que.isEmpty()) {
            int state = que.poll();
            for (int i = 0; i < 26; i++) {
                if (delta[state][i] < n - 1) {
                    int v = delta[state][i];
                    if (map[place[v]] == null) {
                        map[place[v]] = minStates++;
                        if (terminalPos[v]) {
                            minTerminal.add(map[place[v]]);
                        }
                        que.add(v);
                    }
                    transitions.add((map[place[state]] + 1) + " " + (map[place[v]] + 1) + " " + (char) (i + 'a'));
                }
            }
        }
        if (minStates == 1 && transitions.size() == 0 && minTerminal.size() == 0) {
            out.println("0 0 0\n");
            return;
        }
        out.println(minStates + " " + transitions.size() + " " + minTerminal.size());
        for (Integer i : minTerminal) {
            out.print((i + 1) + " ");
        }
        out.println();
        for (String s : transitions) {
            out.println(s);
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("minimization.in"));
            out = new PrintWriter(new File("minimization.out"));

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new Fastminimization().run();
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
}