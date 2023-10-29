package xxl.core;

import java.util.Comparator;

/**
 * Compares strings representing functions within the spreadsheet.
 */
public class FunctionComparator implements Comparator<String> {

    /**
     * Compares two strings representing functions.
     *
     * @param o1 The first function string to compare.
     * @param o2 The second function string to compare.
     * @return An integer indicating the comparison result.
     */
    @Override
    public int compare(String o1, String o2) {
        String[] info1 = o1.split("[;\\|=()]", 0);
        String[] info2 = o2.split("[;\\|=()]", 0);

        // Compares the function names
        if (!info1[3].equals(info2[3])) {
            return info1[3].compareTo(info2[3]);
        } else if (Integer.parseInt(info1[0]) != Integer.parseInt(info2[0])) {
            return Integer.parseInt(info1[0]) - Integer.parseInt(info2[0]);
        } else {
            return Integer.parseInt(info1[1]) - Integer.parseInt(info2[1]);
        }
    }
}
