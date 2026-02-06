package Leetcode;
import java.util.PriorityQueue;

class Solution {

    class Pair {
        int val;
        ListNode node;

        Pair(int val, ListNode node) {
            this.val = val;
            this.node = node;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {

        // Step 1: Priority Queue
        PriorityQueue<Pair> pq = new PriorityQueue<>(
            (a, b) -> a.val - b.val
        );

        // Step 2: Add first node of each list
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                pq.offer(new Pair(lists[i].val, lists[i]));
            }
        }

        // Step 3: Dummy node and temp pointer
        ListNode dummy = new ListNode(-1);
        ListNode temp = dummy;

        // Step 4: Process PQ
        while (!pq.isEmpty()) {
            Pair p = pq.poll();

            temp.next = p.node;

            if (p.node.next != null) {
                pq.offer(new Pair(p.node.next.val, p.node.next));
            }

            temp = temp.next;
        }

        // Step 5: Return merged list
        return dummy.next;
    }
}
/*class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return mergeKListsHelper(lists, 0, lists.length - 1);
    }
    
    private ListNode mergeKListsHelper(ListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        }
        if (start + 1 == end) {
            return merge(lists[start], lists[end]);
        }
        int mid = start + (end - start) / 2;
        ListNode left = mergeKListsHelper(lists, start, mid);
        ListNode right = mergeKListsHelper(lists, mid + 1, end);
        return merge(left, right);
    }
    
    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        
        curr.next = (l1 != null) ? l1 : l2;
        
        return dummy.next;
    }
}
 */
