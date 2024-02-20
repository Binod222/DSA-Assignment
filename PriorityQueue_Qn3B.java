public class PriorityQueue_Qn3B {

    MinHeap_Qn3B heap; // Priority queue implemented using a min-heap

    PriorityQueue_Qn3B() {
        heap = new MinHeap_Qn3B(); // Initialize the min-heap
    }

    // Remove and return the highest priority element from the priority queue
    int dequeue() {
        return heap.deleteHeap(0); // Dequeue the root element from the min-heap
    }

    // Insert an element into the priority queue
    void queue(int element) {
        heap.insertHeap(element); // Enqueue the element into the min-heap
    }

    public static void main(String[] args) {
        PriorityQueue_Qn3B pq = new PriorityQueue_Qn3B(); // Create a priority queue
        pq.queue(12); // Enqueue elements into the priority queue
        pq.queue(213);
        pq.queue(99);
        pq.queue(21);
        pq.queue(143);

        pq.dequeue(); // Dequeue the highest priority element from the priority queue
        System.out.println(pq); // Output the remaining elements in the priority queue
    }

    @Override
    public String toString() {
        return heap.heapList.toString(); // Return a string representation of the priority queue
    }

}
