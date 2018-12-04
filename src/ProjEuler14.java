import java.io.*;

class ProjEuler14 {
    static FastReader in;
    static PrintWriter out;

    public static void main(String[] args) throws IOException {
        in = new FastReader();
        out = new PrintWriter(System.out);
        int MAX = 5000005;
        int tc = in.nextInt();
        Integer dp[] = new Integer[MAX];
        Integer maxSeqN[] = new Integer[MAX];
        Integer maxSeq = 0;
        Integer maxN = 0;

        for(int i = 1; i <= 5000000; i++) {
            if(dp[i] == null) {
                long c = i;
                int chainLen = 1;

                while (c != 1) {
                    if(c < MAX && dp[(int) c] != null) {
                        chainLen += dp[(int) c] - 1;
                        break;

                    }
                    else {
                        c = ((c & 1) == 0) ? (c / 2) : ((c * 3) + 1);
                        chainLen++;
                    }
                }
                dp[i] = chainLen;
            }

            if(dp[i] >= maxSeq) {
                maxSeq = dp[i];
                maxN = i;
            }

            maxSeqN[i] = maxN;
        }

        for(int t = 0; t < tc; t++) {
            int n = in.nextInt();
            pn(maxSeqN[n]);
        }

        out.flush();
        out.close();
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