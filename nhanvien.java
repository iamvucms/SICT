package halfPeriodProject;

public class nhanvien {
	public int id;
	public String name;
	public int absences=0;
	public String phone;
	nhanvien(int a,String b,String c){
		this.id = a;
		this.name = b;
		this.phone  = c;
	}
	public void updateNhanvien(int a,String b, String c){
		this.id = a;
		this.name = b;
		this.phone =c;
	}
	public void absence() {
		this.absences++;
	}
}
