package java8.chapter2;

public class Apple {

	@Override
	public String toString() {
		return "Apple [color=" + color + ", weight=" + weight + "]";
	}

	public Apple(Integer weight,String color) {
		super();
		this.color = color;
		this.weight = weight;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	private String color;
	
	private Integer weight;

	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}



}
