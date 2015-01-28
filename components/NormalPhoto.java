package components;

import messaging.Message;
import messaging.MessageImage;
import types.TaskType;

public class NormalPhoto extends Component {
	//constructor NormalPhoto
	public NormalPhoto() {
		super(TaskType.NORMAL_PHOTO);
	}

	@Override
	public Message notify(Message message) {
		MessageImage normal = (MessageImage) message;
		MessageImage messageImage = new MessageImage(null);
		//crearea de vectori ajutatori
		int[][][] pix = normal.getPixels();
		//setarea imaginii
		messageImage.setHeight(normal.getHeight());
		messageImage.setWidth(normal.getWidth());
		
		//rotirea matrcei 
		for(int i = 0; i < normal.getHeight()/2; i++) {
			for(int j = 0; j < normal.getWidth(); j++) {
				for(int k = 0; k < 3; k++) {
					int aux = pix[i][j][k];
					pix[i][j][k] = pix[normal.getHeight() - i - 1][j][k];
					pix[normal.getHeight() - i - 1][j][k] = aux;
				}
			}	
		}
		messageImage.setPixels(pix);
		//returnarea imaginii
		return messageImage;				
	}
}