import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;

public class day14 {

    private static int keys = 0;
    private static HashMap<Long, String> hashes = new HashMap<>(); // hash map where already calculated hashes are stored

    public static void main(String args[]) {
        // part 1
        for (long i = 0; keys <= 64; i++) {
            char tmp;
            String hash;
            if (!hashes.containsKey(i)) {
                hash = getHash(i, null, 1, null);
            } else {
                hash = hashes.get(i);
            }
            if ((tmp = tripleFound(hash)) != ' ') {
                if (fiveInARow(i, tmp, 1)) {
                    keys++;
                    if (keys == 64) {
                        System.out.println("Index of 64th key for part 1 is: " + i);
                    }
                }
            }
        }

        hashes.clear();

        // part 2
        keys = 0;
        for (long i=0; keys <= 64; i++) {
            char tmp;
            String stretchedHash;
            if (!hashes.containsKey(i)) {
                stretchedHash = getHash(i, null, 1, null);
                for (int j = 0; j < 2016; j++) {
                    stretchedHash = getHash(j, stretchedHash.toLowerCase(), 2, "");
                }
            } else {
                stretchedHash = hashes.get(i);
            }
            if ((tmp = tripleFound(stretchedHash)) != ' ') {
                if (fiveInARow(i, tmp, 2)) {
                    keys++;
                    if (keys == 64) {
                        System.out.println("Index of 64th key for part 2 is: " + i);
                    }
                }
            }
        }
    }

    /**
     * Method finds MD5 hash of determined salt and current index.
     *
     * @param index long index of current hash
     * @param hash String hash of current hash (required only for part 2)
     * @param part int number of part currently solved
     * @param pathTaken String representing path taken (required for day 17)
     *
     * @return String representing MD5 hash
     */
    public static String getHash(long index, String hash, int part, String pathTaken) {
        final String salt = "ngcjuoqr";

        try {
            byte[] bytes;
            if (part == 1) {
                bytes = salt.concat(String.valueOf(index)).getBytes("UTF-8");
            } else {
                bytes = hash.concat(pathTaken).getBytes("UTF-8");
            }
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(bytes);
            // put MD5 hash into hexa
            StringBuilder sb = new StringBuilder();
            for (byte b: digest) {
                sb.append(String.format("%02X", b));
            }

            return sb.toString();

        } catch (Exception e) {
            System.out.println("Exception: " + e);

            return null;
        }
    }

    /**
     * Method searches for triplet in the current hash.
     *
     * @param hash String representing hash in which the triplet is sought
     *
     * @return if triplet was found char of which the tripled consists is returned otherwise ' ' is returned
     */
    private static char tripleFound(String hash) {
        if (hash != null) {
            for (int i = 0; i < hash.length()-2; i++) {
                if (hash.charAt(i) == hash.charAt(i+1) && hash.charAt(i) == hash.charAt(i+2)) {
                    return hash.charAt(i);
                }
            }
        }

        return ' ';
    }

    /**
     * Method searches for five chars in a row in the next 1000 hashes starting after current hash.
     *
     * @param currentIndex long index of current hash
     * @param character char sought for in hashes
     * @param part int number of part currently solved
     *
     * @return true if char sequence was found in the next 1000 hashes
     */
    private static boolean fiveInARow(long currentIndex, char character, int part) {
        StringBuilder tmp = new StringBuilder();
        for (int j = 0; j < 5; j++) {
            tmp.append(character);
        }

        for (long i = currentIndex+1; i < currentIndex+1001; i++) {
            String hash;
            if (!hashes.containsKey(i)) {
                hash = getHash(i, null, 1, null);     // find MD5 hash
                if (part != 1) {
                    for (int j = 0; j < 2016; j++) {            // find 2016 MD5 heshes of current hash - for part 2
                        hash = getHash(j, hash.toLowerCase(), 2, "");
                    }
                }
                hashes.put(i, hash);             // store hash in hashMap
            } else {
                hash = hashes.get(i);           // get hash from hashMap
            }
            if (hash.contains(tmp)) {
                return true;
            }
        }

        return false;
    }
}
