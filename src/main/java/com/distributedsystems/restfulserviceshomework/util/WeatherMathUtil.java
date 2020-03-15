package com.distributedsystems.restfulserviceshomework.util;

import com.distributedsystems.restfulserviceshomework.model.weather.external.ConsolidatedWeather;

import java.util.List;
import java.util.function.ToDoubleFunction;

public class WeatherMathUtil {

    public static double getAverage(final List<ConsolidatedWeather> weatherList,
                                    final ToDoubleFunction<ConsolidatedWeather> function) {
        if (weatherList == null || weatherList.isEmpty()) return 0;

        double sum = weatherList.stream()
                .mapToDouble(function)
                .sum();

        return sum / weatherList.size();
    }

    public static double getMinimum(final List<ConsolidatedWeather> weatherList,
                                    final ToDoubleFunction<ConsolidatedWeather> function) {
        return weatherList.stream()
                .mapToDouble(function)
                .min()
                .orElse(Double.MAX_VALUE);
    }

    public static double getMaximum(final List<ConsolidatedWeather> weatherList,
                                    final ToDoubleFunction<ConsolidatedWeather> function) {
        return weatherList.stream()
                .mapToDouble(function)
                .max()
                .orElse(Double.MIN_VALUE);
    }

    public static double getAverageForDateRange(final List<List<ConsolidatedWeather>> weatherListOfLists,
                                                final ToDoubleFunction<ConsolidatedWeather> function) {
        double sum = 0;
        int numberOfDays = 0;
        for (List<ConsolidatedWeather> weatherList : weatherListOfLists) {
            sum += getAverage(weatherList, function);
            ++numberOfDays;
        }

        return numberOfDays == 0 ? 0 : sum / numberOfDays;
    }

    public static double getMinimumForDateRange(final List<List<ConsolidatedWeather>> weatherListOfLists,
                                                final ToDoubleFunction<ConsolidatedWeather> function) {
        double min = Double.MAX_VALUE;
        for (List<ConsolidatedWeather> weatherList : weatherListOfLists) {
            min = Math.min(min, getMinimum(weatherList, function));
        }

        return min;
    }

    public static double getMaximumForDateRange(final List<List<ConsolidatedWeather>> weatherListOfLists,
                                                final ToDoubleFunction<ConsolidatedWeather> function) {
        double max = Double.MIN_VALUE;
        for (List<ConsolidatedWeather> weatherList : weatherListOfLists) {
            max = Math.max(max, getMaximum(weatherList, function));
        }

        return max;
    }
}
