/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastroserver.run;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import cadastroserver.controller.ProdutoJpaController;
import cadastroserver.controller.UsuarioJpaController;
import cadastroserver.controller.PessoaJpaController;
import cadastroserver.controller.MovimentoJpaController;

public class MainV2 {
    public static void main (String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CadastroServer-2PU");
        
        ProdutoJpaController ctrl = new ProdutoJpaController(emf);
        UsuarioJpaController ctrlUsu = new UsuarioJpaController(emf);
        MovimentoJpaController ctrlMov = new MovimentoJpaController(emf);
        PessoaJpaController ctrlPess = new PessoaJpaController(emf);
        
        try (ServerSocket serverSocket = new ServerSocket(4321)) {
            System.out.println("Servidor iniciado na porta 4321.");
            
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Nova conexao recebida.");
                
                CadastroThreadV2 thread = new CadastroThreadV2(ctrl, ctrlUsu, ctrlPess, ctrlMov, socket);
                thread.start();
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (emf != null) {
                emf.close();
            }
        }
    }
}
