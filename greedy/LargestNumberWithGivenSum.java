package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * A boy lost the password of his super locker.
 * He remembers the number of digits N as well as the sum S of all the digits of his password.
 * He know that his password is the largest number of N digits that can be possible with given sum S.
 * As he is busy doing his homework, help him retrieving his password.
 */

/**
 * Sample Input :
 * 3
 * 5 12
 * 3 29
 * 3 26
 */
public class LargestNumberWithGivenSum {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(reader.readLine());

        while (t-- > 0) {
            String[] s = reader.readLine().split("\\s");
            int n = Integer.parseInt(s[0]);
            int sum = Integer.parseInt(s[1]);

            System.out.println(getLargestNumber(n, sum));
        }
    }

    private static String getLargestNumber(int N, int SUM) {
        if (SUM > 9 * N)
            return "-1";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int temp = 0;
            if (SUM != 0)
                temp = SUM > 9 ? 9 : SUM;
            sb.append(temp);
            SUM -= temp;
        }

        return sb.toString();
    }
}
