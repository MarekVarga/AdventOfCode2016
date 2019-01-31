import java.util.ArrayList;
import java.util.HashMap;

public class day06 {

    public static void main(String args[]) {
        System.out.println("Code is: " + getCode(count(day02.readInput("src/day06Input"))));
    }

    /**
     * Method counts letters for each position for each line
     *
     * @param input ArrayList of Strings from input
     *
     * @return ArrayList of HashMaps containing count of all letters per position
     */
    private static ArrayList<HashMap<Character, Integer>> count (ArrayList<String> input) {
        ArrayList<HashMap<Character, Integer>> count = new ArrayList<>();
        for (int i = 0; i < input.get(0).length(); i++) {
            count.add(new HashMap<>());
        }

        for (String inputLine: input) {
            for (int j = 0; j < inputLine.length(); j++) {
                if (!count.get(j).containsKey(inputLine.charAt(j))) {
                    count.get(j).put(inputLine.charAt(j), 1);
                } else {
                    count.get(j).put(inputLine.charAt(j), count.get(j).get(inputLine.charAt(j)) +1);
                }
            }
        }

        return count;
    }

    /**
     * Method decodes code name.
     *
     * @param count ArrayList of HashMaps containing count of all letters per position
     *
     * @return String of code
     */
    private static String getCode(ArrayList<HashMap<Character, Integer>> count) {
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder stringBuilder1 = new StringBuilder();

        for (HashMap<Character, Integer> occurrences: count) {
            stringBuilder.append(day04.sort(occurrences).charAt(0));
            String tmp = day04.sort(occurrences);
            stringBuilder1.append(tmp.charAt(tmp.length()-1));
        }

        System.out.println("Code for part 2 is: " + stringBuilder1);
        return stringBuilder.toString();
    }

}
