package Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.ArticleController;
import controller.Controller;
import controller.MemberController;
import dto.Articles;
import dto.Member;
import util.Util;

public class App {
	private static List<Articles> list;
	public static List<Member> members;
	public App() {
		list = new ArrayList<Articles>();
		members = new ArrayList<>();
	}

	public void start() {
		System.out.println("====	프로그램 시작	====");
		Scanner sc = new Scanner(System.in);
		MemberController membercontroller = new MemberController(sc, members);
		ArticleController articlecontroller = new ArticleController(sc, list);
		articlecontroller.writeTestarticles();
		while (true) {
			System.out.print("명령어 입력 )");

			String command = sc.nextLine();

			command = command.trim();

			System.out.printf("입력된 명령어 : %s\n", command);
			Controller controller = null; 
			if (command.equals("system exit")) {
				break;
			} 
			String[] commandBits = command.split(" ");
			if(commandBits.length == 1) {
				System.out.println("존재하지 않는 명령어입니다.");
				continue;
			}
			String controllername = commandBits[0];
			String actionMethodname = commandBits[1];
			if(controllername.equals("article")) {
				controller = articlecontroller;
			}else if (controllername.equals("member")) {
				controller = membercontroller;
			}
			
			controller.doAction(command, actionMethodname);

		}
		sc.close();
		System.out.println("==== 프로그램 종료 ====");
	}

	

}
