/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastroserver.run;

import cadastroserver.controller.ProdutoJpaController;
import cadastroserver.controller.UsuarioJpaController;
import cadastroserver.model.Produto;
import cadastroserver.model.Usuario;
import java.io.IOException;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class CadastroThread extends Thread {

    private ProdutoJpaController ctrl;
    private UsuarioJpaController ctrlUsu;
    private Socket s1;

    public CadastroThread(ProdutoJpaController ctrl, UsuarioJpaController ctrlUsu, Socket s1) {
        this.ctrl = ctrl;
        this.ctrlUsu = ctrlUsu;
        this.s1 = s1;
    }

    @Override
    public void run() {
        ObjectOutputStream out = null;
        ObjectInputStream in = null;

        try {
            out = new ObjectOutputStream(s1.getOutputStream());
            in = new ObjectInputStream(s1.getInputStream());

            // Obter login e senha do cliente
            String login = (String) in.readObject();
            String senha = (String) in.readObject();

            Usuario usuario = ctrlUsu.findUsuario(login, senha);
            if (usuario == null) {
                out.writeObject("Usuario invalido, conexao encerrada.");
                out.flush();
                return;
            }

            out.writeObject("Usuario autenticado com sucesso.");
            out.flush();

            while (true) {
                try {
                    String comando = (String) in.readObject();
                    if (comando.equalsIgnoreCase("L")) {
                        List<Produto> produtos = ctrl.findProdutoEntities();
                        out.writeObject(produtos);
                        out.flush();
                    } else if (comando.equalsIgnoreCase("S")) {
                        out.writeObject("Conexao encerrada.");
                        out.flush();
                        break;
                    } else {
                        out.writeObject("Comando desconhecido.");
                        out.flush();
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
                if (s1 != null) {
                    s1.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
