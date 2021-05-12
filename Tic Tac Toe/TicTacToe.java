package game;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Stack;

public class TicTacToe implements TicTacToeInterface{
	
	private char[][] board = new char[3][3];
	private int moves = 0;
	Stack<Moves> undo_stack = new Stack<Moves>();
	Stack<Moves> redo_stack = new Stack<Moves>();
	
	public char getCell(int x, int y) {
		return board[x][y];
	}
	
	boolean setCell(char c, int x, int y) {
		if(!isOccupied(x, y)) {
			return false;
		}
		else if(c == 'x' || c == 'o'){
			board[x][y] = c;
			return true;
		}
		else {
			throw new IllegalArgumentException("Not a valid move");
		}
	}
	
	public boolean isOccupied(int x, int y) {
		if(board[x][y] != 'x' && board[x][y] != 'o') {
			return false;
		}
		return true;
	}
	
	public char getCurrentPlayer() {
		if(moves%2 == 0) {
			return 'x';
		}
		else {
			return 'o';
		}
	}
	
	public void play(int x, int y) {
		if(!isOccupied(x, y)) {
			char player = getCurrentPlayer();
			board[x][y] = player;
			moves++;
			undo_stack.push(new Moves(x, y));
		}
		else {
			System.out.println("That spot is occupied");
		}	
		
	}
	
	public boolean isWinner(char c) {
		String vannrett = "";
		String loddrett = "";
		String diagonal = "";
		String diagonal2 = "";
		for(int k = 0; k < 3; k++) {
			for(int i = 0; i < 3; i++) {
				vannrett += board[i][k];
			}
			if(vannrett.length() == 3) {
				if(vannrett.equals(""+ c + c + c)){
					return true;
				}
				else {
					vannrett = "";
				}
			}
	
		
		}
		for(int m = 0; m < 3; m++) {
			for(int n = 0; n < 3; n++) {
				loddrett += board[m][n];
			}
			if(loddrett.length() == 3) {
				if(loddrett.equals(""+ c + c + c)){
					return true;
					
				}
				else {
					loddrett = "";
				}
			}
		
		}
	
		diagonal = "" + board[0][0] + board[1][1] + board[2][2];
		diagonal2 = "" + board[2][0] + board[1][1] + board[0][2];
		if(diagonal.equals("" + c + c + c) || diagonal2.equals("" + c + c + c)) {
			return true;
		}

	
		return false;
	}
	
	public boolean hasWinner() {
		if(isWinner('x') || isWinner('o')) {
			return true;
		}
		else {
			return false;
		}
		
		
	}
	
	public boolean isFinished() {
		if(hasWinner() || moves == 9) {
			return true;
		}
			return false;
	}
	
	public void getInput(String in) {
		if(in.length() == 3) {
			String[] coor = in.split(" ");
			int x = Integer.parseInt(coor[0]);
			int y = Integer.parseInt(coor[1]);
			if(x < 0 || x > 3 || y < 0 || y > 3) {
				throw new IllegalArgumentException("Invalid coordinate");
			}
			else {
				play(x,y);
			}
		}
		else{
			throw new IllegalArgumentException("Invalid input, format = \"x y\"");
		}

		
	}
	
	public void undo() {
		Moves trekk = undo_stack.pop();
		board[trekk.x][trekk.y] = ' ';
		moves--;
		redo_stack.push(trekk);
	}
	
	public void redo() {
		Moves trekk = redo_stack.pop();
		board[trekk.x][trekk.y]=getCurrentPlayer();
		moves++;
		undo_stack.push(trekk);
	}

	
	public String toString() {
		String text = "";
		if(hasWinner()) {
			if(isWinner('x')) {
				text = "X won the game!";
				return text;
			}
			else{
				text = "O won the game!";
				return text;
			}
		}
		else if(isFinished()) {
			text = "Draw, game over";
			return text;
		}
		else {
		String brett = "";
		for(int i = 0; i < 3; i++) {
			brett += "\t";
			if(i > 0) {
				brett += "\n";
				brett += "\t";
			}
			for(int k = 0; k < 3; k++) {
				char rute = getCell(k, i);
				if(isOccupied(k, i)) {
					if(k < 2) {
						brett += rute + "|";
					}
					else {
						brett += rute; 
					}
				}
				else {
					if(k < 2) {
						brett += " "+ "|";
					}
					else {
						brett += " "; 
					}

				}

			}
		}
		return brett;
		}
	}
	
	public void save(String filename) {
		try {
			PrintWriter file_1 = new PrintWriter(filename);
			String board_string = "";
			for (int i=0; i<3; i++) {
				for (int j=0; j<3; j++) {
					if (isOccupied(i,j)) {
						board_string += board[i][j];
					}
					else {
						board_string += " ";
					}
				}
			}
			file_1.println(board_string);
			file_1.println(moves);
			file_1.close();
		}
		catch(FileNotFoundException e) {
			System.err.println("Error: file " + filename + " does not exist.");
			System.exit(1);
		}
	}
	
	public void load(String filename) {
		Scanner in;
		try {
			in = new Scanner (new FileReader(filename));
			String line = in.nextLine();
			System.out.println(line);
			for(int i=0; i<3; i++) {
				for (int j=0; j<3; j++) {
				int index = i*3+j;
				board[i][j] = line.charAt(index);
				}
			}
			String num_moves = in.nextLine();
			moves = Integer.parseInt(num_moves);
			in.close();
		}
		catch(FileNotFoundException e) {
			System.err.println("Error: file " + filename + " not found.");
			System.exit(1);
		}
	}
	
	public static void main(String[] args) {
		TicTacToe tic = new TicTacToe();
		System.out.println(tic.toString() + "\n");
		tic.play(1, 2);
		tic.play(0, 1);
		tic.play(0, 2);
		tic.play(0, 0);
		tic.play(1, 0);
		tic.play(2, 2);
		tic.play(1, 1);


		System.out.println(tic.toString());
		System.out.println(tic.isWinner('x'));
		System.out.println(tic.hasWinner());
	}
	
}
