package components;

import messaging.*;
import types.FlashType;
import types.TaskType;

public class Flash extends Component {
	//construcor pentru flash
	public Flash() {
		super(TaskType.FLASH);
	}
	@Override
	public Message notify(Message message) {
		MessageFlash flash = (MessageFlash) message;
		MessageImage messageImage = new MessageImage(null);
		//construirea unor vectori ajutatori
		int[][][] pixels = flash.getPixels();
		int[][] L = new int[flash.getHeight()][flash.getWidth()];
		int slight = 0;
		//setarea imaginii
		messageImage.setHeight(flash.getHeight());
		messageImage.setWidth(flash.getWidth());
		messageImage.setPixels(pixels);
		if(flash.getFlash().equals(FlashType.ON)){
			//daca este on va seta 50 + luminozitate
			for(int i = 0; i < flash.getHeight(); i++) {
				for(int j = 0; j < flash.getWidth(); j++) {
					for(int k = 0; k < 3; k++) {
						pixels[i][j][k] = pixels[i][j][k] + 50;
						if(pixels[i][j][k] > 255){
							pixels[i][j][k] = 255;
						}
					}
				}	
			}
		}
		else if(flash.getFlash().equals(FlashType.AUTO)){
			//calcularea luminozitatii medie si stabilirea 50+ daca 
			//luminozitatea este mai mare
			for(int i = 0; i < flash.getHeight(); i++) {
				for(int j = 0; j < flash.getWidth(); j++) {
					L[i][j] = (int) Math.round(0.2126*pixels[i][j][0]
							+ 0.7152*pixels[i][j][1] + 0.0722*pixels[i][j][2]);
					slight += L[i][j];
				}	
			}
			int mlight = slight/(flash.getHeight()*flash.getWidth());
			if(mlight < 60){
				//daca lumina medie mai mica decat 60 aplicam schimbarea
				for(int i = 0; i < flash.getHeight(); i++) {
					for(int j = 0; j < flash.getWidth(); j++) {
						for(int k = 0; k < 3; k++) {
							pixels[i][j][k] = pixels[i][j][k] + 50;
							//daca pixel depaseste valoarea de 255 il setam la 255
							if(pixels[i][j][k] > 255){
								pixels[i][j][k] = 255;
							}
						}
					}	
				}
			}
		}
		else if(flash.getFlash().equals(FlashType.OFF)){
			//Trololo
		}
		//returnarea imaginii
		return messageImage;
	}

}
