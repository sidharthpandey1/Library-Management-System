
import java.io.*;

public class WaitQueue {

    private static class Node {
        String studentName;
        Node next;

        Node(String studentName) {
            this.studentName = studentName;
            this.next = null;
        }
    }

    private Node front;
    private Node rear;

    public boolean isEmpty() {
        return front == null;
    }

    public void enqueue(String studentName) {
        Node newNode = new Node(studentName);

        if (rear == null) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }

        System.out.println(studentName + " added to waiting list.");
    }

    public String dequeue() {
        if (isEmpty()) {
            return null;
        }

        String student = front.studentName;
        front = front.next;

        if (front == null) {
            rear = null;
        }

        return student;
    }

    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("Waiting list is empty.");
            return;
        }

        Node current = front;

        while (current != null) {
            System.out.println(current.studentName);
            current = current.next;
        }
    }


    public String toStorageString() {
        StringBuilder result = new StringBuilder();
        Node current = front;

        while (current != null) {
            if (result.length() > 0) {
                result.append(",");
            }

            String encoded = java.util.Base64.getEncoder()
                    .encodeToString(
                            current.studentName.getBytes(
                                    java.nio.charset.StandardCharsets.UTF_8
                            )
                    );

            result.append(encoded);
            current = current.next;
        }

        return result.toString();
    }

    public void loadFromStorageString(String stored) {
        if (stored == null || stored.isEmpty()) {
            return;
        }

        String[] students = stored.split(",");

        for (String encoded : students) {
            try {
                String student = new String(
                        java.util.Base64.getDecoder().decode(encoded),
                        java.nio.charset.StandardCharsets.UTF_8
                );

                enqueue(student);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid waiting-list record skipped.");
            }
        }
    }

}
