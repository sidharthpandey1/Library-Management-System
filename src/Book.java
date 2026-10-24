import java.time.LocalDate;
public class Book {

    int id;
    String title;
    String author;
    String issuedTo;
    LocalDate issueDate;
    LocalDate dueDate;
    boolean isIssued;
    Book next;
    WaitQueue waitingList = new WaitQueue();

    Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;
        this.issuedTo = null;
        this.issueDate=null;
        this.dueDate=null;
        this.next=null;
    }
}

