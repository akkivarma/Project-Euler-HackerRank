import java.util.*;
import java.io.*;

class ProjEuler8 {
    static BufferedReader in;
    static PrintWriter out;

    public static void main(String[] args) throws IOException {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);

        int tc;
        tc = Integer.parseInt(in.readLine());

        for(int t = 0; t < tc; t++) {
            solve();
        }

        out.flush();
        out.close();
    }

    static void solve() throws IOException {
        int n, k;
        long maxProd = -1;
        String no, t[];

        t = in.readLine().split(" ");
        n = Integer.parseInt(t[0]);
        k = Integer.parseInt(t[1]);
        no = in.readLine();

        for(int i = 0; i < n; i++) {
            if((i + k - 1) < n) {
                maxProd = Math.max(maxProd, getDigitProduct(no.substring(i, i+k)));
            }
        }

        pn(maxProd);
    }

    static long getDigitProduct(String no) {
        int prod = 1;
        for(int i = 0; i < no.length(); i++) {
            prod = prod * Character.getNumericValue(no.charAt(i));
        }

        return prod;
    }

    static int gcd(int a, int b) { if (b == 0) return a; return gcd(b, a % b);}
    static void p(Object o) {out.print(o);}
    static void pn(Object o) {out.println(o);}
    static void pnf(Object o) {out.println(o);out.flush();}
}