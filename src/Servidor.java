// servidor de eco
// recebe uma linha e ecoa a linha recebida.

import java.io.*;
import java.net.*;


public class Servidor implements Runnable{
    public static void main(String args[]) {
        System.out.println("Servidor carregado no IP 127.0.0.1 na porta 9999");
        ServerSocket echoServer = null;     // cria o socket do servidor
        Serv cliente;

        try {
            echoServer = new ServerSocket(9999);  // instancia o socket do servidor na porta 9999
        }
        catch (IOException e) {
            System.out.println(e);
        }

        try {
            System.out.println("Aguardando conexao");
            clientSocket = echoServer.accept();                         // aguarda conexão do cliente
            is = new DataInputStream(clientSocket.getInputStream());    // aponta o duto de entrada para o socket do cliente
            os = new PrintStream(clientSocket.getOutputStream());       // aponta o duto de saída para o socket do cliente
            os.println("Servidor responde: Conexão efetuada com o servidor 127.0.0.1 Porta 9999");
            while (true) {
                line = is.readLine();                           // recebe dados do cliente
                System.out.println("Cliente enviou: " + line );
                os.println("Servidor responde :  Olá cliente");     // envia dados para o cliente
            }
        }

        catch (IOException e) {
            System.out.println(e);
        }
    } // main

    public void run() {
        
    }
} // classe