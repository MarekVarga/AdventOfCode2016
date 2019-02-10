import java.security.MessageDigest;

public class day05 {

    private static StringBuilder password = new StringBuilder();
    private static char[] password2 = {'z', 'z', 'z', 'z', 'z', 'z', 'z', 'z'};

    public static void main(String args[]) {
        int filled = 0;
        try {
            for (long i = 0; filled != 8; i++) {
                // build input
                byte[] bytes = "ffykfhsq".concat(String.valueOf(i)).getBytes("UTF-8");
                // get MD5 hash
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] digest = md.digest(bytes);
                // put MD5 hash into hexa
                StringBuilder sb = new StringBuilder();
                for (byte b : digest) {
                    sb.append(String.format("%02X ", b));
                }
                if (sb.toString().substring(0, 7).equals("00 00 0")) {
                    // part 1
                    password.append(sb.toString().charAt(7));
                    // part 2
                    if (sb.toString().charAt(7)-48 <= 7 && password2[sb.toString().charAt(7)-48] == 'z') {
                        password2[sb.toString().charAt(7)-48] = sb.toString().charAt(9);
                        filled++;
                    }
                }
            }
            System.out.println("Password for part 1 is: " + password.toString().substring(0, 8));
            System.out.println("Password for part 2 is: " + password2[0] + password2[1] + password2[2] + password2[3] + password2[4] + password2[5] + password2[6] + password2[7]);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}
