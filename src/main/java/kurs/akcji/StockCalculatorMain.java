package kurs.akcji;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StockCalculatorMain {

	public static void main(String[] args) {
		StockCalculator stockCalculator = new StockCalculator();
		List<Integer> rates = Stream.of(args).map(s -> Integer.parseInt(s)).collect(Collectors.toList());
		
		SellAndBuyDate sellAndBuyDate = stockCalculator.calculateBuyAndSellDay(rates);
		System.out.println("Buy on " + sellAndBuyDate.getBuyDay());
		System.out.println("Sell on " + sellAndBuyDate.getSellDay());
	}
}
