import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
   public static void main(String[] args) {
        MazeSolver mazeSolver = new MazeSolver();
        try {
            mazeSolver.loadMaze("maze.txt");
            mazeSolver.solveMaze();
        } catch (FileNotFoundException e) {
            System.out.println("Maze file not found.");
        }
    }
}
