package kurs.akcji;

public class RateAndDay {
	private Integer rate;
	private Integer day;

	public RateAndDay(Integer rate, Integer day) {
		this.rate = rate;
		this.day = day;
	}
	
	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}
}
