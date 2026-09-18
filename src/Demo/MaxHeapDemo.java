package Demo;

import java.util.PriorityQueue;
import java.util.Collections;

public class MaxHeapDemo {
	public static void main(String[] args) {
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		
		pq.offer(30);
		pq.offer(10);
		pq.offer(50);
		pq.offer(20);
		
		System.out.println(pq.poll());
		System.out.println(pq.poll());
		System.out.println(pq.poll());
		System.out.println(pq.poll());
	}
}