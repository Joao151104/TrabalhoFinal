import cadastros.CadastroAluno;
import javax.swing.JOptionPane;
import view.MenuAluno;
import view.MenuPrincipal;

public class Principal {

	static CadastroAluno cadAluno;
	
	public static void main(String[] args) {
		cadAluno = new CadastroAluno();
		
		int opcao = 0; 
		
		do {
			opcao = MenuPrincipal.menuOpcoes(); 
			switch (opcao) {
				case 1 -> MenuAluno.menuAluno(cadAluno);
				case 2 -> JOptionPane.showMessageDialog(null, "Cadastro de professores a ser implementado");
				case 3 -> JOptionPane.showMessageDialog(null, "Cadastro de disciplinas a ser implementado");
				case 4 -> JOptionPane.showMessageDialog(null, "Cadastro de turmas a ser implementado");
				case 0 -> {
                        }
				default -> {
                                    JOptionPane.showMessageDialog(null, "Opcao invalida");
                                    opcao = -1;
                        }
			}
		} while (opcao != 0);
	}


}
