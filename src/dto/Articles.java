package dto;

public class Articles extends Dto {
	
		public String title;
		public String body;
		public int viewed;

		public Articles(int id, String title, String body, String regDate) {
			this.id = id;
			this.title = title;
			this.body = body;
			this.regDate = regDate;
			
			
		}
		public Articles(int id, String title, String body, String regDate, int viewed) {
			this.id = id;
			this.title = title;
			this.body = body;
			this.regDate = regDate;
			this.viewed = viewed; 
		}
		public void increseviewed() {
			viewed++;
		}


	}


