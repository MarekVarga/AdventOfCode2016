import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class day02 {

    // keypad for part1
    private static int[][] keypad = new int[][] {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
    };
    // keypad for part2
    private static char[][] keypad2 = new char[][] {
            {'X', 'X', '1', 'X', 'X'},
            {'X', '2', '3', '4', 'X'},
            {'5', '6', '7', '8', '9'},
            {'X', 'A', 'B', 'C', 'X'},
            {'X', 'X', 'D', 'X', 'X'}
    };

    public static void main(String args[]) {
        ArrayList<String> input = readInput("src/day02Input");
        int row = 1;
        int col = 1;
        StringBuilder code = new StringBuilder();
        int row2 = 2;
        int col2 = 0;
        StringBuilder code2 = new StringBuilder();

        // for each line in input
        for(String line: input) {
            // for each letter in line
            for(char direction: line.toCharArray()) {
                // move according to direction/letter
                switch (direction) {
                    case 'U':
                        if (row != 0) {
                            row--;
                        }
                        if (row2 != 0 && keypad2[row2-1][col2] != 'X') {
                            row2--;
                        }
                        break;
                    case 'L':
                        if (col != 0) {
                            col--;
                        }
                        if (col2 != 0 && keypad2[row2][col2-1] != 'X') {
                            col2--;
                        }
                        break;
                    case 'R':
                        if (col != 2) {
                            col++;
                        }
                        if (col2 != 4 && keypad2[row2][col2+1] != 'X') {
                            col2++;
                        }
                        break;
                    case 'D':
                        if (row != 2) {
                            row++;
                        }
                        if (row2 != 4 && keypad2[row2+1][col2] != 'X') {
                            row2++;
                        }
                        break;
                    default:
                        System.out.println("Error.");
                        break;
                }
            }
            code.append(keypad[row][col]);
            code2.append(keypad2[row2][col2]);
        }

        System.out.println("Final code for part 1 is: "+code);
        System.out.println("Final code for part 2 is: "+code2);
    }

    /**
     * Method reads input from a file.
     *
     * @return ArrayList of Strings for each line of input
     */
    public static ArrayList<String> readInput(String fileName) {
        ArrayList<String> input = new ArrayList<>();
        String inLine;

        try {
            BufferedReader bf = new BufferedReader(new FileReader(fileName));
            while ( (inLine = bf.readLine()) != null) {
                input.add(inLine);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found.");
        } catch (IOException ex) {
            System.out.println("IO exception.");
        }

        return input;
    }
}
