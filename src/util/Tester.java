package util;

import java.io.IOException;
import java.time.Clock;
import java.time.Instant;
import java.util.concurrent.Callable;
import java.util.function.Function;

import org.knowm.xchart.*;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.style.Styler.LegendPosition;
import org.knowm.xchart.style.markers.SeriesMarkers;

import sorts.InsertionSort;
import sorts.Quicksort;
import sorts.RadixSort;

public class Tester {
	//Created a class during independent project that utilizes another class
	//also used constructors in both of them.
	
	public static double time(int arrSize, int max, Function <int[], Void> sort) {
		Clock clock = Clock.systemDefaultZone();
		long start = System.nanoTime();
		sort.apply((new RandomArray(arrSize, max).getRandVals()));
		long elapsedTime = System.nanoTime() - start;
		if(elapsedTime < 0) {
			System.out.println("negative error");
		}
		return (double)(elapsedTime) / 1000;
	}
	
	public static void test(int arrSize, int max, int step) throws IOException {
		int numTests = arrSize / step;
		double[] radixTimes = new double[numTests];
		double[] insertionTimes = new double[numTests];
		double[] quicksortTimes = new double[numTests];
		double[] arrSizes = new double[numTests];
		for(int i = 0; i < arrSizes.length; i += step) {
			arrSizes[i] = (double) numTests * ((double)i / arrSizes.length);
		}
		
		for(int i = 0; i < numTests; i++) {
			radixTimes[i] = time((int)arrSizes[i], max, RadixSort :: radixSort);
			quicksortTimes[i] = time((int)arrSizes[i], max, Quicksort :: quicksortWhole);
//			insertionTimes[i] = time((int) arrSizes[i], max, InsertionSort :: insertionSort);
		}
		var radixMax = 0.0;
		var quickMax = 0.0;
		var insertMax = 0.0;
		var maxSize = 0.0;
		for(int i = 0; i < numTests; i++) {
			if(radixTimes[i] > radixMax) {
				radixMax = radixTimes[i];
			}
			if(quicksortTimes[i] > quickMax) {	
				quickMax = quicksortTimes[i];
			}
			if(arrSizes[i] > maxSize) {
				maxSize = arrSizes[i];
			}
			if(insertionTimes[i] > insertMax) {
				insertMax = insertionTimes[i];
			}
		}
	
		
		// Create Chart
		
		XYChart chart = new XYChartBuilder().width(600).height(500).title("Time to sort").xAxisTitle("ArraySize").yAxisTitle("Time in Nanos").build();

		// Customize Chart
		chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Scatter);
		chart.getStyler().setChartTitleVisible(false);
		chart.getStyler().setLegendPosition(LegendPosition.InsideSW);
		chart.getStyler().setMarkerSize(16);
		
		chart.addSeries("radixTimes", arrSizes, radixTimes);
		XYSeries series1 = chart.addSeries("quicksortTimes", arrSizes, quicksortTimes);
		series1.setMarker(SeriesMarkers.DIAMOND);
		
//		XYSeries series2 = chart.addSeries("insertionTimes", arrSizes, insertionTimes);
//		series2.setMarker(SeriesMarkers.SQUARE);
		
		// Show it
		new SwingWrapper(chart).displayChart();

		// Save it
//		BitmapEncoder.saveBitmap(chart, "./Sample_Chart", BitmapFormat.PNG);

		// or save it in high-res
//		BitmapEncoder.saveBitmapWithDPI(chart, "./Sample_Chart_300_DPI", BitmapFormat.PNG, 300);
	}
}
