
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rafael
 */
public class Serv extends Thread {

    String line;                        // string para conter informações transferidas
    BufferedReader in;                 // cria um duto de entrada
    BufferedWriter os;                     // cria um duto de saída
    Socket clientSocket;         // cria o socket do cliente

    public Serv(Socket cliente) {
        this.clientSocket = cliente;
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));    // aponta o duto de entrada para o socket do cliente
            os = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));       // aponta o duto de saída para o socket do cliente
            os.write("Servidor responde: Conexao efetuada com o servidor \n");
            os.flush();
            while (true) {
                line = in.readLine();                           // recebe dados do cliente
                if (line.toUpperCase().equals("FIM")) {
                    System.out.println("O cliente desligou-se");
                    os.write("Encerrando Conexão!\n");
                    os.flush();
                    clientSocket.close();
                } else {
                    System.out.println("Cliente enviou: " + line);
                    os.write(line.toUpperCase()+ "\n");
                    os.flush();// envia dados para o cliente    
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
