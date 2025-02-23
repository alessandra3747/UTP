/**
 *
 *  @author Fus Aleksandra S30395
 *
 */

package zad1;


import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;
import java.util.function.BiFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calc {

    private final HashMap<Character, BiFunction<BigDecimal, BigDecimal, BigDecimal>> operations = new HashMap<>();

    public Calc() {
        operations.put('+', BigDecimal::add);
        operations.put('-', BigDecimal::subtract);
        operations.put('*', BigDecimal::multiply);
        operations.put('/', this::divideWithMathContext);
    }

    private BigDecimal divideWithMathContext(BigDecimal a, BigDecimal b) {
        return a.divide(b, MathContext.DECIMAL32);
    }

    public String doCalc(String cmd) {

        try {

            cmd = cmd.trim();
            String[] tokens = splitArguments(cmd);

            BigDecimal num1 = new BigDecimal(tokens[0]);
            Character operator = tokens[1].charAt(0);
            BigDecimal num2 = new BigDecimal(tokens[2]);

            BigDecimal result = operations.get(operator).apply(num1, num2);

            return result.toString();

        } catch (Exception e) {
            return "Invalid command to calc";
        }
    }

    private String[] splitArguments(String cmd) {
        Pattern pattern = Pattern.compile("(-?\\d*\\.?\\d+)\\s?([+\\-*/])\\s?(-?\\d*\\.?\\d+)");
        Matcher matcher = pattern.matcher(cmd);
        matcher.find();

        return new String[] { matcher.group(1), matcher.group(2), matcher.group(3) };
    }

}
