import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;

    public class Formulario extends JFrame {
        private final HashMap<String, Cliente> clientes;
        private final JTextField nomeField;
        private final JTextField cpfField;
        private final JTextField celularField;
        private final JTextField emailField;
        private final DefaultTableModel tableModel;

        public Formulario() {
            clientes = new HashMap<>();

            setTitle("Formulário de Clientes");
            setSize(600, 400);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

            JPanel cadastroPanel = new JPanel(new GridLayout(6, 2, 8, 9));
            cadastroPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
            cadastroPanel.add(new JLabel("Nome:"));
            nomeField = new JTextField();
            cadastroPanel.add(nomeField);
            cadastroPanel.add(new JLabel("CPF:"));
            cpfField = new JTextField();
            cadastroPanel.add(cpfField);
            cadastroPanel.add(new JLabel("Celular:"));
            celularField = new JTextField();
            cadastroPanel.add(celularField);
            cadastroPanel.add(new JLabel("Email:"));
            emailField = new JTextField();
            cadastroPanel.add(emailField);

            JButton salvarButton = new JButton("Salvar");
            salvarButton.addActionListener(e -> salvarCliente());
            cadastroPanel.add(salvarButton);

            JButton limparButton1 = new JButton("Limpar");
            limparButton1.addActionListener(e -> limparCampos());
            cadastroPanel.add(limparButton1);

            JPanel pesquisaPanel = new JPanel(new BorderLayout());
            JPanel pesquisaFormPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            pesquisaFormPanel.setBorder(BorderFactory.createEmptyBorder(8, 6, 6, 8));
            pesquisaFormPanel.add(new JLabel("CPF:"));
            JTextField cpfPesquisaField = new JTextField(15);
            pesquisaFormPanel.add(cpfPesquisaField);
            JButton buscarButton = new JButton("Buscar");
            buscarButton.addActionListener(e -> buscarCliente(cpfPesquisaField.getText()));
            pesquisaFormPanel.add(buscarButton);
            JButton limparButton2 = new JButton("Limpar");
            limparButton2.addActionListener(e -> {
                cpfPesquisaField.setText("");
                limparResultados();
            });
            pesquisaFormPanel.add(limparButton2);
            pesquisaPanel.add(pesquisaFormPanel, BorderLayout.NORTH);

            tableModel = new DefaultTableModel();
            tableModel.addColumn("ID");
            tableModel.addColumn("Nome");
            tableModel.addColumn("CPF");
            tableModel.addColumn("Celular");
            tableModel.addColumn("Email");
            JTable table = new JTable(tableModel);

            JScrollPane scrollPane = new JScrollPane(table);
            pesquisaPanel.add(scrollPane, BorderLayout.CENTER);

            JTabbedPane tabbedPane = new JTabbedPane();
            tabbedPane.addTab("Cadastro", cadastroPanel);
            tabbedPane.addTab("Pesquisa", pesquisaPanel);

            getContentPane().add(tabbedPane);
        }

        private void salvarCliente() {
            String nome = nomeField.getText().trim();
            String cpf = cpfField.getText().trim();
            String celular = celularField.getText().trim();
            String email = emailField.getText().trim();

            if (!validarEntradas(nome, cpf, celular, email)) {
                JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos corretamente.");
                return;
            }

            Cliente cliente = new Cliente(nome, cpf, celular, email);
            clientes.put(cpf, cliente);

            tableModel.addRow(new Object[]{clientes.size(), nome, cpf, celular, email});

            JOptionPane.showMessageDialog(this, "Cliente salvo com sucesso!");
            limparCampos();
        }

        private boolean validarEntradas(String nome, String cpf, String celular, String email) {
            return !nome.isEmpty() && !cpf.isEmpty() && validarCPF(cpf) && !celular.isEmpty() && validarEmail(email);
        }

        private boolean validarCPF(String cpf) {
            return cpf.matches("\\d{11}");
        }

        private boolean validarEmail(String email) {
            return email.contains("@") && email.contains(".");
        }

        private void buscarCliente(String cpf) {
            if (cpf.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, digite um CPF válido.");
                return;
            }

            Cliente cliente = clientes.get(cpf);
            if (cliente != null) {
                JOptionPane.showMessageDialog(this, "Cliente encontrado:\n" + cliente);
            } else {
                JOptionPane.showMessageDialog(this, "Cliente não encontrado.");
            }
        }

        private void limparCampos() {
            nomeField.setText("");
            cpfField.setText("");
            celularField.setText("");
            emailField.setText("");
        }

        private void limparResultados() {
            tableModel.setRowCount(0);
        }
    }