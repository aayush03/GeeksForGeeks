package stacks;

import java.util.Stack;

public class StockSpanProblem {

    public static void main(String[] args) {

    }

    private static void printStockSpan(int[] arr,int n) {
        class Pair{
            int spanOfPrice;
            int price;

            public Pair(int spanOfPrice, int price) {
                this.spanOfPrice = spanOfPrice;
                this.price = price;
            }
        }

        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(1,arr[0]));
        for (int i=1; i<n;i++){

                while (arr[i]>stack.peek().price){
                    Pair pair = stack.pop();
                    System.out.println();
                }
        }

        Stack<Integer> span = new Stack<>();

        for(int i=0;i<n; i++) {
            if(span.isEmpty())
                span.push(1);
            else {
                if(arr[i-1]<=arr[i]){
                    Stack<Integer> temp = new Stack<>();
                    int k = i;
                    while(arr[k-1]<=arr[k]){
                    int x = span.pop();
                }
                span.push(span.peek()+1);
            }
                else
            span.push(1);
        }
    }

         while(!span.isEmpty()) {
        System.out.print(span.pop()+" ");
    }
         System.out.println();
}
}
