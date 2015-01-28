package messaging;

import types.TaskType;

//import java.util.Random;
//import java.lang.*;

public abstract class Message {
	private TaskType taskType;
	private int messageId;
	private static int id=0;
	
	public Message(TaskType taskType) {
		super();
		this.taskType = taskType;
		generateId();
	}
	
	public void generateId() {
		//generearea unui id unic
		id++;
		this.messageId = id;
		//Random r = new Random();
		//this.messageId = r.nextInt(1000000);
		
	}
	
	public void setTaskType(TaskType taskType) {
		this.taskType = taskType;
	}

	public TaskType getTaskType() {
		return taskType;
	}

	public int getId() {
		return messageId;
	}
	
	
}
