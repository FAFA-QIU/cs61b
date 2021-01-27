package lab11.graphs;

/**
 * @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int[] parent;
    private boolean found = false;

    public MazeCycles(Maze m) {
        super(m);
        parent = new int[maze.V()];
    }

    @Override
    public void solve() {
        marked[0] = true;
        parent[0] = 0;
        dfs(0);

    }

    // Helper methods go here
    private void dfs(int v) {

        for (int w : maze.adj(v)) {
            if (found) {
                return;
            }
            if (!marked[w]) {
                marked[w] = true;
                parent[w] = v;
                announce();
                dfs(w);
            } else if (parent[v] != w) {
                parent[w] = v;
                int n = v;
                edgeTo[n] = parent[n];
                while (n != w) {
                    n = parent[n];
                    edgeTo[n] = parent[n];
                }
                announce();
                found = true;
                return;
            }

        }
    }
}


