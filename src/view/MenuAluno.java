package view;

import app.Aluno;
import cadastros.CadastroAluno;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.*;

public class MenuAluno extends Exception {

    public static Aluno dadosNovoAluno() {
        JTextField nomeField = new JTextField(15);
        JTextField cpfField = new JTextField(11);
        JTextField emailField = new JTextField(20);
        JTextField matriculaField = new JTextField(9);
        JTextField cursoField = new JTextField(15);

        while (true) {
            JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
            
            panel.add(new JLabel("Nome:"));
            panel.add(nomeField);
            panel.add(new JLabel("CPF:"));
            panel.add(cpfField);
            panel.add(new JLabel("Email:"));
            panel.add(emailField);
            panel.add(new JLabel("Matrícula:"));
            panel.add(matriculaField);
            panel.add(new JLabel("Curso:"));
            panel.add(cursoField);

            int result = JOptionPane.showConfirmDialog(null, panel, "Dados do Novo Aluno", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                String nome = nomeField.getText();
                String cpf = cpfField.getText();
                String email = emailField.getText();
                String matricula = matriculaField.getText();
                String curso = cursoField.getText();

                boolean nomeValido = nome.matches("[a-zA-Z ]+") && nome.replace(" ", "").length() >= 4;
                boolean cpfValido = cpf.matches("\\d{11}");
                boolean matriculaValida = matricula.matches("\\d{9}");
                
                if (nomeValido && cpfValido && matriculaValida) {
                    return new Aluno(nome, cpf, email, matricula, curso);
                } else {
                    StringBuilder mensagemErro = new StringBuilder("Dados inválidos:\n");
                    if (!nomeValido) mensagemErro.append(" - Nome deve conter apenas letras e ter no mínimo 4 letras.\n");
                    if (!cpfValido) mensagemErro.append(" - CPF deve conter 11 dígitos.\n");
                    if (!matriculaValida) mensagemErro.append(" - Matrícula deve conter 9 dígitos.\n");
                    JOptionPane.showMessageDialog(null, mensagemErro.toString());
                }
            } else {
                return null;
            }
        }
    }

    private static void listarTodosAlunos(CadastroAluno cadAluno) {
        List<Aluno> alunos = cadAluno.listarTodosAlunos();
        if (alunos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum aluno cadastrado.");
        } else {
            StringBuilder listaAlunos = new StringBuilder("Alunos cadastrados:\n\n");
            for (Aluno aluno : alunos) {
                listaAlunos.append(aluno.toString()).append("\n");
            }
            JOptionPane.showMessageDialog(null, listaAlunos.toString(), "Lista de Alunos", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void menuAluno(CadastroAluno cadAluno) {
        String txt = "Informe a opção desejada \n"
                + "1 - Cadastrar aluno\n"
                + "2 - Pesquisar aluno\n"
                + "3 - Atualizar aluno\n"
                + "4 - Remover aluno\n"
                + "5 - Ver todos os alunos do sistema\n"
                + "0 - Voltar para menu anterior";

        int opcao = -1;
        do {
            String strOpcao = JOptionPane.showInputDialog(txt);
            opcao = Integer.parseInt(strOpcao);

            switch (opcao) {
                case 1:
                    Aluno novoAluno = dadosNovoAluno();
                    if (novoAluno != null) {
                        cadAluno.cadastrarAluno(novoAluno);
                    }
                    break;

                case 2:
                    String matricula = lerMatricula();
                    Aluno a = cadAluno.pesquisarAluno(matricula);
                    if (a != null)
                        JOptionPane.showMessageDialog(null, a.toString());
                    break;

                case 3:
                    matricula = lerMatricula();
                    Aluno novoCadastro = dadosNovoAluno();
                    if (novoCadastro != null) {
                        boolean atualizado = cadAluno.atualizarAluno(matricula, novoCadastro);
                        if (atualizado) {
                            JOptionPane.showMessageDialog(null, "Cadastro atualizado.");
                        }
                    }
                    break;

                case 4:
                    matricula = lerMatricula();
                    Aluno remover = cadAluno.pesquisarAluno(matricula);
                    boolean removido = cadAluno.removerAluno(remover);
                    if (removido) {
                        JOptionPane.showMessageDialog(null, "Aluno removido do cadastro");
                        System.gc();
                    }
                    break;
                
                case 5:
                    listarTodosAlunos(cadAluno);
                    break;

                default:
                    break;
            }
        } while (opcao != 0);
    }

    private static String lerMatricula() {
        while (true) {
            try {
                String matricula = JOptionPane.showInputDialog("Informe a matrícula do aluno: ");
                if (matricula != null && matricula.matches("\\d{9}")) {
                    return matricula;
                } else {
                    JOptionPane.showMessageDialog(null, "Matrícula inválida. Deve conter 9 dígitos.");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao ler a matrícula. Tente novamente.");
            }
        }
    }
}
