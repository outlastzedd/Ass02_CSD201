package Reader_and_LinkedList;

import Book_and_BST.Book;

import java.io.*;

public class ReaderLinkedList {
    private ReaderNode head;
    public ReaderLinkedList() {
        head = null;
        readFile();
    }
    // Add reader to the end of the linked list
    public void add(Reader reader) {
        ReaderNode newNode = new ReaderNode(reader);
        if (head == null) {
            head = newNode;
        } else {
            ReaderNode cur = head;
            while(cur.next != null) {
                cur = cur.next;
            }
            cur.next = newNode; //cur are already at the end but to add the new node it must be extended
        }
    }

    // Display the list
    public void display() {
        ReaderNode cur = head;
        while (cur != null) {
            System.out.println(cur.reader);
            cur = cur.next;
        }
    }

    //Search reader by rcode
    public Reader search(String rcode) {
        ReaderNode cur = head;
        while (cur.next != null) {
            if (cur.reader.rcode.equals(rcode)) {
                return cur.reader;
            }
            cur = cur.next;
        }
        return null;
    }

    //delete by rcode
    public void delete(String rcode) {
        if (head == null) return;
        if (head.reader.rcode.equals(rcode)) {
            head = head.next;
            return;
        }
        ReaderNode cur = head;
        while (cur.next != null && !cur.next.reader.rcode.equals(rcode))
            cur = cur.next;
        if (cur.next != null)
            cur.next = cur.next.next;
    }
    void readFile()
    {
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader("readers.txt"));
            String line;
            int count = 0;
            while ((line = reader.readLine()) != null)
            {
                String[] parts = line.split(":");
                Reader r = new Reader(parts[0], parts[1], checkYear(Integer.parseInt(parts[2])));
                add(r);
                count++;
            }
            reader.close();
            System.out.println("| >> Loaded " + count + "/5 readers.");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    int checkYear(int byear)
    {
        if (byear < 1900 || byear > 2010) return 2010;
        else return byear;
    }
    public void writeFile()
    {
        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter("readers.txt"));
            for (ReaderNode cur = head; cur != null; cur = cur.next)
            {
                writer.write(cur.reader.rcode + ":" + cur.reader.name + ":" + cur.reader.byear);
                writer.newLine();
            }
            writer.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}