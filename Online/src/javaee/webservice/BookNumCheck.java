package javaee.webservice;

public class BookNumCheck {

	public BookNumCheck() {
		// TODO Auto-generated constructor stub
	}
	public boolean Check(String booknum){
		if(!booknum.matches("[0-9]{3}-[0-9]{1}-[0-9]{4}-[0-9]{4}-[0-9]{1}"))
			return false;
		else{
			int checkNum=0;
			int total = 0;
			for(int i=0; i<booknum.length(); i++){
				int n = booknum.charAt(i)-'0';
				if(i%2==0){
					total+=n*3;
				}else{
					total+=n;
				}
			}
			checkNum=(10-total%10)%10;
			if(booknum.charAt(12)-'0'==checkNum){
				return true;
			}else{
				return false;
			}
		}
	}
	public String normalize(String booknum){
		StringBuffer ans = new StringBuffer();
		String g = "-";
		ans.append("ISBN");
		ans.append(booknum.substring(0, 3));
		ans.append(g);
		ans.append(booknum.charAt(3));
		ans.append(g);
		ans.append(booknum.substring(4,8));
		ans.append(g);
		ans.append(booknum.substring(8,12));
		ans.append(g);
		ans.append(booknum.charAt(12));
		return ans.toString();
	}
}
