package Lending_and_LinkedList;

import Book_and_BST.Book;
import Book_and_BST.BookBST;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LendingLinkedList {
    private LendingNode head;

    // Add lending to the end of the linked list
    public void add(Lending lending) {
        LendingNode newNode = new LendingNode(lending);
        Book book = BookBST.search(lending.bcode);
        if (book != null)
        {
            switch (lending.getState())
            {
                case 0:
                    break;
                case 1:
                    if (book.getLended() < book.getQty())
                        book.setLended(book.getLended() + 1);
                    else
                        System.out.println("| >> Cannot lend. No more books are available.");
                    break;
                case 2:
                    if (book.getLended() > 0)
                        book.setLended(book.getLended() - 1);
                    else
                        System.out.println("| >> Cannot return. No books have been borrowed.");
                    break;
            }
        }
        else
            System.out.println("| >> Book does not exist. Cannot add.");
        if (head == null) {
            head = newNode;
        } else {
            LendingNode cur = head;
            while(cur.next != null) {
                cur = cur.next;
            }
            cur.next = newNode; //cur are already at the end but to add the new node it must be extended
        }
    }

    // Display the list
    public void display() {
        LendingNode cur = head;
        while (cur != null) {
            System.out.println(cur.lending);
            cur = cur.next;
        }
    }

    public void sort() {
        if (head == null || head.next == null) return;
        head = mergeSort(head);
    }

    private LendingNode mergeSort(LendingNode head) {
        if (head == null || head.next == null) return head;
        LendingNode mid = getMid(head);
        LendingNode rightMid = mid.next;
        mid.next = null;
        LendingNode left = mergeSort(head);
        LendingNode right = mergeSort(rightMid);
        return merge(left, right);
    }

    private LendingNode getMid(LendingNode head) {
        if (head == null) return head;
        LendingNode slow = head;
        LendingNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private LendingNode merge(LendingNode left, LendingNode right) {
        LendingNode result = null;
        if (left == null) return right;
        if (right == null) return left;
        if (left.lending.bcode.equals(right.lending.bcode) && left.lending.rcode.compareTo(right.lending.rcode) <= 0) {
            result = left;
            result.next = merge(left.next, right);
        } else {
            result = right;
            result.next = merge(left, right.next);
        }
        return result;
    }
}
