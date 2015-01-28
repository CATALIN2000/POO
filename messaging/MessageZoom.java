package messaging;

import types.TaskType;

public class MessageZoom extends MessageImage {
	private int l0,c0,ln,cn;
	//construcotr zoom
	public MessageZoom(TaskType taskType, int[][][] pixels,
			int width, int height, int c0, int l0, int cn, int ln) {
		super(taskType, pixels, width, height);
		this.setC0(c0);
		this.setL0(l0);
		this.setCn(cn);
		this.setLn(ln);
	}
	
	public MessageZoom(TaskType taskType) {
		super(taskType);
	}
	//getter valoare ultima linie
	public int getLn() {
		return ln;
	}
	//setter valoare ultima linie
	public void setLn(int ln) {
		this.ln = ln;
	}
	//getter valoare ultima coloana
	public int getCn() {
		return cn;
	}
	//setter valoare ultima coloana
	public void setCn(int cn) {
		this.cn = cn;
	}
	//getter prima linie de unde porneste zoom
	public int getL0() {
		return l0;
	}
	//setter prima linie de unde porneste zoom
	public void setL0(int l0) {
		this.l0 = l0;
	}
	//getter prima coloana de unde porneste zoom
	public int getC0() {
		return c0;
	}
	//setter prima coloana de unde porneste zoom
	public void setC0(int c0) {
		this.c0 = c0;
	}
}
