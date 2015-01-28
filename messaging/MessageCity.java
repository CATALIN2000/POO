package messaging;

import java.util.ArrayList;
import java.util.HashSet;

import components.*;

public class MessageCity extends MessageCenter{
	
	public HashSet<Integer> hash = new HashSet<Integer>();

	public ArrayList<Component> mcity = new ArrayList<Component>();
	public ArrayList<MessageCity> vecini = new ArrayList<MessageCity>();
	
	//constructor MessageCity
	public MessageCity(String centerName) {
		super(centerName);
	}
	//aplicarea pseudocodului de publishAlgorithm
	public Message publishAlgorithm(Message message){
		if(this.hash.contains(message.getId())){
			return null;
		}
		this.hash.add(message.getId());
		for(Component c: this.mcity)
			if(message.getTaskType().equals(c.getTaskType()))
				return c.notify(message);

		for(MessageCity v: this.vecini){
			
			Message mes = v.publish(message);
			if(mes != null){
				return mes;
			}
		}
	
		return null;
	}
	
	//un too string care imi intoarce vecinii
	public String toString(){
		String pen = this.getName() + "Are vecini pe:";
		for(MessageCity v: this.vecini){
			pen += v.getName()+" ";	
		}	
		return pen;
	}

}
