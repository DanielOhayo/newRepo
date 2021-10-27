import java.util.ArrayList;
import java.util.Scanner;

public class TestThreadCheckArray {
	
	/** 
	 *  The main method create SharedData object that used like common resource for the two threads.
	 * The method get input size of array from user, get input elements from the user to the array and get input sum "b" element.
	 * The method check with two threads if there is elements in array that equals together to sum "b".
	 * If there is solution the thread that find the solution report to the second thread with boolean array that indicate the place of the elements that equal together to sum b.
	 */
	public static void main(String[] args) {
		try (Scanner input = new Scanner(System.in)) {
			Thread thread1, thread2;
			System.out.println("Enter array size");
			int num = input.nextInt();
			ArrayList<Integer> array = new ArrayList<Integer>();
			System.out.println("Enter numbers for array");

			for (int index = 0; index < num; index++)
				array.add(input.nextInt());

			System.out.println("Enter number");
			num = input.nextInt();

			SharedData sd = new SharedData(array, num);

			thread1 = new Thread(new ThreadCheckArray(sd), "thread1");
			thread2 = new Thread(new ThreadCheckArray(sd), "thread2");
			thread1.start();
			thread2.start();
			try {
				thread1.join();
				thread2.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (!sd.getFlag()) {
				System.out.println("Sorry");
				return;
			}
			System.out.println("Solution for b : " + sd.getB() + ",n = " + sd.getArray().size());
			System.out.print("I:    ");

			for(int index = 0; index < sd.getArray().size() ; index++)
				System.out.print(index + "    ");
			System.out.println();
			System.out.print("A:    ");
			for (int index : sd.getArray()) {
				System.out.print(index);
				int counter = 5;
				while (true) {
					index = index / 10;
					counter--;
					if (index == 0)
						break;
				}
				for (int i = 0; i < counter; i++)
					System.out.print(" ");
			}

			System.out.println();
			System.out.print("C:    ");
			for (boolean index : sd.getWinArray()) {
				if (index)
					System.out.print("1    ");
				else
					System.out.print("0    ");
			}
		}
	}

}
