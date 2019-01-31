import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class day09 {
    private static String input28 = loadInput("src/day09Input");
    //private static StringBuilder tmp = new StringBuilder();
    private static long tmp = 0;

    public static void main(String args[]) {
        System.out.println("Length of code is: " + parseCode(loadInput("src/day09Input")));
        decompress2(0, input28);
        System.out.println("Length for part is: " + tmp);
    }

    /**
     * Method loads input from given file.
     *
     * @param fileName name of the file to be read from
     *
     * @return read String from file
     */
    private static String loadInput(String fileName) {
        String input = "";

        try{
            BufferedReader bf = new BufferedReader(new FileReader(fileName));
            input = bf.readLine();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("Cannot read from file.");
        }

        return input;
    }

    private static int parseCode(String input) {
        int length = 0;
        int[] tmp;

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(') {
                StringBuilder stringBuilder = new StringBuilder();
                for (int j = i; input.charAt(j) != ')'; j++) {
                    stringBuilder.append(input.charAt(j));
                }
                tmp = countOffset(stringBuilder.toString());
                i += tmp[2] + tmp[0];
                length += tmp[0]*tmp[1];
            } else {
                length++;
            }
        }

        return length;
    }

    private static int[] countOffset(String offset) {
        int[] result = new int[3];
        StringBuilder stringBuilder = new StringBuilder();

        for (char character: offset.toCharArray()) {
            if (character == '(' || character == ')') {
                continue;
            }
            if (result[0] == 0 && character != 'x') {
                stringBuilder.append(character);
            } else if (character == 'x') {
                result[0] = Integer.valueOf(stringBuilder.toString()); // number of chars to be repeated
                stringBuilder = new StringBuilder();
            } else {
                stringBuilder.append(character);
            }
        }
        result[1] = Integer.valueOf(stringBuilder.toString()); // number of repetitions
        result[2] = offset.length(); // length of bracket

        return result;
    }

    /*private static void decompress2() {
        int[] offsets;

        for (int i = 0; i <input2.length(); i++) {
            if (input2.charAt(i) == '(') {
                StringBuilder stringBuilder = new StringBuilder();
                for (int j = i; input2.charAt(j) != ')'; j++) {
                    stringBuilder.append(input2.charAt(j));
                }
                offsets = countOffset(stringBuilder.toString());
                stringBuilder = new StringBuilder();
                for (int j = 0; j < offsets[1]-1; j++) {
                    stringBuilder.append(input2.substring(i+offsets[2]+1,i+offsets[2]+offsets[0]+1));
                }
                input2 = input2.replaceFirst("\\(\\d+x\\d+\\)", stringBuilder.toString());
                i -= 1;
            }
        }
    }*/

    private static int decompress2(int startPos, String input2) {
        int[] offsets = new int[3];

        for (int i = 0; i < input2.length(); i++) {
//            if (!input2.matches("[^(]*\\(?[^(]*")) {
//                decompress2(input2.indexOf(')')+1, input2.substring(input2.indexOf(')')+1, input2.length()));
//            }
            if (input2.charAt(i) == '(') {
                StringBuilder stringBuilder = new StringBuilder();
                for (int j = i; input2.charAt(j) != ')'; j++) {
                    stringBuilder.append(input2.charAt(j));
                }
                offsets = countOffset(stringBuilder.toString());
                //stringBuilder = new StringBuilder();
                /*for (int j = 0; j < offsets[1]-1; j++) {
                    stringBuilder.append(input2.substring(i+offsets[2]+1,i+offsets[2]+offsets[0]+1));
                }*/
                //tmp.append(input2.replaceFirst("\\(\\d+x\\d+\\)", stringBuilder.toString()));
                //i -= 1;
                for (int j = 0; j < offsets[1]; j++) {
                    decompress2(i, input2.substring(i+offsets[2]+1,i+offsets[2]+offsets[0]+1));
                    //decompress2(i, input2.substring(input2.indexOf(')')+1, input2.indexOf(')')+1+offsets[0]));
                }
                i += offsets[2] + offsets[0];
                //i = offsets[0] + input2.indexOf(')') ;
                //break;
            } else {
                //tmp.append(input2.charAt(i));
                tmp++;
            }
        }

        return offsets[0];
    }
}
