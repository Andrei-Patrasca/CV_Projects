package org.example;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import java.io.IOException;
import java.util.Scanner;

public class EmailService {

    private static final String SENDGRID_API_KEY = "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"; // Replace with your SendGrid API key

    /**
     * Sends an email using SendGrid.
     *
     * @param fromEmail   The sender's email address.
     * @param toEmail     The recipient's email address.
     * @param subject     The subject of the email.
     * @param contentText The content of the email (can include HTML).
     * @return true if the email was sent successfully, false otherwise.
     */
    public boolean sendEmail(String fromEmail, String toEmail, String subject, String contentText) {
        Email from = new Email(fromEmail);
        Email to = new Email(toEmail);
        Content content = new Content("text/html", contentText); // Use "text/plain" for plain text emails
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(SENDGRID_API_KEY);
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);

            int statusCode = response.getStatusCode();
            if (statusCode >= 200 && statusCode < 300) {
                System.out.println("Email sent successfully. Status Code: " + statusCode);
                return true; // Success
            } else {
                System.err.println("Failed to send email. Status Code: " + statusCode);
                System.err.println("Response Body: " + response.getBody());
                return false; // Failure
            }
        } catch (IOException ex) {
            System.err.println("Error sending email: " + ex.getMessage());
            return false; // Failure
        }
    }

    public static void main(String[] args) {
        EmailService emailService = new EmailService();
        Scanner scanner = new Scanner(System.in);

        // Default email details
        String defaultFrom = "andreipatrasca5658@gmail.com";
        String defaultTo = "andrei.patrasca04@e-uvt.ro";

        String from;
        String to;

        // Ask the user whether to use default email or custom email
        System.out.println("Choose an option:");
        System.out.println("1. Use default email");
        System.out.println("2. Use a custom email");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (choice == 1) {
            from = defaultFrom;
            to = defaultTo;
            System.out.println("Using default email addresses.");
        } else if (choice == 2) {
            System.out.print("Enter sender's email address: ");
            from = scanner.nextLine();

            System.out.print("Enter recipient's email address: ");
            to = scanner.nextLine();
        } else {
            System.out.println("Invalid choice. Exiting...");
            scanner.close();
            return;
        }

        // Ask for the subject and content of the email
        System.out.print("Enter the subject of the email: ");
        String subject = scanner.nextLine();

        System.out.print("Enter the content of the email (HTML or plain text): ");
        String content = scanner.nextLine();

        // Send the email and check if it was successful
        boolean emailSent = emailService.sendEmail(from, to, subject, content);

        if (emailSent) {
            System.out.println("Email sent successfully!");
        } else {
            System.out.println("Failed to send email. Please check the logs for more details.");
        }

        scanner.close(); // Close the scanner
    }
}
