package components;

import messaging.Message;
import messaging.MessageImage;
import types.TaskType;

public class Sepia extends Component {
	//constructor sepia
	public Sepia() {
		super(TaskType.SEPIA);
	}

	@Override
	public Message notify(Message message) {
		MessageImage sepia = (MessageImage) message;
		MessageImage messageImage = new MessageImage(null);
		//crearea unui vector ajutator
		int[][][] pixels = sepia.getPixels();
		//setarea imaginii
		messageImage.setHeight(sepia.getHeight());
		messageImage.setWidth(sepia.getWidth());
		messageImage.setPixels(pixels);
		
		for(int i = 0; i < sepia.getHeight(); i++) {
			for(int j = 0; j < sepia.getWidth(); j++) {
				int red =  (int) Math.round( (pixels[i][j][0] * 0.393)
						+ (pixels[i][j][1] * 0.769) + (pixels[i][j][2] * 0.189) );
				int green = (int) Math.round( (pixels[i][j][0] * 0.349)
						+ (pixels[i][j][1] * 0.686) + (pixels[i][j][2] * 0.168) );
				int blue = (int) Math.round( (pixels[i][j][0] * 0.272)
						+ (pixels[i][j][1] * 0.534) + (pixels[i][j][2] * 0.131) );
				
				pixels[i][j][0] = red;
				pixels[i][j][1] = green;
				pixels[i][j][2] = blue;
				for(int k = 0; k < 3; k++){
					if(pixels[i][j][k] > 255){
						pixels[i][j][k] = 255;
					}
				}
			}	
		}
		
		return messageImage;				
	}
}