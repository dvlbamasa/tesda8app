package com.tesda8.region8.util.service;

import com.tesda8.region8.util.model.DataPoints;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class ReportUtil {

    private static DecimalFormat df2 = new DecimalFormat("#");
    private static Logger logger = LoggerFactory.getLogger(ReportUtil.class);


    public static double calculateRate(long target, long output) {
        return Double.parseDouble(df2.format(Math.round(100.0 * output/target)));
    }

    public static BigDecimal calculateRate(BigDecimal dividend, BigDecimal divisor) {
        return new BigDecimal("100.00").multiply(dividend.divide(divisor, 2, RoundingMode.HALF_UP));
    }

    public static DataPoints initializeTotalDataPoints() {
        DataPoints totalDataPoints = new DataPoints();
        totalDataPoints.setValue(0L);
        totalDataPoints.setLabel("Total");
        return totalDataPoints;
    }
}
