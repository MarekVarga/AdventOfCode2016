import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class day03 {

    public static void main(String args[]) {
        ArrayList<String> input = load();
        int triangles = 0;
        int triangles2 = 0;

        for(int i = 0; i < input.size(); i ++) {
            // test triangles by rows
            int[] sides = toInt(input.get(i).split("\\s+"));
            triangles = testTriangles(sides, triangles);
            // test triangles by columns, only test each third line
            if (i % 3 == 0) {
                for (int j = 0; j < 3; j++) {
                    int[] sides2 = toInt2(input.get(i).split("\\s+"), input.get(i+1).split("\\s+"), input.get(i+2).split("\\s+"), j);
                    triangles2 = testTriangles(sides2, triangles2);
                }
            }
        }

        System.out.println("Triangles: " + triangles + " , and triangles for part 2: " + triangles2);
    }

    /**
     * Method loads input.
     *
     * @return ArrayList of Strings containing triangle sides
     */
    private static ArrayList<String> load() {
        ArrayList<String> input = new ArrayList<>();
        String tmp;
        try {
            BufferedReader bf = new BufferedReader(new FileReader("src/day03Input"));
            while( (tmp = bf.readLine()) != null) {
                input.add(tmp);
            }
        } catch(Exception e) {
            System.out.println("File not found.");
        }

        return input;
    }

    /**
     * Method converts String sides to int array.
     *
     * @param sides array of Strings of sides
     *
     * @return array of ints of sides
     */
    private static int[] toInt(String[] sides) {
        int[] result = new int[3];
        int i = 0;

        for (String side: sides) {
            if (side.compareTo("") != 0) {
                result[i] = Integer.valueOf(side);
                i++;
            }
        }

        return result;
    }

    /**
     * Method converts String sides to int array. Takes sides by columns.
     *
     * @param row1 array of Strings of sides of first row
     * @param row2 array of Strings of sides of second row
     * @param row3 array of Strings of sides of third row
     * @param colIndex int index of column
     *
     * @return array of ints of sides
     */
    private static int[] toInt2(String[] row1, String[] row2, String[] row3, int colIndex) {
        int[] result = new int[3];

        result[0] = Integer.valueOf(row1[colIndex+1]);
        result[1] = Integer.valueOf(row2[colIndex+1]);
        result[2] = Integer.valueOf(row3[colIndex+1]);

        return result;
    }

    /**
     * Method tests whether triangle is valid.
     *
     * @param sides int array containing sides of a triangle
     * @param count int number of valid triangles so far
     *
     * @return int number of valid triangles after this test
     */
    private static int testTriangles(int[] sides, int count) {
        int min1;
        int min2;
        int max;

        // find two smaller sides and the largest one
        if (sides[0] > sides[1]) {
            if (sides[0] > sides[2]) {
                min1 = sides[1];
                min2 = sides[2];
                max = sides[0];
            } else {
                min1 = sides[1];
                min2 = sides[0];
                max = sides[2];
            }
        } else {
            if (sides[1] > sides[2]) {
                min1 = sides[0];
                min2 = sides[2];
                max = sides[1];
            } else {
                min1 = sides[1];
                min2 = sides[0];
                max = sides[2];
            }
        }

        // test sides
        if ((min1 + min2) > max) {
            return count+1;
        } else {
            return count;
        }
    }
}
