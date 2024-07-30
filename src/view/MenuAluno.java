package view;

import app.Aluno;
import cadastros.CadastroAluno;
import java.awt.*;
import javax.swing.*;

public class MenuAluno extends Exception {

    public static Aluno dadosNovoAluno() {
        JTextField nomeField = new JTextField(15);
        JTextField cpfField = new JTextField(11);
        JTextField emailField = new JTextField(20);
        JTextField matriculaField = new JTextField(9);
        JTextField cursoField = new JTextField(15);

        while (true) {
            // Cria um painel para conter todos os campos de entrada
            JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
            
            // Adiciona os componentes ao painel
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

                // Verifica se os dados são válidos
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

    // Outros métodos permanecem inalterados

    private static String lerCurso() {
        return JOptionPane.showInputDialog("Informe o curso do aluno: ");
    }

    private static String lerEmail() {
        return JOptionPane.showInputDialog("Informe o email do aluno: ");
    }

    private static String lerCPF() {
        while (true) {
            try {
                String cpf = JOptionPane.showInputDialog("Informe o CPF do aluno: ");
                if (cpf != null && cpf.length() == 11) {
                    return cpf;
                } else {
                    JOptionPane.showMessageDialog(null, "CPF inválido. Deve conter 11 dígitos.");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao ler o CPF. Tente novamente.");
            }
        }
    }

    private static String lerNome() {
        while(true)
            try {
                String nome = JOptionPane.showInputDialog("Informe o nome do aluno: ");
                if (nome != null && nome.matches("[a-zA-Z ]+") && nome.replace(" ", "").length() >= 4){ 
                    return nome; 
                } else { 
                    JOptionPane.showMessageDialog(null, "Insira um nome válido.");
                }   
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao ler o nome. Tente novamente.");
            }
            
    }

    private static String lerMatricula() {
        while (true) { 
            try {
                String matricula =  JOptionPane.showInputDialog("Informe a matrícula do aluno: ");
                if(matricula != null && matricula.matches("\\d{9}")){
                    return matricula; 
                } else{
                    JOptionPane.showMessageDialog(null, "Matrícula inválida. Deve conter 9 dígitos.");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao ler a matrícula. Tente novamente.");
            }
        }
    }
        
    public static void menuAluno(CadastroAluno cadAluno) {
        String txt = "Informe a opção desejada \n"
                + "1 - Cadastrar aluno\n"
                + "2 - Pesquisar aluno\n"
                + "3 - Atualizar aluno\n"
                + "4 - Remover aluno\n"
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

                default:
                    break;
            }
        } while (opcao != 0);
    }
}
