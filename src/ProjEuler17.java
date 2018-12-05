import java.util.*;
import java.io.*;

class ProjEuler17 {
    static FastReader in;
    static PrintWriter out;
//    static String tens[], nums[], nums2[], hund[];
    static HashMap<Long, String> wrdMp;

    public static void main(String[] args) throws IOException {
        in = new FastReader();
        out = new PrintWriter(System.out);

        init();

        int tc = in.nextInt();
        for(int t = 0; t < tc; t++) {
            solve();
        }

        out.flush();
        out.close();
    }

    static void init() {
        wrdMp = new HashMap<>();
        wrdMp.put(0L, "Zero"); wrdMp.put(1L, "One"); wrdMp.put(2L, "Two"); wrdMp.put(3L, "Three");
        wrdMp.put(4L, "Four"); wrdMp.put(5L, "Five"); wrdMp.put(6L, "Six"); wrdMp.put(7L, "Seven");
        wrdMp.put(8L, "Eight"); wrdMp.put(9L, "Nine"); wrdMp.put(10L, "Ten"); wrdMp.put(11L, "Eleven");
        wrdMp.put(12L, "Twelve"); wrdMp.put(13L, "Thirteen"); wrdMp.put(14L, "Fourteen"); wrdMp.put(15L, "Fifteen");
        wrdMp.put(16L, "Sixteen"); wrdMp.put(17L, "Seventeen"); wrdMp.put(18L, "Eighteen"); wrdMp.put(19L, "Nineteen");
        wrdMp.put(20L, "Twenty"); wrdMp.put(30L, "Thirty"); wrdMp.put(40L, "Forty"); wrdMp.put(50L, "Fifty");
        wrdMp.put(60L, "Sixty"); wrdMp.put(70L, "Seventy"); wrdMp.put(80L, "Eighty"); wrdMp.put(90L, "Ninety");
        wrdMp.put(100L, "Hundred"); wrdMp.put(1000L, "Thousand"); wrdMp.put(1_000_000L, "Million");
        wrdMp.put(1_000_000_000L, "Billion"); wrdMp.put(1_000_000_000_000L, "Trillion");

//        nums = new String[]{"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
//        nums2 = new String[]{"", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
//        tens = new String[]{"Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
//        hund = new String[]{"", "Hundred", "Thousand", "Million", "Billion", "Trillion"};
    }

    static void solve() throws IOException {
        // 104 382 426 112
        char[] n = in.readLine().toCharArray();
        numToWrd(n);
    }

    static void numToWrd(char[] n) {
        int st, ed, p = 1;
        Stack<String> wrds = new Stack<>();

        for(st = n.length - 1, ed = st - 3; st >= 0; st -= 3, ed -= 3) {
            long num = 0;
            if(p > 1) {
                pn("p : " + p);
                wrds.push(getWordFrmNum(p));
            }

            for(int i = st; i >= ed && i >= 0; i--) {
                int curNo = Character.getNumericValue(n[i]);
                num = num + (curNo * p);
//                pn(num);
                if(!wrds.isEmpty() && wrdMp.containsKey(num)) {
                    wrds.pop();
                    wrds.push(getWordFrmNum(num));
                }
                else if(num >= 100) {
                    wrds.push(getWordFrmNum(100));
                    wrds.push(getWordFrmNum(curNo));
                }
                else {
                    wrds.push(getWordFrmNum(curNo * p));
                }

                p = p * 10;
//                printStack(wrds);
            }
        }

        while(!wrds.isEmpty()) {
            p(wrds.pop() + " ");
        }
        pn("");
    }

    static String getWordFrmNum(long n) {
        return wrdMp.get(n);
    }

    static void printStack(Stack<String> stk) {
        Iterator<String> itr = stk.iterator();
        while(itr.hasNext()) {
            p(itr.next() + " ");
        }
        pn("");
    }

    static int gcd(int a, int b) { if (b == 0) return a; return gcd(b, a % b);}
    static void p(Object o) {out.print(o);}
    static void pn(Object o) {out.println(o);}
    static void pnf(Object o) {out.println(o);out.flush();}

    static class FastReader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public FastReader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public FastReader(String file_name) throws IOException
        {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException
        {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1)
            {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException
        {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do
            {
                ret = ret * 10 + c - '0';
            }  while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException
        {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException
        {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');

            if (c == '.')
            {
                while ((c = read()) >= '0' && c <= '9')
                {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException
        {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException
        {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException
        {
            if (din == null)
                return;
            din.close();
        }
    }
}