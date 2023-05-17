//Kazi Shaffan

import java.util.Scanner ;
import java.util.ArrayList ;
import java.util.Random ;
class battleship { 
  public static boolean place(int column, int row, int direction, int ship, boolean placement){
    if (direction == 0){
      column = column + ship ;
      if(column > 10){
        placement = false ;
        return placement ;
      }
    }else if (direction == 1){
      row = row + ship ;
      if(row > 10){
        placement = false ;
        return placement ;
      }
    }
    placement = true ;
    return placement ;
  }
  public static int[][] ships(int[][] battle){
    Random rand = new Random();
    int random1row = 10 ; // out of the 10 rows
    int random1column = 10 ; // out of the 10 column
    int random3 = 2 ; // x y directions
    int ship = 1 ; // ships get placed from smallest to biggest
    int numships = 0 ; // used to check if no ship overlap
    int totalships = 0 ;
    boolean placement = true ;
    do {
      ship = ship + 1 ; // moves on to the next smallest ship
      int row = rand.nextInt(random1row); // randomizes row
      int column = rand.nextInt(random1column); // randomizes column
      int direction = rand.nextInt(random3) ; //right and down direction
      placement = place(column,row,direction,ship,placement) ; // checks if ship can be placed in 2D array
      if (placement == true){
        numships = 0 ;
        for(int i = 0 ; i < ship; i++){
          if (direction == 0){
            if (battle[row][column+i] != 2) {
              numships = numships + 1;
            }
          }else if (direction == 1){
            if (battle[row+i][column] != 2) {
              numships = numships + 1;
            }
          }
        }
        if(numships == ship){
          for(int i = 0 ; i < ship; i++){
            if (direction == 0){
              battle[row][column+i] = 2 ;
            }else if (direction == 1){
              battle[row+i][column] = 2 ;
            }else if (direction == 1){
              battle[row+i][column] = 2 ;
            }
          }
          totalships = totalships + 1 ;
        }else{
          ship = ship - 1 ;
        } 
      }else {
        ship = ship - 1 ;
      }  
    } while (totalships != 4) ;
    return battle ;   
  }
  public static int[][] checking(int[][] battle, int row, int column){
    if (battle[row-1][column-1] == 0){
      battle[row-1][column-1] = 1;
    }
    if (battle[row-1][column-1] == 2){
      battle[row-1][column-1] = 3;
    }
    return battle ;
  }
  public static void board(int[][] battle){
    for (int[] rows : battle)  {
      for (int column : rows){
        System.out.print("[");
        System.out.print(column);
        System.out.print("]");
        System.out.print(" ");
      }
      System.out.println();
    }
  }
  public static int killed(int[][] battle, int killed){
    killed = 0 ;
    for (int[] rows : battle)  {
      for (int column : rows){
        if(column == 3){
          killed = killed + 1 ;
        }
      }
    }
    return killed ;
  }
  public static void display(int[][] battle, String[][] display){
    for (int[] rows : battle)  {
      for (int column : rows){
        System.out.print("[");
        if (column == 0){
          System.out.print(" ");
        } else if (column == 1){
          System.out.print("O");
        }else if (column == 2){
          System.out.print(" ");
        }else if (column == 3){
          System.out.print("H");
        }else if (column == 4){
          System.out.print("H");
        }
        System.out.print("]");
        System.out.print(" ");
      }
      System.out.println();
    }
  }
  public static void main(String[]args){
    Scanner input = new Scanner(System.in) ;
    int [][] battle = new int [10][10] ; //board that does the calculations
    String [][] display = new String [10][10] ; //board that displayed
    int point = 0;
    int row ;
    int column; 
    int tries = 0 ;
    int killed = 0 ; // 14 killed means all 4 ships destroyed
    battle = ships(battle) ; // randomly places ships on the board
    display(battle, display) ; // prints board
    while(true){
      System.out.print("Give row(1-10): ");
      row = input.nextInt() ;
      System.out.print("Give column(1-10): ");
      column = input.nextInt() ;
      battle = checking(battle, row, column) ;
      display(battle, display) ; // prints board
      tries = tries + 1 ;
      killed = killed(battle, killed) ;
      if (killed == 14){
        System.out.println("YOU HAVE DESTROYED ALL THE SHIPS!!") ;
        System.out.println("It took you " + tries + " tries") ;
        System.exit(0);
      }
    }
  }
}