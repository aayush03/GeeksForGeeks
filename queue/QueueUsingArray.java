package queue;

public class QueueUsingArray {

    int front, rear;
    int arr[] = new int[1000];

    public QueueUsingArray() {
        this.front = 0;
        this.rear = 0;
    }

    void push(int a) {
        arr[rear++] = a;
    }

    int pop() {
        if (front == rear)
            return -1;

        return arr[front++];
    }
}
