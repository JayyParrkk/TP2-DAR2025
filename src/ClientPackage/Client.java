package ClientPackage; 
import java.net.*;      
import java.io.*;       
import java.util.Scanner; 
public class Client {

    public static void main(String[] args) throws Exception {

        // connecter a la serveur de port 7040
        Socket socket = new Socket("localhost", 7040);
        Scanner s = new Scanner(System.in); 

        // demande de lutilisateur de loperand 1 
        System.out.print("Premier opérande : ");
        double operand1 = s.nextDouble();

        // demande de loperator 
        System.out.print("Opérateur (+ - * /) : ");
        String operator = s.next();

        // demande de la seconde operand
        System.out.print("Deuxième opérande : ");
        double operand2 = s.nextDouble();

        //pour receverez les data depuis server 
        PrintWriter pw = new PrintWriter(socket.getOutputStream(), true); 
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // envoyer loperation ala server
        pw.println(operand1); 
        pw.println(operator); 
        pw.println(operand2); 

        // recevez la resultat
        String resultat = br.readLine(); 
        System.out.println("Résultat reçu : " + resultat); // Display result

        // fermeture
        br.close();      
        pw.close();       
        socket.close();   
        s.close();        

        System.out.println("Client terminé.");
    }
}
