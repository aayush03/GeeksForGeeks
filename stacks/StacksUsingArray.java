package stacks;

public class StacksUsingArray {
    int top;
    int arr[] = new int[1000];
    StacksUsingArray()
    {
        top = -1;
    }

    /* The method push to push element
        into the stack */
    void push(int elementToBePushed)
    {
        arr[++top] = elementToBePushed;
    }

    /* The method pop which return
      the element poped out of the stack*/
    int pop()
    {
        return top != -1 ? arr[top--] : top;
    }
}
