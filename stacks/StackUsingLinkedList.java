package stacks;

public class StackUsingLinkedList {

    StackNode top;
    void push(int a) {
        if(top == null){
            top = new StackNode(a);
        }else{
            StackNode temp = new StackNode(a);
            temp.next = top;
            top = temp;
        }
    }
    int pop() {
        StackNode temp = top;
        if(temp==null){
            return -1;
        }else{

            int r = temp.data;
            temp=temp.next;
            top = temp;
            return r;
        }
    }
}

class StackNode
{
    int data;
    StackNode next;
    StackNode(int a)
    {
        data = a;
        next = null;
    }
}