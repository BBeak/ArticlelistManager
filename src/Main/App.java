package Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

		writeTestarticles();

		int lastarticleId = list.size();
		int lastmemberId = members.size();

		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.print("명령어 입력 )");

			String command = sc.nextLine();

			command = command.trim();

			System.out.printf("입력된 명령어 : %s\n", command);

			if (command.equals("system exit")) {
				break;
			} else if (command.equals("article write")) {
				int id = lastarticleId + 1;

				lastarticleId = id;

				System.out.print("제목 ) ");
				String title = sc.nextLine();
				System.out.print("내용 ) ");
				String body = sc.nextLine();
				String regDate = Util.getNowDatestr();
				Articles article = new Articles(id, title, body, regDate);
				list.add(article);
				System.out.printf("%d번째 게시물이 작성되었습니다.\n", id);

			} else if (command.startsWith("article list")) {

				String searchKeyword = command.substring("article list".length()).trim();
				List<Articles> forListArticles = list;

				if (searchKeyword.length() > 0) {
					forListArticles = new ArrayList<>();
					for (Articles article : list) {
						if (article.title.contains(searchKeyword)) {
							forListArticles.add(article);
						}

					}
				}
				if (list.size() == 0) {
					System.out.print("현재 등록된 게시물이 없습니다. \n");
					continue;
				}
				System.out.printf("	제목 |  제목   |  조회수  |  등록시간  \n");

				for (int i = forListArticles.size() - 1; i >= 0; i--) {
					Articles article = forListArticles.get(i);

					System.out.printf("%11d  |%6s  |%7d  |%10s  \n", article.id, article.title, article.viewed,
							article.regDate);
				}

			}

			else if (command.startsWith("article detail ")) {
				String[] commandBits = command.split(" ");
				int getId = Integer.parseInt(commandBits[2]);
				
				Articles fdarticle = getArticleById(getId);
				fdarticle.increseviewed();
				System.out.printf(" 번호) : %d\n", fdarticle.id);
				System.out.printf(" 제목) : %s\n", fdarticle.title);
				System.out.printf(" 내용) : %s\n", fdarticle.body);
				System.out.printf(" 등록시간) : %s\n", fdarticle.regDate);
				System.out.printf(" 조회수) : %s\n", fdarticle.viewed);
				if (fdarticle.id != getId) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", getId);
				}

			} else if (command.startsWith("article modify")) {
				String[] commandBits = command.split(" ");
				int getId = Integer.parseInt(commandBits[2]);

				Articles fdarticle = getArticleById(getId);
				
					if (fdarticle.id == getId) {
						fdarticle.increseviewed();
						System.out.printf(" 제목) : \n");
						String title = sc.nextLine();
						System.out.printf(" 내용) : \n");
						String body = sc.nextLine();

						fdarticle.title = title;
						fdarticle.body = body;

					}
				

			} else if (command.startsWith("article delete ")) {
				String[] commandBits = command.split(" ");
				int getId = Integer.parseInt(commandBits[2]);
				int foundIndex = getid(getId);
				if (foundIndex == -1) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n",getId);
				}
				System.out.printf("%d번 게시물을 삭제하였습니다.\n", getId);
				list.remove(foundIndex);
				
				}else if (command.equals("signup")) {
					
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
						if(signInPw == confirmPw) {
							System.out.printf("%s님 회원 가입을 축하드립니다. regdate : %s", signInId, regdate );
							Member member = new Member(lastmemberId, signInId, signInPw, regdate, name);
							members.add(member);
							break;
						}else {
							System.out.print("Pw가 일치하지 않습니다\n");
							continue;
						}
					}
				}
			 else {
				System.out.printf("%s(은)는 올바른 명령어가 아닙니다.\n", command);
			}

		}
		sc.close();
		System.out.println("==== 프로그램 종료 ====");
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
	private static int getid(int id) {
		int i = 0; 
		for (Articles article : list) {
			if (article.id == id) {
				return i;
			}
			i++;
		}
		return -1;
	}
	private Articles getArticleById(int id) {
		Articles fdarticle = null;
		
		int index = getid(id);
		if (index != -1) {
			fdarticle = list.get(index);
			return fdarticle;
		}
//		for (int i = 0; i < list.size(); i++) {
//			fdarticle = list.get(i);
//
//			if (fdarticle.id == id) {
//				fdarticle.increseviewed();
//				fdarticle = list.get(i);
//				return fdarticle;
//			}
//		}
		
		return null;
	}
	private static void writeTestarticles() {
		System.out.println("테스트를 위한 데이터를 생성합니다.");

		list.add(new Articles(1, "title 1", "body 1", Util.getNowDatestr(), 11));
		list.add(new Articles(2, "title 2", "body 1", Util.getNowDatestr(), 22));
		list.add(new Articles(3, "title 3", "body 1", Util.getNowDatestr(), 33));
	}

}


