package serverPackage;  

import java.net.*;    
import java.io.*;       

public class Server {

    public static void main(String[] args) throws Exception {

        
        ServerSocket serverSocket = new ServerSocket(7040); //creation de serverr et attendre de la connexion du client 
        System.out.println("Serveur en attente de connexion...");
        Socket socket = serverSocket.accept();
        System.out.println("Client connecté : " + socket.getInetAddress());

        // 3. Create streams to read from and write to the client
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter pw = new PrintWriter(socket.getOutputStream(), true); // auto-flush

        // 4. Read operation data sent by client
        double operand1 = Double.parseDouble(br.readLine()); // Read first number
        String operator = br.readLine();                     // Read operator (+ - * /)
        double operand2 = Double.parseDouble(br.readLine()); // Read second number

        // 5. Compute result based on operator
        double res = 0; // Initialize result
        switch (operator) {
            case "+": res = operand1 + operand2; break;      // Addition
            case "-": res = operand1 - operand2; break;      // Subtraction
            case "*": res = operand1 * operand2; break;      // Multiplication
            case "/":                                          // Division
                if (operand2 != 0) { 
                    res = operand1 / operand2;               // Avoid division by zero
                } else {
                    pw.println("Erreur : division par zéro"); // Send error to client
                    br.close();
                    pw.close();
                    socket.close();
                    serverSocket.close();
                    return;                                   // Stop server after error
                }
                break;
            default:                                         // Invalid operator
                pw.println("Opérateur invalide");           // Send error to client
                br.close();
                pw.close();
                socket.close();
                serverSocket.close();
                return;                                     // Stop server after error
        }

        // 6. Send result back to the client
        pw.println(res);

        // 7. Close all resources
        br.close();           // Close input stream
        pw.close();           // Close output stream
        socket.close();       // Close client socket
        serverSocket.close(); // Close server socket
    }
}
