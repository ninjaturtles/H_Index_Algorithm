
//import statements
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Algorithm to find the hindex 
 * 
 * @author: Akanksha 
 * @since: October 30, 2017
 */
public class Index {
	
	//global variables
	private static ArrayList<Researcher> researchers;
	
	/**
	 * Main function
	 * 
	 * @param args Last Name First Name c1 c2 ... cn
	 */
	public static void main(String[] args) {
		
		Index index = new Index();
		researchers = new ArrayList<Researcher>();

		Scanner fileScanner = new Scanner(System.in);
		
		System.out.println("\n<Last Name> <First name> c1 c2 . . . cn");
		System.out.println("Empty line indicates end of input");
		while(fileScanner.hasNextLine()) {

			String fname = "";
			String lname ="";
			int ans = 0;
			ArrayList<Integer> array= new ArrayList<Integer>();

			String line = fileScanner.nextLine();
			Scanner lineScanner = new Scanner(line);
			if (line.equals(""))
				break;
			
			//reads and stores the papers written
			while(lineScanner.hasNext()) {
				fname = lineScanner.next();
				lname = lineScanner.next();
				while(lineScanner.hasNextInt()) {
					array.add(lineScanner.nextInt());
				}
				Collections.sort(array);
				ans = index.findHIndex(array);
			}

			Researcher researcher = index.new Researcher(fname,lname, ans);
			researchers.add(researcher);
			lineScanner.close();


		}
		fileScanner.close();

		index.printResult(researchers);
	}

	/**
	 * Finds the hindex of researchers
	 * 
	 * @param a - arraylist of integer corresponding number of citations
	 * @return hindex of the researcher
	 */
	public int findHIndex(ArrayList<Integer> a) {
		int hindex = 0;
		int n = a.size();
		int first = 0;
		int last = n - 1;

		if (n == 0)
			return hindex;

		while (first <= last) {
			int mid = first + (last - first) / 2;
			if (a.get(mid) >= n-mid)
				hindex = n - mid;
			if (a.get(mid) < n-mid)
				first = mid + 1;
			else
				last  = mid - 1;
		}
		return hindex;

	}
	
	private void printResult(ArrayList<Researcher> researchers) {
		System.out.println("---------------------------");
		System.out.println("Researcher Name : H-index");
		Collections.sort(researchers);
		for (int i = 0; i < researchers.size(); i++)
			System.out.println(researchers.get(i));
	}

	private class Researcher implements Comparable<Researcher> {
		public String first;
		public String last;
		public int hIndex;
		
		/**
		 * Class constructor
		 * 
		 * @param fname - first name
		 * @param lname - last name
		 * @param hindex - researcher's h-index
		 */
		public Researcher (String fname, String lname, int hindex) {
			first = fname;
			last = lname;
			hIndex = hindex;
		}

		/**
		 * Prints researcher first and last name and their h-index
		 */
		@Override
		public String toString() {
			return (this.first + " " + this.last + " : " + this.hIndex);
		}

		/**
		 * Compares the last name to sort alphabetically
		 */
		@Override
		public int compareTo(Researcher o) {
			// TODO Auto-generated method stub
			if(o instanceof Researcher) {
				Researcher rhs = (Researcher) o;
				int result = this.hIndex < rhs.hIndex ? 1: this.hIndex > rhs.hIndex ? -1 :0;
				if(result == 0) {
					int result2 = this.last.compareTo(rhs.last);
					if (result2 == 0) {
						return this.first.compareTo(rhs.first);
					}
					return result2;
				}
				return result;
			}else {
				return -1;
			}
		}
	} // end of researcher

} // end of index