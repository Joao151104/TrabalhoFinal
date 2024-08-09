package cadastros;

import app.Turma;
import app.Disciplina;
import app.Professor;
import app.Aluno;
import cadastros.CadastroProfessor;
import cadastros.CadastroDisciplina;
import cadastros.CadastroAluno;

import java.util.ArrayList;
import java.util.List;

public class CadastroTurma{
    private List <Turma> turmas; //declara a ref de uma lista a ser implementada
    private CadastroProfessor cadastroProfessor;
    private CadastroDisciplina cadastroDisciplina;

    public CadastroTurma(CadastroProfessor cadProf, CadastroDisciplina cadDisc) {
        turmas = new ArrayList<>(); //cria uma lista referenciado por turma
        cadastroProfessor = cadProf;
        cadastroDisciplina = cadDisc;
    }

    //para criar uma turma, preciso, ao mesmo tempo, de um nome e codigo, pelo menos um professor e apenas uma disciplina
    public boolean verificaRequisitos(String codigoTurma, String codDisciplina) {
       Disciplina disciplina = cadastroDisciplina.pesquisarDisciplina(null, codDisciplina) //verifica se a turma existe procurando direto pelo codigo da mesma
        if(disciplina == null) {
            return false; // (implementar msg erro disc nao cadastrada)
        }

        if(cadastroProfessor.listarTodosProfessores().isEmpty()) {
            return = false; //(implementar msg erro nao ha professor)
        }

        return true; //se passar nos dois testes, pode criar a turma
    }

    public int cadastrarTurma(String codigoTurma, String sala, int qtdMaxAlunos, int qtdMaxProfessores, String codigoDisc) {
        for(Turma t : turmas) { //verifica se ja existe uma turma com o codigo inserido
            if(t.getCodigoTurma().equalsIgnoreCase(codigoTurma)){
                return -3; //sinaliza que ja existe uma turma com esse codigo
            }
        }
        if(verificaRequisitos(codigoTurma, codigoDisc)) {
            Disciplina disciplina = cadastroDisciplina.pesquisarDisciplina(null, codigoDisc);
            if(disciplina != null) {
                Turma atual = new Turma(codigoTurma, sala, qtdMaxAlunos, qtdMaxProfessores, disciplina);
                turmas.add(turma);
                return 97; //sucesso na adicao da turma                
            }
        } else {
            return -4; //sinaliza que a disciplina nao existe
        }
    }

    public Turmas procurarTurma(String codigoTurma) {
        for(Turma t : turmas) { //roda toda a lista atras da turma
            if(t.getCodigoTurma().equalsIgnoreCase(codigoTurma)) {
                return t; //retorna a turma que bater
            }
        }
        return null; //se nao bater nenhuma, retorna vazio (implementar o aviso)
    }

    public boolean excluirTurma(String codigoTurma) {
        for(Turma t : turmas) { //roda a lista de turmas todinha
            if(t.getCodigoTurma().equalsIgnoreCase(codigoTurma)) { //procura a que o codigo e igual
                turmas.remove(t); //apaga a turma
            }
        }
    }

    public List<Turma> getTurmas() {
        return turmas; //lista todas as turmas;
    }

}