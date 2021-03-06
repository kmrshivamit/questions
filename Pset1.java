import java.util.*;
 
public class Pset1 {
    static int[] deckValue = new int[] { 1,1,1,1,2,2,2,2,3,3,3,3,4,4,4,4,5,5,5,5,6,6,6,6,7,7,7,7,8,8,8,8,9,9,9,9,10
    		,10,10,10};
 
    static String[] userDeck = new String[] { "1H", "1D", "1C", "1S", "2H", "2D", "2C", "2S", "3H", "3D", "3C", "3S",
            "4H", "4D", "4C", "4S", "5H", "5D", "5C", "5S", "6H", "6D", "6C", "6S", "7H", "7D", "7C", "7S", "8H", "8D",
            "8C", "8S", "9H", "9D", "9C", "9S", "10H", "10D", "10C", "10S" };
 
    static String[] userBoard = new String[9];
    static int[] logicBoard = new int[9];
 
    static String userName;
    static Random rng = new Random();
    static boolean game = true;
    static int randNum;
    static int userChoice;
//    static int cardOne;
//    static int cardTwo;
    static int cardInDeck=40; //Initial number of cards
    static int score = 0;//Initial score
    static Scanner input = new Scanner(System.in);
 
    public static void main(String[] args) throws InterruptedException {
        welcome();
        game();
        System.out.println("The game is over because total cards in userDeck is 0");
    }
 
 
 
    private static void welcome() throws InterruptedException {
        System.out.println("Welcome to the Solitaire Game of Elevens!");
        Thread.sleep(1000);
        System.out.println("The goal is to eliminate cards that equal to 11 and eliminate the entire deck. Let's Go!");
 
    }
 
    //This method is used to replace the card which user has taken out.
    private static void refill(int n) {
        randNum = rng.nextInt(40);
        while (deckValue[randNum] == 0) {
            randNum = rng.nextInt(40);
        }
        logicBoard[n] = deckValue[randNum];
        userBoard[n] = userDeck[randNum];
        deckValue[randNum] = 0;
        cardInDeck--;
    }
 static int Flag=0;
    private static Integer getCardValue(String card) {
    	try {
        return Integer.parseInt(card.replaceAll("\\D", ""));
    	}
    	catch(Exception e)
    	{Flag=1;
    		return 0;
    	
    	}
    	
      }
    
  private static int findIndex(String str) {
	  int i;
	  for( i=0;i<9;i++)
		  if(str.equals(userBoard[i]))
			  break;
		  
		  
     return i;
    }
 
    private static void game() {
    	fillBoard();
        while (game) {
        	Flag=0;
            
            displayGrid();
           
            System.out.println("What is the 1st card you want to remove?");
      String cardOne = input.nextLine();    
            System.out.println("What is the 2nd card you want to remove?");
      String cardTwo = input.nextLine();
                       if (((getCardValue(cardOne) + getCardValue(cardTwo) )== 11)&&(Flag==0)) {
                System.out.println("Great job!");
                // addition of 2 points to the users score
                score = score + 2;
                // outputting score to the user
                System.out.println("Your current score is: " + score);
 
                // when the array completes, display this
            /*  if (endOfDeck()) {
                    System.out.println("Congrats, you cleared the deck. Finish off your current hand.");
                } else {
                    // methods for refilling 2 cards once they're taken off the board by the user
                    refillTwo();
                }
            */
                
                refill(findIndex(cardOne));
                if(cardInDeck<=0)
                	game=false;
                refill(findIndex(cardTwo));
                if(cardInDeck<=0)
                	game=false;
            } else {
                // in case the user accidently inputs the wrong number or can't do math in that
                // instant
                System.err.println("Please select the correct cards or check your spelling");
                System.out.println();
            }  
        }
    }
 
    //Fill the board with 9 values.
    private static void fillBoard() {
        for (int i = 0; i < logicBoard.length; i++) {
            int randNum = rng.nextInt(40);
            while (deckValue[randNum] == 0) {
                randNum = rng.nextInt(40);
            }
            logicBoard[i] = deckValue[randNum];
            userBoard[i] = userDeck[randNum];
            deckValue[randNum] = 0;
        }
        cardInDeck=40-9;
    }
 
    private static void displayGrid() {
        for (int i = 0; i < userBoard.length; i++) {
            if (i == 0 || i == 3 || i == 6) {
                System.out.print("|");
            }
            System.out.print(" " + userBoard[i] + " |");
            if (i == 2 || i == 5 || i == 8) {
                System.out.println();
            }
        }
        System.out.println(" ");
        System.out.println("^!That's your draw!^");
    }
 
   
    private static boolean cardsInDeck() {
 
        int deckSum = 0;
        if (deckSum > 0) {
            return true;
        } else {
            return false;
        }
 
    }
}
