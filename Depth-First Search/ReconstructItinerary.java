import java.util.*;

public class ReconstructItinerary {

    // Graph: source -> min heap of destinations
    private static Map<String, PriorityQueue<String>> graph = new HashMap<>();

    // Stores itinerary in reverse order
    private static LinkedList<String> itinerary = new LinkedList<>();

    // DFS Traversal
    public static void dfs(String airport) {

        // Get all destinations from current airport
        PriorityQueue<String> destinations = graph.get(airport);

        // Visit destinations in lexical order
        while (destinations != null && !destinations.isEmpty()) {

            String nextAirport = destinations.poll();

            dfs(nextAirport);
        }

        // Add airport after visiting all neighbors
        itinerary.addFirst(airport);
    }

    public static List<String> findItinerary(List<List<String>> tickets) {

        // Build graph
        for (List<String> ticket : tickets) {

            String from = ticket.get(0);
            String to = ticket.get(1);

            graph.putIfAbsent(from, new PriorityQueue<>());

            graph.get(from).offer(to);
        }

        // Start DFS from JFK
        dfs("JFK");

        return itinerary;
    }

    public static void main(String[] args) {

        List<List<String>> tickets = new ArrayList<>();

        tickets.add(Arrays.asList("MUC", "LHR"));
        tickets.add(Arrays.asList("JFK", "MUC"));
        tickets.add(Arrays.asList("SFO", "SJC"));
        tickets.add(Arrays.asList("LHR", "SFO"));

        List<String> result = findItinerary(tickets);

        System.out.println(result);
    }
}