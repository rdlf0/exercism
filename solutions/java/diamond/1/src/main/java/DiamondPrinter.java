import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class DiamondPrinter {

    List<String> printToList(char a) {
        int size = (a - 64) * 2 - 1;
        List<String> result = new ArrayList<>();

        for (int i = 0; i < size / 2 + 1; i++) {
            char[] row = new char[size];
            Arrays.fill(row, ' ');
            StringBuilder sb = new StringBuilder(new String(row));
            if (i == 0) {
                sb.setCharAt(size / 2, 'A');
            } else {
                char ch = (char) ('A' + i);
                sb.setCharAt(size / 2 - i, ch);
                sb.setCharAt(size / 2 + i, ch);
            }

            result.add(sb.toString());
        }

        for (int i = result.size() - 2; i >= 0; i--) {
            result.add(result.get(i));
        }

        return result;
    }

}
