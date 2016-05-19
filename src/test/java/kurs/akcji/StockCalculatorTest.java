package kurs.akcji;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jdk.nashorn.internal.objects.annotations.Getter;

import org.junit.Test;

public class StockCalculatorTest {

	@Test
	public void shouldReturnFirstDayAndLastDayWhenPriceAlwaysIncrease() {
		// given
		StockCalculator stockCalculator = new StockCalculator();
		List<Integer> rates = Stream.iterate(Integer.valueOf(1), i -> i + 1).limit(20).collect(Collectors.toList());
		
		// when
		SellAndBuyDate caluclationResult = stockCalculator.calculateBuyAndSellDay(rates);
		
		// then
		assertThat(caluclationResult.getBuyDay()).isEqualTo(0);
		assertThat(caluclationResult.getSellDay()).isEqualTo(19);
	}
	
	@Test
	public void shouldReturnFirstDayAndMiddleDayWhenPriceInTheMiddleIsTheHighest() {
		// given
		StockCalculator stockCalculator = new StockCalculator();
		List<Integer> rates = Stream.iterate(Integer.valueOf(1), i -> i + 1).limit(10).collect(Collectors.toList());
		rates.addAll(Stream.iterate(Integer.valueOf(11), i -> i - 1).limit(10).collect(Collectors.toList()));
		
		// when
		SellAndBuyDate caluclationResult = stockCalculator.calculateBuyAndSellDay(rates);
		
		// then
		assertThat(caluclationResult.getBuyDay()).isEqualTo(0);
		assertThat(caluclationResult.getSellDay()).isEqualTo(10);
	}
	
	/**
	 * Input:
	 * 5, 6, 7, 1, 2, 3, 4, 5, 6, 6, 6, 6, 6, 6, 6
	 *          ^              ^
	 *          Buy            Sell
	 */
	@Test
	public void shouldReturnThirdAndEightDayWhenTheRevenueIsMaksimal() throws Exception {
		// given
		StockCalculator stockCalculator = new StockCalculator();
		List<Integer> rates = Stream.iterate(Integer.valueOf(5), i -> i + 1).limit(3).collect(Collectors.toList());
		rates.addAll(Stream.iterate(Integer.valueOf(1), i -> i + 1).limit(6).collect(Collectors.toList()));
		rates.addAll(Stream.generate(() -> Integer.valueOf(6)).limit(11).collect(Collectors.toList()));
		
		// when
		SellAndBuyDate caluclationResult = stockCalculator.calculateBuyAndSellDay(rates);
		
		// then
		assertThat(caluclationResult.getBuyDay()).isEqualTo(3);
		assertThat(caluclationResult.getSellDay()).isEqualTo(8);
	}
	
	/**
	 * Input:
	 * 20, 19, 18, 17, ... 1
     * should buy and sell the same day.
	 */
	@Test
	public void shouldReturnBuyAndSellOntheFirstDayWhenRateAlwaysDecrease() throws Exception {
		// given
		StockCalculator stockCalculator = new StockCalculator();
		List<Integer> rates = Stream.iterate(Integer.valueOf(20), i -> i - 1).limit(20).collect(Collectors.toList());
		
		// when
		SellAndBuyDate caluclationResult = stockCalculator.calculateBuyAndSellDay(rates);
		
		// then
		assertThat(caluclationResult.getBuyDay()).isEqualTo(0);
		assertThat(caluclationResult.getSellDay()).isEqualTo(0);
	}
	
	/**
	 * Input:
	 * 5, 6, 7, 1, 2, 3, 4, 5, 6, 6, 5, 6, 8, 6, 6..
	 *          ^                          ^
	 *          Buy                        Sell
	 */
	@Test
	public void shouldReturnThirdAndTwelvDayWhenTheRevenueIsMaksimal() throws Exception {
		// given
		StockCalculator stockCalculator = new StockCalculator();
		List<Integer> rates = Stream.iterate(Integer.valueOf(5), i -> i + 1).limit(3).collect(Collectors.toList());
		rates.addAll(Stream.iterate(Integer.valueOf(1), i -> i + 1).limit(6).collect(Collectors.toList()));
		rates.add(Integer.valueOf(6));
		rates.add(Integer.valueOf(5));
		rates.add(Integer.valueOf(6));
		rates.add(Integer.valueOf(8));
		rates.addAll(Stream.generate(() -> Integer.valueOf(6)).limit(9).collect(Collectors.toList()));
		
		// when
		SellAndBuyDate caluclationResult = stockCalculator.calculateBuyAndSellDay(rates);
		
		// then
		assertThat(caluclationResult.getBuyDay()).isEqualTo(3);
		assertThat(caluclationResult.getSellDay()).isEqualTo(12);
	}
	
	/**
	 * It is not real performance test. This test is just to check if for
	 * 1000000 entries this code will work and the result came in reasonable
	 * time.
	 */
	@Test
	@Getter
	public void performanceRandomTest() throws Exception {
		// given
		StockCalculator stockCalculator = new StockCalculator();
		List<Integer> rates = Stream.generate(() -> 1 + (int)(Math.random() * 100)).limit(1000000).collect(Collectors.toList());
		
		// when
		stockCalculator.calculateBuyAndSellDay(rates);
		
		// check nothing. Just check the execution time.
	}

}
