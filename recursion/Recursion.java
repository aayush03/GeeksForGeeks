package recursion;

import java.util.*;
class Recursion {

    public static long rev(long n)
    {
        long rev_num = 0;
        while(n > 0)
        {
            rev_num = rev_num*10 + n%10;
            n = n/10;
        }
        return rev_num;
    }

    public static void main (String[] args) {
        Scanner sc=new Scanner(System.in);

        int T=sc.nextInt();
        while(T-->0)
        {
            PowerProblem obj=new PowerProblem();

            int N;
            N=sc.nextInt();
            int R=0;
            R=(int)rev(N);
            long ans=(long)obj.power(N,R);
            System.out.println(ans);


        }

    }
}

class PowerProblem
{
    int moduloNumber = 1000000007;

    int power(int N,int R)
    {
        //Your code here

        int p;
        long q;

        if(R==0) {
            return 1;
        }
        if(R%2==0) {
            q = (power(N, R / 2) % moduloNumber) * (power(N, R / 2) % moduloNumber);
            p = (int) q;
            //return p;
        }

        else {
            q = (N * power(N,R-1))%moduloNumber;
            p = (int) q;
            //return p;
        }

        if(q<0) {
            q = (q + moduloNumber) % moduloNumber;
            p = (int) q;
        }



        return p;
    }
}