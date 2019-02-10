import java.util.ArrayList;
import java.util.HashMap;

public class day15 {

    public static void main(String args[]) {
        HashMap<Integer, Integer[]> discs = parseInput(day02.readInput("src/day15Input"));
        // part 1
        System.out.println("Need to start at time="+getStartingTime(discs));
        // part 2
        Integer[] tmp = {11, 0, calculateFirstOverlap(discs.size(), 11, 0)};
        discs.put(discs.size()+1, tmp);
        System.out.println("For part 2, starting time will be: "+getStartingTime(discs));
    }

    /**
     * Method parses input and creates HashMap representing discs.
     *
     * @param input ArrayList of Strings representing input
     *
     * @return HashMap<Integer, Integer[]> representing discs
     */
    private static HashMap<Integer, Integer[]> parseInput(ArrayList<String> input) {
        HashMap<Integer, Integer[]> discs = new HashMap<>();
        int index = 1;

        for (String inputLine: input) {
            inputLine = inputLine.replaceFirst("Disc #\\d has ", "");
            inputLine = inputLine.replaceFirst("\\.", "");
            Integer[] tmp = new Integer[3];
            tmp[0] = Integer.valueOf(inputLine.split(" ",2)[0]);            // number of positions
            tmp[1] = Integer.valueOf(inputLine.split(" ")[inputLine.split(" ").length-1]); // starting position
            tmp[2] = calculateFirstOverlap(index, tmp[0], tmp[1]);                      // first overlapping time
            discs.put(index++, tmp);
        }

        return discs;
    }

    /**
     * Method calculates at which time the disc will first overlap.
     *
     * @param discNumber int number of disc
     * @param numberOfPositions int number of positions the disc has
     * @param startingPosition int number of starting position of the disc
     *
     * @return int number representing time when the disc will overlap
     */
    private static int calculateFirstOverlap(int discNumber, int numberOfPositions, int startingPosition) {
        int positionIfStartingAtTime0 = (discNumber+startingPosition) % numberOfPositions;
        if (positionIfStartingAtTime0 == 0) {
            return 0;
        } else {
            return numberOfPositions-positionIfStartingAtTime0;
        }
    }

    /**
     * Method finds the starting time.
     *
     * @param discs HashMap<Integer, Integer[]> containing all discs
     *
     * @return int number representing starting time
     */
    private static int getStartingTime(HashMap<Integer, Integer[]> discs) {
        Integer[] mostConstrainingDisc = getMostConstrainingDisc(discs);

        for (int i = mostConstrainingDisc[2]; ; i+=mostConstrainingDisc[0]) { // only need to check for every multiple of the most constraining disc
            for (HashMap.Entry<Integer, Integer[]> disc: discs.entrySet()) {
                if ( (i+disc.getKey()+disc.getValue()[1])%disc.getValue()[0] != 0) { // check if discs are overlapping
                    break;
                } else if (disc.getKey() == discs.size()) {
                    return i;
                }
            }
        }
    }

    /**
     * Method finds the most constraining disc.
     *
     * @param discs HashMap<Integer, Integer[]> containing all discs
     *
     * @return returns value part of HashMap for the most constraining disc
     */
    private static Integer[] getMostConstrainingDisc(HashMap<Integer, Integer[]> discs) {
        Integer[] positions = {0, 0, 0};

        for (HashMap.Entry<Integer, Integer[]> disc: discs.entrySet()) {
            if (disc.getValue()[0] > positions[0]) {
                positions = disc.getValue();
            }
        }

        return positions;
    }
}
