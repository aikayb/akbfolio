/*
 Date: December 6, 2021
 Programmer: Aiden Burgess
 Final Project
*/

import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;


public class Keno {
   //Constants
	int maxNum = 80;
	int rowNum = 8;
	int colNum = 10;
	int payNum = 25;
   
	int winNum, pickNum, kenoNum;
	int[] board, pickInput, pickWins;
	Random number;
	Scanner sc;


	public Keno(){

		board = new int[maxNum];
		kenoNum = 0;
		sc = new Scanner(System.in);
		number = new Random();
		pickWins = new int[20];
	}

	public void readInput() {
		
		for(int i = 0; i < maxNum; i++) {
			board[i] = i + 1;
			}
		System.out.println("\tLet's Play Keno\n");
		System.out.println("Available numbers are\n");
		int i = 0;
		for(int y = 0; y < rowNum; y++) {
			for(int k = 0; k < colNum; k++) {
				if(i < 10) {
					System.out.print(board[i]+"  ");
					}
				else {
					System.out.print(board[i]+" ");  
				}
				i++;
			}
			System.out.println();
		}

		System.out.println("How many numbers will you play?");
		
		pickNum = sc.nextInt();
		pickInput = new int[pickNum];

		i = 0;
		do {
			boolean check;
			int num;
			while(true) {
				System.out.println("Enter your numbers " + (i+1));
				num = sc.nextInt();
				check = false;
				while(num<1 || num>maxNum) {
					
					System.out.println("Number must be 1 - 80. Please retry.");
					System.out.println("Enter your numbers " + (i + 1));
					num = sc.nextInt();
               }
				
				for(int y = 0; y<i; y++) {
					if(pickInput[y] == num) {
						System.out.println("You have already entered these numbers. Please retry.");
						check = true;
						break;
					}
				}
				if(!check) {
					break;
				}
			}
          
			pickInput[i]=num;
			i++;
		}
		
		while(i < pickNum);
      }

	public void getWinningNumbers() {
		
       for(int i = 0; i < 20; i++) {
           pickWins[i] = number.nextInt(maxNum);
       }
   }
	
	public int getWinningCount() {
		
		for(int i = 0; i < pickNum; i++) {
			for(int y = 0; y < 20; y++) {
				if(pickInput[i] == pickWins[y]) {
					kenoNum++;
				}
			}
		}
		return kenoNum;
	}
	
	public void writeOutput() {

		System.out.println("The numbers you chose are: ");
		for(int i = 0; i < pickNum; i++) {
			System.out.print(pickInput[i] + " ");
			}
		System.out.println();
       
		getWinningNumbers();
		Arrays.sort(pickWins);
		System.out.println("\nThe winning numbers are: ");
		for(int i = 0; i < 20; i++) {
			System.out.print(pickWins[i] + " ");
			}
		System.out.println();

       getWinningCount();
       if(kenoNum == 0) {
    	   System.out.println("Sorry, but none of your numbers were generated. Better luck next time.");
       }
       
       else {
    	   double winNum = (kenoNum * payNum);
    	   System.out.println("Congratulations! You have won with the following number(s): " + kenoNum + ". You win $" + winNum/100 + "!");
       }
   }
}