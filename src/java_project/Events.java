package java_project;

public class Events {
	private String ename,edet,edate,eorg;
	public Events(String e1,String e2,String e3, String e4)
	{
		ename=e1;
		edet=e2;
		edate=e3;
		eorg=e4;
	}
	public String getEname()
	{
		return ename;
	}
	public String getEdet()
	{
		return edet;
	}
	public String getEdate()
	{
		return edate;
	}
	public String getEorg()
	{
		return eorg;
	}
}
