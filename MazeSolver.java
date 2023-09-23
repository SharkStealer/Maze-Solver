import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MazeSolver {

    private static final char WALL = 'X';
    private static final char SPACE = 'O';
    private static final char BC_TRAIL = '.';

    private static final int MZ_SIZE = 20; // Adjust to adapt to maze size (Square)

    private char[][] maze;
    private int steps;
    private int mouseRow;
    private int mouseCol;
    private int[] lastMoves;

    public MazeSolver() {
        maze = new char[MZ_SIZE][MZ_SIZE];
        lastMoves = new int[3];
    }

    public void loadMaze(String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner scanner = new Scanner(file);

        for (int row = 0; row < MZ_SIZE; row++) {
            String line = scanner.nextLine();
            for (int col = 0; col < MZ_SIZE; col++) {
                maze[row][col] = line.charAt(col);
                if (maze[row][col] == SPACE) {
                    mouseRow = row;
                    mouseCol = col;
                }
            }
        }

        scanner.close();
    }

    public void solveMaze() {
        steps = 0;
        printMaze();
        moveMouse(mouseRow, mouseCol);
        System.out.println("Steps taken: " + steps);
    }

    private void moveMouse(int row, int col) {
        if (maze[row][col] == WALL || maze[row][col] == BC_TRAIL) {
            return;
        }

        lastMoves[steps % 3] = row * MZ_SIZE + col;

        if (maze[row][col] != SPACE) {
            maze[row][col] = BC_TRAIL;
            steps++;
            printMaze();
            return;
        }

        maze[row][col] = BC_TRAIL;

        int[][] moves = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        for (int[] move : moves) {
            int newRow = row + move[0];
            int newCol = col + move[1];
            if (isValidMove(newRow, newCol)) {
                steps++;
                moveMouse(newRow, newCol);
                if (maze[newRow][newCol] == BC_TRAIL) {
                    break;
                }
            }
        }

        maze[row][col] = SPACE;
        steps--;
    }

    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < MZ_SIZE && col >= 0 && col < MZ_SIZE;
    }

    private void printMaze() {
        for (char[] row : maze) {
            for (char cell : row) {
                System.out.print(cell);
            }
            System.out.println();
        }
        System.out.println();
    }

    
}
