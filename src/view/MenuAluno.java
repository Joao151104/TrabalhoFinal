package view;

import app.Aluno;
import cadastros.CadastroAluno;
import javax.swing.JOptionPane;

public class MenuAluno {

    public static Aluno dadosNovoAluno() {
        String nome = lerNome();
        String cpf = lerCPF();
        String email = lerEmail();
        String matricula = lerMatricula();
        String curso = lerCurso();
        return new Aluno(nome, cpf, email, matricula, curso);
    }

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
        return JOptionPane.showInputDialog("Informe o nome do aluno: ");
    }

    private static String lerMatricula() {
        return JOptionPane.showInputDialog("Informe a matrícula do aluno: ");
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
                    cadAluno.cadastrarAluno(novoAluno);
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
                    boolean atualizado = cadAluno.atualizarAluno(matricula, novoCadastro);
                    if (atualizado) {
                        JOptionPane.showMessageDialog(null, "Cadastro atualizado.");
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
        return;
    }
}
