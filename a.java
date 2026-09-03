import java.util.*;

/**
 * BOTCLEAN
 *
 * 0 = Clean
 * 1 = Dirty
 * X = Obstacle
 * B = Bot
 *
 * Algorithms:
 * 1. BFS
 * 2. DFS
 * 3. A*
 */
public class BotClean {

    // Movement: UP, DOWN, LEFT, RIGHT
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static String[] directionNames = {
            "UP", "DOWN", "LEFT", "RIGHT"
    };

    // =========================================================
    // POSITION
    // =========================================================

    static class Position {
        int row;
        int col;

        Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    // =========================================================
    // STATE
    // =========================================================

    static class State {

        int row;
        int col;

        // Bitmask stores dirty cells remaining
        int dirtyMask;

        int g;  // Cost so far
        int h;  // Heuristic

        State parent;
        String move;

        State(int row, int col, int dirtyMask) {
            this.row = row;
            this.col = col;
            this.dirtyMask = dirtyMask;
        }

        State(int row, int col, int dirtyMask,
              int g, int h,
              State parent, String move) {

            this.row = row;
            this.col = col;
            this.dirtyMask = dirtyMask;
            this.g = g;
            this.h = h;
            this.parent = parent;
            this.move = move;
        }

        int f() {
            return g + h;
        }
    }

    // =========================================================
    // RESULT
    // =========================================================

    static class Result {

        List<State> path;

        int moves;
        int nodesExplored;
        long executionTime;

        Result(List<State> path,
               int nodesExplored,
               long executionTime) {

            this.path = path;
            this.moves = path.size() - 1;
            this.nodesExplored = nodesExplored;
            this.executionTime = executionTime;
        }
    }

    // =========================================================
    // CHECK VALID CELL
    // =========================================================

    static boolean isValid(char[][] grid, int row, int col) {

        return row >= 0 &&
                row < grid.length &&
                col >= 0 &&
                col < grid[0].length &&
                grid[row][col] != 'X';
    }

    // =========================================================
    // GET DIRTY CELLS
    // =========================================================

    static List<Position> getDirtyCells(char[][] grid) {

        List<Position> dirtyCells = new ArrayList<>();

        for (int i = 0; i < grid.length; i++) {

            for (int j = 0; j < grid[0].length; j++) {

                if (grid[i][j] == '1') {
                    dirtyCells.add(new Position(i, j));
                }
            }
        }

        return dirtyCells;
    }

    // =========================================================
    // CREATE DIRTY MASK
    // =========================================================

    static int createDirtyMask(
            List<Position> dirtyCells) {

        int mask = 0;

        for (int i = 0; i < dirtyCells.size(); i++) {
            mask |= (1 << i);
        }

        return mask;
    }

    // =========================================================
    // CLEAN CELL
    // =========================================================

    static int cleanCell(
            int mask,
            int row,
            int col,
            List<Position> dirtyCells) {

        for (int i = 0; i < dirtyCells.size(); i++) {

            Position p = dirtyCells.get(i);

            if (p.row == row && p.col == col) {

                mask = mask & ~(1 << i);
                break;
            }
        }

        return mask;
    }

    // =========================================================
    // CHECK WHETHER CELL IS DIRTY
    // =========================================================

    static boolean isDirty(
            int row,
            int col,
            int mask,
            List<Position> dirtyCells) {

        for (int i = 0; i < dirtyCells.size(); i++) {

            if ((mask & (1 << i)) != 0) {

                Position p = dirtyCells.get(i);

                if (p.row == row && p.col == col) {
                    return true;
                }
            }
        }

        return false;
    }

    // =========================================================
    // STATE KEY
    // =========================================================

    static String getKey(
            int row,
            int col,
            int dirtyMask) {

        return row + "," + col + "," + dirtyMask;
    }

    // =========================================================
    // BFS
    // =========================================================

    static Result bfs(
            char[][] grid,
            int startRow,
            int startCol,
            List<Position> dirtyCells) {

        long startTime = System.nanoTime();

        int nodesExplored = 0;

        int initialMask =
                createDirtyMask(dirtyCells);

        State start =
                new State(
                        startRow,
                        startCol,
                        initialMask
                );

        // Clean starting cell if dirty
        start.dirtyMask =
                cleanCell(
                        start.dirtyMask,
                        startRow,
                        startCol,
                        dirtyCells
                );

        Queue<State> queue =
                new LinkedList<>();

        queue.add(start);

        Set<String> visited =
                new HashSet<>();

        visited.add(
                getKey(
                        start.row,
                        start.col,
                        start.dirtyMask
                )
        );

        while (!queue.isEmpty()) {

            State current = queue.poll();

            nodesExplored++;

            // Goal
            if (current.dirtyMask == 0) {

                long endTime = System.nanoTime();

                return new Result(
                        reconstructPath(current),
                        nodesExplored,
                        endTime - startTime
                );
            }

            // Explore four directions
            for (int i = 0; i < 4; i++) {

                int nr = current.row + dr[i];
                int nc = current.col + dc[i];

                if (!isValid(grid, nr, nc)) {
                    continue;
                }

                int newMask =
                        cleanCell(
                                current.dirtyMask,
                                nr,
                                nc,
                                dirtyCells
                        );

                String key =
                        getKey(
                                nr,
                                nc,
                                newMask
                        );

                if (!visited.contains(key)) {

                    visited.add(key);

                    State next =
                            new State(
                                    nr,
                                    nc,
                                    newMask,
                                    current.g + 1,
                                    0,
                                    current,
                                    directionNames[i]
                            );

                    queue.add(next);
                }
            }
        }

        return null;
    }

    // =========================================================
    // DFS
    // =========================================================

    static Result dfs(
            char[][] grid,
            int startRow,
            int startCol,
            List<Position> dirtyCells) {

        long startTime = System.nanoTime();

        int initialMask =
                createDirtyMask(dirtyCells);

        State start =
                new State(
                        startRow,
                        startCol,
                        initialMask
                );

        start.dirtyMask =
                cleanCell(
                        start.dirtyMask,
                        startRow,
                        startCol,
                        dirtyCells
                );

        Set<String> visited =
                new HashSet<>();

        int[] nodesExplored = {0};

        State solution =
                dfsRecursive(
                        grid,
                        start,
                        dirtyCells,
                        visited,
                        nodesExplored
                );

        long endTime = System.nanoTime();

        if (solution == null) {
            return null;
        }

        return new Result(
                reconstructPath(solution),
                nodesExplored[0],
                endTime - startTime
        );
    }

    // =========================================================
    // DFS RECURSION
    // =========================================================

    static State dfsRecursive(
            char[][] grid,
            State current,
            List<Position> dirtyCells,
            Set<String> visited,
            int[] nodesExplored) {

        nodesExplored[0]++;

        // Goal
        if (current.dirtyMask == 0) {
            return current;
        }

        String key =
                getKey(
                        current.row,
                        current.col,
                        current.dirtyMask
                );

        if (visited.contains(key)) {
            return null;
        }

        visited.add(key);

        for (int i = 0; i < 4; i++) {

            int nr =
                    current.row + dr[i];

            int nc =
                    current.col + dc[i];

            if (!isValid(grid, nr, nc)) {
                continue;
            }

            int newMask =
                    cleanCell(
                            current.dirtyMask,
                            nr,
                            nc,
                            dirtyCells
                    );

            State next =
                    new State(
                            nr,
                            nc,
                            newMask,
                            current.g + 1,
                            0,
                            current,
                            directionNames[i]
                    );

            State result =
                    dfsRecursive(
                            grid,
                            next,
                            dirtyCells,
                            visited,
                            nodesExplored
                    );

            if (result != null) {
                return result;
            }
        }

        return null;
    }

    // =========================================================
    // A*
    // =========================================================

    static Result aStar(
            char[][] grid,
            int startRow,
            int startCol,
            List<Position> dirtyCells) {

        long startTime = System.nanoTime();

        int nodesExplored = 0;

        int initialMask =
                createDirtyMask(dirtyCells);

        State start =
                new State(
                        startRow,
                        startCol,
                        initialMask
                );

        start.dirtyMask =
                cleanCell(
                        start.dirtyMask,
                        startRow,
                        startCol,
                        dirtyCells
                );

        start.g = 0;

        start.h =
                heuristic(
                        start.row,
                        start.col,
                        start.dirtyMask,
                        dirtyCells
                );

        PriorityQueue<State> openList =
                new PriorityQueue<>(
                        Comparator.comparingInt(State::f)
                );

        openList.add(start);

        Map<String, Integer> bestCost =
                new HashMap<>();

        bestCost.put(
                getKey(
                        start.row,
                        start.col,
                        start.dirtyMask
                ),
                0
        );

        while (!openList.isEmpty()) {

            State current =
                    openList.poll();

            nodesExplored++;

            // Goal
            if (current.dirtyMask == 0) {

                long endTime =
                        System.nanoTime();

                return new Result(
                        reconstructPath(current),
                        nodesExplored,
                        endTime - startTime
                );
            }

            String currentKey =
                    getKey(
                            current.row,
                            current.col,
                            current.dirtyMask
                    );

            if (current.g >
                    bestCost.getOrDefault(
                            currentKey,
                            Integer.MAX_VALUE)) {

                continue;
            }

            for (int i = 0; i < 4; i++) {

                int nr =
                        current.row + dr[i];

                int nc =
                        current.col + dc[i];

                if (!isValid(grid, nr, nc)) {
                    continue;
                }

                int newMask =
                        cleanCell(
                                current.dirtyMask,
                                nr,
                                nc,
                                dirtyCells
                        );

                int newG =
                        current.g + 1;

                int newH =
                        heuristic(
                                nr,
                                nc,
                                newMask,
                                dirtyCells
                        );

                State next =
                        new State(
                                nr,
                                nc,
                                newMask,
                                newG,
                                newH,
                                current,
                                directionNames[i]
                        );

                String key =
                        getKey(
                                nr,
                                nc,
                                newMask
                        );

                if (newG <
                        bestCost.getOrDefault(
                                key,
                                Integer.MAX_VALUE)) {

                    bestCost.put(
                            key,
                            newG
                    );

                    openList.add(next);
                }
            }
        }

        return null;
    }

    // =========================================================
    // A* HEURISTIC
    // =========================================================

    static int heuristic(
            int row,
            int col,
            int mask,
            List<Position> dirtyCells) {

        int minimumDistance =
                Integer.MAX_VALUE;

        for (int i = 0;
             i < dirtyCells.size();
             i++) {

            if ((mask & (1 << i)) != 0) {

                Position p =
                        dirtyCells.get(i);

                int distance =
                        Math.abs(row - p.row)
                        +
                        Math.abs(col - p.col);

                minimumDistance =
                        Math.min(
                                minimumDistance,
                                distance
                        );
            }
        }

        if (minimumDistance ==
                Integer.MAX_VALUE) {

            return 0;
        }

        return minimumDistance;
    }

    // =========================================================
    // RECONSTRUCT PATH
    // =========================================================

    static List<State> reconstructPath(
            State goal) {

        List<State> path =
                new ArrayList<>();

        State current = goal;

        while (current != null) {

            path.add(current);

            current = current.parent;
        }

        Collections.reverse(path);

        return path;
    }

    // =========================================================
    // PRINT GRID
    // =========================================================

    static void printGrid(
            char[][] originalGrid,
            State state,
            List<Position> dirtyCells) {

        char[][] grid =
                new char[originalGrid.length][];

        for (int i = 0;
             i < originalGrid.length;
             i++) {

            grid[i] =
                    originalGrid[i].clone();
        }

        // Show cleaned dirty cells as 0
        for (int i = 0;
             i < dirtyCells.size();
             i++) {

            Position p =
                    dirtyCells.get(i);

            if ((state.dirtyMask &
                    (1 << i)) == 0) {

                grid[p.row][p.col] = '0';
            }
        }

        // Bot position
        grid[state.row][state.col] = 'B';

        for (int i = 0;
             i < grid.length;
             i++) {

            for (int j = 0;
                 j < grid[0].length;
                 j++) {

                System.out.print(
                        grid[i][j] + " "
                );
            }

            System.out.println();
        }

        System.out.println();
    }

    // =========================================================
    // DISPLAY RESULT
    // =========================================================

    static void displayResult(
            String algorithm,
            Result result,
            char[][] grid,
            List<Position> dirtyCells) {

        System.out.println();
        System.out.println(
                "========================================"
        );

        System.out.println(
                algorithm + " MOVEMENT"
        );

        System.out.println(
                "========================================"
        );

        List<State> path =
                result.path;

        // Initial state
        System.out.println(
                "Step 0: START"
        );

        printGrid(
                grid,
                path.get(0),
                dirtyCells
        );

        // Every movement
        for (int i = 1;
             i < path.size();
             i++) {

            State current =
                    path.get(i);

            State previous =
                    path.get(i - 1);

            System.out.println(
                    "Step " + i +
                    ": MOVE " +
                    current.move
            );

            // Check if a dirty cell was cleaned
            if (isDirty(
                    current.row,
                    current.col,
                    previous.dirtyMask,
                    dirtyCells)) {

                System.out.println(
                        ">>> CLEANED DIRTY CELL <<<"
                );
            }

            printGrid(
                    grid,
                    current,
                    dirtyCells
            );
        }

        System.out.println(
                algorithm +
                " TOTAL MOVES      : " +
                result.moves
        );

        System.out.println(
                algorithm +
                " NODES EXPLORED    : " +
                result.nodesExplored
        );

        System.out.printf(
                "%s EXECUTION TIME : %.3f ms%n",
                algorithm,
                result.executionTime / 1_000_000.0
        );
    }

    // =========================================================
    // MAIN
    // =========================================================

    public static void main(String[] args) {

        // -----------------------------------------------------
        // GRID
        // -----------------------------------------------------

        char[][] grid = {

                {'0', '1', '0', '0', '0'},

                {'0', 'X', '0', '1', '0'},

                {'0', '0', '0', '0', '0'},

                {'1', '0', 'X', '0', '1'},

                {'0', '0', '0', '0', '0'}
        };

        // Bot starting position
        int startRow = 0;
        int startCol = 0;

        // Get dirty cells
        List<Position> dirtyCells =
                getDirtyCells(grid);

        // -----------------------------------------------------
        // PROJECT INFORMATION
        // -----------------------------------------------------

        System.out.println(
                "========================================"
        );

        System.out.println(
                "       BOTCLEAN AI SEARCH PROJECT"
        );

        System.out.println(
                "========================================"
        );

        System.out.println(
                "Grid Size       : " +
                grid.length + " x " +
                grid[0].length
        );

        System.out.println(
                "Dirty Cells     : " +
                dirtyCells.size()
        );

        System.out.println(
                "Obstacles       : X"
        );

        System.out.println(
                "Start Position  : (" +
                startRow + ", " +
                startCol + ")"
        );

        System.out.println();

        // -----------------------------------------------------
        // BFS
        // -----------------------------------------------------

        Result bfsResult =
                bfs(
                        grid,
                        startRow,
                        startCol,
                        dirtyCells
                );

        // -----------------------------------------------------
        // DFS
        // -----------------------------------------------------

        Result dfsResult =
                dfs(
                        grid,
                        startRow,
                        startCol,
                        dirtyCells
                );

        // -----------------------------------------------------
        // A*
        // -----------------------------------------------------

        Result aStarResult =
                aStar(
                        grid,
                        startRow,
                        startCol,
                        dirtyCells
                );

        // -----------------------------------------------------
        // DISPLAY BFS
        // -----------------------------------------------------

        if (bfsResult != null) {

            displayResult(
                    "BFS",
                    bfsResult,
                    grid,
                    dirtyCells
            );
        }

        // -----------------------------------------------------
        // DISPLAY DFS
        // -----------------------------------------------------

        if (dfsResult != null) {

            displayResult(
                    "DFS",
                    dfsResult,
                    grid,
                    dirtyCells
            );
        }

        // -----------------------------------------------------
        // DISPLAY A*
        // -----------------------------------------------------

        if (aStarResult != null) {

            displayResult(
                    "A*",
                    aStarResult,
                    grid,
                    dirtyCells
            );
        }

        // -----------------------------------------------------
        // FINAL COMPARISON
        // -----------------------------------------------------

        System.out.println();
        System.out.println(
                "========================================"
        );

        System.out.println(
                "          FINAL COMPARISON"
        );

        System.out.println(
                "========================================"
        );

        System.out.printf(
                "%-12s %-12s %-18s %-15s%n",
                "Algorithm",
                "Moves",
                "Nodes Explored",
                "Time (ms)"
        );

        System.out.println(
                "-------------------------------------------------------"
        );

        if (bfsResult != null) {

            System.out.printf(
                    "%-12s %-12d %-18d %.3f%n",
                    "BFS",
                    bfsResult.moves,
                    bfsResult.nodesExplored,
                    bfsResult.executionTime
                            / 1_000_000.0
            );
        }

        if (dfsResult != null) {

            System.out.printf(
                    "%-12s %-12d %-18d %.3f%n",
                    "DFS",
                    dfsResult.moves,
                    dfsResult.nodesExplored,
                    dfsResult.executionTime
                            / 1_000_000.0
            );
        }

        if (aStarResult != null) {

            System.out.printf(
                    "%-12s %-12d %-18d %.3f%n",
                    "A*",
                    aStarResult.moves,
                    aStarResult.nodesExplored,
                    aStarResult.executionTime
                            / 1_000_000.0
            );
        }

        // -----------------------------------------------------
        // FIND MINIMUM MOVES
        // -----------------------------------------------------

        int minimumMoves =
                Integer.MAX_VALUE;

        if (bfsResult != null) {
            minimumMoves =
                    Math.min(
                            minimumMoves,
                            bfsResult.moves
                    );
        }

        if (dfsResult != null) {
            minimumMoves =
                    Math.min(
                            minimumMoves,
                            dfsResult.moves
                    );
        }

        if (aStarResult != null) {
            minimumMoves =
                    Math.min(
                            minimumMoves,
                            aStarResult.moves
                    );
        }

        System.out.println();
        System.out.println(
                "Minimum Moves = " +
                minimumMoves
        );

        System.out.println();

        // -----------------------------------------------------
        // DETERMINE OPTIMAL ALGORITHMS
        // -----------------------------------------------------

        System.out.println(
                "Optimal Algorithm(s) based on moves:"
        );

        if (bfsResult != null &&
                bfsResult.moves == minimumMoves) {

            System.out.println(
                    "✓ BFS = " +
                    bfsResult.moves +
                    " moves"
            );
        }

        if (dfsResult != null &&
                dfsResult.moves == minimumMoves) {

            System.out.println(
                    "✓ DFS = " +
                    dfsResult.moves +
                    " moves"
            );
        }

        if (aStarResult != null &&
                aStarResult.moves == minimumMoves) {

            System.out.println(
                    "✓ A* = " +
                    aStarResult.moves +
                    " moves"
            );
        }

        // -----------------------------------------------------
        // FINAL CONCLUSION
        // -----------------------------------------------------

        System.out.println();
        System.out.println(
                "========================================"
        );

        System.out.println(
                "              CONCLUSION"
        );

        System.out.println(
                "========================================"
        );

        System.out.println(
                "BFS guarantees an optimal solution."
        );

        System.out.println(
                "DFS does NOT guarantee an optimal solution."
        );

        System.out.println(
                "A* can guarantee an optimal solution when"
        );

        System.out.println(
                "an admissible heuristic is used."
        );

        System.out.println();

        if (bfsResult != null &&
                aStarResult != null &&
                bfsResult.moves ==
                        aStarResult.moves) {

            System.out.println(
                    "For this grid, BFS and A* both"
            );

            System.out.println(
                    "found the optimal solution of " +
                    bfsResult.moves +
                    " moves."
            );
        }

        System.out.println(
                "========================================"
        );
    }
}
