package it.com.demo.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

/**
 * BigDecimal 操作的工具类
 */
public class BigDecimalUtil {

    //DOUBLE.MAX_VALUE 的 BigDecimal 表示
    private static final BigDecimal DOUBLE_MAX_VALUE = BigDecimal.valueOf(Double.MAX_VALUE);

    // 除法保留小数位数
    public static final int DIVIDE_SCALE = 2;

    // 除法舍入模式 (默认四舍五入)
    public static final RoundingMode DIVIDE_ROUND_MODE = RoundingMode.HALF_UP;

    public static BigDecimal add(BigDecimal a, BigDecimal b) {
        return a.add(b);
    }

    public static String add2String(BigDecimal a, BigDecimal b) {
        return a.add(b).toString();
    }

    /**
     * 加法
     *
     * @param a
     * @param b
     * @return
     */
    public static BigDecimal add(String a, String b) {
        BigDecimal bigDecimalA = new BigDecimal(a);
        BigDecimal bigDecimalB = new BigDecimal(b);
        return bigDecimalA.add(bigDecimalB);
    }

    public static BigDecimal add(BigDecimal a, String b) {
        BigDecimal bigDecimalB = new BigDecimal(b);
        return a.add(bigDecimalB);
    }

    public static String add2String(String a, String b) {
        return BigDecimalUtil.add(a, b).toString();
    }

    /**
     * 减法
     *
     * @param a
     * @param b
     * @return
     */
    private static BigDecimal subtract(String a, String b) {
        BigDecimal bigDecimalA = new BigDecimal(a);
        BigDecimal bigDecimalB = new BigDecimal(b);
        return bigDecimalA.subtract(bigDecimalB);
    }

    public static String subtract2String(String a, String b) {
        return BigDecimalUtil.subtract(a, b).toString();
    }


    /**
     * 除法
     *
     * @param bigDecimal
     * @param bigDecimal2
     */
    public static BigDecimal divide(BigDecimal bigDecimal, BigDecimal bigDecimal2) {
        return bigDecimal.divide(bigDecimal2, DIVIDE_SCALE, DIVIDE_ROUND_MODE);
    }

    /**
     * 除法
     *
     * @param bigDecimal
     * @param num
     */
    public static BigDecimal divide(BigDecimal bigDecimal, int num) {
        String s = String.valueOf(num);
        BigDecimal bigDecimal2 = new BigDecimal(s);
        return BigDecimalUtil.divide(bigDecimal, bigDecimal2);
    }

    //获取整数部分
    public static BigDecimal integralPart(BigDecimal value) {
        return value.setScale(0, RoundingMode.DOWN);
    }

    //获取小数部分
    public static BigDecimal fractionalPart(BigDecimal value) {
        return value.subtract(BigDecimalUtil.integralPart(value));
    }

    //检查BigDecimal是否可以表示为int值
    public static boolean isIntValue(BigDecimal value) {
        try {
            value.intValueExact();
            return true;
        } catch (ArithmeticException ex) {
            // ignored
        }
        return false;
    }

    //检查BigDecimal是否可以表示为double值
    public static boolean isDoubleValue(BigDecimal value) {
        if (value.compareTo(DOUBLE_MAX_VALUE) > 0
                || value.compareTo(DOUBLE_MAX_VALUE.negate()) < 0) {
            return false;
        }
        return true;
    }

    //格式化为货币,￥100,123.45
    public static String formatMoney(BigDecimal value) {
        // java.text.NumberFormat
        NumberFormat format = NumberFormat.getCurrencyInstance();
        return format.format(value);
    }

    //格式化为百分比,0.028%
    public static String formatPercent(BigDecimal value) {
        NumberFormat format = NumberFormat.getPercentInstance();
        return format.format(value);
    }

    //格式化为百分比,0.028%
    public static String formatPercent(BigDecimal value, int num) {
        NumberFormat format = NumberFormat.getPercentInstance();
        // 注意, 如果小数位超过2位,就不准确了,可以使用该方法指定格式化到几位小数.一般, BigDecimal 的小数位有几位,就设置为几
        format.setMaximumFractionDigits(num);
        return format.format(value);
    }
}
