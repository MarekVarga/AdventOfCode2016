import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class day01 {

    // possible facing directions
    private enum FaceDirections {North, South, East, West}
    private static FaceDirections faceing = FaceDirections.North;
    // array list of all visited coordinates
    private static ArrayList<String> visited = new ArrayList<>();
    private static boolean found = false;

    /**
     * Main method
     * @param args String
     */
    public static void main (String args[]) {
        ArrayList<HashMap<Character, Integer>> directions = load();
        int yOffset = 0;
        int xOffset = 0;
        visited.add("0:0");

        for (HashMap<Character, Integer> hashMap: directions) {

            for (Map.Entry<Character, Integer> map : hashMap.entrySet()) {
                char character = map.getKey();
                int value = map.getValue();

                switch (faceing) {
                    case North:
                        if (character == 'L') {
                            faceing = FaceDirections.West;
                            xMovement(xOffset, yOffset, -value);
                            xOffset -= value;
                        }
                        else {
                            faceing = FaceDirections.East;
                            xMovement(xOffset, yOffset, value);
                            xOffset += value;
                        }
                        break;
                    case East:
                        if (character == 'L') {
                            faceing = FaceDirections.North;
                            yMovement(xOffset, yOffset, value);
                            yOffset += value;
                        }
                        else {
                            faceing = FaceDirections.South;
                            yMovement(xOffset, yOffset, -value);
                            yOffset -= value;
                        }
                        break;
                    case West:
                        if (character == 'L') {
                            faceing = FaceDirections.South;
                            yMovement(xOffset, yOffset, -value);
                            yOffset -= value;
                        }
                        else {
                            faceing = FaceDirections.North;
                            yMovement(xOffset, yOffset, value);
                            yOffset += value;
                        }
                        break;
                    default:
                        if (character == 'L') {
                            faceing = FaceDirections.East;
                            xMovement(xOffset, yOffset, value);
                            xOffset += value;
                        }
                        else {
                            faceing = FaceDirections.West;
                            xMovement(xOffset, yOffset, -value);
                            xOffset -= value;
                        }
                }
            }
        }

        int manhattanDist = Math.abs(yOffset+xOffset);
        System.out.println("Distance for part 1: "+manhattanDist);

    }

    /**
     * Method loads and parses input.
     *
     * @return String of loaded input
     */
    private static ArrayList<HashMap<Character, Integer>> load () {
        int inChar;
        char tmpChar = ' ';
        int tmpVal = 0;
        ArrayList<HashMap<Character, Integer>> directions = new ArrayList<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/day01Input"));
            while ( (inChar = bufferedReader.read()) != -1) {
              if ( (char) inChar == ' ') {
                  continue;
              } else if ( (char) inChar == 'L' || (char) inChar == 'R'){
                  tmpChar = (char) inChar;
              } else if ( (char) inChar == ',' || inChar == 10) {
                  HashMap<Character, Integer> tmpHashMap = new HashMap<>();
                  tmpHashMap.put(tmpChar, tmpVal);
                  directions.add(tmpHashMap);
                  tmpVal = 0;
              } else {
                  tmpVal *= 10;
                  tmpVal += inChar - 48;
              }
            }
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
        } catch (IOException ex) {
            System.out.println("Cannot read from file");
        }

        return directions;
    }

    /**
     * Method for writing coordinates for movement on x axis.
     *
     * @param xCoor current x coordinate
     * @param yCoor current y coordinate
     * @param newX final x coordinate
     */
    private static void xMovement (int xCoor, int yCoor, int newX) {
        if (!found) {
            for (int i = 1; i <= Math.abs(newX); i++) {
                if (newX > 0) {
                    findMatch((xCoor + i) + ":" + yCoor);
                    visited.add((xCoor + i) + ":" + yCoor);
                } else {
                    findMatch((xCoor - i) + ":" + yCoor);
                    visited.add((xCoor - i) + ":" + yCoor);
                }
            }
        }
    }

    /**
     * Method for writing coordinates for movement on y axis.
     *
     * @param xCoor current x coordinate
     * @param yCoor current y coordinate
     * @param newY final y coordinate
     */
    private static void yMovement (int xCoor, int yCoor, int newY) {
        if (!found) {
            for (int i = 1; i <= Math.abs(newY); i++) {
                if (newY > 0) {
                    findMatch(xCoor + ":" + (yCoor + i));
                    visited.add(xCoor + ":" + (yCoor + i));
                } else {
                    findMatch(xCoor + ":" + (yCoor - i));
                    visited.add(xCoor + ":" + (yCoor - i));
                }
            }
        }
    }

    /**
     * Method for searching visited places.
     *
     * @param coordinates coordinates of current position
     */
    private static void findMatch (String coordinates) {
        for (String position: visited) {
            if (position.equals(coordinates)){
                String split[] = position.split(":");
                System.out.println("Distance for part 2: "+Math.abs(Integer.valueOf(split[0])+Integer.valueOf(split[1])));
                found = true;
            }
        }
    }
}
