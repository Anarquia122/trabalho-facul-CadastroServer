/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastroserver.run;

import cadastroserver.controller.ProdutoJpaController;
import cadastroserver.controller.UsuarioJpaController;
import cadastroserver.controller.PessoaJpaController;
import cadastroserver.controller.MovimentoJpaController;
import cadastroserver.model.Movimento;
import cadastroserver.model.Pessoa;

import cadastroserver.model.Produto;
import cadastroserver.model.Usuario;
import java.io.IOException;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class CadastroThreadV2 extends Thread {
    private ProdutoJpaController ctrl;
    private UsuarioJpaController ctrlUsu;
    private PessoaJpaController ctrlPess;
    private MovimentoJpaController ctrlMov;
    private Socket s1;

    public CadastroThreadV2(ProdutoJpaController ctrl, UsuarioJpaController ctrlUsu, PessoaJpaController ctrlPess, MovimentoJpaController ctrlMov, Socket s1) {
        this.ctrl = ctrl;
        this.ctrlUsu = ctrlUsu;
        this.ctrlPess = ctrlPess;
        this.ctrlMov = ctrlMov;
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
                    } else if (comando.equalsIgnoreCase("X")) {
                        out.writeObject("Conexao encerrada.");
                        out.flush();
                        break;
                    } else if (comando.equalsIgnoreCase("E") || comando.equalsIgnoreCase("S")) {
                        Movimento movimento = new Movimento();
                        movimento.setIdusuario(usuario);
                        movimento.setTipo(comando);

                        Integer idPessoa = (Integer) in.readObject();
                        Pessoa pessoa = ctrlPess.findPessoa(idPessoa);
                        movimento.setIdpessoa(pessoa);

                        Integer idProduto = (Integer) in.readObject();
                        Produto produto = ctrl.findProdutoById(idProduto);
                        movimento.setIdproduto(produto);

                        Integer quantidade = (Integer) in.readObject();
                        movimento.setQuantidade(quantidade);

                        BigDecimal valorUnitario = (BigDecimal) in.readObject();
                        movimento.setValorunitario(valorUnitario);

                        movimento.setDatamovimento(new Date());

                        ctrlMov.create(movimento);

                        if (comando.equalsIgnoreCase("S")) {
                            Integer quantidadeAtual = produto.getQuantidade() - quantidade;
                            produto.setQuantidade(quantidadeAtual);
                            try {
                                ctrl.edit(produto);
                            } catch (Exception e) {
                                System.out.println("Erro ao atualizar produto.");
                                e.printStackTrace();
                            }
                        } else {
                            Integer quantidadeAtual = produto.getQuantidade() + quantidade;
                            produto.setQuantidade(quantidadeAtual);
                            try {
                                ctrl.edit(produto);
                            } catch (Exception e) {
                                System.out.println("Erro ao atualizar produto.");
                                e.printStackTrace();
                            }
                        }
                        out.writeObject("Movimento registrado com sucesso.");
                        out.flush();
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