package view;

import app.Disciplina;
import cadastros.CadastroDisciplina;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.*;



public class MenuDisciplina {

    public static Disciplina dadosNovaDisciplina(){

        JTextField nomeDisciplinaField = new JTextField(30);
        JTextField codigoDisciplinaField = new JTextField(7);

        while (true) {
            JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
            
            panel.add(new JLabel("Nome da Disciplina:"));
            panel.add(nomeDisciplinaField);
            panel.add(new JLabel("Código da Disciplina:"));
            panel.add(codigoDisciplinaField);

            int result = JOptionPane.showConfirmDialog(null, panel, "Dados da nova disciplina", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                String nomeDisciplina = nomeDisciplinaField.getText();
                String codigoDisciplina = codigoDisciplinaField.getText();

                boolean nomeDisciplinaValido = nomeDisciplina.replace(" ", "").length() >= 1;
                boolean codigoDisciplinaValido = codigoDisciplina.matches("[a-zA-Z0-9]{1,7}");

                if (nomeDisciplinaValido && codigoDisciplinaValido) {
                    return new Disciplina(nomeDisciplina, codigoDisciplina);
                } else {
                    StringBuilder mensagemErro = new StringBuilder("Dados inválidos:\n");
                    if (!nomeDisciplinaValido) mensagemErro.append(" - O campo 'nome da disciplina' não pode estar em branco.\n");
                    if (!codigoDisciplinaValido) mensagemErro.append(" - O campo 'código da disciplina' não pode estar em branco e deve conter 3 letras e 7 dígitos.\n");
                    JOptionPane.showMessageDialog(null, mensagemErro.toString());
                }
            } else {
                return null;
            }
        }
    }
    


    private static void listarTodasDisciplinas(CadastroDisciplina cadDisciplina) {
        List<Disciplina> disciplinas = cadDisciplina.listarTodasDisciplinas();
        if (disciplinas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma disciplina cadastrada.");
        } else {
            StringBuilder listaDisciplinas = new StringBuilder("Disciplinas cadastradas:\n\n");
            for (Disciplina disciplina : disciplinas) {
                listaDisciplinas.append(disciplina.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, listaDisciplinas.toString(), "Lista de disciplinas", JOptionPane.INFORMATION_MESSAGE);
    }
}

public static void menuDisciplina(CadastroDisciplina cadDisciplina){

    String txt = "Informe a opção desejada \n"
    + "1 - Cadastrar disciplina\n"
    + "2 - Pesquisar disciplina\n"
    + "3 - Atualizar disciplina\n"
    + "4 - Remover disciplina\n"
    + "5 - Ver todas as disciplinas listadas\n"
    + "0 - Voltar para menu anterior";

int opcao = -1;
do{
    String strOpcao = JOptionPane.showInputDialog(txt);
    opcao = Integer.parseInt(strOpcao);

    switch (opcao) {
        case 1:
            Disciplina novaDisciplina = dadosNovaDisciplina();
            if(novaDisciplina != null){
               int resultado = cadDisciplina.cadastrarDisciplina(novaDisciplina);
               switch(resultado) {
                    case -1:
                        JOptionPane.showMessageDialog(null, "Já existe uma disciplina cadastrada com esse código.");
                        break;
                    case -2:
                        JOptionPane.showMessageDialog(null, "Ocorreu um erro ao cadastrar a disciplina. Código -2.");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Disciplina cadastrada.");
                        break;
               }
            }
            break;
        
        case 2:
            String codigoDisciplina = lerCodigoDisciplina();
            Disciplina d = cadDisciplina.pesquisarDisciplina(codigoDisciplina);
            if(d != null){
                JOptionPane.showMessageDialog(null, d.toString());
            }
            break;

        case 3:
            codigoDisciplina = lerCodigoDisciplina();
            Disciplina atualizarDisciplina = dadosNovaDisciplina();
            if (atualizarDisciplina != null){
                boolean atualizada = cadDisciplina.atualizarDisciplina(codigoDisciplina, atualizarDisciplina);
                if (atualizada){
                    JOptionPane.showMessageDialog(null, "Cadastro atualizado.");
                } else {
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro ao atualizar a disciplina.");
                }
            }
            break;
        
        case 4:
            codigoDisciplina = lerCodigoDisciplina();
            Disciplina remover = cadDisciplina.pesquisarDisciplina(codigoDisciplina);
            boolean removida = cadDisciplina.removerDisciplina(remover);
            if(removida){
                JOptionPane.showMessageDialog(null, "Disciplina removida do cadastro");
            } else {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao remover a disciplina.");
            }
            break;

        case 5:
            listarTodasDisciplinas(cadDisciplina);
            break;
        default:
            break;
     }
    } while (opcao != 0);

}

private static String lerNomeDisciplina(){

    while (true) { 
        try{
            String nomeDisciplina = JOptionPane.showInputDialog("Informe o nome da Disciplina: ");
            if(nomeDisciplina != null && !nomeDisciplina.trim().isEmpty()){
                return nomeDisciplina;
            } else{
                JOptionPane.showMessageDialog(null, "Disciplina inválida. Não aceita campo em branco.");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao ler a disciplina. Tente novamente.");
             }
        }
    }

    private static String lerCodigoDisciplina(){

        while (true) { 
            try{
                String codigoDisciplina = JOptionPane.showInputDialog("Informe o codigo da Disciplina: ");
                if(codigoDisciplina != null && codigoDisciplina.matches("[a-zA-Z0-9]{1,7}")){
                    return codigoDisciplina;
                } else{
                    JOptionPane.showMessageDialog(null, "Disciplina inválida. Codigo da disciplina deve conter 7 digitos.");
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Erro ao ler a disciplina. Tente novamente.");
                 }
            }
        }
}