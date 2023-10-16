package xxl.core;

import java.util.Comparator;

public class FunctionComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        String[] info1 = o1.split("[;\\|=()]", 0);
        String[] info2 = o2.split("[;\\|=()]", 0);
        // Compares the functions names
        if (!info1[3].equals(info2[3])){
            return info1[3].compareTo(info2[3]);
        } else if (Integer.parseInt(info1[1]) != Integer.parseInt(info2[1])) {
            return Integer.parseInt(info1[1]) - Integer.parseInt(info2[1]);
        } else
            return Integer.parseInt(info1[2]) - Integer.parseInt(info2[2]);
    }
}
