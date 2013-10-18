// servidor de eco
// recebe uma linha e ecoa a linha recebida.

import java.io.*;
import java.net.*;

public class Servidor {

    public static void main(String args[]) throws IOException {
        System.out.println("Servidor carregado na porta 7000");
        ServerSocket echoServer = null;     // cria o socket do servidor
        Socket cliSocket = null;
        try {
            echoServer = new ServerSocket(7000);  // instancia o socket do servidor na porta 9999
        } catch (IOException e) {
            System.out.println(e);
        }
        while (true) {// listen
            
                System.out.println("Aguardando conexao");
                cliSocket = echoServer.accept();
                Serv cliente = new Serv(cliSocket);// aguarda conexão do cliente
                cliente.start();
                System.out.println("lol");
        }
    } // main
} // classe