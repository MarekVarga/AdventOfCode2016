import java.util.ArrayList;

public class day07 {

    public static void main(String args[]) {
        int[] results = countIPs(day02.readInput("src/day07Input"));
        System.out.println("There are "+ results[0] + " valid IPs for part 1 \n" +
                            "and " + results[1] + " valid IPs for part2.");
    }

    /**
     * Method counts valid IPs.
     *
     * @param ips ArrayList of Strings containing IPs
     *
     * @return number of valid IPs
     */
    private static int[] countIPs(ArrayList<String> ips) {
        int[] count = new int[]{0, 0};

        for (String ip: ips) {
            // test for valid IP for part1
            if (testIP(ip)) {
                count[0]++;
            }
            // test for valid IP for part2
            if (testIP2(ip)) {
                count[1]++;
            }
        }

        return count;
    }

    /**
     * Method goes over given IP and decides whether it can be considered valid for part 1.
     *
     * @param ip String representation of IP
     *
     * @return boolean value of valid IP
     */
    private static boolean testIP(String ip) {
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder brackets = new StringBuilder();
        boolean result = false;
        boolean bracket = false;

        for (char letter: ip.toCharArray()) {
            if (letter != '[' && !bracket) {
                brackets = new StringBuilder();
                stringBuilder.append(letter);
                if (stringBuilder.length() == 4) {
                    StringBuilder tmp = new StringBuilder();
                    tmp.append(stringBuilder.charAt(3));
                    tmp.append(stringBuilder.charAt(2));
                    if (stringBuilder.substring(0, 2).equals(tmp.toString()) && stringBuilder.charAt(0) != stringBuilder.charAt(1)) {
                        result = true;
                    }
                    stringBuilder.deleteCharAt(0);
                }
            } else {
                stringBuilder = new StringBuilder();
                bracket = true;
                if (letter != '[' && letter != ']') {
                    brackets.append(letter);
                    if (brackets.length() == 4) {
                        StringBuilder tmp = new StringBuilder();
                        tmp.append(brackets.charAt(3));
                        tmp.append(brackets.charAt(2));
                        if (brackets.substring(0, 2).equals(tmp.toString()) && brackets.charAt(0) != brackets.charAt(1)) {
                            return false;
                        }
                        brackets.deleteCharAt(0);
                    }
                }
                if (letter == ']') {
                    bracket = false;
                }
            }
        }

        return result;
    }

    /**
     * Method goes over given IP and decides whether it can be considered valid for part 2.
     *
     * @param ip String representation of IP
     *
     * @return boolean value of valid IP
     */
    private static boolean testIP2(String ip) {
        boolean bracket = false;
        StringBuilder brackets = new StringBuilder();
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<String> supernet = new ArrayList<>(); // outside square brackets
        ArrayList<String> hypernet = new ArrayList<>(); // inside square brackets

        for (char letter: ip.toCharArray()) {
            if (letter != '[' && !bracket) {
                brackets = new StringBuilder();
                stringBuilder.append(letter);
                if (stringBuilder.length() == 3) {
                    supernet.add(stringBuilder.toString());
                    stringBuilder.deleteCharAt(0);
                }
            } else {
                bracket = true;
                stringBuilder = new StringBuilder();
                if (letter != '[' && letter != ']') {
                    brackets.append(letter);
                    if (brackets.length() == 3) {
                        hypernet.add(brackets.toString());
                        brackets.deleteCharAt(0);
                    }
                }
                if (letter == ']') {
                    bracket = false;
                }
            }
        }

        return checkSSL(supernet, hypernet);
    }

    /**
     * Method checks wheter SSL is supported
     *
     * @param supernet ArrayList of Strings of IPs outside brackets
     * @param hypernet ArrayList of Strings of IPs inside brackets
     *
     * @return SSL support verdict
     */
    private static boolean checkSSL(ArrayList<String> supernet, ArrayList<String> hypernet) {
        for (String supernetIP: supernet) {
            for (String hypernetIP: hypernet) {
                // e.g. aba = bab
                if (supernetIP.charAt(0) == supernetIP.charAt(2) && supernetIP.charAt(0) == hypernetIP.charAt(1) && supernetIP.charAt(1) == hypernetIP.charAt(0) && hypernetIP.charAt(0) == hypernetIP.charAt(2) ) {
                    return true;
                }
            }
        }

        return false;
    }

}
