import java.util.*;

public class Graph {
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addEdge(3,5,true);
        graph.addEdge(3,4,true);
        graph.addEdge(5,6,false);
        graph.display();
        graph.BFS(3);
        graph.DFS(3);
    }
    Map<Integer, List<Integer>> map = new HashMap<>();
    public void addVertex(Integer vertex){
        map.put(vertex, new ArrayList<>());
    }
    public void addEdge(Integer vertex, Integer edge, boolean biDirectional){
        if(!map.containsKey(vertex))
            addVertex(vertex);
        if(!map.containsKey(edge))
            addVertex(edge);

        map.get(vertex).add(edge);
        if(biDirectional)
            map.get(edge).add(vertex);
    }
    public void removeEdge(Integer vertex, Integer edge, boolean isBidirectional){
        if(map.containsKey(vertex) && map.containsKey(vertex)){
            map.get(vertex).remove(edge);
            if(isBidirectional)
                map.get(edge).remove(vertex);
        }
    }
    public void display(){
        for(Integer vertex : map.keySet()){
            System.out.print(vertex+":");
                for(Integer edge : map.get(vertex)){
                    System.out.printf(edge+" ");
                }
            System.out.println();
        }
    }
    public void BFS(int start){
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.offer(start);
        visited.add(start);
        while(!queue.isEmpty()){
            int current = queue.poll();
            System.out.printf(current+" ");
            for(int neighbour: map.get(current)){
                if(!visited.contains(neighbour)){
                    visited.add(neighbour);
                    queue.offer(neighbour);
                }
            }
        }

    }
    public void DFS(int start){
        System.out.println("\nDFS");
        Set<Integer> visited = new HashSet<>();
        DFS(start, visited);
    }
    public void DFS(int current, Set<Integer> visited){
        visited.add(current);
        System.out.print(current+" ");
        for (int neighbour : map.get(current)){
            if(!visited.contains(neighbour)){
                DFS(neighbour, visited);
            }
        }
    }
}
