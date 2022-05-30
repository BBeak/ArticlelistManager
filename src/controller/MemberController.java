package controller;

import java.util.List;
import java.util.Scanner;

import dto.Member;
import util.Util;

public class MemberController extends Controller {
	private List<Member>members;
	private Scanner sc;
	private int lastmemberId;
		
	

	public MemberController(Scanner sc, List<Member> members) {
		this.sc = sc;
		this.members = members;
	}
	public void doSignup() {
		while(true) {
			System.out.print("Id를 입력해주세요 )");
			String signInId = sc.nextLine();
			checkAbleID(signInId);
			System.out.print("이름을 입력해주세요 )");
			String name = sc.nextLine();
			System.out.print("Pw를 입력해주세요 )");
			String signInPw = sc.nextLine();
			String regdate = Util.getNowDatestr();
			System.out.print("Pw를 다시 입력해주세요 )");
			String confirmPw = sc.nextLine();
			if(signInPw.equals(confirmPw)) {
				int lastId = members.size()+1;
				System.out.printf("%s님 회원 가입을 축하드립니다. regdate : %s", signInId, regdate );
				Member member = new Member(lastId, signInId, signInPw, regdate, name);
				members.add(member);
				break;
			}else {
				System.out.print("Pw가 일치하지 않습니다\n");
				continue;
	}
		}

}
	private void checkAbleID(String signInId) {
		for (Member member : members) {
			while(true) {
			if (member.signupId == signInId ) {
				System.out.printf("%s는 사용이 가능한 ID입니다.\n", signInId);
				break;
			}else {
				System.out.printf("%s는 이미 사용중인 ID입니다.");
				continue;
			}
		}
		}
		
		
	}
}