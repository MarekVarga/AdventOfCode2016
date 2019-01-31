import java.util.ArrayList;
import java.util.Iterator;

public class day11 {

    private static ArrayList<String> firstFloor = new ArrayList<>();
    private static ArrayList<String> secondFloor = new ArrayList<>();
    private static ArrayList<String> thirdFloor = new ArrayList<>();
    private static ArrayList<String> fourthFloor = new ArrayList<>();
    private static ArrayList<String> elevator = new ArrayList<>();
    private static ArrayList<String> currentFloor = new ArrayList<>();
    private static ArrayList<String> nextFloor = new ArrayList<>();
    private static ArrayList<String> previousFloor = new ArrayList<>();
    private static int floor = 1;
    private static boolean goingDown = false;

    public static void main(String args[]) {
        initFloors();
        System.out.println("Steps required: " + moveObjects());
    }

    private static void initFloors() {
        firstFloor.add("E");
        /*firstFloor.add("SG");
        firstFloor.add("SM");
        firstFloor.add("PG");
        firstFloor.add("PM");

        secondFloor.add("TG");
        secondFloor.add("RG");
        secondFloor.add("RM");
        secondFloor.add("CG");
        secondFloor.add("CM");

        thirdFloor.add("TM");*/

        firstFloor.add("HM");
        firstFloor.add("LM");
        secondFloor.add("HG");
        thirdFloor.add("LG");
    }

    private static int moveObjects() {
        int steps = 0;

        while (!firstFloor.isEmpty() || !secondFloor.isEmpty() || !thirdFloor.isEmpty()) {
            findElevator();
            loadElevator();
            moveElevator();
            //testSafety();
        }

        return steps;
    }

    private static void findElevator() {
        if (firstFloor.contains("E")) {
            currentFloor = firstFloor;
            nextFloor = secondFloor;
            previousFloor = firstFloor;
            floor = 1;
        } else if (secondFloor.contains("E")) {
            currentFloor = secondFloor;
            nextFloor = thirdFloor;
            previousFloor = firstFloor;
            floor = 2;
        } else if (thirdFloor.contains("E")) {
            currentFloor = thirdFloor;
            nextFloor = fourthFloor;
            previousFloor = secondFloor;
            floor = 3;
        } else {
            currentFloor = fourthFloor;
            nextFloor = thirdFloor;
            previousFloor = thirdFloor;
            floor = 4;
        }
    }

    private static void loadElevator() {
        for (String nextFloorItem: nextFloor) {
            for (String currentFloorItem: currentFloor) {
                if ((nextFloorItem.charAt(0) == currentFloorItem.charAt(0)) && elevator.size() < 2) {
                    elevator.add(currentFloorItem);
                    currentFloor.remove(currentFloorItem);
                }
            }
        }

        if (elevator.size() == 0) {
            if (!hasPairs(currentFloor).isEmpty()) {
                if (testSafety()) {
                    elevator.addAll(hasPairs(currentFloor));
                    currentFloor.removeAll(hasPairs(currentFloor));
                } else {
                    goingDown = true;

                }
            }
        }
    }

    private static void moveElevator() {
        if (!goingDown) {
            nextFloor.addAll(elevator);
            nextFloor.add("E");
            currentFloor.remove("E");

            Iterator<String> iterator = elevator.iterator();
            while (iterator.hasNext()) {
                String string = iterator.next();
                iterator.remove();
            }
        } else {

            goingDown = false;
        }
    }

    private static ArrayList<String> hasPairs(ArrayList<String> floor) {
        ArrayList<String> tmp = new ArrayList<>();

        for (String item1: floor) {
            for (String item2: floor) {
                if ((item1.charAt(0) == item2.charAt(0)) && !item1.equals(item2)) {
                    tmp.add(item1);
                    tmp.add(item2);
                    return tmp;
                }
            }
        }
        return tmp;
    }

    private static boolean testSafety() {
        boolean safe = false;

        for (String item: currentFloor) {
            if (item.charAt(item.length()-1) == 'M') {
                for (String item2: currentFloor) {
                    if ((item.charAt(0) == item2.charAt(0)) && !item.equals(item2)) {
                        safe = true;
                    }
                }
            }
        }

        if (!safe && currentFloor.size() == 1 && currentFloor.get(0).charAt(1) == 'M') {
            return true;
        }
        return safe;
    }
}
