
public class Subset {
   public static void main(String[] args) {
       int k = Integer.parseInt(args[0]);
       RandomizedQueue<String> queue = new RandomizedQueue<String>();

       for(String string: StdIn.readAllStrings()) {
	   queue.enqueue(string);
       }

       for(int i = 0; i < k; i++) {
	   StdOut.println(queue.dequeue());
       }
   }
}
