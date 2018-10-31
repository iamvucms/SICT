package halfPeriodProject;

public class food implements Comparable<food>{
	public String foodCode;
	public String foodName;
	public int price;
	food(String a,String b, int c){
		this.foodCode = a;
		this.foodName =b;
		this.price = c;
		
	}
	public void updateFood(String a,String b, int c){
		this.foodCode = a;
		this.foodName =b;
		this.price = c;
	}
	@Override
	public int compareTo(food o) {
		
		return this.price-o.price;
	}
}
