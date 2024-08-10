package cadastros;

import app.*;
import cadastros.*;

import java.util.ArrayList;
import java.util.List;

public class CadastroTurma{
    private List <Turma> turmas; //declara a ref de uma lista a ser implementada
    private CadastroProfessor cadastroProfessor;
    private CadastroDisciplina cadastroDisciplina;

    public CadastroTurma(CadastroProfessor cadProf, CadastroDisciplina cadDisc) {
        turmas = new ArrayList<>(); //cria uma lista (para as turmas) referenciado por "turmas"
        cadastroProfessor = cadProf;
        cadastroDisciplina = cadDisc;
    }

    //para criar uma turma, eu preciso, ao mesmo tempo de UM professor e UM codigo de disciplina
    public int cadastrarTurma(Turma t) {

        for(Turma teste : turmas) { //verifica se ja existe uma turma com o codigo inserido
            if(teste.getCodigoTurma().equalsIgnoreCase(t.getCodigoTurma())){
                return -3; //msg de erro "Ja existe turma com este codigo"
            }
        }

        Disciplina disciplinaVerificadora = cadastroDisciplina.pesquisarDisciplina(t.getDisciplina()); //verifica se disciplina existe
        if(disciplinaVerificadora == null) {
            return -4; //msg de erro "A disciplina nao existe"
        }

        if(cadastroProfessor.listarTodosProfessores().isEmpty()) {
            return -5; //msg de erro "Nao ha professores cadastrados no sistema"
        }

        Professor professorVerificador = cadastroProfessor.pesquisarProfessor(t.getProfessorAssociado());
        if(professorVerificador == null) {
            return -6; //msg de erro "Professor nao cadastrado"
        }

        boolean ok = turmas.add(t);
        if(ok) {
            return turmas.size();
        } else {
            return -7;
        }
    }

    public Turma procurarTurma(String codigoTurma) {
        for(Turma t : turmas) { //roda toda a lista atras da turma
            if(t.getCodigoTurma().equalsIgnoreCase(codigoTurma)) {
                return t; //retorna a turma que bater
            }
        }
        return null; //se nao bater nenhuma, retorna vazio (implementar o aviso)
    }

    public boolean excluirTurma(Turma t) {
        return turmas.remove(t);
    }

    public List<Turma> getTurmas() {
        return new ArrayList<>(turmas); //retorna todas as turmas;
    }
}