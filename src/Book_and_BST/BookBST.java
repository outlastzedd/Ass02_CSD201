package Book_and_BST;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
// Only call public methods when working with Book data, private methods are for background data processing and should not be used directly
public class BookBST
{
    private static BookNode root;
    public BookBST()
    {
        this.root = null;
        readFile();
    }

    public void insert(Book book)
    {
        root = insert(root, book);
    }

    private BookNode insert(BookNode root, Book book)
    {
        if (root == null)
            return new BookNode(book);
        if (book.bcode.compareTo(root.book.bcode) > 0)
            root.right = insert(root.right, book);
        else if (book.bcode.compareTo(root.book.bcode) < 0)
            root.left = insert(root.left, book);
        return root;
    }

    public void breadthFirst()
    {
        if (root == null) return;
        BookNode cur = root;
        Headers.printHeader();
        while (cur != null)
        {
            System.out.print(cur.book + " ");
            if (cur.left != null)
                cur = cur.left;
            else if (cur.right != null)
                cur = cur.right;
            else
                cur = null;
        }
    }
    public void inOrder()
    {
        Headers.printHeader();
        inOrder(root);
    }
    private void inOrder(BookNode root)
    {
        if (root != null)
        {
            inOrder(root.left);
            System.out.println(root.book);
            inOrder(root.right);
        }
    }
    public static Book search(String bcode)
    {
        return search(root, bcode);
    }

    private static Book search(BookNode root, String bcode)
    {
        if (root == null)
            return null;
        if (bcode.compareTo(root.book.bcode) > 0)
            return search(root.right, bcode);
        else if (bcode.compareTo(root.book.bcode) < 0)
            return search(root.left, bcode);
        else
            return root.book;
    }
    public void delete(String bcode)
    {
        root = delete(root, bcode);
    }
    private BookNode delete(BookNode root, String bcode)
    {
        if (root == null) return null;
        if (bcode.compareTo(root.book.bcode) < 0)
            root.left = delete(root.left, bcode);
        else if (bcode.compareTo(root.book.bcode) > 0)
            root.right = delete(root.right, bcode);
        else
        {
            if (root.right == null && root.left == null) // deleting leaf node
                return null;
            if (root.right == null && root.left != null) // 1 left child
                return root.left;
            if (root.right != null && root.left == null) // 1 right child
                return root.right;
            BookNode leftMostNode = findLeftMostNode(root.right);
            root.book = leftMostNode.book;
            root.right = delete(root.right, leftMostNode.book.bcode);
        }
        return root;
    }
    BookNode findLeftMostNode(BookNode root)
    {
        if (root == null) return null;
        BookNode leftMostNode = root;
        while (leftMostNode.left != null)
            leftMostNode = leftMostNode.left;
        return leftMostNode;
    }
    public void balanceTree()
    {
        List<Book> books = new ArrayList<>();
        sortBooks(root, books);
        root = balanceTree(books, 0, books.size() - 1);
    }
    private BookNode balanceTree(List<Book> books, int start, int end)
    {
        if (start > end) return null;
        int mid = (start + end) / 2;
        BookNode node = new BookNode(books.get(mid));
        node.left = balanceTree(books, start, mid - 1);
        node.right = balanceTree(books, mid + 1, end);
        return node;
    }
    private void sortBooks(BookNode root, List<Book> books)
    {
        if (root == null) return;
        sortBooks(root.left, books);
        books.add(root.book);
        sortBooks(root.right, books);
    }
    public static int countBooks()
    {
        return countBooks(root);
    }
    private int countBooks(BookNode root)
    {
        if (root == null) return 0;
        return countBooks(root.left) + countBooks(root.right) + 1;
    }
    void readFile()
    {
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader("books.txt"));
            String line;
            int count = 0;
            while ((line = reader.readLine()) != null)
            {
                String[] parts = line.split(":");
                Book book = new Book(parts[0], parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), Double.parseDouble(parts[4]));
                insert(book);
                count++;
            }
            reader.close();
            System.out.println("| >> Loaded " + count + "/10 books.");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
