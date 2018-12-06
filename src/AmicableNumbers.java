import java.util.*;
import java.io.*;

class AmicableNumbers {
    static FastReader in;
    static PrintWriter out;
    static int amiSum[], divSum[], N = (int) 1e5+3, MAX = (int) 1e6;

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
        amiSum = new int[MAX];
        divSum = new int[MAX];
        for(int i = 1; i < N; i++) {
            if(divSum[i] == 0) {
                int as = divisorsSum(i);
                int bs = divisorsSum(as);
                if(i == bs && i != as) {
                    divSum[i] = 1;
                    divSum[as] = 1;
                }
            }
        }

        for(int i = 1; i < N; i++) {
            amiSum[i] = amiSum[i-1];
            if(divSum[i] == 1) {
                amiSum[i] += i;
            }
        }
    }

    static void solve() throws IOException {
        int n = in.nextInt();
        pn(amiSum[n-1]);
    }

    static int divisorsSum(int n) {
        int sum = 1;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                if (n/i == i) {
                    sum += i;
                }
                else {
                    sum += i + n/i;
                }
            }
        }

        return sum;
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