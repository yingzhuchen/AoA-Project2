import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/*
 * PROJECT 2: NETWORK FLOW CASE STUDY
 * Real Problem: Tutor Allocation System
 * * MAPPING REALITY TO ALGORITHM:
 * 1. Nodes 0 to (N-1): Represent Student Groups (Demand Side)
 * 2. Nodes N to (N+M-1): Represent Tutors (Supply Side)
 * 3. Capacity (Source -> Student): Represents the number of hours a student group needs.
 * 4. Capacity (Tutor -> Sink): Represents the number of hours a tutor is available.
 * 5. Edges (Student -> Tutor): Represent compatibility (subject match).
 * * This program constructs this specific bipartite graph structure and solves it 
 * using the Edmonds-Karp Max-Flow algorithm to find the optimal schedule.
 */

public class TutorAllocation {

  // Internal class: Edge
  static class Edge {
    int to;
    int capacity;
    int flow;
    int reverseEdge; // Index of the reverse edge in the adjacency list

    public Edge(int to, int capacity, int reverseEdge) {
      this.to = to;
      this.capacity = capacity;
      this.flow = 0;
      this.reverseEdge = reverseEdge;
    }
  }

  List<List<Edge>> graph;
  int source;
  int sink;
  int n; // Total nodes

  public TutorAllocation(int n, int source, int sink) {
    this.n = n;
    this.source = source;
    this.sink = sink;
    this.graph = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      graph.add(new ArrayList<>());
    }
  }

  // Add directional edge with capacity
  public void addEdge(int from, int to, int capacity) {
    Edge forward = new Edge(to, capacity, graph.get(to).size());
    Edge backward = new Edge(from, 0, graph.get(from).size()); // Residual edge starts 0
    graph.get(from).add(forward);
    graph.get(to).add(backward);
  }

  // BFS to find augmenting path
  private int bfs(int[] parent, int[] edgeIndex) {
    Arrays.fill(parent, -1);
    parent[source] = -2;
    Queue<AbstractMap.SimpleEntry<Integer, Integer>> q = new LinkedList<>();
    q.add(new AbstractMap.SimpleEntry<>(source, Integer.MAX_VALUE));

    while (!q.isEmpty()) {
      AbstractMap.SimpleEntry<Integer, Integer> curr = q.poll();
      int u = curr.getKey();
      int flow = curr.getValue();

      for (int i = 0; i < graph.get(u).size(); i++) {
        Edge e = graph.get(u).get(i);
        if (parent[e.to] == -1 && e.capacity > e.flow) {
          parent[e.to] = u;
          edgeIndex[e.to] = i;
          int newFlow = Math.min(flow, e.capacity - e.flow);
          if (e.to == sink)
            return newFlow;
          q.add(new AbstractMap.SimpleEntry<>(e.to, newFlow));
        }
      }
    }
    return 0;
  }

  // Edmonds-Karp Algorithm
  public int getMaxFlow() {
    int maxFlow = 0;
    int[] parent = new int[n];
    int[] edgeIndex = new int[n];

    while (true) {
      int pathFlow = bfs(parent, edgeIndex);
      if (pathFlow == 0)
        break;
      maxFlow += pathFlow;
      int curr = sink;
      while (curr != source) {
        int prev = parent[curr];
        int idx = edgeIndex[curr];
        Edge forward = graph.get(prev).get(idx);
        Edge backward = graph.get(curr).get(forward.reverseEdge);
        forward.flow += pathFlow;
        backward.flow -= pathFlow;
        curr = prev;
      }
    }
    return maxFlow;
  }

  public static void runExperiment() throws IOException {
    FileWriter csvWriter = new FileWriter("tutor_results.csv");
    csvWriter.append("NumGroups,TimeMillis\n");

    // Sizes to test (from small to large)
    int[] sizes = { 10, 50, 100, 200, 400, 600, 800, 1000 };
    System.out.println("Running experiments (Java)...");

    for (int numGroups : sizes) {
      int numTutors = numGroups / 2;

      // Node mapping:
      // Source = 0
      // Sink = 1
      // Groups = 2 to numGroups+1
      // Tutors = numGroups+2 to End
      int source = 0;
      int sink = 1;
      int totalNodes = 2 + numGroups + numTutors;

      TutorAllocation solver = new TutorAllocation(totalNodes, source, sink);
      Random rand = new Random();

      // 1. Build Source -> Groups
      for (int i = 0; i < numGroups; i++) {
        solver.addEdge(source, 2 + i, rand.nextInt(15) + 5);
      }
      // 2. Build Tutors -> Sink
      for (int j = 0; j < numTutors; j++) {
        solver.addEdge(2 + numGroups + j, sink, rand.nextInt(25) + 10);
      }
      // 3. Build Groups -> Tutors (Compatibility)
      for (int i = 0; i < numGroups; i++) {
        for (int j = 0; j < numTutors; j++) {
          // 30% chance a tutor can teach this group
          if (rand.nextDouble() < 0.3) {
            solver.addEdge(2 + i, 2 + numGroups + j, Integer.MAX_VALUE);
          }
        }
      }

      // Measure Time
      long startTime = System.nanoTime();
      solver.getMaxFlow();
      long endTime = System.nanoTime();

      double durationMs = (endTime - startTime) / 1e6;
      System.out.println("Size: " + numGroups + " groups | Time: " + durationMs + " ms");
      csvWriter.append(numGroups + "," + durationMs + "\n");
    }

    csvWriter.flush();
    csvWriter.close();
    System.out.println("Done! Results saved to tutor_results.csv");
  }

  public static void main(String[] args) throws IOException {
    runExperiment();
  }
}