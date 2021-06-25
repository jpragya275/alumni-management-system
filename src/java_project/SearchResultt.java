package java_project;

public class SearchResultt {
		private String name, course,year_of_adm,year_of_pass;
		public SearchResultt(String n,String c,String y1,String y2)
		{
			name=n;
			course=c;
			year_of_adm=y1;
			year_of_pass=y2;
		}
		public String getName()
		{
			return name;
		}
		public String getyoa()
		{
			return year_of_adm;
		}
		public String getyop()
		{
			return year_of_pass;
		}
		public String getCourse()
		{
			return course;
		}
	/*	public String getWorking_at()
		{
			return working_at;
		}*/
}


