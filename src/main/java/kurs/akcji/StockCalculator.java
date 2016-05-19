package kurs.akcji;

import java.util.ArrayList;
import java.util.List;

public class StockCalculator {

	public SellAndBuyDate calculateBuyAndSellDay(List<Integer> rates) {
		List<RateAndDay> rateAndDays = new ArrayList<RateAndDay>();
		rates.stream().forEachOrdered(
				(rate) -> rateAndDays.add(new RateAndDay(rate, rateAndDays
						.size())));
		rateAndDays.sort((rateAndDay1, rateAndDay2) -> {
			if (rateAndDay1.getRate() - rateAndDay2.getRate() != 0) {
				return rateAndDay1.getRate() - rateAndDay2.getRate();
			}
			else {
				return rateAndDay2.getDay() - rateAndDay1.getDay();
			}
		});
		SellAndBuyDate bestSellAndBuyDate = new SellAndBuyDate(0, 0);
		int bestRevenue = 0;
		for (int i = rateAndDays.size() - 1; i >= 0; i--) {
			RateAndDay sellRateDay = rateAndDays.get(i);
			RateAndDay buyRateDay = detemineBuyDay(rateAndDays, sellRateDay);
			int newRevenue = sellRateDay.getRate() - buyRateDay.getRate();
			if (i == rateAndDays.size() - 1 || newRevenue > bestRevenue) {
				bestRevenue = newRevenue;
				bestSellAndBuyDate.setBuyDay(buyRateDay.getDay());
				bestSellAndBuyDate.setSellDay(sellRateDay.getDay());
			}
		}
		return bestSellAndBuyDate;
	}

	private RateAndDay detemineBuyDay(List<RateAndDay> rateAndDays, RateAndDay sellRateDay) {
		for (int i = 0; i < rateAndDays.size(); i++) {
			RateAndDay buyDate = rateAndDays.get(i);
			if(sellRateDay.getDay() > buyDate.getDay()) {
				return buyDate;
			}
		}
		return sellRateDay;
	}
}
