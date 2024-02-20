import java.util.ArrayList;

public class MinHeap_Qn3B {
    // Min heap is such that binary tree where Parent node <= child node
    ArrayList<Integer> heapList;

    // Constructor
    MinHeap_Qn3B() {
        heapList = new ArrayList<>();
    }

    // Swap elements at given indices in the heapList
    void swapHeapListItems(int index1, int index2) {
        // Swap elements at index1 and index2 in heapList
        int temp = heapList.get(index1);
        heapList.set(index1, heapList.get(index2));
        heapList.set(index2, temp);
    }

    // Insert a new data element into the min-heap
    void insertHeap(int data) {
        heapList.add(data);
        int childIndex = heapList.size() - 1;

        // Continue swapping until the inserted data is at the top or in the correct position
        while (childIndex > 0) {
            int parentIndex = (childIndex - 1) / 2;

            if (heapList.get(childIndex) < heapList.get(parentIndex)) {
                swapHeapListItems(childIndex, parentIndex);
                childIndex = parentIndex; // Update childIndex after swapping
            } else {
                return;
            }
        }
    }

    // Delete the root element (minimum) from the min-heap
    int deleteHeap(int n) {
        int temp = heapList.remove(0);
        heapList.add(0, heapList.get(n));
        int index = heapList.size();

        // Continue swapping until the heap property is restored
        while (index <= heapList.size()) {
            int largestIndex = index;
            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;

            if (leftChild < heapList.size() && heapList.get(leftChild) > heapList.get(largestIndex)) {
                largestIndex = leftChild;
            }
            if (rightChild < heapList.size() && heapList.get(rightChild) > heapList.get(largestIndex)) {
                largestIndex = rightChild;
            }

            if (largestIndex != index) {
                swapHeapListItems(largestIndex, index);
                index = largestIndex; // Update index after swapping
            } else {
                return temp;
            }
        }
        return temp;
    }

    public static void main(String[] args) {
        MinHeap_Qn3B minHeap = new MinHeap_Qn3B();
        minHeap.insertHeap(12);
        minHeap.insertHeap(22);
        minHeap.insertHeap(76);
        minHeap.insertHeap(2);
        minHeap.insertHeap(931);
        minHeap.insertHeap(24);
        System.out.println(minHeap.heapList);
    }
}
