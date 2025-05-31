import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

// Classe Produto
class Produto {
    int id;
    String nome;
    double preco;
    String categoria;

    Produto(int id, String nome, double preco, String categoria) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
    }
}

// Classe Cliente
class Cliente {
    int id;
    String nome;
    double credito;

    Cliente(int id, String nome, double credito) {
        this.id = id;
        this.nome = nome;
        this.credito = credito;
    }
}

// Classe principal com GUI
public class SistemaCadastroGUI extends JFrame {
    HashMap<Integer, Produto> produtos = new HashMap<>();
    HashMap<Integer, Cliente> clientes = new HashMap<>();

    public SistemaCadastroGUI() {
        setTitle("Sistema de Cadastro");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        JButton btnProduto = new JButton("Cadastrar Produto");
        JButton btnCliente = new JButton("Cadastrar Cliente");
        JButton btnCompra = new JButton("Efetuar Compra");
        JButton btnSair = new JButton("Sair");

        btnProduto.addActionListener(e -> cadastrarProduto());
        btnCliente.addActionListener(e -> cadastrarCliente());
        btnCompra.addActionListener(e -> efetuarCompra());
        btnSair.addActionListener(e -> System.exit(0));

        add(btnProduto);
        add(btnCliente);
        add(btnCompra);
        add(btnSair);
    }

    private void cadastrarProduto() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("ID do Produto:"));
            String nome = JOptionPane.showInputDialog("Nome do Produto:");
            double preco = Double.parseDouble(JOptionPane.showInputDialog("Preço:"));
            String categoria = JOptionPane.showInputDialog("Categoria:");
            produtos.put(id, new Produto(id, nome, preco, categoria));
            JOptionPane.showMessageDialog(this, "Produto cadastrado com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar produto!");
        }
    }

    private void cadastrarCliente() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("ID do Cliente:"));
            String nome = JOptionPane.showInputDialog("Nome do Cliente:");
            double credito = Double.parseDouble(JOptionPane.showInputDialog("Crédito:"));
            clientes.put(id, new Cliente(id, nome, credito));
            JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar cliente!");
        }
    }

    private void efetuarCompra() {
        try {
            int idProduto = Integer.parseInt(JOptionPane.showInputDialog("ID do Produto:"));
            int idCliente = Integer.parseInt(JOptionPane.showInputDialog("ID do Cliente:"));

            Produto produto = produtos.get(idProduto);
            Cliente cliente = clientes.get(idCliente);

            if (produto == null || cliente == null) {
                JOptionPane.showMessageDialog(this, "Produto ou Cliente não encontrado!");
                return;
            }

            if (cliente.credito >= produto.preco) {
                cliente.credito -= produto.preco;
                JOptionPane.showMessageDialog(this, "Compra realizada com sucesso!\nNovo crédito: R$ " + cliente.credito);
            } else {
                JOptionPane.showMessageDialog(this, "Crédito insuficiente para realizar a compra!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao efetuar compra!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SistemaCadastroGUI gui = new SistemaCadastroGUI();
            gui.setVisible(true);
        });
    }
}
