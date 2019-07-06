package stacks;

import java.util.Stack;

public class StackToSupportGetMin {

    static Stack<Integer> minStack = new Stack<>();

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();

        int[] arr1 = new int[]{1,2,3,4,5};
        stack = _push(arr1,arr1.length);

        for (int s : arr1) {
            _getMinAtPop(stack);
        }
        System.out.println();
        int[] arr2 = new int[]{1,6,43,1,2,0,5};
        stack = _push(arr2,arr2.length);

        for (int s : arr2) {
            _getMinAtPop(stack);
        }
    }

    public static Stack<Integer> _push(int arr[],int n)
    {
        Stack<Integer> st = new Stack<>();
        int min = arr[0];
        for(int s : arr) {
            st.push(s);

            minStack.push(min = Math.min(s, min));
        }

        return st;
    }

    /* print minimum element of the stack each time
       after popping*/
    static void _getMinAtPop(Stack<Integer> s)
    {
        while (!s.isEmpty()) {
            s.pop();
            System.out.print(minStack.pop()+" ");
        }

    }
}
