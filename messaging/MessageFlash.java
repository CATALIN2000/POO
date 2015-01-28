package messaging;

import types.FlashType;
import types.TaskType;

public class MessageFlash extends MessageImage{
	
	private FlashType flash;
	//constructor flash
	public MessageFlash(TaskType taskType, FlashType flashType, int[][][] pixels,
			int width, int height) {
		super(taskType, pixels, width, height);
		this.flash = flashType;
	}
	
	public MessageFlash(TaskType taskType) {
		super(taskType);
	}
	//getter flash
	public FlashType getFlash() {
		return flash;
	}
	//setter flash
	public void setFlash(FlashType flash) {
		this.flash = flash;
	}

}
