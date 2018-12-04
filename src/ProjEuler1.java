import java.io.*;
import java.util.*;

public class ProjEuler1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long tc = sc.nextLong();
        long finalSum, number;
        for(int j = 0; j < tc; j++)
        {
            number = sc.nextLong() - 1;
            finalSum = sumDivBy(3, number) + sumDivBy(5, number) - sumDivBy(15, number);
            System.out.println(finalSum);
        }
    }

    public static long sumDivBy(long div, long number)
    {
        long temp = (long) Math.floor(number/div);
        return div*temp*(temp+1)/2;
    }
}
