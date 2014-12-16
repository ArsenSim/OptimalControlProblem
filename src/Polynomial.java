import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class for working with polynoms
 */
public class Polynomial {

    /**
     * Contains list of Integer[]{coeffitient, power, free_element}
     * example 237t^34-11 would have Integer[]{237, 34, -11}
     */
    protected List<Integer[]> members;

    /**
     * Regex for parsing polynomial string
     */
    private static final Pattern polynom = Pattern
            .compile("([+-])?(\\d*)t(?:\\^(\\d+))?|([+-])?(\\d+)");

    public Polynomial(String str) {
        members = new ArrayList<Integer[]>();

        Matcher p = polynom.matcher(str);
        while (p.find()) {
            if(p.group(2) != null) {
                int k = 1;
                int pow = 1;
                if (!p.group(2).equals("")) k = Integer.parseInt(p.group(2));
                if (p.group(1) != null && p.group(1).equals("-")) k = -k;
                if(p.group(3) != null) pow = Integer.parseInt(p.group(3));
                members.add(new Integer[]{k, pow, 0});

            } else {
                if (p.group(5) != null) {
                    int b = Integer.parseInt(p.group(5));
                    if (p.group(4) != null && p.group(4).equals("-")) b = -b;
                    members.add(new Integer[]{0, 1, b});
                }
            }

        }
    }

    /**
     * Calculates polynomial value for t
     */
    public int eval(int t) {
        int total = 0;
        for (Integer[] member : members) {
            total += member[0] * Math.pow(t, member[1]) + member[2];
        }
        return total;
    }

    @Override
    public String toString() {
        String str = "Polynomial{\nmembers=";
        for (Integer[] member : members) {
            str += Arrays.toString(member);
        }
        return str + "\n}";
    }
}
