package halfPeriodProject;
 import java.util.*;
public class ban {
	public int idBan;
	public int status;
	Vector<food> foodList = new Vector<food>();
	ban(int a, int b){
		this.idBan = a;
		if(b ==1 || b==0) {
			this.status = b;
		}
		//this.foodList = c;
	}
	public int thanhtoan() {
		int moneyTotal = 0;
		food temp;
		for(int i=0;i< this.foodList.size();i++) {
			
			temp = (food)this.foodList.get(i);
			//System.out.println((i+1)+"."+temp.foodName+" price:"+temp.price);
			moneyTotal += temp.price;
		}
		//System.out.println("Total: "+moneyTotal);
		return moneyTotal;
	}
	public void setStt(int a) {
		if(a ==1 || a==0) {
			this.status = a;
		}
	}
	public void showFoodList() {
		food temp;
		for(int i=0;i< this.foodList.size();i++) {
			temp = (food)this.foodList.get(i); 
			System.out.println((i+1)+"."+temp.foodName+"");
		}
	}
	public void addFood(food a) {
		//Admin.showList();
		this.foodList.add(a);
	}
	public void emptyTable() {
		this.status = 0;
		this.foodList = new Vector<food>();
	}
	public void foodList() {
		food temp;
		for(int i=0;i< this.foodList.size();i++) {
			
			temp = (food)this.foodList.get(i);
			System.out.println((i+1)+"."+temp.foodName+" - Giá:"+temp.price);
			
		}
	}
}
