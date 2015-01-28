package components;

import messaging.Message;
import messaging.MessageImage;
import types.TaskType;

public class Blur extends Component {
	//constructor pentru blur
	public Blur() {
		super(TaskType.BLUR);
	}

	@Override
	public Message notify(Message message) {
		MessageImage blur = (MessageImage) message;
		MessageImage messageImage = new MessageImage(null);
		int n = blur.getHeight() - 1;
		int m = blur.getWidth() - 1;
		//construirea unor vectori ajutatori
		int[][][] pix = blur.getPixels();
		int[][][] aux = new int[n+1][m+1][3];
		int[][][] bux = pix;//new int[n+1][m+1][3];
		//setarea imaginii
		messageImage.setHeight(blur.getHeight());
		messageImage.setWidth(blur.getWidth());
		
		for(int p = 0; p < 10;p++){
			for(int i = 0; i <= n; i++) {
				for(int j = 0; j <= m; j++) {
					for(int k = 0; k < 3; k++){
						if(n == 0 && m == 0){
							aux[i][j][0] = 0;
							aux[i][j][1] = 0;
							aux[i][j][2] = 0;
						}
						///o singura linie
						else if(n == 0 && m > 0){
							if(j > 0 && j < m){
								float s = pix[0][j-1][k] + pix[0][j+1][k];
								aux[i][j][k] = (int) Math.round(s / 2);
							}
							else if(j == m){
								aux[i][j][k] = pix[0][m-1][k];
							}else{
								aux[i][j][k] = pix[0][1][k];
							}
						}
						///o singura coloana
						else if(n > 0 && m == 0){
							if(i > 0 && i < n){
								float s = pix[i-1][0][k] + pix[i+1][0][k];
								aux[i][j][k] = (int) Math.round(s / 2);
							}
							else if(i == n){
								aux[i][j][k] = pix[n-1][0][k];
							}else{
								aux[i][j][k] = pix[1][0][k];
							}
						}
						//colturile
						else if(i == 0 && j == 0){
							float s = pix[0][1][k] + pix[1][1][k] + pix[1][0][k];
							aux[i][j][k] = (int) Math.round(s / 3);
						}
						else if(i == n && j == m){
							float s = pix[n][m-1][k] + pix[n-1][m-1][k] + pix[n-1][m][k];
							aux[i][j][k] = (int) Math.round(s / 3);
						}
						else if(i == 0 && j == m){
							float s = pix[0][m-1][k] + pix[1][m-1][k] + pix[1][m][k];
							aux[i][j][k] = (int) Math.round(s / 3);
						}
						else if(i == n && j == 0){
							float s = pix[n-1][0][k] + pix[n-1][1][k] + pix[n][1][k];
							aux[i][j][k] = (int) Math.round(s / 3);
						}
						//liniile si coloanele
						else if(i == 0 && j != 0 && j != m){
							float s = pix[0][j-1][k] + pix[0][j+1][k] + pix[1][j-1][k]
									+ pix[1][j][k] + pix[1][j+1][k];
							aux[i][j][k] = (int) Math.round(s / 5);
						}
						else if(i == n && j != 0 && j != m){
							float s = pix[n][j-1][k] + pix[n][j+1][k] + pix[n-1][j-1][k]
									+ pix[n-1][j][k] + pix[n-1][j+1][k];
							aux[i][j][k] = (int) Math.round(s / 5);
						}
						else if(i != 0 && i != n && j == 0){
							float s = pix[i-1][0][k] + pix[i+1][0][k] + pix[i-1][1][k]
									+ pix[i][1][k] + pix[i+1][1][k];
							aux[i][j][k] = (int) Math.round(s / 5);
						}
						else if(i != 0 && i != n && j == m){
							float s = pix[i-1][m][k] + pix[i+1][m][k] + pix[i-1][m-1][k]
									+ pix[i][m-1][k] + pix[i+1][m-1][k];
							aux[i][j][k] = (int) Math.round(s / 5);
						}
						///un punct in matrice
						else{
							float s = pix[i-1][j-1][k] + pix[i-1][j][k] + pix[i-1][j+1][k]
									+ pix[i][j-1][k] + pix[i][j+1][k]
									+ pix[i+1][j-1][k] + pix[i+1][j][k] + pix[i+1][j+1][k];
							aux[i][j][k] = (int) Math.round(s / 8);
						}
					}
				}
			}
			//schimbarea referintei
			pix = aux;
			aux = bux;
			bux = pix;
		}
		messageImage.setPixels(pix);
		//returnarea imaginii
		return messageImage;				
	}
}