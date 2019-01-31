import java.util.ArrayList;

public class day12 {

    private static int registers[] = {0, 0, 0, 0};    // for part 1
    // private static int registers[] = {0, 0, 1, 0}; // for part 2

    public static void main(String args[]) {
        executeInstructions(day02.readInput("src/day12Input"));
        System.out.println("Value in register a is: " + registers[0]);
    }

    /**
     * Function executes instructions given in input.
     *
     * @param input ArrayList of Strings containing input instructions
     */
    private static void executeInstructions(ArrayList<String> input) {
        for (int i = 0; i < input.size(); i++) {
            // parse instruction to words
            String[] instruction = input.get(i).split(" ");

            switch (instruction[0]) {
                case "cpy" :
                    if (instruction[1].equals("a") || instruction[1].equals("b") || instruction[1].equals("c") || instruction[1].equals("d")) {
                        // cpy reg to reg
                        registers[instruction[2].toCharArray()[0]-97] = registers[instruction[1].toCharArray()[0]-97];
                    } else {
                        // cpy int to reg
                        registers[instruction[2].toCharArray()[0]-97] = Integer.valueOf(instruction[1]);
                    }
                    break;
                case "inc" :
                    registers[instruction[1].toCharArray()[0]-97]++;
                    break;
                case "dec" :
                    registers[instruction[1].toCharArray()[0]-97]--;
                    break;
                case "jnz" :
                    if (instruction[1].equals("a") || instruction[1].equals("b") || instruction[1].equals("c") || instruction[1].equals("d")) {
                        // comparing reg to zero
                        if (registers[instruction[1].toCharArray()[0]-97] != 0) {
                            i += Integer.valueOf(instruction[2]);
                            i--;
                        }
                    } else {
                        // comparing int to zero
                        if (Integer.valueOf(instruction[1]) != 0) {
                            i += Integer.valueOf(instruction[2]);
                            i--;
                        }
                    }
                    break;
                default:
                    System.out.println("Wrong instruction: " + instruction[0]);
            }
        }
    }
}
