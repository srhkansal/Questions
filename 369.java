369. Plus One Linked List
Given a non-negative integer represented as a linked list of digits, plus one to the integer.
The digits are stored such that the most significant digit is at the head of the list. 

Example 1:
Input: head = [1,2,3]
Output: [1,2,4]

Example 2:
Input: head = [0]
Output: [1]


class Solution {
    public ListNode plusOne(ListNode head) {
        // sentinel head
        ListNode sentinel = new ListNode(0);
        sentinel.next = head;
        ListNode notNine = sentinel;

        // find the rightmost not-nine digit
        while (head != null) {
            if (head.val != 9) {
                notNine = head;
            }
            head = head.next;
        }
    
        // increase this rightmost not-nine digit by 1
        notNine.val++;
        notNine = notNine.next;
    
        // set all the following nines to zeros
        while (notNine != null) {
            notNine.val = 0;
            notNine = notNine.next;
        }
    
    return sentinel.val != 0 ? sentinel : sentinel.next;
  }
}