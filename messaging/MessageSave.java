package messaging;

import types.TaskType;

public class MessageSave extends MessageImage {
	private String path;
	
	public MessageSave(TaskType taskType, int messageId) {
		super(taskType);
	}
	//constructor MessageSave
	public MessageSave(TaskType taskType, int[][][] pixels,
			int width, int height, String path) {
		super(taskType, pixels, width, height);
		this.path = path;
	}
	//getter path
	public String getPath() {
		return path;
	}
	//setter path
	public void setPath(String path) {
		this.path = path;
	}
}
