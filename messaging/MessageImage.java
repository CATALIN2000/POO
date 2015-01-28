package messaging;

import types.TaskType;

public class MessageImage extends Message {

	private int[][][] pixels;
	private int width, height;
	//constructor messageimage
	public MessageImage(TaskType taskType, int[][][] pixels,
			int width, int height) {
		super(taskType);
		this.pixels = pixels;
		this.width = width;
		this.height = height;
	}
	
	public MessageImage(TaskType taskType) {
		super(taskType);
	}
	//getter pixels
	public int[][][] getPixels() {
		return pixels;
	}
	//setter pixels
	public void setPixels(int[][][] pixels) {
		this.pixels = pixels;
	}
	//getter Height
	public int getHeight() {
		return height;
	}
	//setter Height
	public void setHeight(int height) {
		this.height = height;
	}
	//getter Width
	public int getWidth() {
		return width;
	}
	//setter Width
	public void setWidth(int width) {
		this.width = width;
	}
}
