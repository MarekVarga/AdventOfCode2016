public class day16 {

    private static String input = "11011110011011101";
    private static final int diskLen1 = 272;
    private static final int diskLen2 = 35651584;
    private static String checksum = "";

    public static void main(String args[]) {
        getChecksum(diskLen1);                  // part 1
        input = "11011110011011101";
        checksum = "";
        getChecksum(diskLen2);                  // part 2
    }

    /**
     * Method modifies input using dragon curve
     */
    private static void adjustInput() {
        String tmpInput = input;
        input = reverse(input);
        input = input.replaceAll("0", "2");
        input = input.replaceAll("1", "0");
        input = input.replaceAll("2", "1");
        input = tmpInput.concat("0").concat(input);
    }

    /**
     * Method reverses given string.
     *
     * @param string String that will be reversed
     *
     * @return reversed String
     */
    private static String reverse(String string) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = string.length()-1; i >= 0; i--) {
            stringBuilder.append(string.charAt(i));
        }

        return stringBuilder.toString();
    }

    /**
     * Method calculates checksum.
     */
    private static void calculateChecksum() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < checksum.length(); i+=2) {
            if (checksum.charAt(i) == checksum.charAt(i+1)) {
                stringBuilder.append("1");
            } else {
                stringBuilder.append("0");
            }
        }

        checksum = stringBuilder.toString();
    }

    /**
     * Method finds checksum for given disk length.
     *
     * @param diskLen int number of the length of the disk
     */
    private static void getChecksum(int diskLen) {
        while (input.length() < diskLen) {
            adjustInput();
        }
        checksum = input.substring(0,diskLen);
        while (checksum.length() % 2 != 1) {
            calculateChecksum();
        }
        System.out.println("Checksum is: " + checksum);
    }
}
