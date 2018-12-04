import java.util.*;
import java.io.*;

class ProjEuler11 {
    static FastReader in;
    static PrintWriter out;
    static int N = 20;

    public static void main(String[] args) throws IOException {
        in = new FastReader();
        out = new PrintWriter(System.out);

        solve();

        out.flush();
        out.close();
    }

    static void solve() throws IOException {
        int mat[][] = new int[N][N];
        long max = 0;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                mat[i][j] = in.nextInt();
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(j+3 < N) {
                    max = Math.max(mat[i][j] * mat[i][j+1] * mat[i][j+2] * mat[i][j+3], max);
                }

                if(i+3 < N) {
                    max = Math.max(mat[i][j] * mat[i+1][j] * mat[i+2][j] * mat[i+3][j], max);
                }

                if(i+3 < N && j+3 < N) {
                    max = Math.max(mat[i][j] * mat[i+1][j+1] * mat[i+2][j+2] * mat[i+3][j+3], max);
                }

                if(i+3 < N && j-3 >= 0) {
                    max = Math.max(mat[i][j] * mat[i+1][j-1] * mat[i+2][j-2] * mat[i+3][j-3], max);
                }
            }
        }

        pn(max);
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