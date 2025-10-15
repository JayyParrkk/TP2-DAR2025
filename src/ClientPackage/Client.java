package ClientPackage; // Package for client class

import java.net.*;      // For Socket
import java.io.*;       // For Input/Output streams
import java.util.Scanner; // For user input

public class Client {

    public static void main(String[] args) throws Exception {

        // 1. Connect to the server at localhost, port 7040
        Socket socket = new Socket("localhost", 7040);
        Scanner s = new Scanner(System.in); // Scanner for reading user input

        // 2. Ask user for the first operand
        System.out.print("Premier opérande : ");
        double operand1 = s.nextDouble();

        // 3. Ask user for the operator (+ - * /)
        System.out.print("Opérateur (+ - * /) : ");
        String operator = s.next();

        // 4. Ask user for the second operand
        System.out.print("Deuxième opérande : ");
        double operand2 = s.nextDouble();

        // 5. Create streams to send/receive data to/from server
        PrintWriter pw = new PrintWriter(socket.getOutputStream(), true); // auto-flush
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // 6. Send the operation to the server
        pw.println(operand1); // Send first number
        pw.println(operator); // Send operator
        pw.println(operand2); // Send second number

        // 7. Receive result from server
        String resultat = br.readLine(); 
        System.out.println("Résultat reçu : " + resultat); // Display result

        // 8. Close all resources
        br.close();       // Close input stream
        pw.close();       // Close output stream
        socket.close();   // Close socket
        s.close();        // Close Scanner

        // 9. End of client program
        System.out.println("Client terminé.");
    }
}
