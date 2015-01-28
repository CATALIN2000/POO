package messaging;

//import java.util.HashSet;

public abstract class MessageCenter {
	private String centerName;
		
	public MessageCenter(String centerName) {
		super();
		this.centerName = centerName;
	}

	public Message publish(Message message)	{
		System.out.println(centerName);
		//System.out.println(message.getTaskType());
		return publishAlgorithm(message);
	}
	
	public String getName(){
		return centerName;
	}
	
	protected abstract Message publishAlgorithm(Message message);
	
}
