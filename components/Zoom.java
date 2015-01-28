package components;

import messaging.*;
import types.TaskType;

public class Zoom extends Component {
	//constructor zoom
	public Zoom() {
		super(TaskType.ZOOM);
	}
	@Override
	public Message notify(Message message) {
		MessageZoom zoom = (MessageZoom) message;
		MessageImage messageImage = new MessageImage(null);
		//crarea unor vectori ajutatori
		int[][][] zoomed = zoom.getPixels();
		int[][][] pixels = new int[zoom.getLn() - zoom.getL0()+1][zoom.getCn() - zoom.getC0()+1][3];
		//setarea imaginii
		messageImage.setHeight(zoom.getLn() - zoom.getL0()+1);
		messageImage.setWidth(zoom.getCn() - zoom.getC0()+1);
		messageImage.setPixels(pixels);
		//zoom pe portiunea aleasa
		for(int i = 0; i < zoom.getLn() - zoom.getL0()+1; i++) {
			for(int j = 0; j < zoom.getCn() - zoom.getC0()+1; j++) {
				for(int k = 0; k < 3; k++) {
					pixels[i][j][k] = zoomed[i + zoom.getL0()][j + zoom.getC0()][k];
				}
			}	
		}
		//returnarea imaginii
		return messageImage;
	}

}
