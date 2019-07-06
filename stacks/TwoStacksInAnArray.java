package stacks;

public class TwoStacksInAnArray {

    public static void main(String[] args) {
        TwoStack sq = new TwoStack();

        executeTestCase1(sq);

        System.out.println();

        sq = new TwoStack();

        executeTestCase2(sq);
    }

    static void push1(int x, TwoStack sq) {
        sq.arr[++sq.top1] = x;
    }

    /* The method push to push element into the stack 2*/
    static void push2(int x, TwoStack sq) {
        sq.arr[--sq.size] = x;
    }

    /* The method pop to pop element from the stack 1 */
    //Return the popped element
    static int pop1(TwoStack sq) {
        return sq.top1 != -1 ? sq.arr[sq.top1--] : -1;
    }

    /* The method pop to pop element from the stack 2 */
    //Return the popper element
    static int pop2(TwoStack sq) {
        return sq.size != 100 ? sq.arr[sq.size++] : -1;
    }

    static void executeTestCase1(TwoStack sq) {
        push1(25, sq);
        System.out.print(pop1(sq) + " ");
        System.out.print(pop2(sq) + " ");
        push1(56, sq);
        push1(80, sq);
        System.out.print(pop2(sq) + " ");
        push1(56, sq);
        push1(80, sq);
        System.out.print(pop1(sq) + " ");
        System.out.print(pop2(sq) + " ");
        push2(14, sq);
        push2(27, sq);
        push2(3, sq);
        push1(85, sq);
    }

    static void executeTestCase2(TwoStack sq) {
        System.out.print(pop2(sq) + " ");
        push1(100, sq);
        System.out.print(pop1(sq) + " ");
        System.out.print(pop2(sq) + " ");
        System.out.print(pop2(sq) + " ");
        push1(18, sq);
        System.out.print(pop2(sq) + " ");
        push1(85, sq);
        push2(6, sq);
        push1(55, sq);
        push1(94, sq);
    }
}

class TwoStack {
    int size;
    int top1, top2;
    int arr[] = new int[100];

    TwoStack() {
        size = 100;
        top1 = -1;
        top2 = size;
    }
}
