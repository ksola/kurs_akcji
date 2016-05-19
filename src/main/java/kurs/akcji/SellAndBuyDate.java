package kurs.akcji;

public class SellAndBuyDate {

	private int buyDay;
	private int sellDay;

	public SellAndBuyDate(Integer buyDay, Integer sellDay) {
		this.buyDay = buyDay;
		this.sellDay = sellDay;
		
	}
	
	public int getBuyDay() {
		return buyDay;
	}

	public void setBuyDay(int buyDay) {
		this.buyDay = buyDay;
	}

	public int getSellDay() {
		return sellDay;
	}

	public void setSellDay(int sellDay) {
		this.sellDay = sellDay;
	}
}
