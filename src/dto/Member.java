package dto;

public class Member extends Dto{
	
	public String signupId;
	public String signupPw;
	public String name;
	
	public Member(int id, String signupId, String signupPw, String regDate, String name){
		this.id = id;
		this.signupId = signupId;
		this.signupPw = signupPw;
		this.regDate = regDate;
		this.name = name;
		
	}

}