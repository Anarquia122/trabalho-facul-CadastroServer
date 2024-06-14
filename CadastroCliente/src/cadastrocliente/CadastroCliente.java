/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cadastrocliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import cadastroserver.model.Produto;

public class CadastroCliente {

    public static void main(String[] args) {
        String host = "localhost";
        int port = 4321;

        try (Socket socket = new Socket(host, port);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            // login e senha
            out.writeObject("loja");
            out.writeObject("loja");
            out.flush();

            String authResponse = (String) in.readObject();
            System.out.println(authResponse);
            if (!authResponse.equals("Usuario autenticado com sucesso.")) {
                System.out.println("Falha na autenticação.");
                return;
            }

            out.writeObject("L");
            out.flush();

            Object response = in.readObject();
            if (response instanceof List) {
                List<?> produtos = (List<?>) response;
                for (Object obj : produtos) {
                    if (obj instanceof Produto) {
                        Produto produto = (Produto) obj;
                        System.out.println("Nome do produto: " + produto.getNomeproduto());
                    }
                }
            } else {
                System.out.println("Resposta inesperada do servidor.");
            }

            out.writeObject("S");
            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}