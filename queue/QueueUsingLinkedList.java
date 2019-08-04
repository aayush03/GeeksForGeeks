package queue;

public class QueueUsingLinkedList {

    public static void main(String[] args) {
        QueueUsingLinkedList obj = new QueueUsingLinkedList();

        obj.push(2);
        obj.push(3);
        System.out.println(obj.pop());
        obj.push(4);
        System.out.println(obj.pop());

    }

    QueueNode front, rear;

    void push(int a) {
        if (front == null) {
            front = new QueueNode(a);
            rear = front;
        } else {
            rear.next = new QueueNode(a);
            rear = rear.next;
        }
    }

    int pop() {
        if (front == null)
            return -1;
        else {
            int k = front.data;
            front = front.next;
            return k;
        }
    }
}

class QueueNode {
    int data;
    QueueNode next;

    QueueNode(int a) {
        data = a;
        next = null;
    }
}