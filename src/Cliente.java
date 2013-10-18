import java.io.*;
import java.io.*;
import java.net.*;

public class Cliente {
    static DataInputStream in;                  // cria um duto de entrada
    static PrintStream out;                     // cria um duto de saída

    public static void main(String[] args) throws IOException {

        //Rotina para entrada de dados via teclado
        DataInputStream teclado = new DataInputStream(System.in);

        //Geração do socket
        Socket clientSocket = null;
        

        try {
        /* cria o socket do cliente para conexao com o servidor
           que esta na maquina 127.0.0.1 operando na porta 7000 */

            clientSocket = new Socket("127.0.0.1",7000); /** SOCKET - CONNECT **/
            /* associa um buffer de entrada e outro de saida ao socket */
            in = new DataInputStream(clientSocket.getInputStream());    // aponta o duto de entrada para o socket do cliente
            out = new PrintStream(clientSocket.getOutputStream());       // aponta o duto de saída para o socket do cliente

            //aguarda uma digitação pelo teclado para enviar ao servidor
            System.out.println(in.readLine());
            while (true) {
                System.out.print("Digite: ");
                String enviar;
                String receber;
                enviar = teclado.readLine();
                out.println(enviar); /** SEND **/
                if (enviar.toUpperCase().equals("FIM")) {
                    receber = in.readLine();
                    System.out.println("Servidor retornou: " + receber);
                    break;
                }
                receber = in.readLine(); /** RECV**/
                System.out.println("Servidor retornou: " + receber);
            }
            clientSocket.close();
        } catch (UnknownHostException e) {
            System.err.println("Host desconhecido: ");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("IP ou Porta não existe ");
            System.exit(1);
        } catch (Exception e) {
            System.out.println("Falha na conexão com o servidor");
        }

    }
}
