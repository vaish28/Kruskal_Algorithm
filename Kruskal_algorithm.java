package Kruskal;
import java.io.*;
import java.util.*; 

class Graph 
{
	private int adjMat[][]=new int [20][20];//adjacency matrix for taking the input
	int n;//no. of vertices
	int e;//no of edges
	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	ArrayList<node> edges=new ArrayList<node>();//using the ArrayList java collection framework for the input edges
	public void accept()throws IOException
	{
		System.out.println("Enter the number of the vertices");//accepting the number of vertices
		n=Integer.parseInt(br.readLine());
		int flag=0;
		do
		{
			if(n<=0)//if the number of vertices is less than zero
			{
				System.out.println("Invalid number of vertices! Enter valid number of vertices");
				n=Integer.parseInt(br.readLine());
			}
			else
			{
				flag=1;//if not zero initialize flag =1
			}
		}while(flag!=1);//validation for the number of vertices
		
		
		enterGraph();//entering the data in adjacency matrix
	}


	private void enterGraph()throws IOException//accepting the input matrix of egdes
	{
		System.out.println("Enter the adjancency matrix");
		for(int i=0;i<n;i++)
		{
			System.out.print("For ");
			System.out.println();
			for(int j=0;j<n;j++)//traversing the graph
			{
				System.out.println(i+" - "+ j +" enter the weight");
				adjMat[i][j]=Integer.parseInt(br.readLine());//taking the weight as the input
			}

		}
		System.out.println("The adjacency matrix is:");
		System.out.println();
		displayGraph();//displaying the adjacency matrix
	}


	private void displayGraph()//displaying the graph
	{
		
		for(int i=0;i<n;i++)
		{
			System.out.print("      \t  "+i);//displaying the vertices
		}
		
		System.out.println();
		for(int i=0;i<n;i++)
		{
			System.out.println();
			System.out.print(i+"       ");//displaying the vertices
			for(int j=0;j<n;j++)
			{
				System.out.print(" "+adjMat[i][j]+"\t \t ");//displaying the adjacency matrix
			}
		}
		System.out.println(" ");
	}

	public void kruskal_algo()
	{
		int k=addedgelist();//adding the edges to the ArrayList
		int mst_weight=0;//to store the weight
		node output[]=new node[n-1];//output array
		for(int i=0;i<n-1;i++)
		{
			output[i]=new node();   //output node of n-1 edges for MST
		}
		
		int parent[]=new int[n];//parent array
		for(int i=0;i<n;i++)
		{
			parent[i]=i;       //assigning each node as parent of itself
		}
		
		Collections.sort(edges, new Comparator<node>(){//using inbuilt sort method on array list java collection framework
			@Override
			public int compare(node p1,node p2)
			{
				return p1.w-p2.w;//comparing the weights of edges and sorting them
			}
		});
		
				
		int count=0;//to count the number of edges in minimum spanning tree
		int i=0;   //counter for the number of edges of the edges graph
		while(i<k)//while it is less than the number of edges in the minimum spanning tree
		{
			int a=edges.get(i).u;
			int b=edges.get(i).v;
			int w=edges.get(i).w;
			
			int sourceP=find(a,parent);//finding the parent of both the edges
			int destP=find(b,parent);
			if(sourceP!=destP)//if not equal then add in the minimum spanning tree
			{
				output[count].u=a;//adding the edge in output edges array
				output[count].v=b;
				output[count].w=w;
				count++;//counting the number of edges of the MST
				parent[sourceP]=destP;
				mst_weight=mst_weight+w;//adding the minimum spanning tree weight
			}
			i++;
		}

		for(int j=0;j<n-1;j++)//displaying the minimum spanning tree 
		{
			//System.out.println(output[j].u +"and "+output[j].v);
			if(output[j].u < output[j].v)
			{
				System.out.println("Added edge "+output[j].u +" - "+ output[j].v +"= "+output[j].w+" in Minimum Spanning Tree ");
			}
			else
			{
				System.out.println("Added edge "+output[j].v +" - "+ output[j].u +"= "+output[j].w+" in Minimum Spanning Tree ");
			}
		}
		
		System.out.println("The weigth of the mst is "+mst_weight);
	}

	
	
	


	private int find(int t,int parent[])
	{
		if(parent[t]==t)
		{
			return t;//checking if  the parent and t are the same
		}
		else
		{
			return find(parent[t],parent);//finding the parent recursively
		}
	}
	

	private int addedgelist()//adding the edges to the edges object array of the node class
	{
		int u,v,w;
		int k=0;
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<i;j++)//non-repeated edges
			{
				if(adjMat[i][j]!=0)//if weight is not zero
				{
					u=i;
					v=j;
					w=adjMat[i][j];	
					edges.add(new node(u,v,w));//adding the edge in ArrayList of edges
					k++;//counting the number of such edges
				}
			}
		}
		return k;
	}
	
}


class node//node class for the edges
{
	int u,v,w;
	public node(int u1,int v1,int w1)//paramterized constructor for initialization of input edges 
	{
		u=u1;
		v=v1;
		w=w1;
	}

	public node()//default constructor for initialization of the output edges
	{
		u=0;
		v=0;
		w=0;
	}
}


public class main_class
{
	public static void main(String args[])throws IOException
	{
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		Graph g=new Graph();
		System.out.println("-------------------------------------------------------------------------------------");
		int choice=0;
		do
		{
			System.out.println();
			System.out.println("1. Enter the edges of the graph \n2. To display the kruskal algorithm \n0. To exit");
			System.out.println("-------------------------------------------------------------------------------------");	
			choice=Integer.parseInt(br.readLine());
			switch(choice)
			{
				case 1:
					g.accept();
					break;
				case 2:
					g.kruskal_algo();
					break;
				case 0:
					System.out.println("Thank you!");
					break;
				default:
					System.out.println("Invalid choice !");
					break;
			}	
		}while(choice!=0);		
	}
}





/*OUTPUT
-------------------------------------------------------------------------------------

1. Enter the edges of the graph 
2. To display the kruskal algorithm 
0. To exit
-------------------------------------------------------------------------------------
6
Invalid choice !

1. Enter the edges of the graph 
2. To display the kruskal algorithm 
0. To exit
-------------------------------------------------------------------------------------
1
Enter the number of the vertices
-6
Invalid number of vertices! Enter valid number of vertices
6
Enter the adjancency matrix
For 
0 - 0 enter the weight
0
0 - 1 enter the weight
2
0 - 2 enter the weight
4
0 - 3 enter the weight
3
0 - 4 enter the weight
0
0 - 5 enter the weight
0
For 
1 - 0 enter the weight
2
1 - 1 enter the weight
0
1 - 2 enter the weight
8
1 - 3 enter the weight
1
1 - 4 enter the weight
0
1 - 5 enter the weight
0
For 
2 - 0 enter the weight
4
2 - 1 enter the weight
8
2 - 2 enter the weight
0
2 - 3 enter the weight
6
2 - 4 enter the weight
9
2 - 5 enter the weight
10
For 
3 - 0 enter the weight
3
3 - 1 enter the weight
1
3 - 2 enter the weight
6
3 - 3 enter the weight
0
3 - 4 enter the weight
11
3 - 5 enter the weight
7
For 
4 - 0 enter the weight
0
4 - 1 enter the weight
0
4 - 2 enter the weight
9
4 - 3 enter the weight
11
4 - 4 enter the weight
0
4 - 5 enter the weight
5
For 
5 - 0 enter the weight
0
5 - 1 enter the weight
0
5 - 2 enter the weight
10
5 - 3 enter the weight
7
5 - 4 enter the weight
5
5 - 5 enter the weight
0
The adjacency matrix is:

      	  0      	  1      	  2      	  3      	  4      	  5

0        0	 	  2	 	  4	 	  3	 	  0	 	  0	 	 
1        2	 	  0	 	  8	 	  1	 	  0	 	  0	 	 
2        4	 	  8	 	  0	 	  6	 	  9	 	  10	 	 
3        3	 	  1	 	  6	 	  0	 	  11	 	  7	 	 
4        0	 	  0	 	  9	 	  11	 	  0	 	  5	 	 
5        0	 	  0	 	  10	 	  7	 	  5	 	  0	 	  

1. Enter the edges of the graph 
2. To display the kruskal algorithm 
0. To exit
-------------------------------------------------------------------------------------
2
Added edge 1 - 3= 1 in Minimum Spanning Tree 
Added edge 0 - 1= 2 in Minimum Spanning Tree 
Added edge 0 - 2= 4 in Minimum Spanning Tree 
Added edge 4 - 5= 5 in Minimum Spanning Tree 
Added edge 3 - 5= 7 in Minimum Spanning Tree 
The weigth of the mst is 19

1. Enter the edges of the graph 
2. To display the kruskal algorithm 
0. To exit
-------------------------------------------------------------------------------------
1
Enter the number of the vertices
5
Enter the adjancency matrix
For 
0 - 0 enter the weight
0
0 - 1 enter the weight
2
0 - 2 enter the weight
0
0 - 3 enter the weight
1
0 - 4 enter the weight
3
For 
1 - 0 enter the weight
2
1 - 1 enter the weight
0
1 - 2 enter the weight
5
1 - 3 enter the weight
0
1 - 4 enter the weight
0
For 
2 - 0 enter the weight
0
2 - 1 enter the weight
5
2 - 2 enter the weight
0
2 - 3 enter the weight
7
2 - 4 enter the weight
4
For 
3 - 0 enter the weight
1
3 - 1 enter the weight
0
3 - 2 enter the weight
7
3 - 3 enter the weight
0
3 - 4 enter the weight
0
For 
4 - 0 enter the weight
3
4 - 1 enter the weight
0
4 - 2 enter the weight
4
4 - 3 enter the weight
0
4 - 4 enter the weight
0
The adjacency matrix is:

      	  0      	  1      	  2      	  3      	  4

0         0	 	      2	 	      0	 	      1	 	      3	 	 
1         2	 	      0	 	      5	 	      0	 	      0	 	 
2         0	 	      5	 	      0	 	      7	 	      4	 	 
3         1	 	      0	 	      7	 	      0	 	      0	 	 
4         3	 	      0	 	      4	 	      0	 	      0	 	  

1. Enter the edges of the graph 
2. To display the kruskal algorithm 
0. To exit
-------------------------------------------------------------------------------------
2

Added edge 0 - 3 = 1 in Minimum Spanning Tree 
Added edge 0 - 1 = 2 in Minimum Spanning Tree 
Added edge 0 - 4 = 3 in Minimum Spanning Tree 
Added edge 2 - 4 = 4 in Minimum Spanning Tree 
The weigth of the mst is 10

1. Enter the edges of the graph 
2. To display the kruskal algorithm 
0. To exit
-------------------------------------------------------------------------------------
0
Thank you!

*/
