package Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import util.Util;
import dto.Articles;

public class Main {
	public static void main(String[] args) {
		System.out.println("====	프로그램 시작	====");
		
		int lastarticleId = 0;
		
		List<Articles> list = new ArrayList<Articles>();

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

			} else if (command.equals("article list")) {
				if (list.size() == 0) {
					System.out.println("현재 등록된 게시물이 없습니다.");
				}
				if (list.size() != 0) {
					System.out.printf("현재 %d개의 게시물이 있습니다.\n", lastarticleId);
					System.out.printf("		번호		|		제목		|		내용		|		등록시간		|		조회수				\n");

					for (int i = list.size()-1; i >=0 ; i--) {
						Articles article = list.get(i);

						System.out.printf("		%d		|		%s		|		%s		|		%s		|		%d\n", article.id, article.title, article.body,article.regDate, article.viewed );
								
					}
				}

			} else if (command.startsWith("article detail")) {
				String[]commandBits = command.split(" ");
				int getId = Integer.parseInt(commandBits[2]);
				
				Articles fdarticle = null;
				
				
				for (int i = 0; i < list.size(); i++) {
					fdarticle = list.get(i);
					
					if (fdarticle.id == getId) {
						fdarticle.increseviewed();
						System.out.printf(" 번호) : %d\n", fdarticle.id);
						System.out.printf(" 제목) : %s\n", fdarticle.title);
						System.out.printf(" 내용) : %s\n", fdarticle.body);
						System.out.printf(" 등록시간) : %s\n", fdarticle.regDate);
						System.out.printf(" 조회수) : %s\n", fdarticle.viewed);
						break;
						
					}
				}if (fdarticle.id != getId) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n",getId);
				}
				

			}

			else {
				System.out.printf("%s(은)는 올바른 명령어가 아닙니다.\n", command);
			}

		}

		sc.close();

		System.out.println("====	프로그램 끝		====");
	
	}

	
		
	}





