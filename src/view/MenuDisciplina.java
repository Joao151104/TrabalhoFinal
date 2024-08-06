package view;

import app.Disciplina;
import cadastros.CadastroDisciplina;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.*;



public class MenuDisciplina {

    public static Disciplina dadosNovaDisciplina(){

        JTextField nomeDisciplinaField = new JTextField(20);
        JTextField codigoDisciplinaField = new JTextField(9);

        while (true) {
            JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
            
            panel.add(new JLabel("Nome da Disciplina:"));
            panel.add(nomeDisciplinaField);
            panel.add(new JLabel("Codigo da Disciplina:"));
            panel.add(codigoDisciplinaField);

            int result = JOptionPane.showConfirmDialog(null, panel, "Dados da Nova Disciplina", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                String nomeDisciplina = nomeDisciplinaField.getText();
                String codigoDisciplina = codigoDisciplinaField.getText();

                boolean nomeDisciplinaValido = nomeDisciplina.replace(" ", "").length() >= 4;
                boolean codigoDisciplinaValido = codigoDisciplina.matches("\\d{7}");

                if (nomeDisciplinaValido && codigoDisciplinaValido) {
                    return new Disciplina(nomeDisciplina, codigoDisciplina);
                } else {
                    StringBuilder mensagemErro = new StringBuilder("Dados inválidos:\n");
                    if (!nomeDisciplinaValido) mensagemErro.append(" - O campo nome da disciplina não podem estar em branco.\n");
                    if (!codigoDisciplinaValido) mensagemErro.append(" - O campo código da disciplina não podem estar em branco e deve conter 7 dígitos.\n");
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

        JOptionPane.showMessageDialog(null, listaDisciplinas.toString(), "Lista de Disciplinas", JOptionPane.INFORMATION_MESSAGE);
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
                cadDisciplina.cadastrarDisciplina(novaDisciplina);
            }
            break;
        
        case 2:
            String nomeDisciplina = lerNomeDisciplina();
            String codigoDisciplina = lerCodigoDisciplina();
            Disciplina d = cadDisciplina.pesquisarDisciplina(nomeDisciplina, codigoDisciplina);
            if(d != null){
                JOptionPane.showMessageDialog(null, d.toString());
            }  
            break; 

        case 3:
            nomeDisciplina = lerNomeDisciplina();
            codigoDisciplina = lerCodigoDisciplina();
            Disciplina atualizarDisciplina = dadosNovaDisciplina();
            if (atualizarDisciplina != null){
                boolean atualizada = cadDisciplina.atualizarDisciplina(nomeDisciplina, codigoDisciplina, atualizarDisciplina);
             if (atualizada){
                JOptionPane.showMessageDialog(null, "Cadastro atualizado.");
            }
        }
            break;
        
        case 4:
            nomeDisciplina = lerNomeDisciplina();
            codigoDisciplina = lerCodigoDisciplina();
            Disciplina remover = cadDisciplina.pesquisarDisciplina(nomeDisciplina, codigoDisciplina);
            boolean removida = cadDisciplina.removerDisciplina(remover);
            if(removida){
                JOptionPane.showMessageDialog(null, "Disciplina removida do cadastro");
                System.gc();
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
                if(codigoDisciplina != null && codigoDisciplina.matches("\\d{7}")){
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

