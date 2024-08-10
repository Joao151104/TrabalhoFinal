package view;

import app.Turma;
import cadastros.CadastroTurma;
import java.awt.*;
import java.util.List;
import javax.swing.*;

public class MenuTurmas {

    public static Turma dadosNovaTurma() {
        JTextField codTurmaField = new JTextField(3);
        JTextField salaField = new JTextField(3);
        JTextField matriculaFUBField = new JTextField(30);
        JTextField codigoDiscField = new JTextField(7);
        JTextField qtdMaxAlunosField = new JTextField(3);

        while (true) {
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

            if (confirma == JOptionPane.OK_OPTION) {
                String codTurma = codTurmaField.getText();
                String sala = salaField.getText();
                String matriculaFUB = matriculaFUBField.getText();
                String codDisciplina = codigoDiscField.getText();
                String recebeQtdMaxAlunos = qtdMaxAlunosField.getText();

                boolean codTurmaValido = codTurma.replace(" ", "").length() >= 2;
                boolean salaValida = sala.length() >=2;
                boolean matriculaFUBValida = matriculaFUB.replace(".", "").replace("-", "").replace(" ", "").length() <= 10;
                boolean codDisciplinaValido = codDisciplina.replace(" ", "").replace(".", "").replace("-", "").length() == 7;
                boolean qtdMaxAlunosValida = false;

                try {
                    int qtdMaxAlunos = Integer.parseInt(recebeQtdMaxAlunos);
                    qtdMaxAlunosValida = qtdMaxAlunos <= 120;
                } catch (NumberFormatException e) {
                    qtdMaxAlunosValida = false;
                }

                if (codTurmaValido && salaValida && matriculaFUBValida && codDisciplinaValido && qtdMaxAlunosValida) {
                    int qtdMaxAlunos = Integer.parseInt(recebeQtdMaxAlunos);
                    return new Turma(codTurma, sala, matriculaFUB, codDisciplina, qtdMaxAlunos);
                } else {
                    StringBuilder msgDeErro = new StringBuilder("Dados inválidos:\n");
                    if (!codTurmaValido) msgDeErro.append(" - O código da turma deve conter exatamente 3 caracteres: TXX.\n");
                    if (!salaValida) msgDeErro.append(" - A sala deve conter exatamente 3 caracteres: SXX ou IXX.\n");
                    if (!codDisciplinaValido) msgDeErro.append(" - O código da disciplina deve conter exatamente 3 letras e 4 dígitos. Exemplo: FGA0158.\n");
                    if (!qtdMaxAlunosValida) msgDeErro.append(" - A quantidade máxima de alunos por turma é 120. Neste campo só vão dígitos.\n");
                    JOptionPane.showMessageDialog(null, msgDeErro.toString());

                    // Retorna com os campos preenchidos
                    codTurmaField.setText(codTurma);
                    salaField.setText(sala);
                    matriculaFUBField.setText(matriculaFUB);
                    codigoDiscField.setText(codDisciplina);
                    qtdMaxAlunosField.setText(recebeQtdMaxAlunos);
                    continue;  // Retorna ao loop para reexibir a tela com os inputs preenchidos
                }
            } else {
                return null;
            }
        }
    }

    private static String lerCodigoTurma() {
        while (true) {
            String codTurma = JOptionPane.showInputDialog("Insira o código da turma: ");
            if (codTurma == null) {
                return null; // Trata o caso do botão "Cancelar"
            }
            if (codTurma.length() >= 2) {
                return codTurma;
            } else {
                JOptionPane.showMessageDialog(null, "Inválido. O código da turma deve conter exatamente 3 caracteres: TX.");
            }
        }
    }

    private static void listarTurmasCadastradas(CadastroTurma cadTurma) {
        List<Turma> turmas = cadTurma.getTurmas();
        if (turmas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ainda não há nenhuma turma cadastrada.");
        } else {
            StringBuilder listaTurmas = new StringBuilder("Turmas cadastradas:\n\n");
            for (Turma turma : turmas) {
                listaTurmas.append(turma.toString()).append('\n');
            }
            JOptionPane.showMessageDialog(null, listaTurmas.toString(), "Lista de turmas.", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void menuTurma(CadastroTurma cadTurma) {
        while (true) {
            String opcoes = "Informe a opção desejada:\n"
                    + "1 - Adicionar turma\n"
                    + "2 - Apagar turma\n"
                    + "3 - Adicionar ou remover alunos à turma\n"
                    + "4 - Procurar turma\n"
                    + "5 - Listar todas as turmas cadastradas\n"
                    + "0 - Voltar";
            int opcao = -1;
            do {
                String strOpcao = JOptionPane.showInputDialog(opcoes);
                if (strOpcao == null) {
                    return; // Trata o caso do botão "Cancelar"
                }
                try {
                    opcao = Integer.parseInt(strOpcao);
                } catch (NumberFormatException n) {
                    JOptionPane.showMessageDialog(null, "Opção inválida. Por favor, insira um dígito válido.");
                    continue;
                }

                switch (opcao) {
                    case 1:
                        Turma novaTurma = dadosNovaTurma();
                        if (novaTurma != null) {
                            int sucesso = cadTurma.cadastrarTurma(novaTurma);
                            switch (sucesso) {
                                case -3:
                                    JOptionPane.showMessageDialog(null, "Erro: Já existe uma turma cadastrada com esse código. Por favor, insira um código de turma diferente.");
                                    break;
                                case -4:
                                
                                    break;
                                case -5:
                                    JOptionPane.showMessageDialog(null, "Erro: Não há professores cadastrados no sistema. Por favor, cadastre pelo menos um professor antes de adicionar uma turma.");
                                    break;
                                case -6:
                                    JOptionPane.showMessageDialog(null, "Erro: Professor não encontrado com a matrícula fornecida. Verifique se a matrícula está correta ou cadastre o professor no sistema.");
                                    break;
                                case -7:
                                    JOptionPane.showMessageDialog(null, "Erro: Ocorreu um erro ao cadastrar a turma. Tente novamente.");
                                    break;
                                default:
                                    JOptionPane.showMessageDialog(null, "Turma adicionada com sucesso.");
                                    break;
                            }
                        }
                        break;
                    case 2:
                        String codigoTurma = lerCodigoTurma();
                        if (codigoTurma == null) {
                            break; // Sai do loop se o usuário cancelar
                        }
                        Turma excluir = cadTurma.procurarTurma(codigoTurma);
                        if (excluir != null) {
                            boolean apagou = cadTurma.excluirTurma(excluir);
                            if (apagou) {
                                JOptionPane.showMessageDialog(null, "Turma removida do sistema com sucesso.");
                            } else {
                                JOptionPane.showMessageDialog(null, "Erro: Não foi possível remover a turma. Tente novamente.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Erro: Não foi encontrada turma com esse código.");
                        }
                        break;
                    case 3:
                        JOptionPane.showMessageDialog(null, "Essa funcionalidade ainda não está implementada.");
                        break;
                    case 4:
                        String codTur = lerCodigoTurma();
                        if (codTur == null) {
                            break; // Sai do loop se o usuário cancelar
                        }
                        Turma turmaProcura = cadTurma.procurarTurma(codTur);
                        if (turmaProcura != null) {
                            JOptionPane.showMessageDialog(null, turmaProcura.toString());
                        } else {
                            JOptionPane.showMessageDialog(null, "Erro: Turma não cadastrada.");
                        }
                        break;
                    case 5:
                        listarTurmasCadastradas(cadTurma);
                        break;
                    case 0:
                        return;
                    default:
                        JOptionPane.showMessageDialog(null, "Opção inválida. Por favor, insira um dígito válido.");
                }
            } while (opcao < 0 || opcao > 5);
        }
    }
}
