package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        int[] array = new int[M];
        for (Oomage o : oomages) {
            int num = (o.hashCode() & 0x7FFFFFFF) % M;
            array[num] = array[num] + 1;
        }
        double lower = (double) oomages.size() / 50;
        double upper = oomages.size() / 2.5;
        for (int i : array) {
            if (i < lower || i > upper) {
                return false;
            }
        }
        return true;
    }
}
