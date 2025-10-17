package serverPackage;  

import java.net.*;    
import java.io.*;       

public class Server {

    public static void main(String[] args) throws Exception {

        
        ServerSocket serverSocket = new ServerSocket(7040); //creation de serverr et attendre de la connexion du client 
        System.out.println("Serveur en attente de connexion...");
        Socket socket = serverSocket.accept();
        System.out.println("Client connecté : " + socket.getInetAddress());

        // pour read et write depuis le client 
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter pw = new PrintWriter(socket.getOutputStream(), true); // je utilise cette fonction parceque j'ai un erreur avec le bufferedwriter (a l'aide de chatgpt)

        // 4. read les operations de client 
        double operand1 = Double.parseDouble(br.readLine()); 
        String operator = br.readLine();                     
        double operand2 = Double.parseDouble(br.readLine()); 

        // calcul de resultats de calculatrice 
        double res = 0; 
        switch (operator) {
            case "+": res = operand1 + operand2; break;      
            case "-": res = operand1 - operand2; break;      
            case "*": res = operand1 * operand2; break;      
            case "/":                                          
                if (operand2 != 0) { 
                    res = operand1 / operand2;               // pour n'est pas divisé par 0 
                } else {
                    pw.println("Erreur : division par zéro"); 
                    br.close();
                    pw.close();
                    socket.close();
                    serverSocket.close();
                    return;                                   // Stopper le server apress l'erreur 
                }
                break;
            default:                                         // Invalid operatore
                pw.println("Opérateur invalide");           
                br.close();
                pw.close();
                socket.close();
                serverSocket.close();
                return;                                     
        }

        // envoyer le resultat pour client 
        pw.println(res);

        // fermeture
        br.close();         
        pw.close();           
        socket.close();       
        serverSocket.close(); 
    }
}
