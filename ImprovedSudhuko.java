package Project;

import Array2DPack.SimpleArray;

public class ImprovedSudhuko {

	public static void main(String[] args) {
		int [][] sudhuko = 
			 {
				        {7, 0, 2, 0, 5, 0, 6, 0, 0},//row
				        {0, 0, 0, 0, 0, 3, 0, 0, 0},
				        {1, 0, 0, 0, 0, 9, 5, 0, 0},
				        {8, 0, 0, 0, 0, 0, 0, 9, 0},
				        {0, 4, 3, 0, 0, 0, 7, 5, 0},
				        {0, 9, 0, 0, 0, 0, 0, 0, 8},
				        {0, 0, 9, 7, 0, 0, 0, 0, 5},
				        {0, 0, 0, 2, 0, 0, 0, 0, 0},
				        {0, 0, 7, 0, 4, 0, 2, 0, 3} 
				      };
		
		ImprovedSudhuko obj = new ImprovedSudhuko();
		int [] [] copySudhuko = new int [sudhuko.length][sudhuko.length];
		obj.copyTwoDimensionalArray(sudhuko,copySudhuko);
		SimpleArray.printing2dArray(copySudhuko);
		// checking purpose perfectly works 
		obj.safePlace(sudhuko,1,5,6);
		System.out.println(obj.safePlace(sudhuko,1,5,6));
	//	obj.backtracking(sudhuko,copySudhuko,1,5,6);
		

		// method calling while method creation .
			//obj.columCheck(sudhuko,1,1);
			//obj.rowCheck(sudhuko,5,1);
			//obj.threeSquareCheck(sudhuko,5,7,2);
			//System.out.println(obj.threeSquareCheck(sudhuko,5,1,1));
		obj.gamesolver(sudhuko , copySudhuko);

	}	

	
	void gamesolver(int[][] sudhuko , int[][] copySudhuko) {
		for(int row=0 ; row<sudhuko.length;row++)
		{
			boolean flag = false;
			boolean mark = false;
			for(int colum=0 ; colum<sudhuko.length;colum++)
			{
				if(flag==true )
				{
					if(sudhuko[row][colum]==0)
					{
						int count =0;
						for(int num=1;num<=sudhuko.length;num++)
						{
							if(safePlace(sudhuko,row,colum,num) )
							{
								if(count==0)
								{
									num=num+1;
									count++;
								}
								if(safePlace(sudhuko,row,colum,num) && count==1 )
								{
									sudhuko[row][colum]=num;
									flag = false;
									break;
								}
							}
						}
						if(sudhuko[row][colum]==0)
							{
							 mark = true;
							}
					}
				} // if condition end area 
				
				if(sudhuko[row][colum]==0 && mark == false)// primary function area starts 
				{
					for(int num=1;num<=sudhuko.length;num++)
					{
						if(safePlace(sudhuko,row,colum,num))
						{
							sudhuko[row][colum]=num;
							break;
						}
					}
				} // primary function area ends 
				
				if(sudhuko[row][colum]==0 && mark==true || sudhuko[row][colum]==0)
				{
					System.out.println("row"+ row+ " colum " +colum);
					if(colum>0)
					{
						if(copySudhuko[row][colum-1]==0)
						{
							sudhuko[row][colum-1]=0;
							colum = colum-2;
						}
						else if(copySudhuko[row][colum-1]!=0 )
						{
							int num = 0;
							for(int i=0;1<=colum;i++)
							{
								if(copySudhuko[row][i]==0)
								{
									num =i;
									break;
								}
							}
							sudhuko[row][num]=0;
							colum = num;
						}
					}
					else if(colum<=0)
					{
						if(copySudhuko[row-1][sudhuko.length-1]==0)
						{
							sudhuko[row-1][sudhuko.length-1]=0;
							row =row -1;//
							colum = sudhuko.length-1;
						}
						else if(copySudhuko[row-1][sudhuko.length-1]!=0)
						{
							int findnotZero = (sudhuko.length-1)-1;
							sudhuko[row-1][findnotZero]=0;
							row =row -1;
							colum = findnotZero;
						}
						//break; // try break
					}
						flag = true;
				}
			}
			printRow( sudhuko,row);
		}
		SimpleArray.printing2dArray(sudhuko);
	}

	
	private void printRow(int[][] sudhuko,int row) {
		System.out.println("new Formed row ");
		for(int i=0;i<sudhuko.length;i++)
		{
			System.out.print(sudhuko[row][i]+" ");
		}
		
		
	}
	
	public boolean safePlace(int[][] sudhuko, int row, int colum, int num) {
		boolean t1 =columCheck(sudhuko,colum,num);
		boolean t2 = rowCheck(sudhuko,row,num);
		boolean t3 = threeSquareCheck(sudhuko,row,colum,num);
		return(t1 && t2 && t3);
		
		
	}
	void copyTwoDimensionalArray(int[][] sudhuko , int [][] copySudhuko) {
		for(int row =0;row<sudhuko.length;row++)
		{
			for(int colum =0;colum<sudhuko.length;colum++)
			{
				copySudhuko[row][colum]=sudhuko[row][colum];
			}
		}
	}	
	
		// debug finished. 
	 boolean threeSquareCheck(int[][] sudhuko, int row, int colum, int num) {
		boolean flag = true;
		int rowRef = ((row)/3)*3;
		int columRef = ((colum)/3)*3;
		for(int rows = rowRef;rows<=rowRef+2;rows++)
		{
			for(int colums=columRef;colums<=columRef+2;colums++)
			{
				if(sudhuko[rows][colums]==num)
				{
					flag = false;
				}
			}
		}
	//	System.out.println("row ref " + rowRef );
	//	System.out.println("colum ref " + columRef );
		return flag;
		
	}

	boolean rowCheck(int[][] sudhuko, int row, int num) {
			boolean flag = true;
			for(int i =0;i<sudhuko.length;i++)
			{
				if(sudhuko[row][i]==num)
				{
					flag = false;
				}
			}
	//		System.out.println("output of the flag row " + flag);
			return flag;
			
		
	}

	 boolean columCheck(int[][] sudhuko , int colum ,int num) {
		boolean flag = true;
		for(int i =0;i<sudhuko.length;i++)
		{
			if(sudhuko[i][colum]==num)
			{
				flag = false;
			}
		}
		//System.out.println("output of the flag colum " + flag);
		return flag;
		
	}

}



