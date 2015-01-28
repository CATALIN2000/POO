package components;

import messaging.Message;
import messaging.MessageImage;
import types.TaskType;

public class RawPhoto extends Component {
	//constructor RawPhoto
	public RawPhoto() {
		super(TaskType.RAW_PHOTO);
	}

	@Override
	public Message notify(Message message) {
		MessageImage raw = (MessageImage) message;
		MessageImage messageImage = new MessageImage(null);
		//crearea unor vectori ajutatori
		int[][][] pix = raw.getPixels();
		//setarea imaginii
		messageImage.setHeight(raw.getHeight());
		messageImage.setWidth(raw.getWidth());
		
		//intoarcerea matricei
		for(int i = 0; i < raw.getHeight()/2; i++) {
			for(int j = 0; j < raw.getWidth(); j++) {
				for(int k = 0; k < 3; k++) {
					int aux = pix[i][j][k];
					pix[i][j][k] = pix[raw.getHeight() - i - 1][j][k];
					pix[raw.getHeight() - i - 1][j][k] = aux;
				}
			}	
		}
		messageImage.setPixels(pix);
		//returnarea imaginii
		return messageImage;				
	}
}
