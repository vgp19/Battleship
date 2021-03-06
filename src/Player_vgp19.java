
public class Player_vgp19 implements Player{
	private char[][] boardA=new char[10][10];
	private char[][] boardB=new char[10][10];
	
	private int x;
	private int y;
	
	private int damagePB=0;
	private int damageD=0;
	private int damageS=0;
	private int damageB=0;
	private int damageAC=0;
	private int sunk=0;
	
	
	
	public Player_vgp19(){
		initBoard(this.boardA);
		initBoard(this.boardB);
		}
	
	
	public void initBoard(char[][] board){
		for(int i=0;i<board.length;i++)
		{
			for(int j=0;j<board[i].length;j++)
			board[i][j]='~';
			}
	}
	
	
	public void setBoardB(Coordinate c, char result)
	{
		this.boardB[c.x][c.y]=result;
	}

	/**
	* This player is being fired upon. Given a coordinate, updates board(s) accordingly.
	* 
	* @param x - the coordinate that is being fired upon
	* @return M for miss, otherwise the ship's char representation
	*/
	public char fireUpon(Coordinate x)
	{
		this.x=x.x;
		this.y=x.y;
		
		if(boardA[this.x][y]!='~'){
			fireResult(boardA[this.x][y]);
			return boardA[this.x][y];		
		}
		else{
			fireResult('M');
			return 'M';	
		}
	}

	/**
	* Returns a coordinate that this player wishes to guess.
	* 
	* 
	* @return A coordinate object 
	*/
	public Coordinate fire(){
		
		System.out.println("Enter your fire coordinates: ");
		System.out.println("Enter row number:");
		int row1=IO.readInt();
		System.out.println("Enter column number:");
		int col1=IO.readInt();
		
		while((row1<0||row1>=10||col1<0||col1>=10)||(boardB[row1][col1]!='~')){
			
			if(row1<0||row1>=10||col1<0||col1>=10){
			System.out.println("Invalid coordinates.");
			System.out.println("Enter endpoint coordinations:");
			System.out.println("Enter row number:");
			row1=IO.readInt();
			System.out.println("Enter column number:");
			col1=IO.readInt();
			}
		
			else if(boardB[row1][col1]!='~'){
			IO.reportBadInput();
			System.out.println("You already have entered this fire coordinates.");
			System.out.println("Enter fire coordinates again:");
			System.out.println("Enter row number:");
			row1=IO.readInt();
			System.out.println("Enter column number:");
			col1=IO.readInt();
		}
		}
		
		Coordinate c=new Coordinate(row1,col1);
		return c;
		
		
	}
	
	/**
	* Callback method to notify player whether last fire() attempt was successful or not.
	*  
	* @param result 'M' if the last fire() resulted in a miss, otherwise the character code of the ship
	*/
	public void fireResult(char result){
		
		if(result=='P')                            
		{
			System.out.println("It's a hit.");
			damagePB++;
			if(damagePB==2){
				sunk++;
				System.out.println("Opponent's Patrol Boat has been sunk.");
			}
			
		}
		else if(result=='D')
		{
			System.out.println("It's a hit.");
			damageD++;
			if(damageD==3){
				sunk++;
			System.out.println("Opponent's Destroyer has been sunk.");
			}
		}
		else if(result=='S')
		{
			System.out.println("It's a hit.");
			damageS++;
			if(damageS==3){
				sunk++;
			System.out.println("Opponent's Submarine has been sunk.");
			}
		}
		else if(result=='B')
		{
			System.out.println("It's a hit.");
			damageB++;
			if(damageB==4){
				sunk++;
			System.out.println("Opponent's Battleship has been sunk.");
			}
		}
		else if(result=='A')
		{
			System.out.println("It's a hit.");
			damageAC++;
			if(damageAC==5){
				
				sunk++;
			System.out.println("Opponent's Aircraft Carrier has been sunk.");
			}
		}
		else
			System.out.println("It's a miss.");
			
		
	}
	
	
	
	/**
	* Places all the ships for this player
	*/
	public void placeShips(){
		
		int numOfPB=0, numOfD=0, numOfS=0,numOfB=0, numOfAC=0;
		boolean overlap=false;
		
		printingBoard(boardA);
		
		while((numOfPB==0||numOfD==0||numOfS==0||numOfB==0||numOfAC==0)){
		System.out.println("Which ship do you want to place:");
		System.out.println("Patrol Boat\nDestroyer\nSubmarine\nBattleship\nAircraft Carrier");
		String ship=IO.readString();
		
		while(!(ship.equalsIgnoreCase("patrol boat")||ship.equalsIgnoreCase("destroyer")||ship.equalsIgnoreCase("submarine")||ship.equalsIgnoreCase("battleship")||ship.equalsIgnoreCase("aircraft carrier"))){
			IO.reportBadInput();
			System.out.println("Which ship do you want to place:");
			System.out.println("Patrol Boat\nDestroyer\nSubmarine\nBattleship\nAircraft Carrier");
			ship=IO.readString();
		}
		
		if(ship.equalsIgnoreCase("Patrol boat")){
			if(numOfPB==0){
			int shipSize=2;
			char shipIni='P';
			System.out.println("Enter endpoint coordinations:");
			System.out.println("Enter row number:");
			int row=IO.readInt();
			System.out.println("Enter column number:");
			int col=IO.readInt();
			while((row<0||row>=10||col<0||col>=10)||(boardA[row][col]!='~')){
				if(row<0||row>=10||col<0||col>=10){
				System.out.println("Invalid coordinates.");
				System.out.println("Enter endpoint coordinations:");
				System.out.println("Enter row number:");
				row=IO.readInt();
				System.out.println("Enter column number:");
				col=IO.readInt();
				}
				else if(boardA[row][col]!='~'){
					System.out.println("You already have a ship on this cooridnate.");
					System.out.println("Enter endpoint coordinations:");
					System.out.println("Enter row number:");
					row=IO.readInt();
					System.out.println("Enter column number:");
					col=IO.readInt();
				}
			}
			
			System.out.println("Do you want to place a ship horizontally or vertically?");
			String placement=IO.readString();
			placement=placement(placement);
			if(placement.equalsIgnoreCase("horizontally")){
				
				
				overlap=overlap(placement, row, col, boardA, shipSize);
				
				
				if((col-shipSize+1<0)||(overlap==true)){
					if(col-shipSize+1<0)
					System.out.println("Ship will not fit.");
					else if(overlap==true)
						System.out.println("Ship overlaps");
					
				}
				else{
				for(int i=col;i>=col-shipSize+1;i--){
					boardA[row][i]=shipIni;
					
				}
				numOfPB++;
				}
			}
			else {
				
				
				overlap=overlap(placement, row, col, boardA, shipSize);
		
				
				if((row-shipSize+1<0)||(overlap==true)){
					if(row-shipSize+1<0)
					System.out.println("Ship will not fit.");
					else if(overlap==true)
						System.out.println("Ship overlaps");
					
				}
				else{
				for(int i=row;i>=row-shipSize+1;i--){
					boardA[i][col]=shipIni;
				}
				numOfPB++;
				}
			}
			printingBoard(boardA);
			}
			else{
				System.out.println("You have already placed this ship on the board.");
			}
			
			
		}
		
		
		
		
		
		
		
		
		
		else if(ship.equalsIgnoreCase("destroyer"))
		{
			if(numOfD==0){
			int shipSize=3;
			char shipIni='D';
			System.out.println("Enter endpoint coordinations:");
			System.out.println("Enter row number:");
			int row=IO.readInt();
			System.out.println("Enter column number:");
			int col=IO.readInt();
			while((row<0||row>=10||col<0||col>=10)||(boardA[row][col]!='~')){
				if(row<0||row>=10||col<0||col>=10){
				System.out.println("Invalid coordinates.");
				System.out.println("Enter endpoint coordinations:");
				System.out.println("Enter row number:");
				row=IO.readInt();
				System.out.println("Enter column number:");
				col=IO.readInt();
				}
				else if(boardA[row][col]!='~'){
					System.out.println("You already have a ship on this cooridnate.");
					System.out.println("Enter endpoint coordinations:");
					System.out.println("Enter row number:");
					row=IO.readInt();
					System.out.println("Enter column number:");
					col=IO.readInt();
				}
			}
			
			System.out.println("Do you want to place a ship horizontally or vertically?");
			String placement=IO.readString();
			placement=placement(placement);
			if(placement.equalsIgnoreCase("horizontally")){
				
				
				overlap=overlap(placement, row, col, boardA, shipSize);
				
				
				if((col-shipSize+1<0)||(overlap==true)){
					if(col-shipSize+1<0)
					System.out.println("Ship will not fit.");
					else if(overlap==true)
						System.out.println("Ship overlaps");
					
				}
				else{
				for(int i=col;i>=col-shipSize+1;i--){
					boardA[row][i]=shipIni;
					
				}
				numOfD++;
				}
			}
			else {
				
				
				overlap=overlap(placement, row, col, boardA, shipSize);
		
				
				if((row-shipSize+1<0)||(overlap==true)){
					if(row-shipSize+1<0)
					System.out.println("Ship will not fit.");
					else if(overlap==true)
						System.out.println("Ship overlaps");
					
				}
				else{
				for(int i=row;i>=row-shipSize+1;i--){
					boardA[i][col]=shipIni;
				}
				numOfD++;
				}
			}
			printingBoard(boardA);
			}
			else{
				System.out.println("You have already placed this ship on the board.");
			}
		}
		
		
		else if(ship.equalsIgnoreCase("submarine"))
		{
			if(numOfS==0){
			int shipSize=3;
			char shipIni='S';
			System.out.println("Enter endpoint coordinations:");
			System.out.println("Enter row number:");
			int row=IO.readInt();
			System.out.println("Enter column number:");
			int col=IO.readInt();
			while((row<0||row>=10||col<0||col>=10)||(boardA[row][col]!='~')){
				if(row<0||row>=10||col<0||col>=10){
				System.out.println("Invalid coordinates.");
				System.out.println("Enter endpoint coordinations:");
				System.out.println("Enter row number:");
				row=IO.readInt();
				System.out.println("Enter column number:");
				col=IO.readInt();
				}
				else if(boardA[row][col]!='~'){
					System.out.println("You already have a ship on this cooridnate.");
					System.out.println("Enter endpoint coordinations:");
					System.out.println("Enter row number:");
					row=IO.readInt();
					System.out.println("Enter column number:");
					col=IO.readInt();
				}
			}
			
			System.out.println("Do you want to place a ship horizontally or vertically?");
			String placement=IO.readString();
			placement=placement(placement);
			if(placement.equalsIgnoreCase("horizontally")){
				
				
				overlap=overlap(placement, row, col, boardA, shipSize);
				
				
				if((col-shipSize+1<0)||(overlap==true)){
					if(col-shipSize+1<0)
					System.out.println("Ship will not fit.");
					else if(overlap==true)
						System.out.println("Ship overlaps");
					
				}
				else{
				for(int i=col;i>=col-shipSize+1;i--){
					boardA[row][i]=shipIni;
					
				}
				numOfS++;
				}
			}
			else {
				
				
				overlap=overlap(placement, row, col, boardA, shipSize);
		
				
				if((row-shipSize+1<0)||(overlap==true)){
					if(row-shipSize+1<0)
					System.out.println("Ship will not fit.");
					else if(overlap==true)
						System.out.println("Ship overlaps");
					
				}
				else{
				for(int i=row;i>=row-shipSize+1;i--){
					boardA[i][col]=shipIni;
				}
				numOfS++;
				}
			}
			printingBoard(boardA);
			}
			else{
				System.out.println("You have already placed this ship on the board.");
			}
			
			
		}
		
		
		
		
		
		
		
		
		
		
		else if(ship.equalsIgnoreCase("battleship"))
		{
			if(numOfB==0){
			int shipSize=4;
			char shipIni='B';
			System.out.println("Enter endpoint coordinations:");
			System.out.println("Enter row number:");
			int row=IO.readInt();
			System.out.println("Enter column number:");
			int col=IO.readInt();
			while((row<0||row>=10||col<0||col>=10)||(boardA[row][col]!='~')){
				if(row<0||row>=10||col<0||col>=10){
				System.out.println("Invalid coordinates.");
				System.out.println("Enter endpoint coordinations:");
				System.out.println("Enter row number:");
				row=IO.readInt();
				System.out.println("Enter column number:");
				col=IO.readInt();
				}
				else if(boardA[row][col]!='~'){
					System.out.println("You already have a ship on this cooridnate.");
					System.out.println("Enter endpoint coordinations:");
					System.out.println("Enter row number:");
					row=IO.readInt();
					System.out.println("Enter column number:");
					col=IO.readInt();
				}
			}
			
			System.out.println("Do you want to place a ship horizontally or vertically?");
			String placement=IO.readString();
			placement=placement(placement);
			if(placement.equalsIgnoreCase("horizontally")){
				
				
				overlap=overlap(placement, row, col, boardA, shipSize);
				
				
				if((col-shipSize+1<0)||(overlap==true)){
					if(col-shipSize+1<0)
					System.out.println("Ship will not fit.");
					else if(overlap==true)
						System.out.println("Ship overlaps");
					
				}
				else{
				for(int i=col;i>=col-shipSize+1;i--){
					boardA[row][i]=shipIni;
					
				}
				numOfB++;
				}
			}
			else {
				
				overlap=overlap(placement, row, col, boardA, shipSize);
				
		
				
				if((row-shipSize+1<0)||(overlap==true)){
					if(row-shipSize+1<0)
					System.out.println("Ship will not fit.");
					else if(overlap==true)
						System.out.println("Ship overlaps");
					
				}
				else{
				for(int i=row;i>=row-shipSize+1;i--){
					boardA[i][col]=shipIni;
				}
				numOfB++;
				}
			}
			printingBoard(boardA);
			}
			else{
				System.out.println("You have already placed this ship on the board.");
			}
			
			
		}
		
		
		
		
		
		
		
		
		
		if(ship.equalsIgnoreCase("aircraft carrier"))
		{
			if(numOfAC==0){
			int shipSize=5;
			char shipIni='A';
			System.out.println("Enter row number:");
			int row=IO.readInt();
			System.out.println("Enter column number:");
			int col=IO.readInt();
			while((row<0||row>=10||col<0||col>=10)||(boardA[row][col]!='~')){
				if(row<0||row>=10||col<0||col>=10){
				System.out.println("Invalid coordinates.");
				System.out.println("Enter endpoint coordinations:");
				System.out.println("Enter row number:");
				row=IO.readInt();
				System.out.println("Enter column number:");
				col=IO.readInt();
				}
				else if(boardA[row][col]!='~'){
					System.out.println("You already have a ship on this cooridnate.");
					System.out.println("Enter endpoint coordinations:");
					System.out.println("Enter row number:");
					row=IO.readInt();
					System.out.println("Enter column number:");
					col=IO.readInt();
				}
			}
			
			System.out.println("Do you want to place a ship horizontally or vertically?");
			String placement=IO.readString();
			placement=placement(placement);
			if(placement.equalsIgnoreCase("horizontally")){
				
				
				overlap=overlap(placement, row, col, boardA, shipSize);
				
				
				if((col-shipSize+1<0)||(overlap==true)){
					if(col-shipSize+1<0)
					System.out.println("Ship will not fit.");
					else if(overlap==true)
						System.out.println("Ship overlaps");
					
				}
				else{
				for(int i=col;i>=col-shipSize+1;i--){
					boardA[row][i]=shipIni;
					
				}
				numOfAC++;
				}
			}
			else {
				
				
				overlap=overlap(placement, row, col, boardA, shipSize);
		
				
				if((row-shipSize+1<0)||(overlap==true)){
					if(row-shipSize+1<0)
					System.out.println("Ship will not fit.");
					else if(overlap==true)
						System.out.println("Ship overlaps");
					
				}
				else{
				for(int i=row;i>=row-shipSize+1;i--){
					boardA[i][col]=shipIni;
				}
				numOfAC++;
				}
			}
			printingBoard(boardA);
			}
			else{
				System.out.println("You have already placed this ship on the board.");
			}
			
			
		}
		}
		
	}
	
		
		
public String placement(String placement){
			while(!(placement.equalsIgnoreCase("horizontally")||placement.equalsIgnoreCase("vertically"))){
				IO.reportBadInput();
				System.out.println("Do you want to place a ship horizontally or vertically?");
				placement=IO.readString();
			}
			
			if(placement.equalsIgnoreCase("horizontally"))
				return "horizontally";
			else
				return "vertically";
		}



	public boolean overlap(String placement,int row,int col, char[][] board, int shipSize){
		
		if(placement.equalsIgnoreCase("horizontally")){
			
			
			for(int i=col;(i>=col-shipSize+1)&&(!(col-shipSize+1<0));i--){
				if(board[row][i]!='~')
					return true;
				}
			return false;
			}
		
		else{
			for(int i=row;(i>=row-shipSize+1)&&(!(row-shipSize+1<0));i--){
				if(board[i][col]!='~')
					return true;
				
					}
			return false;
		}
	}
	
	public void printingBoard(char[][] board){

		System.out.println("  0 1 2 3 4 5 6 7 8 9");
		for(int i=0;i<board.length;i++)
		{
			System.out.print(i+" ");
			for(int j=0;j<board[i].length;j++)
			{
				System.out.print(board[i][j]+" ");
			}
			System.out.println();
		}
		
	}
	
	
	
	
	/**
	* Returns if opponent has lost 
	*
	* @return true if opponent has lost, false otherwise
	*/
	public boolean lost(){

		if(sunk==5)
			return true;
		
			  return false;
			  
	}
	
	
	
	/**
	* Prints this player's Board B. 
	*
	*/
	public void printBoard(){
		System.out.println("  0 1 2 3 4 5 6 7 8 9");
		for(int i=0;i<boardB.length;i++)
		{
			System.out.print(i+" ");
			for(int j=0;j<boardB[i].length;j++)
			{
				System.out.print(boardB[i][j]+" ");
			}
			System.out.println();
		}
	}
}



