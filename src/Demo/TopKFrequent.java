package Demo;

import java.util.*;

public class TopKFrequent {
	static int[] topKFrequent(int[] nums, int k) {

        Map<Integer, Integer> frequency =
                new HashMap<>();

        // Count frequency
        for (int num : nums) {
            frequency.put(
                num,
                frequency.getOrDefault(num, 0) + 1
            );
        }

        // Min heap based on frequency
        PriorityQueue<Integer> minHeap =
                new PriorityQueue<>(
                    (a, b) ->
                        Integer.compare(
                            frequency.get(a),
                            frequency.get(b)
                        )
                );

        for (int num : frequency.keySet()) {

            minHeap.offer(num);

            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        int[] result = new int[k];

        for (int i = k - 1; i >= 0; i--) {
            result[i] = minHeap.poll();
        }

        return result;
    }

    public static void main(String[] args) {

        int[] nums = {1, 1, 1, 2, 2, 3};

        System.out.println(
            Arrays.toString(topKFrequent(nums, 2))
        );
    }
}