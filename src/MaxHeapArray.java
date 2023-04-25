public class MaxHeapArray {
    public static void main(String[] args) {
        MaxHeapArray maxHeapArray = new MaxHeapArray(10);
        maxHeapArray.insert(19);
        maxHeapArray.insert(15);
        maxHeapArray.insert(1);

        maxHeapArray.printHeap();




    }
    public void printHeap() {
        int n = heap.length;
        for (int i = 0; i < n; i++) {
            System.out.print(heap[i] + " ");
            if (i == 0 || i == 2 || i == 6 || i == 14 || i == 30 || i == 62) {
                System.out.println();
            }
        }
        System.out.println();
    }
    private int[] heap;
    private int size;
    private int capacity;

    public MaxHeapArray(int capacity) {
        this.heap = new int[capacity];
        this.size = 0;
        this.capacity = capacity;
    }

    public int getMax() {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty");
        }
        return heap[0];
    }

    public void insert(int value) {
        if (size == capacity) {
            throw new IllegalStateException("Heap is full");
        }
        heap[size] = value;
        size++;
        heapifyUp(size - 1);
    }

    public int removeMax() {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty");
        }
        int max = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapifyDown(0);
        return max;
    }

    private void heapifyUp(int index) {
        int parentIndex = (index - 1) / 2;
        while (index > 0 && heap[index] > heap[parentIndex]) {
            swap(index, parentIndex);
            index = parentIndex;
            parentIndex = (index - 1) / 2;
        }
    }

    private void heapifyDown(int index) {
        int leftChildIndex = index * 2 + 1;
        int rightChildIndex = index * 2 + 2;
        int largestIndex = index;
        if (leftChildIndex < size && heap[leftChildIndex] > heap[largestIndex]) {
            largestIndex = leftChildIndex;
        }
        if (rightChildIndex < size && heap[rightChildIndex] > heap[largestIndex]) {
            largestIndex = rightChildIndex;
        }
        if (largestIndex != index) {
            swap(index, largestIndex);
            heapifyDown(largestIndex);
        }
    }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
