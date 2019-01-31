import java.util.ArrayList;
import java.util.Scanner;

public class day10 {

    // Bot class representation
    private static class Bot {
        private int botNumber;
        private int microchip1 = -255;
        private int microchip2 = -255;
        private boolean hasTwo = false;

        // constructor
        private Bot(int newBotNumber){
            this.botNumber = newBotNumber;
        }

        // method adds microchip
        private void addMicrochip(int microchip) {
            if (this.microchip1 == -255) {
                this.microchip1 = microchip;
            } else {
                if (this.microchip1 < microchip) {
                    this.microchip2 = microchip;
                } else {
                    this.microchip2 = this.microchip1;
                    this.microchip1 = microchip;
                }
                this.hasTwo = true;
            }
        }
    }

    // ArrayList of all bots
    private static ArrayList<Bot> bots = new ArrayList<>();

    public static void main(String args[]){
        ArrayList<String> input = day02.readInput("src/test");
        initBots(input);
        executeInstructions(input);
        System.out.println("There are: "+bots.size()+" bots");
    }

    /**
     * Mehtod initializes all bots that will be used.
     *
     * @param input ArrayList of Strings read from input
     */
    private static void initBots(ArrayList<String> input){
        for(String instruction: input) {
            if (instruction.charAt(0) == 'b') {
                StringBuilder newBotName = new StringBuilder();
                for (int i = 4; instruction.charAt(i) != ' '; i++) {
                    newBotName.append(instruction.charAt(i));
                }
                if (findBot(Integer.valueOf(newBotName.toString())) == null) {
                    bots.add(new Bot(Integer.valueOf(newBotName.toString())));
                }
                input.set(input.indexOf(instruction), instruction.replaceAll("^bot|gives low to|and high to",""));
            } else {
                StringBuilder newBotName = new StringBuilder();
                for (int i = instruction.length() - 1; instruction.charAt(i) != ' '; i--) {
                    newBotName.append(instruction.charAt(i));
                }
                StringBuilder newMicrochip = new StringBuilder();
                for (int i = instruction.indexOf(' ') + 1; instruction.charAt(i) != ' '; i++) {
                    newMicrochip.append(instruction.charAt(i));
                }
                Bot tmpBot = findBot(Integer.valueOf(newBotName.toString())) ;
                //tmpBot.addMicrochip(Integer.valueOf(newMicrochip.toString()));
                if (tmpBot == null) {
                    bots.add(new Bot(Integer.valueOf(newBotName.toString())));
                }
                findBot(Integer.valueOf(newBotName.toString())).addMicrochip(Integer.valueOf(newMicrochip.toString()));
            }
        }
    }

    /**
     * Method finds bot according to bot number.
     *
     * @param botNumber searched Bot
     *
     * @return found Bot or null if unsuccessful
     */
    private static Bot findBot(int botNumber) {
        for (Bot bot: bots) {
            if (bot.botNumber == botNumber) {
                return bot;
            }
        }

        return null;
    }

    private static void executeInstructions(ArrayList<String> instructions) {
        for (String instruction: instructions) {
            if (instruction.charAt(0) == 'v') {
                continue;
            }
            Scanner scanner = new Scanner(instruction);
            int botNumber = scanner.nextInt();
            String receiver1 = scanner.next();
            int lowNumber = scanner.nextInt();
            String receiver2 = scanner.next();
            int highNumber = scanner.nextInt();
            Bot giver = findBot(botNumber);
            if (giver != null && giver.microchip1 == 2 && giver.microchip2 == 5) {
                System.out.println("Bot number is: "+botNumber);
                break;
            } else if (giver != null){
                if (receiver1.equals("bot") && giver.hasTwo) {
                    giveMicrochip(lowNumber, giver, "low");
                }
                if (receiver2.equals("bot") && giver.hasTwo) {
                    giveMicrochip(highNumber, giver, "high");
                }
            }
        }
    }

    private static void giveMicrochip(int receiver, Bot giver, String chip) {
        Bot tmpReceiver = findBot(receiver);
        if (tmpReceiver != null) {
            if (chip.equals("low")) {
                tmpReceiver.addMicrochip(giver.microchip1);
                giver.microchip1 = -255;
            } else {
                tmpReceiver.addMicrochip(giver.microchip2);
                giver.microchip2 = -255;
            }
        }
    }
}
