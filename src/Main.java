import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("====	프로그램 시작	====");
		int id =0;
		String title;
		String body;
		System.out.println("명령어 입력 )");

		Scanner sc = new Scanner(System.in);

		while (true) {

			String command = sc.nextLine();

			command = command.trim();

			System.out.printf("입력된 명령어 : %s\n", command);

			if (command.equals("system exit")) {
				break;
			} else if (command.equals("article write")) {
				int lastarticle = 0;
				lastarticle++;
				id = lastarticle;
				System.out.println("제목 ) ");
				title = sc.nextLine();
				System.out.println("내용 ) ");
				body = sc.nextLine();
			}

		}

		sc.close();

		System.out.println("====	프로그램 끝		====");

	}
}
