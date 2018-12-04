import java.util.*;
import java.io.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class ProjEuler10 {
    static FastReader in;
    static PrintWriter out;
    static long primePreSum[];
    static int MAX = (int) 1e6+9, pix[];

    public static void main(String[] args) throws IOException {
        in = new FastReader();
        out = new PrintWriter(System.out);

        calculatePrimePrefixSum();

        int tc = in.nextInt();
        for(int t = 0; t < tc; t++) {
            solve();
        }

        out.flush();
        out.close();
    }

    static void solve() throws IOException {
        int n = in.nextInt();
        int i = pix[n];
        if(i > 0) i--;
        pn(primePreSum[i]);
    }

    static void calculatePrimePrefixSum() {
        primePreSum = new long[MAX];
        pix = new int[MAX];
        ArrayList<Integer> list = (ArrayList<Integer>) IntStream.rangeClosed(0, MAX).boxed().collect(Collectors.toList());

        for(int i = 2; i <= MAX; i++) {
            for(int j = 2*i; j <= MAX; j+=i) {
                list.set(j, 1);
            }
        }

        int i = 0, prevPrime = 0, cnt = 0;
        for(Integer p: list) {
            if(p > 1) {
                primePreSum[i] = (i == 0) ? p : (primePreSum[i-1] + p);
                for(int j = prevPrime; j < p; j++) {
                    pix[j] = cnt;
                }

                prevPrime = p;
                cnt++;
                i++;
            }
        }
    }

    static int gcd(int a, int b) { if (b == 0) return a; return gcd(b, a % b);}
    static void p(Object o) {out.print(o);}
    static void pn(Object o) {out.println(o);}
    static void pnf(Object o) {out.println(o);out.flush();}
//    int ni(){return Integer.parseInt(in.readLine());}
//    long nl(){return Long.parseLong(in.readLine());}
//    double nd(){return Double.parseDouble(in.readLine());}

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