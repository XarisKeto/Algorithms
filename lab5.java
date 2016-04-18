import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.util.Scanner;
import java.lang.StringBuilder;

class roadNetwork{
	private int[][] km;
	private ArrayList<ArrayList<Integer>> list=new ArrayList<ArrayList<Integer>>();//gia kathe poli krataei 
	//enan pinaka me tis ipolipes kai an iparxei dromos pou tis enonei,posa km einai
	private int l;
	private int n;
	public roadNetwork(String file,int n,int ntepozito){//n=arithmos polewn
		l=ntepozito;
		try{
			FileInputStream input=new FileInputStream(file);
			Scanner scan=new Scanner(input);
			String line;
			int x=0;
			this.n=n;
			km=new int[n][n];
			ArrayList<Integer> list2=new ArrayList<Integer>();
			while(scan.hasNextLine()){
				line=scan.nextLine();
				int firstNumber=Character.getNumericValue(line.charAt(1));
				int secNumber=Character.getNumericValue(line.charAt(3));
				String kilom=line.substring(line.indexOf(":")+1,line.length());
				int kilometers=Integer.parseInt(kilom);
				km[firstNumber][secNumber]=kilometers;
				if(x==firstNumber){
					list2.add(secNumber);
				}else{
					list.add(list2);
					list2=new ArrayList<Integer>();
					x=firstNumber;
					list2.add(secNumber);
				}
			}
			list.add(list2);
			scan.close();
		}
		catch(FileNotFoundException e){
			System.out.println("Error opening the file.");
			System.exit(0);
		}
	}
	
	public boolean feasiblePath(int s,int t){
		ArrayList<Integer> family=new ArrayList<Integer>();
		boolean[] visited=new boolean[n];
		ArrayList<Integer> list2=new ArrayList<Integer>();
		family.add(s);
		int progonos=s;
		while(!family.isEmpty()){
			int tail=family.size()-1;
			if(km[progonos][family.get(tail)]<=l){
				progonos=family.remove(tail);
			}else{
				family.remove(tail);
			}
			list2=list.get(progonos);
			if(list2.contains(t) && km[progonos][t]<=l){
				return true;
			}else{
				if(visited[progonos]==false){
					visited[progonos]=true;
					family.addAll(list2);
				}
			}
		}
		return false;	
	}
	
	
	
}

class lab5{
	
	public static void main(String[] args){
		Scanner input=new Scanner(System.in);
		System.out.println("Give 2 cities and the reservoir of the car and see if you can travel from one to other.");
		System.out.print("Give 1st city:");
		int firstCity=input.nextInt();
		System.out.print("Give 2nd city:");
		int secondCity=input.nextInt();
		System.out.print("Give the reservoir:");
		int reservoir=input.nextInt();
		roadNetwork rn=new roadNetwork("input.txt",9,reservoir);
		System.out.println("You can travel:"+rn.feasiblePath(firstCity,secondCity));
		
	}
}
