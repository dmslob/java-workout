package com.dmslob.data;

import com.google.common.math.Stats;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.descriptive.SynchronizedDescriptiveStatistics;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.testng.annotations.Test;

import java.text.NumberFormat;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.in;

public class BaseStatsTest {

    /**
     * The term mean, also called the average, is computed by adding values in a list and then
     * dividing the sum by the number of values. This technique is useful for determining the
     * general trend for a set of numbers. It can also be used to fill in missing data elements.
     */
    double mean(double[] doubles) {
        double total = 0;
        for (double element : doubles) {
            total += element;
        }
        return total / doubles.length;
    }

    @Test
    public void should_calculate_mean() {
        // given
        double[] doubles = {12.5, 18.7, 11.2, 19.0, 22.1, 14.3, 16.9, 12.5, 17.8, 16.9};
        double expected = 16.19d;
        // when
        double actual = mean(doubles);
        // then
        assertThat(actual).isEqualTo(expected);
        // or
        // when
        OptionalDouble mean = Arrays.stream(doubles).average();
        // then
        assertThat(mean).isEqualTo(OptionalDouble.of(expected));
        // or
        // given
        var testStat = Stats.of(doubles);
        expected = 16.189999999999998d;
        // when
        double meanByGuava = testStat.mean();
        // then
        assertThat(meanByGuava).isEqualTo(expected);
    }

    @Test
    public void should_calculate_standard_deviation() {
        // given
        double[] nums = {25, 5, 45, 68, 61, 46, 24, 95};
        double expected = 26.732178642976333d;
        // when
        double standardDeviation = std(nums);
        // then
        assertThat(standardDeviation).isEqualTo(expected);
        // or
        // given
        var testStat = Stats.of(nums);
        // when
        double stdByGuava = testStat.populationStandardDeviation();
        // then
        assertThat(stdByGuava).isEqualTo(expected);
    }

    // calculate standard deviation
    private double std(double[] nums) {
        // get the sum of nums
        double sum = 0.0;
        for (double i : nums) {
            sum += i;
        }
        // get the mean of nums
        int len = nums.length;
        double mean = sum / len;
        // calculate the standard deviation
        double standardDeviation = 0.0;
        for (double num : nums) {
            standardDeviation += Math.pow(num - mean, 2);
        }
        return Math.sqrt(standardDeviation / len);
    }

    /**
     * The mean can be misleading if the dataset contains a large number of outlying values or is
     * otherwise skewed. When this happens, the mode and median can be useful.
     * The term median is the value in the middle of a range of values.
     * For an odd number of values, this is easy to compute. For an even number of values,
     * the median is calculated as the average of the middle two values.
     */
    double median(double[] nums) {
        if (nums.length == 0)
            return 0.0d;
        Arrays.sort(nums);
        if (nums.length % 2 == 0) { // Even number of elements
            double mid1 = nums[(nums.length / 2) - 1];
            double mid2 = nums[nums.length / 2];
            return (mid1 + mid2) / 2;
        } else { // Odd number of elements
            return nums[(nums.length / 2) + 1];
        }
    }

    @Test
    public void should_find_median() {
        // given
        double[] doubles = {12.5, 18.3, 11.2, 19.0, 22.1, 14.3, 16.2, 12.5, 17.8, 16.5};
        double expected = 16.35d;
        // when
        var actualMedian = median(doubles);
        // then
        assertThat(actualMedian).isEqualTo(expected);
        // or by means of apache.common.math3.stat
        // given
        DescriptiveStatistics statTest = new SynchronizedDescriptiveStatistics();
        for (double num : doubles) {
            statTest.addValue(num);
        }
        // when
        actualMedian = statTest.getPercentile(50);
        // then
        assertThat(actualMedian).isEqualTo(expected);
    }

    /**
     * The term mode is used for the most frequently occurring value in a dataset.
     * This can be thought of as the most popular result, or the highest bar in a histogram.
     * It can be a useful piece of information when conducting statistical analysis,
     * but it can be more complicated to calculate than it first appears
     */
    double mode(double[] doubles) {
        int modeCount = 0;
        double mode = 0;
        int tempCnt;
        for (double i : doubles) {
            tempCnt = 0;
            for (double j : doubles) {
                if (i == j) {
                    tempCnt++;
                }
            }
            if (tempCnt > modeCount) {
                modeCount = tempCnt;
                mode = i;
            }
        }
        System.out.println("Mode" + mode + " appears " + modeCount + " times.");
        return mode;
    }

    List<Double> findMode(double[] testData) {
        List<Double> modes = new ArrayList<>();
        HashMap<Double, Integer> modeMap = new HashMap<>();
        int maxMode = 0;
        for (double value : testData) {
            int modeCnt;
            if (modeMap.containsKey(value)) {
                modeCnt = modeMap.get(value) + 1;
            } else {
                modeCnt = 1;
            }
            modeMap.put(value, modeCnt);
            if (modeCnt > maxMode) {
                maxMode = modeCnt;
            }
        }
        for (Map.Entry<Double, Integer> multiModes : modeMap.entrySet()) {
            if (multiModes.getValue() == maxMode) {
                modes.add(multiModes.getKey());
            }
        }
        return modes;
    }

    @Test
    public void should_find_mode() {
        // given
        double[] doubles = {12.5, 18.3, 11.2, 19.0, 22.1, 14.3, 16.2, 12.5, 17.8, 16.5, 12.5};
        double expected = 12.5d;
        // when
        double mode = mode(doubles);
        // then
        assertThat(mode).isEqualTo(expected);
        // or
        var modes = findMode(doubles);
        // then
        assertThat(modes).isEqualTo(List.of(expected));
    }

    @Test
    public void should_predict_values() {
        // given
        double[][] input = {
                {1950, 8639369}, {1960, 9118700},
                {1970, 9637800}, {1980, 9846800}, {1990, 9969310},
                {2000, 10263618}
        };
        // when
        var regression = new SimpleRegression();
        regression.addData(input);
        // then
        double[] predictionYears = {1950, 1960, 1970, 1980, 1990, 2000,
                2010, 2020, 2030, 2040};
        var yearFormat = NumberFormat.getNumberInstance();
        yearFormat.setMaximumFractionDigits(0);
        yearFormat.setGroupingUsed(false);
        var populationFormat = NumberFormat.getNumberInstance();
        populationFormat.setMaximumFractionDigits(0);

        for (double predictionYear : predictionYears) {
            System.out.println(yearFormat.format(predictionYear) + "-"
                    + populationFormat.format(regression.predict(predictionYear)));
        }
    }
}
