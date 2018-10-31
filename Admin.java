package halfPeriodProject;

import java.text.SimpleDateFormat;
import java.util.*;

public class Admin {
	private final String USERNAME ="vucms";
	private final String PASSWORD = "123456";
	Scanner key = new Scanner(System.in);
	Vector<food> allFood = new Vector<food>();
	Vector<ban> table = new Vector<ban>();
	Vector<khach> cumtomers= new Vector<khach>();
	Vector<nhanvien> Emps = new Vector<nhanvien>();
	int totalDay = 0;
	
	Admin(){
			
			allFood.addElement(new food("abc","Hamberger",10));
			allFood.addElement(new food("abc2","Meat",20));
			allFood.addElement(new food("abc3","Chicken",15));
			allFood.addElement(new food("abc4","Me",9999));
			allFood.addElement(new food("abc5","Noodle",42));
			allFood.addElement(new food("abc6","Fish",58));
			allFood.addElement(new food("abc7","Bánh xèo",20));
			allFood.addElement(new food("abc8","Bún đậu mắm tôm",50));
			Emps.addElement(new nhanvien(1,"Thành Đạt","01266996688"));
			Emps.addElement(new nhanvien(2,"Minh Thắng","01234566666"));
			Emps.addElement(new nhanvien(3,"Hương Mai","01234555555"));
			Emps.addElement(new nhanvien(4,"Hoàng Vũ","01234444444"));
			cumtomers.add(new khach(1,"Hoàng Vũ",50,"12:22:22 6/10/2018",5));
			cumtomers.add(new khach(2,"Hoàng Vũ",45,"12:22:22 6/10/2018",8));
			cumtomers.add(new khach(3,"Hoàng Vũ",35,"12:22:22 6/10/2018",9));
			cumtomers.add(new khach(4,"Hoàng Vũ",20,"12:22:22 6/10/2018",10));
			cumtomers.add(new khach(5,"Hoàng Vũ",10,"12:22:22 6/10/2018",20));
		//Vector<food> emptyFoodlist ;

		for(int i=1;i<=30;i++) {
			table.add(new ban(i,0));
		}
		this.login();
	}
	public void login() {
		System.out.println("Đăng nhập hệ thống quản lí nhà hàng");
		System.out.print("Tên đăng nhập: ");
		String username = key.nextLine();
		System.out.print("Mật khẩu: ");
		String pwd = key.nextLine();
		if(this.USERNAME.equals(username) && this.PASSWORD.equals(pwd)) {
			System.out.println("Đăng nhập thành công !");
			this.mainMenu();
		}else {
			System.out.println("Lỗi đăng nhập - mật khẩu hoặc tên đăng nhập không chính nhập");
			this.login();
		}
	}
	
	public void addFoodToTable() {
		try {
			System.out.println("Nhập số của bàn:");
			int idTb = key.nextInt();
			
			this.showList();
			System.out.print("Chọn thực đơn (cách nhau bởi dấu phẩy, nếu có số lượng nhiều hơn 1 thì dùng dấu x): ");
			key.nextLine();
			String idFood = String.valueOf(key.nextLine());
			String[] idFoods = idFood.split(",");
			food newFood ;
			ban r = (ban)this.table.get(idTb-1);
			for(String temp : idFoods) {
				if(temp.split("x").length==1) {
					newFood =(food)allFood.get(Integer.parseInt(temp)-1);
					r.addFood(newFood);
				}else {
					int to = Integer.parseInt(temp.split("x")[1]);
					for(int i = 0; i<to;i++) {
						newFood =(food)allFood.get(Integer.parseInt((temp.split("x")[0]))-1);
						r.addFood(newFood);
					}
				}
			}
			
			
			r.status =1;
			this.qlban();
		}catch(Exception e) {
			System.out.println("Có lỗi xảy ra vui lòng nhập đúng định dạng");
			this.addFoodToTable();
		}
		
	}
	public void toNullTable(int idTb) {
		
		ban r = (ban)this.table.get(idTb-1);
		r.emptyTable();
	}
	public void getEmptyTable() {
		ban r;
		
		System.out.print("Các bàn còn trống là: ");
		int c = 0;
		for(int i=0;i<this.table.size();i++) {
			r = (ban)this.table.get(i);
			//System.out.println(r.status);
			if(r.status==0) {
				c++;
				System.out.print(r.idBan+",");
			}
		}
		System.out.println("");
		if(c==0) System.out.print(" Không còn bàn nào trống\n");
		this.qlban();
	}
	public void getUsedTable() {
			ban r;
		
		System.out.print("Các bàn đã được sử dụng là: ");
		int c = 0;
		for(int i=0;i<this.table.size();i++) {
			r = (ban)this.table.get(i);
			//System.out.println(r.status);
			if(r.status==1 ) {
				c++;
				System.out.print(r.idBan+",");
			}
		}
		System.out.println("");
		if(c==0) System.out.print(" Tất cả các bàn đều trống\n");
		this.qlban();
	}
	public void showListFoodOnTable() {
		System.out.print("Nhập số của bàn cần tìm: ");
		int idTb = key.nextInt();
		ban r = (ban)this.table.get(idTb-1);
		r.foodList();
		this.qlban();
		
	}
	public void tinhtien() {
		System.out.print("Nhập tên khách:");
		key.nextLine();
		String ctmName = key.nextLine();
		System.out.print("Nhập số của bàn cần thanh toán:");
		int idTb = key.nextInt();
		ban r = (ban)this.table.get(idTb-1);
		Date d = new Date();
		SimpleDateFormat fm = new SimpleDateFormat("H:m:s dd/MM/yyyy");
		String time = fm.format(d);
		
		cumtomers.add(new khach(this.cumtomers.size()+1,ctmName,r.thanhtoan(),time,idTb));
		
		this.printBill(ctmName, time, idTb);
		this.toNullTable(idTb);
		this.qlban();
	} 
	public void printBill(String ctmName,String time,int idBan) {
		System.out.println("==Hóa đơn==");
		System.out.println("Thời gian: "+time);
		System.out.println("Bàn số: "+idBan);
		System.out.println("Tên khách hàng: "+ctmName);
		ban r = (ban)this.table.get(idBan-1);
		r.foodList();
		System.out.println("Thành tiền: "+r.thanhtoan()+"$");
	}
	//Food Manage Area//
	public void showList() {
		food temp;
		for(int i=0;i< this.allFood.size();i++) {
			temp = (food)this.allFood.get(i); 
			System.out.println((i+1)+"."+temp.foodName+" - Giá:"+temp.price);
		}
	}
	public void addFoodToMenu() {
		System.out.println("Thêm món ăn mới");
		System.out.print("Nhập số lượng món muốn thêm vào: ");
		int sizeF = this.key.nextInt();
		String code,name;
		int price;
		this.key.nextLine();
		for(int i=0;i<sizeF;i++) {
			
			System.out.print("Nhập mã món ăn");
			code = this.key.nextLine();
			boolean check = false;
			
			for(int j =0;j<this.allFood.size();j++) {
				if(this.allFood.get(j).foodCode.equals(code)) {
					System.out.println("Mã đã tồn tại, vui lòng thử lại");
					check= true;
					break;
				}
			}
			while(check ) {
				System.out.print("Nhập mã món ăn: ");
				code = this.key.nextLine();
			}
			System.out.print("Nhập tên món ăn: ");
			name = this.key.nextLine();
			System.out.print("Nhập giá món ăn: ");
			price = this.key.nextInt();
			this.allFood.addElement(new food(code,name,price));
		}
		this.qlmenu();
	}
	public void deleteFoodInList() {
		this.showList();
		System.out.print("Chọn id món ăn cần xóa: ");
		int k = this.key.nextInt();
		
		while(k> this.allFood.size() || k<1) {
			System.out.print("Id món ăn không tồn tại, vui lòng nhập lại id món ăn: ");
			 k = this.key.nextInt();
		}
		this.allFood.remove(k-1);
		System.out.println("Xóa món ăn thành công");
		this.qlmenu();
	}
	public void editFoodInList() {
		this.showList();
		System.out.print("Chọn id món ăn cần sửa: ");
		int k = this.key.nextInt();
		
		while(k> this.allFood.size() || k<1) {
			System.out.print("Id món ăn không tồn tại, vui lòng nhập lại id món ăn: ");
			 k = this.key.nextInt();
		}
		food temp = (food)this.allFood.get(k-1);
		System.out.print("Nhập tên ("+temp.foodName+") : ");
		key.nextLine();
		String name = key.nextLine();
		System.out.print("Nhập giá("+temp.price+ "): ");
		int price = this.key.nextInt();
		temp.updateFood(temp.foodCode, name, price);
		
	}
	public void sortFoods() {
		food foodlists[] = new food[this.allFood.size()];
		for(int i=0,len=this.allFood.size();i<len;i++) {
			foodlists[i] = this.allFood.get(i);
		}
		Arrays.sort(foodlists);
		food temp;
		for(int i=0,len=foodlists.length;i< len;i++) {
			temp = (food)foodlists[i]; 
			System.out.println((i+1)+"."+temp.foodName+" - Giá:"+temp.price);
		}
		this.qlmenu();
	}
	//Employees Manage Area
	public void addNewEmp() {
		System.out.print("Nhập số lượng nhân viên muốn thêm vào: ");
		int sizeE = this.key.nextInt();
		this.key.nextLine();
		for(int i=0;i<sizeE;i++) {
			
			System.out.print("Nhập tên nhân viên: ");
			
			String name = this.key.nextLine();
			System.out.print("Nhập số điện thoại nhân viên: ");
			String phone = this.key.nextLine();
			this.Emps.addElement(new nhanvien(this.Emps.size()+1,name,phone));
		}
		this.qlnv();
		
	}
	public void showEmps() {
		nhanvien Emp;
		for(int i=0;i<this.Emps.size();i++) {
			Emp = this.Emps.get(i);
			System.out.println((i+1)+"."+Emp.name+" - số điện thoại:"+Emp.phone+" - Vắng: "+Emp.absences+" buổi trên tổng số "+this.totalDay+" buổi");
			
		}
		
	}
	public void delEmp() {
		this.showEmps();
		System.out.print("Nhập id nhân viên cần xóa:");
		int idDel = this.key.nextInt();
		while(idDel> this.Emps.size() || idDel<1) {
			System.out.print("Id nhân viên không tồn tại, vui lòng nhập lại id nhân viên: ");
			idDel = this.key.nextInt();
		}
		this.Emps.remove(idDel-1);
		this.qlnv();
	}
	public void editEmp() {
		this.showEmps();
		System.out.print("Nhập id nhân viên cần sửa: ");
		int idEmp = this.key.nextInt();
		while(idEmp> this.Emps.size() || idEmp<1) {
			System.out.print("Id nhân viên không tồn tại, vui lòng nhập lại id nhân viên: ");
			idEmp = this.key.nextInt();
		}
		nhanvien temp = (nhanvien)this.Emps.get(idEmp-1);
		System.out.print("Nhập tên mới của nhân viên ("+temp.name+"): ");
		this.key.nextLine();
		String name = this.key.nextLine();
		System.out.print("Nhập số điện thoại mới của nhân viên ("+temp.phone+"): ");
		String phone = this.key.nextLine();
		temp.updateNhanvien(temp.id, name, phone);
		this.qlnv();
	}
	public void rollUpEmps() {
		nhanvien temp;
		this.key.nextLine();
		for(int i=0;i<this.Emps.size();i++) {
			temp = (nhanvien)this.Emps.get(i);
			System.out.print((i+1)+"."+temp.name+" - Có mặt='Y' & Vắng='N': ");
			String req = this.key.nextLine().trim().toLowerCase();
			if("n".equals(req)) {
				temp.absence();
			}
		}
		if(this.Emps.size()==0) {
			System.out.println("Vui lòng thêm nhân viên");
			this.addNewEmp();
		}else {
			this.totalDay++;
			this.qlnv();
		}
	}
	public void qlnv() {
		System.out.println("==Quản lí nhân viên==");
		System.out.println("1.Thêm nhân viên mới");
		System.out.println("2.Hiển thị danh sách nhân viên");
		System.out.println("3.Xóa nhân viên");
		System.out.println("4.Sửa thông tin nhân viên");
		System.out.println("5.Điểm danh nhân viên");
		System.out.println("6.Trở về menu chính");
		System.out.print("Chọn: ");
		int selected = key.nextInt();
		switch(selected) {
		case 1:
			this.addNewEmp();
			break;
		case 2:
			this.showEmps();
			this.qlnv();
			break;
		case 3:
			this.delEmp();
			break;
		case 4:
			this.editEmp();
			break;
		case 5:
			this.rollUpEmps();
			break;
		case 6:
			this.mainMenu();
			break;
		default:
			this.qlnv();
			break;
		}
	}
	public void qlban() {
		System.out.println("==Quản lí bàn==");
		System.out.println("1.Tính tiền và in hóa đơn");
		System.out.println("2.Hiển thị các bàn còn trống");
		System.out.println("3.Hiển thị các bàn đã được sử dụng");
		System.out.println("4.Thêm món ăn cho bàn");
		System.out.println("5.Hiển thị các món ăn trên bàn");
		System.out.println("6.Trở về menu chính");
		System.out.print("Chọn: ");
		int selected = key.nextInt();
		switch(selected) {
		case 1:
			this.tinhtien();
			break;
		case 2:
			this.getEmptyTable();
			break;
		case 3:
			this.getUsedTable();
			break;
		case 4:
			this.addFoodToTable();
			break;
		case 5:
			this.showListFoodOnTable();
			break;
		case 6:
			this.mainMenu();
			break;
		default:
			this.qlnv();
			break;
		}
	}
	//Customers Manage Area
	public void addCtm() {
		System.out.print("Nhập số lượng lịch sử cần thêm vào: ");
		int sizeC = this.key.nextInt();
		
		for(int i=0;i<sizeC;i++) {
			this.key.nextLine();
			System.out.print("Nhập tên khách hàng: ");
			String name = this.key.nextLine().trim();
			System.out.print("Nhập thời gian: ");
			String time = this.key.nextLine().trim();
			System.out.print("Nhập số tiền thanh toán: ");
			int money = this.key.nextInt();
			System.out.print("Nhập số bàn: ");
			int idBan = this.key.nextInt();
			this.cumtomers.add(new khach(this.cumtomers.size()+1,name,money,time,idBan));
		}
		this.qlkhachhang();
	}
	public void showCtms() {
		khach temp;
		for(int i=0;i<this.cumtomers.size();i++) {
			temp = (khach)this.cumtomers.get(i);
			System.out.println((i+1)+"."+temp.name+" - Bàn số "+temp.idBan+" - vào lúc "+temp.time+" - số tiền "+temp.moneyPaid+"$");
		}
	}
	public void delAllCtm() {
		this.cumtomers.removeAllElements();
		System.out.println("Xóa lịch sử thành công");
		this.qlkhachhang();
	}
	public void editCtm() {
		this.showCtms();
		System.out.print("Nhập id lịch sử cần sửa: ");
		int req = this.key.nextInt();
		khach ctm = (khach)this.cumtomers.get(req-1);
		this.key.nextLine();
		System.out.print("Nhập tên khách hàng ("+ctm.name+"):");
		String name = this.key.nextLine().trim();
		System.out.print("Nhập thời gian ("+ctm.time+"):");
		String time = this.key.nextLine().trim();
		System.out.print("Nhập số bàn ("+ctm.idBan+")");
		int idBan = this.key.nextInt();
		System.out.print("Nhập số tiền thanh toán ("+ctm.moneyPaid+"):");
		int money = this.key.nextInt();
		ctm.Updatekhach(name, money, time, idBan);
		this.qlkhachhang();
	}
	public void sortCtms() {
		 khach Ctms[] = new khach[this.cumtomers.size()];
		for(int i=0;i<this.cumtomers.size();i++) {
			Ctms[i] = (khach)this.cumtomers.get(i);
		}
		Arrays.sort(Ctms);
		khach temp;
		for(int i=0,len =Ctms.length;i<len;i++) {
			temp = (khach)Ctms[i];
			System.out.println((i+1)+"."+temp.name+" - Bàn số "+temp.idBan+" - vào lúc "+temp.time+" - số tiền "+temp.moneyPaid+"$");
		}
		
		this.qlkhachhang();
	}
	public void qlkhachhang() {
		System.out.println("==Quản lí lịch sử khách hàng==");
		System.out.println("1.Thêm lịch sử khách hàng mới");
		System.out.println("2.Hiển thị danh sách khách hàng");
		System.out.println("3.Xóa toàn bộ lịch sử");
		System.out.println("4.Sửa thông tin lịch sử khách hàng");
		System.out.println("5.Sắp xếp khách hàng theo số tiền chi trả (bé đến lớn)");
		System.out.println("6.Trở về menu chính");
		System.out.print("Chọn: ");
		int selected = key.nextInt();
		switch(selected) {
		case 1:
			this.addCtm();
			break;
		case 2:
			this.showCtms();
			this.qlkhachhang();
			break;
		case 3:
			this.delAllCtm();
			break;
		case 4:
			this.editCtm();
			break;
		case 5:
			this.sortCtms();
		case 6:
			this.mainMenu();
			break;
		default:
			this.qlnv();
			break;
		}
	}
	public void qlmenu() {
		System.out.println("==Quản lí thực đơn==");
		System.out.println("1.Thêm món ăn mới");
		System.out.println("2.Hiển thị danh sách món ăn");
		System.out.println("3.Xóa món ăn");
		System.out.println("4.Sửa thông tin món ăn");
		System.out.println("5.Sắp xếp thực đơn theo giá (bé đến lớn)");
		System.out.println("6.Trở về menu chính");
		System.out.print("Chọn: ");
		int selected = key.nextInt();
		switch(selected) {
		case 1:
			this.addFoodToMenu();
			break;
		case 2:
			this.showList();
			this.qlmenu();
			break;
		case 3:
			this.deleteFoodInList();
			break;
		case 4:
			this.editFoodInList();
			break;
		case 5:
			this.sortFoods();
			break;
		case 6:
			this.mainMenu();
			break;
		default:
			this.qlmenu();
			break;
		}
	}
	
	public void sayBye() {
		System.out.println("Tạm biệt...");
	}
	public void mainMenu() {
		System.out.println("===Quản lí nhà hàng===");
		System.out.println("1.Quản lí nhân viên");
		System.out.println("2.Quản lí khách hàng");
		System.out.println("3.Quản lí bàn");
		System.out.println("4.Quản lí món ăn");
		
		System.out.println("5.Thoát");
		System.out.print("Chọn: ");
		int selected = this.key.nextInt();
		switch(selected) {
		case 1:
			this.qlnv();
			break;
		case 2:
			this.qlkhachhang();
			break;
		case 3:
			this.qlban();
			break;
		case 4:
			this.qlmenu();
			break;
		case 5:
			this.sayBye();
			break;
		default:
			this.mainMenu();
			break;
		}
	}
	public static void main(String...args) {
		new Admin();
	}
}
