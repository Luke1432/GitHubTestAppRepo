package Jeu;

/*
 * Projet Final ICS3U/4U
 * Lukas, Faress, Elvis
 * June 9 2021
 * Description: 
 * Le jeu de connect four est relativement simple. Il s'agit d'un jeu de deux joueurs. 
 * On joue au jeu sur un cadre d'une taille de 7x6 ou 7x7 (7x7 dans notre cas)
 * L'objectif du jeu est de creer une colonne, une rangee ou une diagonale de 4 jetons d'une couleur. 
 * Chaque joueur a une couleur, habituellement rouge et jaune. Dans ce cas, les couleurs sont representees par X et O
 * A son tour, l'utilisateur place un jeton a une position des 7 places possible dans le tableau. Ensuite, c'est le tour de l'adversaire. 
 * Cela continue de cette facon jusqu'a ce qu'une colonne, rangee ou diagonale de 4 X ou de 4 O de suite apparait. 
 * Celui qui a forme cette colonne, rangee ou diagonale a gagne. 
 * Puisque c'est du code java, l'utilisateur entre une valeur de 0 a 6 pour choisir la colonne. 
 * Le programme place ensuite le jeton dans la colonne appropriee. 
*/
import java.util.Scanner;

public class ConnectFourFinal{
  	static int turn=1;//variable qui montre l'utilisateur dont c'est le tour
  	static String turnSymbol="X";//symbole pour l'utilisateur dont c'est le tour
  	static Scanner in=new Scanner(System.in);//Scanner
  	static String[][] tableau;
	static String[][] table=new String[7][7];//tableau double qui contient la grille de 7x7 de connect four
	static int j=0;
	/*
    Méthode main, execution du jeu
    */
	public static void main(String[] args) throws InterruptedException{
		
      	//affiche des espaces, augmente la visibilité
		do {
			for(int i=0; i<table[0].length;i++) {
				System.out.println("");
		}
				
		
		
		printGrid();//affiche la grille
        System.out.print("Joueur "+turn+" ("+turnSymbol+") choisissez une colonne: ");//demande à l'utilisateur de choisir une colonne
		int playerChoice=in.nextInt();//prend l'entrée de l'utilisateur
		
		//pendant que la valeur de l'utilisateur est invalide, l'utilisateur continue à entrer des valeurs
        try {
			table=placePlayerChoice(playerChoice,table);
				
		}catch(Exception e) {
			while(playerChoice>6) {//pendant que la valeur est supérieure à 6
				System.out.println("\nValeur invalide, essayez encore \n");//demande à l'utilisateur d'entrer une autre valeur
				System.out.print("Joueur "+turn+" ("+turnSymbol+") choisissez une colonne: ");//demande à l'utilisateur de choisir une colonne
				playerChoice=in.nextInt();
			}
          table=placePlayerChoice(playerChoice,table);//entre le choix de l'utilisateur dans la grille de connect four
		}
		
		if(turn==1){//si le tour est égal à 1
			turn=2;//change le tour
			turnSymbol="O";//change le symbole
		}		
		else if(turn==2){//si le tour est égal à 2
					turn=1;//change le tour
					turnSymbol="X";//change le symbole
				}
		}while(true);//pour toujours
	}
		
	/*
     methode pour imprimer le tableau de connect4
     */
    public static void printGrid() {
		System.out.println("");
		System.out.println(" 0  1  2  3  4  5  6 ");
		System.out.println("|-------------------|");
        
		for(String[] rangee:table) {
			for(String carre:rangee) {
				if(carre!=null) {
					System.out.print(" "+carre+" ");
				}else {
					System.out.print(" - ");
				}
			}
			System.out.println();
		}System.out.println("|-------------------|");
		System.out.println(" 0  1  2  3  4  5  6 ");
		System.out.println("");
    }
		
    /*
    Méthode qui place le choix de l'utilisateur dans la colonne appropriée dans la grille
    */
	public static String[][] placePlayerChoice(int column, String[][] tableau) throws InterruptedException{
    	
      X: for(int i=tableau[0].length-1; i>=0; i--){
        	if(tableau[i][column]==("X")||tableau[i][column]==("O")){
            	continue;
            }else{
            	if(turn==1){
                	tableau[i][column]="X";
                	break X;
                }
              	else if(turn==2){
                	tableau[i][column]="O";
                	break X;
              	}
            }
      }
	checkWinner(tableau);
	return tableau;
	}
              
    
      
  	/*
    	Méthode qui vérifie si quelqu'un a gagné et qui a gagné
    */
  	public static String checkWinner(String[][] tableau) throws InterruptedException{
      int Xcount = 0;//compte le nombre de X dans une rangée, colonne ou diagonale
      int Ocount=0;//compte le nombre de O dans une rangée, colonne ou diagonale
      String winner = " ";//contiendra le gagnant du jeu
     
     //vérifie s'il y a un gagnant avec les colonnes de 4
     FirstFor: for(int j = 0; j < 7; j++){//pour chaque rangée
        SecondFor: for(int i = 0; i < 7; i++){//pour chaque case dans la rangée
         if((tableau[i][j] == "X")){//si la case contient un X
            Xcount++;//augmente la variable X
            Ocount=0;//annule le compte O
          }
         else if((tableau[i][j]=="O")){//si la case contient un O
            Ocount++;//augmente la variable O
            Xcount=0;//annule le compte X
          }
          else{//sinon
          	Xcount=0;//annule le compte X
            Ocount=0;//annule le compte O
          }
          if(Xcount == 4){//si le compte X est égal à 4
            winner = "X";//le gagnant est X
            printGrid();//affiche le cadre
			 playerOneWinAnimation();//joue l'animation pour quand le premier joueur gagne
			 System.exit(0);//termine le programme
          }
          if(Ocount == 4){//si le compte O est égal à 4
            winner = "O";//le gagnant est O
            printGrid();//affiche le cadre
			 playerTwoWinAnimation();//joue l'animation pour quand le deuxieme joueur gagne
			 System.exit(0);//termine le programme
          }
       }
     }
	   //vérifie s'il y a un gagnant avec les rangées de 4
      FirstFor2: for(int j = 0; j < 7; j++){//pour chaque rangée
        SecondFor2: for(int i = 0; i < 7; i++){//pour chaque case dans la rangée
        	 if((tableau[j][i] == "X")){//si la case contient un X
                 Xcount++;//augmente la variable X
               	 Ocount=0;//annule le compte O
               }
              else if((tableau[j][i]=="O")){ //si la case contient un O
                 Ocount++;//augmente la variable O
                 Xcount=0;//annule le compte X
               }
               else{//sinon
               	 Xcount=0;//annule le compte X
                 Ocount=0;//annule le compte O
               }
               if(Xcount == 4){//si le compte X est égal à 4
           		 	winner = "X";//le gagnant est X
            		printGrid();//affiche le cadre
			 		playerOneWinAnimation();//joue l'animation pour quand le premier joueur gagne
			 		System.exit(0);//termine le programme
         	   }
          	   if(Ocount == 4){//si le compte O est égal à 4
           			winner = "O";//le gagnant est O
            		printGrid();//affiche le cadre
			 		playerTwoWinAnimation();//joue l'animation pour quand le deuxieme joueur gagne
			 		System.exit(0);//termine le programme
      			}
      }
      }
     for(int i=0; i<=tableau.length-4; i++) {//pour chaque rangée
    	 for(j=0; j<=tableau[0].length-4; j++) {//pour chaque case dans la rangée
    		 
    		 for(int x=0; x<4; x++) {//pour chaque valeur de x
    			 if(tableau[i+x][j+x]=="X") {//si la case est egale a x
    				 Xcount++;//augmente le compte X
    				 Ocount=0;//annule le compte O
    			 }else if(tableau[i+x][j+x]=="O") {//si la case est egale a o
    				 Ocount++;//augmente le compte O
    				 Xcount=0;//annule le compte X
    			 }else {//sinon
    				 Xcount=0; //annule les deux comptes
    				 Ocount=0;
    			 }
    			 
    		 }
    		 if(Xcount == 4){//si le compte X est égal à 4
           		 	winner = "X";//le gagnant est X
            		printGrid();//affiche le cadre
			 		playerOneWinAnimation();//joue l'animation pour quand le premier joueur gagne
			 		System.exit(0);//termine le programme
             }
             else if(Ocount == 4){//si le compte O est égal à 4
                  winner = "O";//le gagnant est O
                  printGrid();//affiche le cadre
                  playerTwoWinAnimation();//joue l'animation pour quand le deuxieme joueur gagne
                  System.exit(0);//termine le programme
              }
    		 
    			 
    		 }
    		 
    	 }
     for(int i=tableau.length-1; i>=3; i--) {//pour chaque rangée
    	 for(j=0; j<=tableau[0].length-4; j++) {//pour chaque case dans la rangée
    		 
    		 for(int x=0; x<4; x++) {//pour chaque valeur de x
    			 if(tableau[i-x][j+x]=="X") {//si la case est egale a x
    				 Xcount++;//augmente le compte X
    				 Ocount=0;//annule le compte O
    			 }else if(tableau[i-x][j+x]=="O") {//si la case est egale a o
    				 Ocount++;//augmente le compte O
    				 Xcount=0;//annule le compte X
    			 }else {//sinon
    				 Xcount=0; //annule les deux comptes
    				 Ocount=0;
    			 }
    			 
    		 }
    		 if(Xcount == 4){//si le compte X est égal à 4
           		 	winner = "X";//le gagnant est X
            		printGrid();//affiche le cadre
			 		playerOneWinAnimation();//joue l'animation pour quand le premier joueur gagne
			 		System.exit(0);//termine le programme
             }
             else if(Ocount == 4){//si le compte O est égal à 4
                  winner = "O";//le gagnant est O
                  printGrid();//affiche le cadre
                  playerTwoWinAnimation();//joue l'animation pour quand le deuxieme joueur gagne
                  System.exit(0);//termine le programme
              }
    		 
    			 
    		 }
    		 
    	 }
        
    
      
     return winner; //retourne le gagnant
 }
  /*
   * Cette methode affiche l'animation lorsque le deuxieme joueur gagne
   */
  public static void playerTwoWinAnimation() throws InterruptedException {
	 System.out.println("\r\n" + 
	            "\r\n" + 
	            "\r\n" + 
	            "\r\n" + 
	            "\r\n" + 
	            "\r\n" + 
	            "\r\n" + 
	            "");
	 Thread.sleep(100);
	 System.out.println("  \r\n" + 
	            "  \r\n" + 
	            "_ \r\n" + 
	            "_|\r\n" + 
	            " \\\r\n" + 
	            "_/\r\n" + 
	            "  \r\n" + 
	            "  ");
	 Thread.sleep(100);
	 System.out.println("    \r\n" + 
	            "    \r\n" + 
	            "___ \r\n" + 
	            " __|\r\n" + 
	            "__ \\\r\n" + 
	            "___/\r\n" + 
	            "    \r\n" + 
	            "    ");
	 Thread.sleep(100);
	 System.out.println("      \r\n" + 
	            "      \r\n" + 
	            "  ___ \r\n" + 
	            "\\/ __|\r\n" + 
	            " \\__ \\\r\n" + 
	            "_|___/\r\n" + 
	            "      \r\n" + 
	            "      ");
	 Thread.sleep(100);
	 System.out.println("        \r\n" + 
	            "        \r\n" + 
	            "__  ___ \r\n" + 
	            "_ \\/ __|\r\n" + 
	            " | \\__ \\\r\n" + 
	            " |_|___/\r\n" + 
	            "        \r\n" + 
	            "        ");
	 Thread.sleep(100);
	 System.out.println("          \r\n" + 
	            "          \r\n" + 
	            "_ __  ___ \r\n" + 
	            " '_ \\/ __|\r\n" + 
	            " | | \\__ \\\r\n" + 
	            "_| |_|___/\r\n" + 
	            "          \r\n" + 
	            "          ");
	 Thread.sleep(100);
	 System.out.println("_           \r\n" + 
	            "_)          \r\n" + 
	            "_ _ __  ___ \r\n" + 
	            " | '_ \\/ __|\r\n" + 
	            " | | | \\__ \\\r\n" + 
	            "_|_| |_|___/\r\n" + 
	            "            \r\n" + 
	            "            ");
	 Thread.sleep(100);
	 System.out.println("  _           \r\n" + 
	            " (_)          \r\n" + 
	            "___ _ __  ___ \r\n" + 
	            " / | '_ \\/ __|\r\n" + 
	            "/| | | | \\__ \\\r\n" + 
	            " |_|_| |_|___/\r\n" + 
	            "              \r\n" + 
	            "              ");
	 Thread.sleep(100);
	 System.out.println("    _           \r\n" + 
	            "   (_)          \r\n" + 
	            "  ___ _ __  ___ \r\n" + 
	            " / / | '_ \\/ __|\r\n" + 
	            "V /| | | | \\__ \\\r\n" + 
	            "_/ |_|_| |_|___/\r\n" + 
	            "                \r\n" + 
	            "                ");
	 Thread.sleep(100);
	 System.out.println("      _           \r\n" + 
	            "     (_)          \r\n" + 
	            "    ___ _ __  ___ \r\n" + 
	            "/\\ / / | '_ \\/ __|\r\n" + 
	            "  V /| | | | \\__ \\\r\n" + 
	            "/\\_/ |_|_| |_|___/\r\n" + 
	            "                  \r\n" + 
	            "                  ");
	 Thread.sleep(100);
	 System.out.println("        _           \r\n" + 
	            "       (_)          \r\n" + 
	            "      ___ _ __  ___ \r\n" + 
	            "\\ /\\ / / | '_ \\/ __|\r\n" + 
	            " V  V /| | | | \\__ \\\r\n" + 
	            "\\_/\\_/ |_|_| |_|___/\r\n" + 
	            "                    \r\n" + 
	            "                    ");
	 Thread.sleep(100);
	 System.out.println("          _           \r\n" + 
	            "         (_)          \r\n" + 
	            "__      ___ _ __  ___ \r\n" + 
	            "\\ \\ /\\ / / | '_ \\/ __|\r\n" + 
	            " \\ V  V /| | | | \\__ \\\r\n" + 
	            "  \\_/\\_/ |_|_| |_|___/\r\n" + 
	            "                      \r\n" + 
	            "                      ");
	 Thread.sleep(100);
	 System.out.println("            _           \r\n" + 
	            "\\          (_)          \r\n" + 
	            "' __      ___ _ __  ___ \r\n" + 
	            "  \\ \\ /\\ / / | '_ \\/ __|\r\n" + 
	            "_  \\ V  V /| | | | \\__ \\\r\n" + 
	            "/   \\_/\\_/ |_|_| |_|___/\r\n" + 
	            "                        \r\n" + 
	            "                        ");
	 Thread.sleep(100);
	 System.out.println("__            _           \r\n" + 
	            "  \\          (_)          \r\n" + 
	            " /' __      ___ _ __  ___ \r\n" + 
	            "/   \\ \\ /\\ / / | '_ \\/ __|\r\n" + 
	            "___  \\ V  V /| | | | \\__ \\\r\n" + 
	            "__/   \\_/\\_/ |_|_| |_|___/\r\n" + 
	            "                          \r\n" + 
	            "                          ");
	 Thread.sleep(100);
	 System.out.println("____            _           \r\n" + 
	            "__  \\          (_)          \r\n" + 
	            " / /' __      ___ _ __  ___ \r\n" + 
	            "/ /   \\ \\ /\\ / / | '_ \\/ __|\r\n" + 
	            " /___  \\ V  V /| | | | \\__ \\\r\n" + 
	            "____/   \\_/\\_/ |_|_| |_|___/\r\n" + 
	            "                            \r\n" + 
	            "                            ");
	 Thread.sleep(100);
	 System.out.println(" _____            _           \r\n" + 
	            "/ __  \\          (_)          \r\n" + 
	            "`' / /' __      ___ _ __  ___ \r\n" + 
	            "  / /   \\ \\ /\\ / / | '_ \\/ __|\r\n" + 
	            "./ /___  \\ V  V /| | | | \\__ \\\r\n" + 
	            "\\_____/   \\_/\\_/ |_|_| |_|___/\r\n" + 
	            "                              \r\n" + 
	            "                              ");
	 Thread.sleep(100);
	 System.out.println("   _____            _           \r\n" + 
	            "  / __  \\          (_)          \r\n" + 
	            "  `' / /' __      ___ _ __  ___ \r\n" + 
	            "|   / /   \\ \\ /\\ / / | '_ \\/ __|\r\n" + 
	            "  ./ /___  \\ V  V /| | | | \\__ \\\r\n" + 
	            "  \\_____/   \\_/\\_/ |_|_| |_|___/\r\n" + 
	            "                                \r\n" + 
	            "                                ");
	 Thread.sleep(100);
	 System.out.println("     _____            _           \r\n" + 
	            "    / __  \\          (_)          \r\n" + 
	            "__  `' / /' __      ___ _ __  ___ \r\n" + 
	            "__|   / /   \\ \\ /\\ / / | '_ \\/ __|\r\n" + 
	            "    ./ /___  \\ V  V /| | | | \\__ \\\r\n" + 
	            "    \\_____/   \\_/\\_/ |_|_| |_|___/\r\n" + 
	            "                                  \r\n" + 
	            "                                  ");
	 Thread.sleep(100);
	 System.out.println("       _____            _           \r\n" + 
	            "      / __  \\          (_)          \r\n" + 
	            "_ __  `' / /' __      ___ _ __  ___ \r\n" + 
	            " '__|   / /   \\ \\ /\\ / / | '_ \\/ __|\r\n" + 
	            " |    ./ /___  \\ V  V /| | | | \\__ \\\r\n" + 
	            "_|    \\_____/   \\_/\\_/ |_|_| |_|___/\r\n" + 
	            "                                    \r\n" + 
	            "                                    ");
	 Thread.sleep(100);
	 System.out.println("         _____            _           \r\n" + 
	            "        / __  \\          (_)          \r\n" + 
	            "_ _ __  `' / /' __      ___ _ __  ___ \r\n" + 
	            " \\ '__|   / /   \\ \\ /\\ / / | '_ \\/ __|\r\n" + 
	            "_/ |    ./ /___  \\ V  V /| | | | \\__ \\\r\n" + 
	            "_|_|    \\_____/   \\_/\\_/ |_|_| |_|___/\r\n" + 
	            "                                      \r\n" + 
	            "                                      ");
	 Thread.sleep(100);
	 System.out.println("           _____            _           \r\n" + 
	            "          / __  \\          (_)          \r\n" + 
	            "___ _ __  `' / /' __      ___ _ __  ___ \r\n" + 
	            " _ \\ '__|   / /   \\ \\ /\\ / / | '_ \\/ __|\r\n" + 
	            " __/ |    ./ /___  \\ V  V /| | | | \\__ \\\r\n" + 
	            "___|_|    \\_____/   \\_/\\_/ |_|_| |_|___/\r\n" + 
	            "                                        \r\n" + 
	            "                                        ");
	 Thread.sleep(100);
	 System.out.println("             _____            _           \r\n" + 
	            "            / __  \\          (_)          \r\n" + 
	            "  ___ _ __  `' / /' __      ___ _ __  ___ \r\n" + 
	            "|/ _ \\ '__|   / /   \\ \\ /\\ / / | '_ \\/ __|\r\n" + 
	            "|  __/ |    ./ /___  \\ V  V /| | | | \\__ \\\r\n" + 
	            "|\\___|_|    \\_____/   \\_/\\_/ |_|_| |_|___/\r\n" + 
	            "|                                         \r\n" + 
	            "                                          ");
	 Thread.sleep(100);
	 System.out.println("               _____            _           \r\n" + 
	            "              / __  \\          (_)          \r\n" + 
	            " _  ___ _ __  `' / /' __      ___ _ __  ___ \r\n" + 
	            "| |/ _ \\ '__|   / /   \\ \\ /\\ / / | '_ \\/ __|\r\n" + 
	            "| |  __/ |    ./ /___  \\ V  V /| | | | \\__ \\\r\n" + 
	            ", |\\___|_|    \\_____/   \\_/\\_/ |_|_| |_|___/\r\n" + 
	            "/ |                                         \r\n" + 
	            "_/                                          ");
	 Thread.sleep(100);
	 System.out.println("                 _____            _           \r\n" + 
	            "                / __  \\          (_)          \r\n" + 
	            "   _  ___ _ __  `' / /' __      ___ _ __  ___ \r\n" + 
	            "| | |/ _ \\ '__|   / /   \\ \\ /\\ / / | '_ \\/ __|\r\n" + 
	            "|_| |  __/ |    ./ /___  \\ V  V /| | | | \\__ \\\r\n" + 
	            "__, |\\___|_|    \\_____/   \\_/\\_/ |_|_| |_|___/\r\n" + 
	            "__/ |                                         \r\n" + 
	            "___/                                          ");
	 Thread.sleep(100);
	 System.out.println("                   _____            _           \r\n" + 
	            "                  / __  \\          (_)          \r\n" + 
	            " _   _  ___ _ __  `' / /' __      ___ _ __  ___ \r\n" + 
	            "| | | |/ _ \\ '__|   / /   \\ \\ /\\ / / | '_ \\/ __|\r\n" + 
	            "| |_| |  __/ |    ./ /___  \\ V  V /| | | | \\__ \\\r\n" + 
	            "|\\__, |\\___|_|    \\_____/   \\_/\\_/ |_|_| |_|___/\r\n" + 
	            "  __/ |                                         \r\n" + 
	            " |___/                                          ");
	 Thread.sleep(100);
	 System.out.println("                     _____            _           \r\n" + 
	            "                    / __  \\          (_)          \r\n" + 
	            " _ _   _  ___ _ __  `' / /' __      ___ _ __  ___ \r\n" + 
	            "` | | | |/ _ \\ '__|   / /   \\ \\ /\\ / / | '_ \\/ __|\r\n" + 
	            "| | |_| |  __/ |    ./ /___  \\ V  V /| | | | \\__ \\\r\n" + 
	            ",_|\\__, |\\___|_|    \\_____/   \\_/\\_/ |_|_| |_|___/\r\n" + 
	            "    __/ |                                         \r\n" + 
	            "   |___/                                          ");
	 Thread.sleep(100);
	 System.out.println("                       _____            _           \r\n" + 
	            "                      / __  \\          (_)          \r\n" + 
	            "__ _ _   _  ___ _ __  `' / /' __      ___ _ __  ___ \r\n" + 
	            " _` | | | |/ _ \\ '__|   / /   \\ \\ /\\ / / | '_ \\/ __|\r\n" + 
	            "(_| | |_| |  __/ |    ./ /___  \\ V  V /| | | | \\__ \\\r\n" + 
	            "__,_|\\__, |\\___|_|    \\_____/   \\_/\\_/ |_|_| |_|___/\r\n" + 
	            "      __/ |                                         \r\n" + 
	            "     |___/                                          ");
	 Thread.sleep(100);
	 System.out.println("                         _____            _           \r\n" + 
	            "|                       / __  \\          (_)          \r\n" + 
	            "| __ _ _   _  ___ _ __  `' / /' __      ___ _ __  ___ \r\n" + 
	            "|/ _` | | | |/ _ \\ '__|   / /   \\ \\ /\\ / / | '_ \\/ __|\r\n" + 
	            "| (_| | |_| |  __/ |    ./ /___  \\ V  V /| | | | \\__ \\\r\n" + 
	            "|\\__,_|\\__, |\\___|_|    \\_____/   \\_/\\_/ |_|_| |_|___/\r\n" + 
	            "        __/ |                                         \r\n" + 
	            "       |___/                                          ");
	 Thread.sleep(100);
	 System.out.println(" _                         _____            _           \r\n" + 
	            "\\ |                       / __  \\          (_)          \r\n" + 
	            "/ | __ _ _   _  ___ _ __  `' / /' __      ___ _ __  ___ \r\n" + 
	            "| |/ _` | | | |/ _ \\ '__|   / /   \\ \\ /\\ / / | '_ \\/ __|\r\n" + 
	            "| | (_| | |_| |  __/ |    ./ /___  \\ V  V /| | | | \\__ \\\r\n" + 
	            "|_|\\__,_|\\__, |\\___|_|    \\_____/   \\_/\\_/ |_|_| |_|___/\r\n" + 
	            "          __/ |                                         \r\n" + 
	            "         |___/                                          ");
	 Thread.sleep(100);
	 System.out.println("__ _                         _____            _           \r\n" + 
	            "_ \\ |                       / __  \\          (_)          \r\n" + 
	            "/ / | __ _ _   _  ___ _ __  `' / /' __      ___ _ __  ___ \r\n" + 
	            "_/| |/ _` | | | |/ _ \\ '__|   / /   \\ \\ /\\ / / | '_ \\/ __|\r\n" + 
	            "  | | (_| | |_| |  __/ |    ./ /___  \\ V  V /| | | | \\__ \\\r\n" + 
	            "  |_|\\__,_|\\__, |\\___|_|    \\_____/   \\_/\\_/ |_|_| |_|___/\r\n" + 
	            "            __/ |                                         \r\n" + 
	            "           |___/                                          ");
	 Thread.sleep(100);
	 System.out.println("____ _                         _____            _           \r\n" + 
	            "___ \\ |                       / __  \\          (_)          \r\n" + 
	            "|_/ / | __ _ _   _  ___ _ __  `' / /' __      ___ _ __  ___ \r\n" + 
	            " __/| |/ _` | | | |/ _ \\ '__|   / /   \\ \\ /\\ / / | '_ \\/ __|\r\n" + 
	            "|   | | (_| | |_| |  __/ |    ./ /___  \\ V  V /| | | | \\__ \\\r\n" + 
	            "|   |_|\\__,_|\\__, |\\___|_|    \\_____/   \\_/\\_/ |_|_| |_|___/\r\n" + 
	            "              __/ |                                         \r\n" + 
	            "             |___/                                          ");
	 Thread.sleep(100);
	 System.out.println("______ _                         _____            _           \r\n" + 
	            "| ___ \\ |                       / __  \\          (_)          \r\n" + 
	            "| |_/ / | __ _ _   _  ___ _ __  `' / /' __      ___ _ __  ___ \r\n" + 
	            "|  __/| |/ _` | | | |/ _ \\ '__|   / /   \\ \\ /\\ / / | '_ \\/ __|\r\n" + 
	            "| |   | | (_| | |_| |  __/ |    ./ /___  \\ V  V /| | | | \\__ \\\r\n" + 
	            "\\_|   |_|\\__,_|\\__, |\\___|_|    \\_____/   \\_/\\_/ |_|_| |_|___/\r\n" + 
	            "                __/ |                                         \r\n" + 
	            "               |___/                                          ");
		
	}
  		/*
        * Cette methode affiche l'animation lorsque le premier joueur gagne
        */
	public static void playerOneWinAnimation() throws InterruptedException {
	 System.out.println("\r\n" + 
	    		"\r\n" + 
	    		"\r\n" + 
	    		"\r\n" + 
	    		"\r\n" + 
	    		"\r\n" + 
	    		"\r\n" + 
				"");
	 Thread.sleep(100);
	 System.out.println("  \r\n" + 
	    		"  \r\n" + 
	    		"_ \r\n" + 
	    		"_|\r\n" + 
	    		" \\\r\n" + 
	    		"_/\r\n" + 
	    		"  \r\n" + 
				"");
	 Thread.sleep(100);

	 System.out.println("    \r\n" + 
	    		"    \r\n" + 
	    		"___ \r\n" + 
	    		" __|\r\n" + 
	    		"__ \\\r\n" + 
	    		"___/\r\n" + 
	    		"    \r\n" + 
				"");
	 Thread.sleep(100);

	 System.out.println("      \r\n" + 
	    		"      \r\n" + 
	    		"  ___ \r\n" + 
	    		"\\/ __|\r\n" + 
	    		" \\__ \\\r\n" + 
	    		"_|___/\r\n" + 
	    		"      \r\n" + 
				"");
	 Thread.sleep(100);

	 System.out.println("        \r\n" + 
	    		"        \r\n" + 
	    		"__  ___ \r\n" + 
	    		"_ \\/ __|\r\n" + 
	    		" | \\__ \\\r\n" + 
	    		" |_|___/\r\n" + 
	    		"        \r\n" + 
				"");
	 Thread.sleep(100);

	 System.out.println("          \r\n" + 
	    		"          \r\n" + 
	    		"_ __  ___ \r\n" + 
	    		" '_ \\/ __|\r\n" + 
	    		" | | \\__ \\\r\n" + 
	    		"_| |_|___/\r\n" + 
	    		"          \r\n" + 
				"");
	 Thread.sleep(100);

	 System.out.println("_           \r\n" + 
	    		"_)          \r\n" + 
	    		"_ _ __  ___ \r\n" + 
	    		" | '_ \\/ __|\r\n" + 
	    		" | | | \\__ \\\r\n" + 
	    		"_|_| |_|___/\r\n" + 
	    		"            \r\n" + 
				"");
	 Thread.sleep(100);

	 System.out.println("  _           \r\n" + 
	    		" (_)          \r\n" + 
	    		"___ _ __  ___ \r\n" + 
	    		" / | '_ \\/ __|\r\n" + 
	    		"/| | | | \\__ \\\r\n" + 
	    		" |_|_| |_|___/\r\n" + 
	    		"              \r\n" + 
				"");
	 Thread.sleep(100);

	 System.out.println("    _           \r\n" + 
	    		"   (_)          \r\n" + 
	    		"  ___ _ __  ___ \r\n" + 
	    		" / / | '_ \\/ __|\r\n" + 
	    		"V /| | | | \\__ \\\r\n" + 
	    		"_/ |_|_| |_|___/\r\n" + 
	    		"                \r\n" + 
				"");
	 System.out.println("      _           \r\n" + 
	    		"     (_)          \r\n" + 
	    		"    ___ _ __  ___ \r\n" + 
	    		"/\\ / / | '_ \\/ __|\r\n" + 
	    		"  V /| | | | \\__ \\\r\n" + 
	    		"/\\_/ |_|_| |_|___/\r\n" + 
	    		"                  \r\n" + 
				"");
	 Thread.sleep(100);

	 System.out.println("        _           \r\n" + 
	    		"       (_)          \r\n" + 
	    		"      ___ _ __  ___ \r\n" + 
	    		"\\ /\\ / / | '_ \\/ __|\r\n" + 
	    		" V  V /| | | | \\__ \\\r\n" + 
	    		"\\_/\\_/ |_|_| |_|___/\r\n" + 
	    		"                    \r\n" + 
				"");
	 Thread.sleep(100);

	 System.out.println("          _           \r\n" + 
	    		"         (_)          \r\n" + 
	    		"__      ___ _ __  ___ \r\n" + 
	    		"\\ \\ /\\ / / | '_ \\/ __|\r\n" + 
	    		" \\ V  V /| | | | \\__ \\\r\n" + 
	    		"  \\_/\\_/ |_|_| |_|___/\r\n" + 
	    		"                      \r\n" + 
				"");
	 Thread.sleep(100);

	 System.out.println("            _           \r\n" + 
	    		"           (_)          \r\n" + 
	    		"  __      ___ _ __  ___ \r\n" + 
	    		"  \\ \\ /\\ / / | '_ \\/ __|\r\n" + 
	    		"_  \\ V  V /| | | | \\__ \\\r\n" + 
	    		"/   \\_/\\_/ |_|_| |_|___/\r\n" + 
	    		"                        \r\n" + 
				"");
	 Thread.sleep(100);

	 System.out.println("_             _           \r\n" + 
	    		" |           (_)          \r\n" + 
	    		" |  __      ___ _ __  ___ \r\n" + 
	    		" |  \\ \\ /\\ / / | '_ \\/ __|\r\n" + 
	    		" |_  \\ V  V /| | | | \\__ \\\r\n" + 
	    		"__/   \\_/\\_/ |_|_| |_|___/\r\n" + 
	    		"                          \r\n" + 
				"");
	 Thread.sleep(100);

	 System.out.println(" __             _           \r\n" + 
	    		"/  |           (_)          \r\n" + 
	    		"`| |  __      ___ _ __  ___ \r\n" + 
	    		" | |  \\ \\ /\\ / / | '_ \\/ __|\r\n" + 
	    		"_| |_  \\ V  V /| | | | \\__ \\\r\n" + 
	    		"\\___/   \\_/\\_/ |_|_| |_|___/\r\n" + 
	    		"                            \r\n" + 
				"");
	 Thread.sleep(100);

	 System.out.println("   __             _           \r\n" + 
	    		"  /  |           (_)          \r\n" + 
	    		"  `| |  __      ___ _ __  ___ \r\n" + 
	    		"|  | |  \\ \\ /\\ / / | '_ \\/ __|\r\n" + 
	    		"  _| |_  \\ V  V /| | | | \\__ \\\r\n" + 
	    		"  \\___/   \\_/\\_/ |_|_| |_|___/\r\n" + 
	    		"                              \r\n" + 
				"");
	 System.out.println("     __             _           \r\n" + 
	    		"    /  |           (_)          \r\n" + 
	    		"__  `| |  __      ___ _ __  ___ \r\n" + 
	    		"__|  | |  \\ \\ /\\ / / | '_ \\/ __|\r\n" + 
	    		"    _| |_  \\ V  V /| | | | \\__ \\\r\n" + 
	    		"    \\___/   \\_/\\_/ |_|_| |_|___/\r\n" + 
	    		"                                \r\n" + 
				"");
	 Thread.sleep(100);

	 System.out.println("       __             _           \r\n" + 
	    		"      /  |           (_)          \r\n" + 
	    		"_ __  `| |  __      ___ _ __  ___ \r\n" + 
	    		" '__|  | |  \\ \\ /\\ / / | '_ \\/ __|\r\n" + 
	    		" |    _| |_  \\ V  V /| | | | \\__ \\\r\n" + 
	    		"_|    \\___/   \\_/\\_/ |_|_| |_|___/\r\n" + 
	    		"                                  \r\n" + 
				"");
	 Thread.sleep(100);

	 System.out.println("         __             _           \r\n" + 
	    		"        /  |           (_)          \r\n" + 
	    		"_ _ __  `| |  __      ___ _ __  ___ \r\n" + 
	    		" \\ '__|  | |  \\ \\ /\\ / / | '_ \\/ __|\r\n" + 
	    		"_/ |    _| |_  \\ V  V /| | | | \\__ \\\r\n" + 
	    		"_|_|    \\___/   \\_/\\_/ |_|_| |_|___/\r\n" + 
	    		"                                    \r\n" + 
				"");
	 Thread.sleep(100);

	 System.out.println("             __             _           \r\n" + 
	    		"            /  |           (_)          \r\n" + 
	    		"  ___ _ __  `| |  __      ___ _ __  ___ \r\n" + 
	    		"|/ _ \\ '__|  | |  \\ \\ /\\ / / | '_ \\/ __|\r\n" + 
	    		"|  __/ |    _| |_  \\ V  V /| | | | \\__ \\\r\n" + 
	    		"|\\___|_|    \\___/   \\_/\\_/ |_|_| |_|___/\r\n" + 
	    		"|                                       \r\n" + 
				" ");
	 Thread.sleep(100);

	 System.out.println("           __             _           \r\n" + 
	    		"          /  |           (_)          \r\n" + 
	    		"___ _ __  `| |  __      ___ _ __  ___ \r\n" + 
	    		" _ \\ '__|  | |  \\ \\ /\\ / / | '_ \\/ __|\r\n" + 
	    		" __/ |    _| |_  \\ V  V /| | | | \\__ \\\r\n" + 
	    		"___|_|    \\___/   \\_/\\_/ |_|_| |_|___/\r\n" + 
	    		"                                      \r\n" + 
				"");
	 Thread.sleep(100);

	 System.out.println("             __             _           \r\n" + 
	    		"            /  |           (_)          \r\n" + 
	    		"  ___ _ __  `| |  __      ___ _ __  ___ \r\n" + 
	    		"|/ _ \\ '__|  | |  \\ \\ /\\ / / | '_ \\/ __|\r\n" + 
	    		"|  __/ |    _| |_  \\ V  V /| | | | \\__ \\\r\n" + 
	    		"|\\___|_|    \\___/   \\_/\\_/ |_|_| |_|___/\r\n" + 
	    		"|                                       \r\n" + 
				" ");
	 Thread.sleep(100);

	 System.out.println("               __             _           \r\n" + 
			    		"              /  |           (_)          \r\n" + 
			    		" _  ___ _ __  `| |  __      ___ _ __  ___ \r\n" + 
			    		"| |/ _ \\ '__|  | |  \\ \\ /\\ / / | '_ \\/ __|\r\n" + 
			    		"| |  __/ |    _| |_  \\ V  V /| | | | \\__ \\\r\n" + 
			    		", |\\___|_|    \\___/   \\_/\\_/ |_|_| |_|___/\r\n" + 
			    		"/ |                                       \r\n" + 
	 					"_/ ");
	 Thread.sleep(100);

	 System.out.println("                 __             _           \r\n" + 
			    "                /  |           (_)          \r\n" + 
			    "   _  ___ _ __  `| |  __      ___ _ __  ___ \r\n" + 
			    "| | |/ _ \\ '__|  | |  \\ \\ /\\ / / | '_ \\/ __|\r\n" + 
			    "|_| |  __/ |    _| |_  \\ V  V /| | | | \\__ \\\r\n" + 
			    "__, |\\___|_|    \\___/   \\_/\\_/ |_|_| |_|___/\r\n" + 
			    "__/ |                                       \r\n" + 
	 			"___/ ");
	 Thread.sleep(100);

	 System.out.println("                   __             _           \r\n" + 
			    "                  /  |           (_)          \r\n" + 
			    " _   _  ___ _ __  `| |  __      ___ _ __  ___ \r\n" + 
			    "| | | |/ _ \\ '__|  | |  \\ \\ /\\ / / | '_ \\/ __|\r\n" + 
			    "| |_| |  __/ |    _| |_  \\ V  V /| | | | \\__ \\\r\n" + 
			    "|\\__, |\\___|_|    \\___/   \\_/\\_/ |_|_| |_|___/\r\n" + 
			    "  __/ |                                       \r\n" + 
	 			" |___/ ");
	 Thread.sleep(100);

	 System.out.println("                     __             _           \r\n" + 
			    "                    /  |           (_)          \r\n" + 
			    " _ _   _  ___ _ __  `| |  __      ___ _ __  ___ \r\n" + 
			    "` | | | |/ _ \\ '__|  | |  \\ \\ /\\ / / | '_ \\/ __|\r\n" + 
			    "| | |_| |  __/ |    _| |_  \\ V  V /| | | | \\__ \\\r\n" + 
			    ",_|\\__, |\\___|_|    \\___/   \\_/\\_/ |_|_| |_|___/\r\n" + 
			    "    __/ |                                       \r\n" + 
	 			"   |___/ ");
	 Thread.sleep(100);

	 System.out.println("                       __             _           \r\n" + 
			    "                      /  |           (_)          \r\n" + 
			    "__ _ _   _  ___ _ __  `| |  __      ___ _ __  ___ \r\n" + 
			    " _` | | | |/ _ \\ '__|  | |  \\ \\ /\\ / / | '_ \\/ __|\r\n" + 
			    "(_| | |_| |  __/ |    _| |_  \\ V  V /| | | | \\__ \\\r\n" + 
			    "__,_|\\__, |\\___|_|    \\___/   \\_/\\_/ |_|_| |_|___/\r\n" + 
			    "      __/ |                                       \r\n" + 
	 			"     |___/ ");
	 Thread.sleep(100);

	 System.out.println("                         __             _           \r\n" + 
			    "|                       /  |           (_)          \r\n" + 
			    "| __ _ _   _  ___ _ __  `| |  __      ___ _ __  ___ \r\n" + 
			    "|/ _` | | | |/ _ \\ '__|  | |  \\ \\ /\\ / / | '_ \\/ __|\r\n" + 
			    "| (_| | |_| |  __/ |    _| |_  \\ V  V /| | | | \\__ \\\r\n" + 
			    "|\\__,_|\\__, |\\___|_|    \\___/   \\_/\\_/ |_|_| |_|___/\r\n" + 
			    "        __/ |                                       \r\n" + 
	 			"       |___/ ");
	 Thread.sleep(100);

	 System.out.println(" _                         __             _           \r\n" + 
			    "\\ |                       /  |           (_)          \r\n" + 
			    "/ | __ _ _   _  ___ _ __  `| |  __      ___ _ __  ___ \r\n" + 
			    "| |/ _` | | | |/ _ \\ '__|  | |  \\ \\ /\\ / / | '_ \\/ __|\r\n" + 
			    "| | (_| | |_| |  __/ |    _| |_  \\ V  V /| | | | \\__ \\\r\n" + 
			    "|_|\\__,_|\\__, |\\___|_|    \\___/   \\_/\\_/ |_|_| |_|___/\r\n" + 
			    "          __/ |                                       \r\n" + 
	 			"         |___/ ");
	 Thread.sleep(100);

	 System.out.println("__ _                         __             _           \r\n" + 
			    "_ \\ |                       /  |           (_)          \r\n" + 
			    "/ / | __ _ _   _  ___ _ __  `| |  __      ___ _ __  ___ \r\n" + 
			    "_/| |/ _` | | | |/ _ \\ '__|  | |  \\ \\ /\\ / / | '_ \\/ __|\r\n" + 
			    "  | | (_| | |_| |  __/ |    _| |_  \\ V  V /| | | | \\__ \\\r\n" + 
			    "  |_|\\__,_|\\__, |\\___|_|    \\___/   \\_/\\_/ |_|_| |_|___/\r\n" + 
			    "            __/ |                                       \r\n" + 
	 			"           |___/ ");
	 Thread.sleep(100);

	 System.out.println("____ _                         __             _           \r\n" + 
			    "___ \\ |                       /  |           (_)          \r\n" + 
			    "|_/ / | __ _ _   _  ___ _ __  `| |  __      ___ _ __  ___ \r\n" + 
			    " __/| |/ _` | | | |/ _ \\ '__|  | |  \\ \\ /\\ / / | '_ \\/ __|\r\n" + 
			    "|   | | (_| | |_| |  __/ |    _| |_  \\ V  V /| | | | \\__ \\\r\n" + 
			    "|   |_|\\__,_|\\__, |\\___|_|    \\___/   \\_/\\_/ |_|_| |_|___/\r\n" + 
			    "              __/ |                                       \r\n" + 
	 			"             |___/ ");
	 Thread.sleep(100);

	 System.out.println("______ _                         __             _           \r\n" + 
			    "| ___ \\ |                       /  |           (_)          \r\n" + 
			    "| |_/ / | __ _ _   _  ___ _ __  `| |  __      ___ _ __  ___ \r\n" + 
			    "|  __/| |/ _` | | | |/ _ \\ '__|  | |  \\ \\ /\\ / / | '_ \\/ __|\r\n" + 
			    "| |   | | (_| | |_| |  __/ |    _| |_  \\ V  V /| | | | \\__ \\\r\n" + 
			    "\\_|   |_|\\__,_|\\__, |\\___|_|    \\___/   \\_/\\_/ |_|_| |_|___/\r\n" + 
			    "                __/ |                                       \r\n" + 
	 			"               |___/ ");
 }
}
