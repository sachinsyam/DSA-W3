package oth;

public class Dijkstra {

    private static final int NO_PARENT = -1;

    // Implementation of oth.Dijkstra's algorithm for finding the shortest path
    // from a start node to all other nodes in a weighted graph
    public static void dijkstra(int[][] graph, int startNode) {
        int n = graph.length;
        int[] shortestDistances = new int[n]; // shortest distance from startNode to each node
        boolean[] visited = new boolean[n]; // true if node is visited

        // Initialize shortestDistances and visited arrays
        for (int i = 0; i < n; i++) {
            shortestDistances[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }

        // Distance from startNode to itself is 0
        shortestDistances[startNode] = 0;

        // Parent of startNode is undefined
        int[] parents = new int[n];
        parents[startNode] = NO_PARENT;

        // Loop through all nodes
        for (int i = 0; i < n - 1; i++) {
            // Find the node with the shortest distance that has not been visited yet
            int currentNode = findShortestDistanceNode(shortestDistances, visited);
            visited[currentNode] = true;

            // Update the distances of the neighbors of the current node
            for (int j = 0; j < n; j++) {
                int edgeDistance = graph[currentNode][j];
                if (edgeDistance > 0 && shortestDistances[currentNode] + edgeDistance < shortestDistances[j]) {
                    parents[j] = currentNode;
                    shortestDistances[j] = shortestDistances[currentNode] + edgeDistance;
                }
            }
        }

        // Print the shortest distances and the path from startNode to each node
        for (int i = 0; i < n; i++) {
            if (i != startNode) {
                System.out.println("Shortest distance from " + startNode + " to " + i + ": " + shortestDistances[i]);
                System.out.print("Path: " + i);
                int current = i;
                while (current != startNode) {
                    current = parents[current];
                    System.out.print(" <- " + current);
                }
                System.out.println();
            }
        }
    }

    // Helper method to find the node with the shortest distance that has not been visited yet
    private static int findShortestDistanceNode(int[] distances, boolean[] visited) {
        int shortestDistance = Integer.MAX_VALUE;
        int shortestDistanceNode = -1;
        for (int i = 0; i < distances.length; i++) {
            if (!visited[i] && distances[i] < shortestDistance) {
                shortestDistance = distances[i];
                shortestDistanceNode = i;
            }
        }
        return shortestDistanceNode;
    }

    // Test the implementation
    public static void main(String[] args) {
        int[][] graph = {
                {0, 6, 0, 1, 0},
                {6, 0, 5, 2, 2},
                {0, 5, 0, 0, 5},
                {1, 2, 0, 0, 1},
                {0, 2, 5, 1, 0}
        };
        dijkstra(graph, 0);
    }
}
