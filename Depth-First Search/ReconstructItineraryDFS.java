/*
Question:
You are given a list of airline tickets where
tickets[i] = [from, to].

Reconstruct the itinerary in order and return it.

Rules:
1. The itinerary must begin with "JFK"
2. Use all tickets exactly once
3. If multiple valid itineraries exist,
   return the lexicographically smallest one

--------------------------------------------------

How It Works (DFS + Hierholzer Algorithm):

1. Build a graph:
   from -> destinations

2. Use PriorityQueue:
   Always pick the smallest lexical airport first

3. Apply Depth First Search (DFS):
   Keep traveling deeply until no more destinations exist

4. Backtracking:
   Add airport to the front of itinerary
   AFTER visiting all neighbors

This guarantees:
- every ticket is used once
- lexical order is maintained

--------------------------------------------------

Running Time:
O(E log E)

E = number of tickets

Reason:
Each ticket is inserted and removed
from PriorityQueue once.

--------------------------------------------------

Space Complexity:
O(E)

Used for:
- graph
- recursion stack
- itinerary list
*/

import java.util.*;

public class ReconstructItineraryDFS {

    // Graph: source -> destinations
    private Map<String, PriorityQueue<String>> graph;

    // Final itinerary
    private LinkedList<String> itinerary;

    public List<String> findItinerary(List<List<String>> tickets) {

        graph = new HashMap<>();
        itinerary = new LinkedList<>();

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

    // Depth First Search
    private void dfs(String airport) {

        PriorityQueue<String> destinations = graph.get(airport);

        // Visit all destinations
        while (destinations != null && !destinations.isEmpty()) {

            String nextAirport = destinations.poll();

            dfs(nextAirport);
        }

        // Add airport after visiting neighbors
        itinerary.addFirst(airport);
    }

    public static void main(String[] args) {

        List<List<String>> tickets = new ArrayList<>();

        tickets.add(Arrays.asList("JFK", "SFO"));
        tickets.add(Arrays.asList("JFK", "ATL"));
        tickets.add(Arrays.asList("SFO", "ATL"));
        tickets.add(Arrays.asList("ATL", "JFK"));
        tickets.add(Arrays.asList("ATL", "SFO"));

        ReconstructItineraryDFS solution =
                new ReconstructItineraryDFS();

        List<String> result =
                solution.findItinerary(tickets);

        System.out.println(result);
    }
}