package Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.ArticleController;
import controller.MemberController;
import dto.Articles;
import dto.Member;
import util.Util;

public class App {
	private static List<Articles> list;
	public static List<Member> members;
	static {
		list = new ArrayList<Articles>();
		members = new ArrayList<>();
	}

	public void start(){
		System.out.println("====	프로그램 시작	====");
		Scanner sc = new Scanner(System.in);
		writeTestarticles();
		MemberController membercontroller = new MemberController(sc, members);
		ArticleController articlecontroller = new ArticleController(sc, list);
		

		int lastarticleId = list.size();
		int lastmemberId = members.size();

		

		while (true) {
			System.out.print("명령어 입력 )");

			String command = sc.nextLine();

			command = command.trim();

			System.out.printf("입력된 명령어 : %s\n", command);

			if (command.equals("system exit")) {
				break;
			} else if (command.equals("article write")) {
				articlecontroller.dowrite();
			} else if (command.startsWith("article list")) {
				articlecontroller.showlist(command);
			}

			else if (command.startsWith("article detail ")) {
				articlecontroller.showdetail(command);

			} else if (command.startsWith("article modify ")) {
				articlecontroller.domodify(command);
				

			} else if (command.startsWith("article delete ")) {
				articlecontroller.doDelete(command);
				}else if (command.equals("signup")) {
					membercontroller.doSignup();
						}
					
				
			 else {
				System.out.printf("%s(은)는 올바른 명령어가 아닙니다.\n", command);
			}

		}
		sc.close();
		System.out.println("==== 프로그램 종료 ====");
	}
	
	
	private static void writeTestarticles() {
		System.out.println("테스트를 위한 데이터를 생성합니다.");

		list.add(new Articles(1, "title 1", "body 1", Util.getNowDatestr(), 11));
		list.add(new Articles(2, "title 2", "body 1", Util.getNowDatestr(), 22));
		list.add(new Articles(3, "title 3", "body 1", Util.getNowDatestr(), 33));
	}

}


