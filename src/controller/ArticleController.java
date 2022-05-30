package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dto.Articles;
import util.Util;

public class ArticleController extends Controller {
	private static List<Articles>list;
	private Scanner sc;
	private String command;
	private String actionMethodName;
	public ArticleController(Scanner sc, List<Articles> list) {
		this.list = list;
		this.sc = sc;
		
	}
	public void doAction(String command, String actionMethodName) {
		this.command = command;
		this.actionMethodName = actionMethodName;
		switch(actionMethodName) {
		case "list":
			showlist();
			break;
		case "write":
			dowrite();
			break;
		case "delete":
			doDelete();
			break;
		case "modify":
			domodify();
			break;
		case "detail":
			showdetail();
			break;
		
		}
		
	}

	public void dowrite() {
		int id = list.size() + 1;
		System.out.print("제목 ) ");
		String title = sc.nextLine();
		System.out.print("내용 ) ");
		String body = sc.nextLine();
		String regDate = Util.getNowDatestr();
		Articles article = new Articles(id, title, body, regDate);
		list.add(article);
		System.out.printf("%d번째 게시물이 작성되었습니다.\n", id);

		
	}

	public void showlist() {
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
			
		}
		System.out.printf("	제목 |  제목   |  조회수  |  등록시간  \n");

		for (int i = forListArticles.size() - 1; i >= 0; i--) {
			Articles article = forListArticles.get(i);

			System.out.printf("%11d  |%6s  |%7d  |%10s  \n", article.id, article.title, article.viewed,
					article.regDate);
		}
		
	}

	public void showdetail() {
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
		
	}

	public void domodify() {
		String[] commandBits = command.split(" ");
		int getId = Integer.parseInt(commandBits[2]);

		Articles fdarticle = getArticleById(getId);
		
			if (fdarticle.id == getId) {
				fdarticle.increseviewed();
				System.out.printf(" 제목) : ");
				String title = sc.nextLine();
				System.out.printf(" 내용) : ");
				String body = sc.nextLine();

				fdarticle.title = title;
				fdarticle.body = body;

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

		return null;
	}

	public void doDelete() {
		String[] commandBits = command.split(" ");
		int getId = Integer.parseInt(commandBits[2]);
		int foundIndex = getid(getId);
		if (foundIndex == -1) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n",getId);
		}
		System.out.printf("%d번 게시물을 삭제하였습니다.\n", getId);
		list.remove(foundIndex);
		
		
	}
	public void writeTestarticles() {
		System.out.println("테스트를 위한 데이터를 생성합니다.");

		list.add(new Articles(1, "title 1", "body 1", Util.getNowDatestr(), 11));
		list.add(new Articles(2, "title 2", "body 1", Util.getNowDatestr(), 22));
		list.add(new Articles(3, "title 3", "body 1", Util.getNowDatestr(), 33));
	}

	
	

}
