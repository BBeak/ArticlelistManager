import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("====	프로그램 시작	====");
		int lastarticleId = 0;
		String title;
		String body;
		List<Articles> list = new ArrayList<Articles>();

		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("명령어 입력 )");

			String command = sc.nextLine();

			command = command.trim();

			System.out.printf("입력된 명령어 : %s\n", command);

			if (command.equals("system exit")) {
				break;
			} else if (command.equals("article write")) {
				int id = lastarticleId + 1;

				lastarticleId = id;

				System.out.println("제목 ) ");
				title = sc.nextLine();
				System.out.println("내용 ) ");
				body = sc.nextLine();
				System.out.printf("%d번째 게시물이 작성되었습니다.\n", id);
				list.add(new Articles(id, title, body));

			} else if (command.equals("article list") && lastarticleId != 0) {
				System.out.printf("현재 %d개의 게시물이 있습니다.\n", lastarticleId);
				System.out.printf("		번호		|		제목		|		내용		|\n");

				for (int i = 0; i < list.size(); i++) {

					System.out.printf("		%d		|		%s		|		%s		|\n", list.get(i).getId(),
							list.get(i).getTitle(), list.get(i).getbody());
				}

			} else if (command.equals("article list") && lastarticleId == 0) {
				System.out.printf("현재 게시물이 없습니다.\n");
			} else if (command.equals("search")) {
				System.out.printf("검색을 원하는 게시물의 id를 입력해주세요");
				int num = sc.nextInt();
				System.out.printf("		%d		|		%s		|		%s		\n", list.get(num - 1).getId(),
						list.get(num - 1).getTitle(), list.get(num - 1).getbody());

			}

			else {
				System.out.printf("%s(은)는 올바른 명령어가 아닙니다.\n", command);
			}

		}

		sc.close();

		System.out.println("====	프로그램 끝		====");

	}
}

class Articles {
	private int id;
	private String title;
	private String body;

	Articles(int id, String title, String body) {
		this.id = id;
		this.title = title;
		this.body = body;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getbody() {
		return body;
	}

}
