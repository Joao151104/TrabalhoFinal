package view;

import app.Turma;
import cadastros.CadastroTurma;

import java.awt.GridLayout;
import java.util.List;

import javax.print.attribute.standard.JobPriority;
import javax.swing.*;

public class MenuTurmas {

    public static Turma dadosNovaTurma() {

        JTextField codTurmaField = new JTextField(3);
        JTextField salaField = new JTextField(3);
        JTextField matriculaFUBField = new JTextField(30);
        JTextField codigoDiscField = new JTextField(7);
        JTextField qtdMaxAlunosField = new JTextField(3);

        while(true) {
            JPanel painel = new JPanel(new GridLayout(0, 2, 10, 10));

            painel.add(new JLabel("Código da turma: "));
            painel.add(codTurmaField);
            painel.add(new JLabel("Sala: "));
            painel.add(salaField);
            painel.add(new JLabel("Matrícula FUB do professor: "));
            painel.add(matriculaFUBField);
            painel.add(new JLabel("Código da disciplina: "));
            painel.add(codigoDiscField);
            painel.add(new JLabel("Quantidade limite de alunos: "));
            painel.add(qtdMaxAlunosField);

            int confirma = JOptionPane.showConfirmDialog(null, painel, "Dados da nova turma", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if(confirma == JOptionPane.OK_OPTION) {
                String codTurma = codTurmaField.getText();
                String sala = salaField.getText();
                String matriculaFUB = matriculaFUBField.getText();
                String codDisciplina = codigoDiscField.getText();
                String recebeQtdMaxAlunos = qtdMaxAlunosField.getText();

                boolean codTurmaValido = codTurma.replace(" ", "").length() <= 3;
                boolean salaValida = sala.length() <= 3;
                boolean matriculaFUBValida = matriculaFUB.replace(".","").replace("-","").replace(" ", "").length() <= 10;
                boolean codDisciplinaValido = codDisciplina.replace(" ","").replace(".","").replace("-","").length() == 7;
                boolean qtdMaxAlunosValida = Integer.parseInt(recebeQtdMaxAlunos) <= 120;

                if(codTurmaValido & salaValida && matriculaFUBValida && codDisciplinaValido && qtdMaxAlunosValida) {
                    int qtdMaxAlunos = Integer.parseInt(recebeQtdMaxAlunos);
                    return new Turma(codTurma, sala, matriculaFUB, codDisciplina, qtdMaxAlunos);
                } else {
                    String msgDeErro = "Dados inválidos:\n";
                    if(codTurmaValido == false) msgDeErro += " - O código da turma contém apenas 3 caracteres: TXX.\n";
                    if(salaValida == false) msgDeErro += " - A sala contém apenas 3 caracteres: SXX ou IXX.\n";
                    if(codDisciplinaValido == false) msgDeErro += " - O código da disciplina contém apenas 3 letras e 4 digitos. Exemplo: FGA0158.\n";
                    if(qtdMaxAlunosValida == false) msgDeErro += " - A quantidade máxima de alunos por turma é 120. Neste campo só vão digitos.\n";
                    JOptionPane.showMessageDialog(null, msgDeErro);
                }
            } else {
                return null;
            }
        }
    }

    private static String lerCodigoTurma() {
        while(true) {
            String codTurma = JOptionPane.showInputDialog("Insira o código da turma: ");
            if(codTurma != null && codTurma.length() <= 3) {
                return codTurma;
            } else {
                JOptionPane.showMessageDialog(null, "Inválido. O código da turma contém apenas 3 caracteres: TXX.");
            }
        }
    }

    private static void listarTurmasCadastradas(CadastroTurma cadTurma) {
        List<Turma> turmas = cadTurma.getTurmas();
        if(turmas.isEmpty()){
            JOptionPane.showMessageDialog(null, "Ainda não há nenhuma turma cadastrada.");
        } else {
            String listaTurmas = "Turmas cadastradas:\n\n";
            for(Turma turmaProcura : turmas) {
                listaTurmas += turmas.toString() + '\n';
            }
            JOptionPane.showMessageDialog(null, listaTurmas.toString(), "Lista de turmas.", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void menuTurma(CadastroTurma cadTurma) {
        while(true) {
            String opcoes = "Informe a opção desejada:\n"
                            + "1 - Adicionar turma\n" //ok
                            + "2 - Apagar turma\n" //ok
                            + "3 - Adicionar ou remover alunos à turma\n" //implementar
                            + "4 - Procurar turma\n" //ok
                            + "5 - Listar todas as turmas cadastradas\n" //ok
                            + "0 - Voltar";
            int opcao = -19;
            do {
                String strOpcao = JOptionPane.showInputDialog(opcoes);
                try {
                    opcao = Integer.parseInt(strOpcao);
                } catch (NumberFormatException n) {
                    JOptionPane.showMessageDialog(null, "Opção inválida. Por favor, insira um digito válido.");
                    continue;
                }
            

                switch(opcao) {
                    case 1: 
                        Turma novaTurma = dadosNovaTurma();
                        if(novaTurma != null) {
                            int sucesso = cadTurma.cadastrarTurma(novaTurma);
                            switch (sucesso) {
                                case -3:
                                    JOptionPane.showMessageDialog(null, "Já existe uma turma cadastrada com esse código.");
                                    break;
                                case -4:
                                    //JOptionPane.showMessageDialog(null, "A disciplina não está cadastrada.");
                                    break;
                                case -5:
                                    JOptionPane.showMessageDialog(null, "Não há professores cadastrados. Cadastre pelo menos um professor antes de adicionar uma turma.");
                                    break;
                                case -6:
                                    JOptionPane.showMessageDialog(null, "O professor não está cadastrado.");
                                    break;
                                case -7:
                                    JOptionPane.showMessageDialog(null, "Ocorreu um erro ao cadastrar a turma. Código -7.");
                                default:
                                    JOptionPane.showMessageDialog(null, "Turma adicionada.");
                                    break;
                            }
                        }
                        break;
                    case 2:
                        String codigoTurma = lerCodigoTurma();
                        Turma excluir = cadTurma.procurarTurma(codigoTurma);
                        if(excluir != null) {
                            boolean apagou = cadTurma.excluirTurma(excluir);
                            if(apagou) {
                                JOptionPane.showMessageDialog(null, "Turma removida do sistema.");
                            } else {
                                JOptionPane.showMessageDialog(null, "Não foi possível remover a turma.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Não foi encontrada turma com esse código.");
                        }
                        break;
                    case 3:
                        break;
                    case 4:
                        String codTur = lerCodigoTurma();
                        Turma turmaProcura = cadTurma.procurarTurma(codTur);
                        if(turmaProcura != null) {
                            JOptionPane.showMessageDialog(null, turmaProcura.toString());
                        } else {
                            JOptionPane.showMessageDialog(null, "Turma não cadastrada.");
                        }
                        break;
                    case 5:
                        listarTurmasCadastradas(cadTurma);
                    case 0:
                        return;
                    default:
                        JOptionPane.showMessageDialog(null, "Opção inválida.");
                }
            } while(opcao != 0);
        }
    }
}