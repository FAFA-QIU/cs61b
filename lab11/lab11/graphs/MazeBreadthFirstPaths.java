package lab11.graphs;

import java.util.LinkedList;

/**
 * @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int start;
    private int goal;
    private LinkedList<Integer> queue;
    private boolean targetFound;
    private Maze m;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        this.m = m;
        start = maze.xyTo1D(sourceX, sourceY);
        goal = maze.xyTo1D(targetX, targetY);
        queue = new LinkedList<>();
        distTo[start] = 0;
        edgeTo[start] = start;
        targetFound = false;
    }

    /**
     * Conducts a breadth first search of the maze starting at the source.
     */
    private void bfs() {
        queue.add(start);
        while (true) {
            if (queue.isEmpty()) {
                return;
            }
            int vertice = queue.remove();
            marked[vertice] = true;
            announce();
            if (vertice == goal) {
                targetFound = true;
            }
            if (targetFound) {
                return;
            }

            for (int i : m.adj(vertice)) {
                if (!marked[i]) {
                    edgeTo[i] = vertice;
                    distTo[i] = distTo[vertice] + 1;
                    queue.add(i);
                }
            }
        }

    }


    @Override
    public void solve() {
        bfs();
    }
}

