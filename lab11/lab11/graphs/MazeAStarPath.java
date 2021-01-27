package lab11.graphs;

import edu.princeton.cs.algs4.MinPQ;

/**
 * @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;

    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    private class SearchNode implements Comparable<SearchNode> {
        private int v;
        private int priority;
        private SearchNode prev;

        public int compareTo(SearchNode n) {
            return priority - n.priority;
        }

        SearchNode(int v, SearchNode prev) {
            this.v = v;
            this.priority = h(this.v);
            this.prev = prev;
        }
    }

    /**
     * Estimate of the distance from v to the target.
     */
    private int h(int v) {
        return Math.abs(maze.toX(s) - maze.toX(t))
                + Math.abs(maze.toY(s) - maze.toY(t));
    }

    /**
     * Finds vertex estimated to be closest to target.
     */
    private int findMinimumUnmarked() {
        return -1;
        /* You do not have to use this method. */
    }

    /**
     * Performs an A star search from vertex s.
     */
    private void astar(int n) {
        helper(n);
    }

    private void helper(int x) {
        SearchNode node = new SearchNode(s, null);
        MinPQ<SearchNode> pq = new MinPQ<>();
        pq.insert(node);
        while (!targetFound) {
            SearchNode n = pq.delMin();
            marked[n.v] = true;
            if (n.v != 0) {
                edgeTo[n.v] = n.prev.v;
                distTo[n.v] = distTo[n.prev.v] + 1;
            }
            announce();
            if (n.v == t) {
                targetFound = true;
            } else {
                for (int w : maze.adj(n.v)) {
                    if (!marked[w]) {
                        pq.insert(new SearchNode(w, n));
                    }
                }
            }
        }

    }

    @Override
    public void solve() {
        astar(s);
    }

}

