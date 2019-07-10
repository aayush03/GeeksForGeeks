package stacks;

import java.util.Arrays;
import java.util.Stack;

public class NextLargerElement {

    public static void main(String[] args){

        String input1 = "1 3 2 4";

        computeNextLargerElements(input1);
        System.out.println();

        String input2 = "4 3 2 1";
        computeNextLargerElements(input2);

    }

    private static void computeNextLargerElements(String input2) {
        Long[] array1 = Arrays.stream(input2.split("\\s"))
                .map(Long::valueOf)
                .toArray(Long[]::new);
        printNextLargerElement(array1,array1.length);
    }

    private static void printNextLargerElement(Long[] arr, int n){
        class Pair{
            Long value;
            int index;

            public Pair(Long value, int index) {
                this.value = value;
                this.index = index;
            }
        }

        Long[] nextLargerElementArray = new Long[n];

        Stack<Pair> stack = new Stack<>();

        stack.push(new Pair(arr[0],0));
        for (int i=1;i<n;i++){
            while (!stack.isEmpty() && stack.peek().value <arr[i]){
                Pair pair = stack.pop();
                nextLargerElementArray[pair.index] = arr[i];
            }
            stack.push(new Pair(arr[i], i));
        }

        while (!stack.isEmpty()){
            Pair pair = stack.pop();
            nextLargerElementArray[pair.index] = -1L;
        }

        for (int i=0;i<n;i++){
            System.out.print(nextLargerElementArray[i]+" ");
        }
    }
}
