package halfPeriodProject;
public class khach implements Comparable<khach>{
	public int id;
	public String name;
	public int moneyPaid;
	public String time;
	public int idBan;
	khach(int a, String b, int c,String d,int e){
		this.id =a;
		this.name = b;
		this.moneyPaid = c;
		this.time = d;
		this.idBan = e;
	}
	public void Updatekhach(String b, int c,String d,int e){
		
		this.name = b;
		this.moneyPaid = c;
		this.time = d;
		this.idBan = e;
	}
	
	public int compareTo(khach o) {
		
		return this.moneyPaid-o.moneyPaid;
	}
	 
	
}
