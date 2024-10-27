import Book_and_BST.*;
import Lending_and_LinkedList.*;
import Reader_and_LinkedList.*;
public class Ass02_New
{
    public static void main(String[] args)
    {
        BookBST bookList = new BookBST();
        bookList.insert(new Book("B011", "Java", 20, 10, 10000));
        bookList.inOrder();
        bookList.breadthFirst();
        Book searchResult = BookBST.search("B004");
        if (searchResult != null)
            System.out.println("| >> Book found: " + searchResult);
        else
            System.out.println("| >> Book not found.");
        bookList.delete("B002");
        bookList.inOrder();
        System.out.println("| >> Total number of books: " + BookBST.countBooks());
        bookList.balanceTree();
        bookList.inOrder();

        ReaderLinkedList readerList = new ReaderLinkedList();
        readerList.add(new Reader("R006", "Ronando", 1985));
        readerList.add(new Reader("R007", "ishowspeed", 2005));
        readerList.display();
        readerList.writeFile();
        readerList.search("R001");
        readerList.delete("R001");
        readerList.display();

        LendingLinkedList lendingList = new LendingLinkedList();
        lendingList.add(new Lending("R006", "B001", 0));
        lendingList.add(new Lending("R007", "B002", 1));
        lendingList.add(new Lending("R003", "B005", 2));
        lendingList.display();
        lendingList.sort();
        lendingList.display();
    }
}
