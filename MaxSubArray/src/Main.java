import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
	
	private static int phase = 0;
	private static long startTime, endTime, elapsedTime;
	
	public static void main(String[] args) throws FileNotFoundException{
		int[] a;
		int size;
		// Read the input from file
		Scanner in;
		if(args.length > 0){
			in = new Scanner(new BufferedReader(new FileReader(args[0])));
		}
		// Read the input from console
		else{
			in = new Scanner(System.in);	
			System.out.println("Enter the size of the array followed by each element");
		}
		size = in.nextInt();
		a = new int[size];
		for(int count = 0; count < size; count++){
			a[count] = in.nextInt();
		}
		// Pass to the class
		MaxSum m = new MaxSum(a);
		Main.timer();
		m.start();
		Main.timer();
		in.close();
	}
	
	public static void timer()
	{
		if(phase == 0) {
			startTime = System.currentTimeMillis();
			phase = 1;
		} else {
			endTime = System.currentTimeMillis();
			elapsedTime = endTime-startTime;
			System.out.println( elapsedTime + " msec" + "\t");
			//memory();
			phase = 0; 
		}
	}
}
