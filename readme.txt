################################################################
##########OPRICA ADRIAN-CATALIN 321 CA##########################
################################################################
##########Tema: Selfie                ##########################
##########Data: 12/5/2014             ##########################
##########Autor: OPRICA ADRIAN-CATALIN##########################
################################################################
Tema contine componentele care s-ar gasi intr-un aparat foto.
Ele ajuta la simularea prelucrarii unei fotografi intr-un
aparat foto. Componentele sunt urmatoarele:
BlackWhite: - prelucreaza poza in alb negru
			-Componenta BlackWhite va primi un mesaj de tipul 
			MessageImage si va returna tot un mesaj de tipul 
			MessageImage ce vor contine matricele de pixeli si
			dimensiunea lor.
Blur: - prelucreaza poza oferind un efect de neclaritate
	  - Componenta Blur va primi un mesaj de tipul MessageImage
	  si va returna tot un mesaj de tipul MessageImage ce vor 
	  contine matricele de pixeli si dimensiunea lor.
Component: - clasa de baza de unde sunt derivate toate 
		   componentele
Flash: - adauga luminozitatea pozei daca este setat ON
	   - nu adauga luminozitate pozei daca este setat OFF
	   - adauga luminozitate pozei daca este setat AUTO doar
	   daca media luminozitatii pozei este mai mica de 60
	   -Componenta Flash va primi un mesaj de tipul MessageFlash 
	   ce va contine un camp de tip enum FlashType (ON, OFF, AUTO)
	   si matricea de pixeli ai unei imagini precum si dimensiunea
	   acesteia.Va intoarce un mesaj de tipul MessageImage cu 
	   matricea de pixeli si dimensiunea ei, cu modificarile 
	   corespunzatoare optiunilor. 
ImageLoader : - ImageLoader va primi un mesaj de tipul MessageLoad 
				ce va contine un string cu calea catre imagine si 
				returneaza un mesaj de tipul MessageImage ce contine
				o matrice de pixeli tridimensionala si dimensiunea 
				imaginii.
			  - incarca o fotografie in vederea prelucrarii ei.
ImageSaver : - componenta ImageSaver va primi un mesaj de tipul 
			 MessageSave ce va contine atat calea imaginii, unde 
			 va trebui sa salveze imaginea, cat si matricea de 
			 pixeli si dimensiunile acesteia. Va returna un mesaj 
			 de tipul MessageSuccess prin care va confirma faptul 
			 ca a putut salva imaginea. 
			 - salveaza fotografia prelucrata
NormalPhoto : - Componenta NormalPhoto primeste un mesaj de tipul
			  MessageImage si returneaza un mesaj tot de tipul 
			  MessageImage cu matricea normala.
			  - Daca dorim o poza normala, dupa etapa de precaptura, 
			  imaginea va trece mai intai prin componenta RawPhoto 
			  si apoi prin NormalPhoto
RawPhoto : - Componenta RawPhoto primeste un mesaj de tipul
			  MessageImage si returneaza un mesaj tot de tipul 
			  MessageImage cu matricea rasturnata.
Sepia : - prelucreaza poza oferind un efect de vechi
		- Componenta Sepia va primi un mesaj de tipul MessageImage 
		si va returna tot un mesaj de tipul MessageImage ce vor 
		contine matricele de pixeli si dimensiunea lor.
Zoom : - returneaza o parte din imagine marita
	   - Componenta Zoom va primi un mesaj de tipul MessageZoom ce
	   va contine matricea de pixeli ai unei imagini, dimensiunea 
	   imaginii si inca 2 perechi de coordonate ce vor reprezenta 
	   coltul din stanga sus si dreapta jos a portiunii pe care face 
	   zoom. Returneaza un mesaj de tipul MessageImage care va contine 
	   o submatrice din matricea originala incadrata intre cele 2 
	   puncte primite ca parametrul si noua dimensiune. 
	   
Tema mai contine si un centru de mesagerie pentru a facilita comunicarea
intre componente:
Message: - Este o clasa de baza pentru mesaje.
MessageCenter : - Clasa de baza pentru centrul care gestioneaza mesajele.
MessageCity : - Se ocupa cu comunicarea dintre centre.
MessageFlash : - Se ocupa cu gestionearea flashului si asigura anumite
				facilitati.
MessageImage : - Reprezinta mesajul de baza returnat de facilitati.
MessageLoad : - Reprezinta mesajul care se asigura de incarcarea imaginii.
MessageSave : - Reprezinta mesajul care se ocupa cu salvarea imaginii.
MessageSucces : - Reprezinta mesajul care ne asigura ca centrele au 
				functionat cu success.
MessageZoom : - Se ocupa cu gestionarea zoom-umuli si asigura anumite
				facititati.
				
Clasa Simulation manager are functia buildNetwork se ocupa cu generarea 
retelei si o functie start care se ocupa cu schimbul de mesaje dintre centre.