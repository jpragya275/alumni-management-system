package java_project;

public class Msg {
	private String sender,msgs;
	public Msg(String s, String m)
	{
		sender=s;
		msgs=m;
	}
	
	public String getSender()
	{
		return sender;
	}
	public String getMsg()
	{
		return msgs;
	}
}
