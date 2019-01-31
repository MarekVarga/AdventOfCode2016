import java.util.ArrayList;

public class day08 {
    private static boolean[][] display = new boolean[6][50];

    public static void main(String args[]) {
        execInstrutions(day02.readInput("src/day08Input"));
        System.out.println("There are " + countLit() + " pixels lit.\nAnd code for pard 2 is:");
        displayCode();
    }

    /**
     * Method goes over given instructions and decides which ones should be executed.
     *
     * @param instructions ArrayList of Strings of given instructions
     */
    private static void execInstrutions(ArrayList<String> instructions) {
        int[] tmp;
        for (String instruction: instructions) {
            if (instruction.substring(0, 4).equals("rect")) {
                tmp = getPositions(instruction, 5, 7);
                rectangle(tmp[0], tmp[1]);
            } else if (instruction.substring(0, 10).equals("rotate row")) {
                tmp = getPositions(instruction, 13, 18);
                rotateRow(tmp[0], tmp[1]);
            } else if (instruction.substring(0, 13).equals("rotate column")) {
                tmp = getPositions(instruction, 16,21);
                rotateCol(tmp[0], tmp[1]);
            } else {
                System.out.println("Error");
                break;
            }
        }
    }

    /**
     * Method extracts x,y coordinates from instruction according to offsets.
     *
     * @param instruction String representing instruction
     * @param offset1 offset of chars for x coordinate
     * @param offset2 offset of chars for y coordinate
     *
     * @return array of ints of x,y coordinates
     */
    private static int[] getPositions(String instruction, int offset1, int offset2) {
        int[] tmp = new int[2];

        StringBuilder x = new StringBuilder();
        x.append(instruction.charAt(offset1));
        if (instruction.charAt(offset1+1) != ' ' && instruction.charAt(offset1+1) != 'x') {
            x.append(instruction.charAt(offset1+1));
        }
        StringBuilder y = new StringBuilder();
        if (instruction.charAt(offset2) != ' ' && instruction.charAt(offset2) != 'x') {
            y.append(instruction.charAt(offset2));
        }
        if (instruction.length() == offset2+2) {
            y.append(instruction.charAt(offset2+1));
        }

        tmp[0] = Integer.valueOf(x.toString());
        tmp[1] = Integer.valueOf(y.toString());

        return tmp;
    }

    /**
     * Method that lights up pixels in a rectangle.
     *
     * @param x length of rectangle
     * @param y hight of rectangle
     */
    private static void rectangle(int x, int y) {
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if (!display[i][j]) {
                    display[i][j] = true;
                }
            }
        }
    }

    /**
     * Method that shifts pixels in a row by num pixels.
     *
     * @param row number of row
     * @param num number of pixels to be shifted by
     */
    private static void rotateRow(int row, int num) {
        boolean[] tmpDisplay = new boolean[50];
        // remember former display
        System.arraycopy(display[row],0,tmpDisplay,0,50);
        // set new pixels
        for (int i = 0; i < 50; i++) {
            display[row][i] = tmpDisplay[(i+50-num)%50];
        }
    }

    /**
     * Method shifts pixels in a column by num pixels.
     *
     * @param col number of column
     * @param num number of pixels to be shifted by
     */
    private static void rotateCol(int col, int num) {
        boolean[] tmpDisplay = new boolean[6];
        // remember former display
        for (int i = 0; i < 6; i++) {
            tmpDisplay[i] = display[i][col];
        }
        // set new pixels
        for (int i = 0; i < 6; i++) {
            display[i][col] = tmpDisplay[(i+6-num)%6];
        }
    }

    /**
     * Method counts lit pixels on the display.
     *
     * @return number of lit pixels
     */
    private static int countLit() {
        int result = 0;

        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 50; j++) {
                if (display[i][j]) {
                    result++;
                }
            }
        }

        return result;
    }

    /**
     * Method shows code needed for part 2.
     */
    private static void displayCode() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 50; j++) {
                if (display[i][j]) {
                    System.out.print("#");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }
}
