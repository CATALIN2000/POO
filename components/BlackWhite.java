package components;

import messaging.Message;
import messaging.MessageImage;
import types.TaskType;

public class BlackWhite extends Component {
	//constructor pentru BlackWhite
	public BlackWhite() {
		super(TaskType.BLACK_WHITE);
	}

	@Override
	public Message notify(Message message) {
		MessageImage blackwhite = (MessageImage) message;
		MessageImage messageImage = new MessageImage(null);
		
		int[][][] pixels = blackwhite.getPixels();
		//setarea messageImage
		messageImage.setHeight(blackwhite.getHeight());
		messageImage.setWidth(blackwhite.getWidth());
		messageImage.setPixels(pixels);
		
		for(int i = 0; i < blackwhite.getHeight(); i++) {
			for(int j = 0; j < blackwhite.getWidth(); j++) {
				//calcularea valorilor de rosu verde si albastru
				int red = (int) Math.round( (pixels[i][j][0] * 0.3)
						+ (pixels[i][j][1] * 0.59) + (pixels[i][j][2] * 0.11) );
				int green = (int) Math.round( (pixels[i][j][0] * 0.3)
						+ (pixels[i][j][1] * 0.59) + (pixels[i][j][2] * 0.11) );
				int blue = (int) Math.round( (pixels[i][j][0] * 0.3)
						+ (pixels[i][j][1] * 0.59) + (pixels[i][j][2] * 0.11) );
				//setarea valorilor de rosu verde si albastru
				pixels[i][j][0] = red;
				pixels[i][j][1] = green;
				pixels[i][j][2] = blue;
				//daca pixelul depaseste 255 il setam la 255
				for(int k = 0; k < 3; k++){
					if(pixels[i][j][k] > 255){
						pixels[i][j][k] = 255;
					}
				}
			}	
		}
		//retrunarea imaginilor
		return messageImage;				
	}
}