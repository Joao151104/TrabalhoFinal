package cadastros;

import app.*;
import java.util.ArrayList;
import java.util.List;

public class CadastroTurma {
    private List<Turma> turmas;
    private CadastroProfessor cadastroProfessor;
    private CadastroDisciplina cadastroDisciplina;

    public CadastroTurma(CadastroProfessor cadProf, CadastroDisciplina cadDisc) {
        turmas = new ArrayList<>();
        cadastroProfessor = cadProf;
        cadastroDisciplina = cadDisc;
    }

    public int cadastrarTurma(Turma t) {
        // Verifica se já existe uma turma com o mesmo código
        for (Turma teste : turmas) {
            if (teste.getCodigoTurma().equalsIgnoreCase(t.getCodigoTurma())) {
                return -3; // Já existe turma com este código
            }
        }

        // Verifica se a disciplina associada existe
        Disciplina disciplinaVerificadora = cadastroDisciplina.pesquisarDisciplina(t.getDisciplina());
        if (disciplinaVerificadora == null) {
            return -4; // A disciplina não existe
        }

        // Verifica se há professores cadastrados no sistema
        if (cadastroProfessor.listarTodosProfessores().isEmpty()) {
            return -5; // Não há professores cadastrados no sistema
        }

        // Verifica se o professor associado existe no sistema
        Professor professorVerificador = cadastroProfessor.pesquisarProfessor(t.getProfessorAssociado());
        if (professorVerificador == null) {
            return -6; // Professor não encontrado com a matrícula fornecida
        }

        // Adiciona a turma se todas as verificações foram bem-sucedidas
        boolean ok = turmas.add(t);
        return ok ? turmas.size() : -7; // Código de erro genérico
    }

    public Turma procurarTurma(String codigoTurma) {
        for (Turma t : turmas) {
            if (t.getCodigoTurma().equalsIgnoreCase(codigoTurma)) {
                return t;
            }
        }
        return null; // Turma não encontrada
    }

    public boolean excluirTurma(Turma t) {
        return turmas.remove(t);
    }

    public List<Turma> getTurmas() {
        return new ArrayList<>(turmas);
    }
}
