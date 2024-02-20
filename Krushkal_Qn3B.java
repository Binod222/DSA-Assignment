// Implement Kruskal's algorithm and priority queue using a minimum heap

// Time Complexity: O(E log E) where E is the number of edges 
// Space Complexity: O(V + E) where V means the number of vertices or nodes and E is the number of edges

import java.util.PriorityQueue;

public class Krushkal_Qn3B {

    public static class Edge implements Comparable<Edge> {
        int source; // Renamed variable for better readability
        int destination; // Renamed variable for better readability
        int weight;

        Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return this.weight - other.weight;
        }
    }

    int vertices; // Renamed variable for better readability
    PriorityQueue<Edge> minHeap; // Renamed variable for better readability

    Krushkal_Qn3B(int vertices) {
        this.vertices = vertices;
        this.minHeap = new PriorityQueue<>();
    }

    void addEdge(int source, int destination, int weight) {
        minHeap.add(new Edge(source, destination, weight));
    }

    void kruskal() {
        int[] parent = new int[vertices];

        // Initialize each vertex as a separate set
        for (int i = 0; i < vertices; i++) {
            parent[i] = i;
        }

        while (!minHeap.isEmpty()) {
            Edge edge = minHeap.poll();
            int sourceParent = findParent(edge.source, parent);
            int destinationParent = findParent(edge.destination, parent);

            // If including this edge doesn't create a cycle, include it in the minimum spanning tree
            if (sourceParent != destinationParent) {
                System.out.println(edge.source + " - " + edge.destination + " : " + edge.weight);
                union(sourceParent, destinationParent, parent);
            }
        }
    }

    int findParent(int vertex, int[] parent) {
        if (parent[vertex] == vertex) {
            return vertex;
        }
        return parent[vertex] = findParent(parent[vertex], parent);
    }

    void union(int sourceParent, int destinationParent, int[] parent) {
        parent[sourceParent] = destinationParent;
    }

    public static void main(String[] args) {
        Krushkal_Qn3B graph = new Krushkal_Qn3B(4);

        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 2, 6);
        graph.addEdge(0, 3, 5);
        graph.addEdge(1, 3, 15);
        graph.addEdge(2, 3, 4);

        graph.kruskal();
    }
}
