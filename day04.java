import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class day04 {

    public static void main(String args[]) {
        ArrayList<String> input = day02.readInput("src/day04Input");
        int IDsum = 0;

        for (String line: input) {
            String checksum = line.substring(line.length()-6, line.length()-1);
            line = line.substring(0, line.length()-7);
            String[] lineSplit = line.split("-");
            String sum = sort(getOccurrence(lineSplit));
            decipher(lineSplit);
            if (sum.substring(0, 5).equals(checksum)) {
                IDsum += Integer.valueOf(lineSplit[lineSplit.length-1]);
            }
        }

        System.out.println("Sum of IDs is: " + IDsum);
    }

    /**
     * Method counts all occurrences of letters in line split by dashes.
     *
     * @param splitLine array of Strings got by line split by dashes
     *
     * @return HashMap of letter as key and occurrence number as value
     */
    private static HashMap<Character, Integer> getOccurrence(String[] splitLine) {
        HashMap<Character, Integer> occurenceMap = new HashMap<>();

        for (int i = 0; i < splitLine.length-1; i++) {
            for (char letter: splitLine[i].toCharArray()) {
                if (!occurenceMap.containsKey(letter)) {
                    occurenceMap.put(letter, 1);
                } else {
                    occurenceMap.put(letter, occurenceMap.get(letter) +1);
                }
            }
        }

        return occurenceMap;
    }

    /**
     * Method sorts letter occurrences.
     *
     * @param occurrenceMap HashMap of letter as key and occurrence number as value
     *
     * @return String of sorted letters
     */
    public static String sort(HashMap<Character, Integer> occurrenceMap) {
        int[] values = new int[occurrenceMap.size()+1];
        values[0] = 0;
        char[] sum = new char[occurrenceMap.size()];

        for (Map.Entry<Character, Integer> occurrence: occurrenceMap.entrySet()) {
            char key = occurrence.getKey();
            int value = occurrence.getValue();
            for (int i = 0; i < values.length; i++) {
                if (value > values[i] || (value == values[i] && key < sum[i]) ) {
                    System.arraycopy(sum, i, sum, i+1, sum.length-1-i);
                    sum[i] = key;
                    System.arraycopy(values, i, values, i+1, values.length-1-i);
                    values[i] = value;
                    break;
                }
            }
        }

        StringBuilder returned = new StringBuilder();
        for (char sumPart: sum) {
                returned.append(sumPart);
        }
        return returned.toString();
    }

    /**
     * Method deciphers room name.
     *
     * @param line array of Strings got by line split by dashes
     */
    private static void decipher(String[] line) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int ID = Integer.valueOf(line[line.length-1]);
        StringBuilder stringBuilder = new StringBuilder();

        for (String string: line) {
            for (char character: string.toCharArray()) {
                stringBuilder.append(alphabet.charAt((ID + alphabet.indexOf(character-32)) % 26));
            }
            stringBuilder.append(" ");
        }

        if (stringBuilder.toString().contains("NORTH")){
            System.out.println(stringBuilder.toString() + "ID: " + ID);
        }
    }
}
