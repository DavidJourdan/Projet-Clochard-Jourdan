package maze;

import java.io.IOException;


public class MainTest {

    /**
     * @param args
     * @throws MazeReadingException
     * @throws IOException
     */
    public static void main(String[] args) throws MazeReadingException, IOException {

        Maze maze = new Maze(10, 10);
        maze.initFromTextFile("data/labyrinthe.txt");
        maze.saveToTextFile("data/labyrinthe2.txt");
    }

}
