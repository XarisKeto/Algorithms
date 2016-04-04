import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.util.Scanner;

class tomb{
	private int counter=0;
	private ArrayList<String> family=new ArrayList<String>();//pinkas pou mou leei kathe fora tous tafous pou prepei na episkeftw,molis tous episkeftw leei tous epomenous k.o.k.
	private int n;
	private String[][] array;
	private boolean[][] visited;//pinakas pou mou leei pious tafous episkeftika
	public tomb(String file,int n1,int n2){
		n=n1*n2; //diastaseis tou tafou n1 katheta tetragwna,n2 orizontia
		visited=new boolean[n1][n2];//poio tafoi episkeftikan
		array=new String[n1][n2];//i methodos pou briskomaste bazei se auton ton pinaka ola ta stoixeia apo to arxeio gia na ta kanw meta bfs
		try{
			FileInputStream input=new FileInputStream(file);
			Scanner scan=new Scanner(input);
			String line;
			for(int i=0;i<n1;i++){
				for(int j=0;j<n2;j++){
					line=scan.nextLine();
					array[i][j]=line;
				}
			}
			scan.close();			
		}
		catch(FileNotFoundException e){
			System.out.println("Error opening the file.");
			System.exit(0);
		}
	}
	public void bfsTomb(){
			int i=0;
			int j=0;
			family.add(array[i][j]);
			visited[i][j]=true;
			counter ++;
			while(!family.isEmpty()){
				String progonos=family.remove(0);//aferese to head kathe fora
				//System.out.println("  progonos:"+progonos);
				String si=Character.toString(progonos.charAt(1));//pare to i apo ton progono
				String sj=Character.toString(progonos.charAt(3));//pare to j apo ton progono
				i=Integer.parseInt(si);//epeidi to si einai string kanto integer
				j=Integer.parseInt(sj);
				if(progonos.indexOf("B")!=-1){//an iparxei B stin leksi exei geitona o tafos ston borra ara an den ton exw episkeftei(visited=false) tote ton
					if(visited[i-1][j]==false){//episkeptomai (visited=true) ton prosthetw sto family gia na dw argotera pious geitones exei autos
						family.add(array[i-1][j]);//kai bazw +1 ston counter kathws brika enan akoma prosbasimo tafo
						visited[i-1][j]=true;
						counter ++;
					}
				}
				if(progonos.indexOf("N")!=-1){
					if(visited[i+1][j]==false){
						family.add(array[i+1][j]);
						visited[i+1][j]=true;
						counter ++;
					}
				}
				if(progonos.indexOf("D")!=-1){
					if(visited[i][j-1]==false){
						family.add(array[i][j-1]);
						visited[i][j-1]=true;
						counter ++;
					}
				}
				if(progonos.indexOf("A")!=-1){
					if(visited[i][j+1]==false){
						family.add(array[i][j+1]);
						visited[i][j+1]=true;
						counter ++;
					}
				}
			}
	}

	public int getCounter(){
		return counter;
	}
}
	
	
	
class lab4{
	public static void main(String[] args){
		tomb mytomb=new tomb("tomb.txt",6,7);
		mytomb.bfsTomb();//to bfsTomb gia emena einai auto pou zitaei o kathigitis ws tomb,sto diko mou tomb apla ginontai antigrafes apo to arxeio se enan pinaka
		System.out.println("Prospelasimoi tafoi:"+mytomb.getCounter());
	}
}
