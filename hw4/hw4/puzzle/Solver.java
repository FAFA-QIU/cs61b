package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;

public class Solver {
    private SearchNode goal;

    private static class SearchNode implements Comparable<SearchNode> {
        private WorldState worldState;
        private int moves;
        private SearchNode prev;
        private int priority;

        SearchNode(WorldState worldState, int moves, SearchNode prev) {
            this.worldState = worldState;
            this.moves = moves;
            this.prev = prev;
            priority = this.moves + worldState.estimatedDistanceToGoal();
        }

        public int compareTo(SearchNode o) {
            return this.priority - o.priority;
        }
    }

    /**
     * Constructor which solves the puzzle, computing
     * everything necessary for moves() and solution() to
     * not have to solve the problem again. Solves the
     * puzzle using the A* algorithm. Assumes a solution exists.
     */
    public Solver(WorldState initial) {
        if (initial == null) {
            throw new IllegalArgumentException();
        }

        MinPQ<SearchNode> pq = new MinPQ<>();
        SearchNode node = new SearchNode(initial, 0, null);
        pq.insert(node);
        while (true) {
            SearchNode n = pq.delMin();
            if (n.worldState.isGoal()) {
                goal = n;
                break;
            }
            for (WorldState worldState : n.worldState.neighbors()) {
                if (n.prev == null || !n.prev.worldState.equals(worldState)) {
                    pq.insert(new SearchNode(worldState, n.moves + 1, n));
                }
            }
        }
    }

    /**
     * Returns the minimum number of moves to solve the puzzle starting
     * at the initial WorldState.
     */
    public int moves() {
        return goal.moves;
    }

    /**
     * Returns a sequence of WorldStates from the initial WorldState
     * to the solution.
     */
    public Iterable<WorldState> solution() {
        ArrayList<WorldState> list = new ArrayList<>();
        SearchNode node = goal;
        while (node != null) {
            list.add(0, node.worldState);
            node = node.prev;
        }
        return list;
    }
}
