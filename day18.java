import java.util.ArrayList;

public class day18 {

    private static int safeTiles = 0;

    public static void main(String[] args) {
        ArrayList<String> input = day02.readInput("src/day18Input");
        // part 1
        createMaze(input, 39);
        System.out.println("There are: " + safeTiles + " safe tiles for part 1.");
        safeTiles = 0;
        // part 2
        createMaze(input, 399999);
        System.out.println("There are: " + safeTiles + " safe tiles for part 2.");
    }

    /**
     * Method creates full maze, initial row is given via input and number of maximal rows is also given.
     *
     * @param input ArrayList<String> is input
     * @param maxRows int number of maximal rows in maze
     */
    private static void createMaze(ArrayList<String> input, int maxRows) {
        countSafeTiles(input.get(0));
        for (int i = 0; i < maxRows; i++) {
            String newRow = "";
            char newTile;
            for (int j = 0; j < input.get(input.size()-1).length(); j++) {
                if (j == 0) {
                    // new tile is at the beginning of a row
                    newTile = addTile("."+input.get(input.size()-1).substring(j, j+2));
                    newRow += newTile;
                } else if (j == input.get(input.size()-1).length()-1) {
                    // new tile is at the end of a row
                    newTile = addTile(input.get(input.size()-1).substring(j-1, j+1)+".");
                    newRow += newTile;
                } else {
                    // new tile is somewhere in between the end and the beginning of a row
                    newTile = addTile(input.get(input.size() - 1).substring(j - 1, j + 2));
                    newRow += newTile;
                }
                if (newTile == '.') {
                    safeTiles++;
                }
            }
            input.add(newRow);
        }
    }

    /**
     * Method adds a tile based on tiles located in a previous row.
     *
     * @param previousTiles String of tiles in a previous row
     *
     * @return char representation of a new tile
     */
    private static char addTile(String previousTiles) {
        switch (previousTiles) {
            case "^^.":
            case ".^^":
            case "^..":
            case "..^":
                return '^';
            default:
                return '.';
        }
    }

    /**
     * Method counts safe tiles in a row.
     *
     * @param row String representing row
     */
    private static void countSafeTiles(String row) {
        for (int i = 0; i < row.length(); i++) {
            if (row.charAt(i) == '.') {
                safeTiles++;
            }
        }
    }
}
