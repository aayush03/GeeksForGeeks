package heap;

public class BasicOperations {
    int[] harr;
    int capacity;
    int heap_size;

    public BasicOperations(int capacity) {
        heap_size = 0;
        this.capacity = capacity;
        harr = new int[capacity];
    }

    int parent(int i) {
        return (i - 1) / 2;
    }

    int left(int i) {
        return (2 * i + 1);
    }

    int right(int i) {
        return (2 * i + 2);
    }

    private int extractMin() {
        // Your code here.
        if (heap_size == 0) {
            return -1;
        } else if (heap_size == 1) {
            return harr[--heap_size];
        }

        int root = harr[0];
        harr[0] = harr[--heap_size];
        MinHeapify(0);

        return root;
    }

    private void insertKey(int k) {
        //Your code here.
        decreaseKey(heap_size, k);
        heap_size++;
    }

    private void deleteKey(int i) {
        //Your code here.
        if (i >= heap_size)
            return;
        heap_size--;
        decreaseKey(i, harr[heap_size]);
    }

    private void decreaseKey(int i, int new_val) {
        harr[i] = new_val;
        while (i != 0 && harr[parent(i)] > harr[i]) {
            int temp = harr[i];
            harr[i] = harr[parent(i)];
            harr[parent(i)] = temp;
            i = parent(i);
        }
    }

    private void MinHeapify(int i) {
        int l = left(i);
        int r = right(i);
        int smallest = i;
        if (l < heap_size && harr[l] < harr[i])
            smallest = l;
        if (r < heap_size && harr[r] < harr[smallest])
            smallest = r;
        if (smallest != i) {
            int temp = harr[i];
            harr[i] = harr[smallest];
            harr[smallest] = temp;
            MinHeapify(smallest);
        }
    }
}
