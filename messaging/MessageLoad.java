package messaging;

import types.TaskType;

public class MessageLoad extends Message {

	private String path;
	//getter Path
	public String getPath() {
		return path;
	}
	//setter Path
	public void setPath(String path) {
		this.path = path;
	}
	//constructor MessageLoad
	public MessageLoad(TaskType taskType, String path) {
		super(taskType);
		this.path = path;
	}

}
