public class MinHeap {
    public static void main(String[] args) {

        MinHeap minHeap = new MinHeap(15);
        minHeap.insert(5);
        minHeap.insert(3);
        minHeap.insert(17);
        minHeap.insert(10);
        minHeap.insert(84);
        minHeap.insert(19);
        minHeap.insert(6);
        minHeap.insert(22);
        minHeap.insert(9);

        minHeap.print();

        minHeap.delete();
        minHeap.print();
        
    }

    int size, maxSize;
    int heap [];

    public MinHeap(int maxSize) {
        this.maxSize = maxSize;
        size = 0;
        heap = new int[maxSize];
    }
    public void insert(int val){
        int current = size;

        heap[current] = val;

        while(heap[current] < heap[getParent(current)]){
            swap(current, getParent(current));
            current = getParent(current);
        }

        size++;
    }

    public void print(){
        for (int i = 0; i < size/2; i++) {
            System.out.print("Parent:"+heap[i]  + "\t" + "Left:" + heap[getLeft(i)] + "\t" + "Right:" + heap[getRight(i)] + "\n");
        }
    }
    public void swap(int i, int j){
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
    public int getLeft(int pos) {
        return 2 * pos + 1;
    }
    public int getRight(int pos){
        return 2* pos +2;
    }
    public int getParent(int pos){
        return (pos-1)/2;
    }
    public boolean isLeaf(int pos){
        return (pos <= size && pos > size/2);
    }
    public int delete(){
        int deleted = heap[0];
        heap[0] = heap[--size];
        heapify(0);
        System.out.println("Deleted:"+deleted);
        return deleted;
    }
    public void heapify(int pos){
        if(heap[pos] > heap[getLeft(pos)] || heap[pos] > heap[getRight(pos)]){
            if(heap[getLeft(pos)] < heap[getRight(pos)]){
                swap(pos, getLeft(pos));
                heapify(getLeft(pos));
            }
            else{
                swap(pos, getRight(pos));
                heapify(getRight(pos));
            }
        }
    }
}

