import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import components.*;
import types.FlashType;
import types.TaskType;
import messaging.*;


public class SimulationManager {
	private MessageCenter messageCenter;
	
	public SimulationManager(String networkConfigFile) throws FileNotFoundException {
		this.messageCenter = buildNetwork(networkConfigFile);
	}
	
	
	/**
	 * Builds the network of message centers.
	 * @param networkConfigFile configuration file
	 * @return the first message center from the config file
	 * @throws FileNotFoundException 
	 */
	private MessageCenter buildNetwork(String networkConfigFile) throws FileNotFoundException {
		@SuppressWarnings("resource")
		//citire network config
		Scanner s = new Scanner(new FileReader(networkConfigFile));
		ArrayList<MessageCity> city = new ArrayList<MessageCity>();
		String linie = s.nextLine();
		int n = Integer.parseInt(linie);
		//parse integer ca variabila sa devina int
		for (int i = 0; i < n ; i++){
			//construire un string co orasele citite de la tastatura
			String[] oras = s.nextLine().split(" ");
			MessageCity bob = new MessageCity(oras[0]);
			//bob un nou MesageCity de orasul principal
			for(int j = 1; j < oras.length; j++){
				//daca bob are BlackWhite ii adaug componenta
				if(oras[j].equals("BlackWhite")){
					BlackWhite black = new BlackWhite();
					bob.mcity.add(black);
				}
				//daca bob are Blur ii adaug componenta
				else if(oras[j].equals("Blur")){
					Blur blur = new Blur();
					bob.mcity.add(blur);
				}
				//daca bob are Flash ii adaug componenta
				else if(oras[j].equals("Flash")){
					Flash flash = new Flash();
					bob.mcity.add(flash);
				}
				//daca bob are NormalPhoto ii adaug componenta
				else if(oras[j].equals("NormalPhoto")){
					NormalPhoto normal = new NormalPhoto();
					bob.mcity.add(normal);
				}
				//daca bob are RawPhoto ii adaug componenta
				else if(oras[j].equals("RawPhoto")){
					RawPhoto raw = new RawPhoto();
					bob.mcity.add(raw);
				}
				//daca bob are Sepia ii adaug componenta
				else if(oras[j].equals("Sepia")){
					Sepia sepia = new Sepia();
					bob.mcity.add(sepia);
				}
				//daca bob are zoom ii adaug componenta
				else if(oras[j].equals("Zoom")){
					Zoom zoom = new Zoom();
					bob.mcity.add(zoom);
				}
				//daca bob are ImageLoader ii adaug componenta
				else if(oras[j].equals("ImageLoader")){
					ImageLoader load = new ImageLoader();
					bob.mcity.add(load);
				}
				//daca bob are ImageSaver ii adaug componenta
				else if(oras[j].equals("ImageSaver")){
					ImageSaver save = new ImageSaver();
					bob.mcity.add(save);
				}
			}
			//adaug componenta
			city.add(bob);
		}
		//construiesc lista de vecini a fiecarui oras
		for(int i = 0; i < n; i++){
			String[] oras = s.nextLine().split(" ");
			for(int j = 1; j < oras.length; j++){
				//parcurg lista de orase principale
				for(MessageCity c: city){
					if(oras[j].equals(c.getName())){
						//adaug vecinul orasului respectiv
						city.get(i).vecini.add(c);
					}
				}
			}
		}
		
		return city.get(0);
	}
	
	
	/**
	 * Reads the commands from stdin and uses the messageCenter to solve all the tasks
	 */
	public void start() {
		//@SuppressWarnings("resource")

		Scanner s = new Scanner(System.in);
		String[] line = s.nextLine().split(" ");
		while (!line[0].equals("exit")){
			MessageLoad load = new MessageLoad(TaskType.IMAGE_LOAD, line[0]);
			MessageImage image = (MessageImage)this.messageCenter.publish(load);
			
			image.generateId(); //pentru ca utilizam acelasi mesaj image trebuie sa-i generam un nou id
			//spargerea stringului mare in stringuri utile
			String pre = line[2];
			String[] spl1 = pre.split("\\(");
			String[] spl2 = spl1[1].split("\\)");
			String[] spl3 = spl2[0].split(";");
			String[] flash = spl3[0].split("=");
			MessageFlash fl;
			//daca flash on creez un nou flash on
			if(flash[1].equals("on")){
				fl = new MessageFlash(TaskType.FLASH, FlashType.ON,
						image.getPixels(), image.getWidth(), image.getHeight());
			}
			//daca flash off creez un nou flash off
			else if(flash[1].equals("off")){
				fl = new MessageFlash(TaskType.FLASH, FlashType.OFF,
						image.getPixels(), image.getWidth(), image.getHeight());
			}
			//daca flash Auto creez un nou flash auto
			else{
				fl = new MessageFlash(TaskType.FLASH, FlashType.AUTO,
						image.getPixels(), image.getWidth(), image.getHeight());
			}
			//public flash-ul generat
			image = (MessageImage)this.messageCenter.publish(fl);
			image.generateId();
			
			//MessageZoom
			MessageZoom zm;
			for(int i = 1 ; i < spl3.length ; i++){
				//impartirea stringului in stringuri utile
				String[] zoom = spl3[i].split("=");
				String[] szoom = zoom[1].split(",");
				int z1 = Integer.parseInt(szoom[0]);
				int z2 = Integer.parseInt(szoom[1]);
				int z3 = Integer.parseInt(szoom[2]);
				int z4 = Integer.parseInt(szoom[3]);
				//generarea unui zoom
				zm = new MessageZoom(TaskType.ZOOM, image.getPixels(), image.getWidth(),
						image.getHeight(), z1, z2, z3, z4);
				//publicarea zoom-ului
				image = (MessageImage)this.messageCenter.publish(zm);
				image.generateId();
			}
			
			//impartirea stringului in stringuri utile
			String photo = line[3];
			String[] pho1 = photo.split("\\(");
			String[] pho2 = pho1[1].split("\\)");
			String[] type = pho2[0].split("=");
			MessageImage mi;
			//daca imaginea e de tip rau generez un nou raw
			if(type[1].equals("raw")){
				mi = new MessageImage(TaskType.RAW_PHOTO, image.getPixels(),
						image.getWidth(), image.getHeight()); 
			}else{
				//daca imaginea e de tip normal generez un nou raw
				mi = new MessageImage(TaskType.RAW_PHOTO, image.getPixels(),
						image.getWidth(), image.getHeight());
				//public raw
				image = (MessageImage)this.messageCenter.publish(mi);
				image.generateId();
				//generez un id
				//generez un nou normal
				mi = new MessageImage(TaskType.NORMAL_PHOTO, image.getPixels(),
						image.getWidth(), image.getHeight()); 
			}
			//public imaginea de tip rau saau normal
			image = (MessageImage)this.messageCenter.publish(mi);
			image.generateId();
			//impartirea stringului in stringuri utile
			String post = line[4];
			String[] pos1 = post.split("\\)");
			String[] pos2 = pos1[0].split("\\(");
			MessageImage ef;
			if(1 < pos2.length){
				String[] efect = pos2[1].split(";");
				for(int i = 0 ; i < efect.length ; i++){
					//daca efectul este black_white creez un efect black_white
					if(efect[i].equals("black_white")){
						ef = new MessageImage(TaskType.BLACK_WHITE, image.getPixels(),
								image.getWidth(), image.getHeight());
					}
					//daca efectul este sepia creez un efect sepia
					else if(efect[i].equals("sepia")){
						ef = new MessageImage(TaskType.SEPIA, image.getPixels(),
								image.getWidth(), image.getHeight()); 
					}
					//daca efectul este blur creez un efect blur
					else{
						ef = new MessageImage(TaskType.BLUR, image.getPixels(),
								image.getWidth(), image.getHeight()); 
					}
					//public efectul
					image = (MessageImage)this.messageCenter.publish(ef);
					image.generateId();
				}
			}
			
			//salvez poza:D
			MessageSave save = new MessageSave(TaskType.IMAGE_SAVE, 
						image.getPixels(), image.getWidth(), image.getHeight(), 
						line[1]);
			@SuppressWarnings("unused")
			MessageSuccess success = (MessageSuccess)this.messageCenter.publish(save);
			
			//citesc o noua linie
			line = s.nextLine().split(" ");
		}
		//inchid scannerul
		s.close();
	}
	
	
	/**
	 * Main method
	 * @param args program arguments
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) {
		if(args.length != 1) {
			System.out.println("Usage: java SimulationManager <network_config_file>");
			return;
		}
		SimulationManager simulationManager;
		try {
			simulationManager = new SimulationManager(args[0]);
			simulationManager.start();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}
