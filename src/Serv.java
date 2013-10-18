
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
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
    DataInputStream is;                 // cria um duto de entrada
    PrintStream os;                     // cria um duto de saída
    Socket clientSocket;         // cria o socket do cliente

    public Serv(Socket cliente) {
        this.clientSocket = cliente;
    }

    @Override
    public void run() {
        try {
            is = new DataInputStream(clientSocket.getInputStream());    // aponta o duto de entrada para o socket do cliente
            os = new PrintStream(clientSocket.getOutputStream());       // aponta o duto de saída para o socket do cliente
            os.println("Servidor responde: Conexao efetuada com o servidor");
            while (true) {
                line = is.readLine();                           // recebe dados do cliente
                if (line.toUpperCase().equals("FIM")) {
                    System.out.println("O cliente desligou-se");
                    os.println("Encerrando Conexão!");
                    clientSocket.close();
                } else {
                    System.out.println("Cliente enviou: " + line);
                    os.println(line.toUpperCase());     // envia dados para o cliente    
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
