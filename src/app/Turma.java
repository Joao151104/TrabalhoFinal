package app;

import java.util.ArrayList;
import java.util.List;

import app.Aluno;
import app.Professor;
import app.Disciplina;

public class Turma {
    private String codigoTurma, sala;
    private List <Professor> professorAssociado;
    private List <Aluno> alunoAssociado;
    private int qtdMaxAlunos;
    private int qtdMaxProfessores;
    private Disciplina materia;

    public Turma(String cT, String s, Disciplina d, int qtdMA, int qtdMP) {
        codigoTurma = cT;
        sala = s;
        materia = d;
        qtdMaxAlunos = qtdMA;
        qtdMaxProfessores = qtdMP;
        professorAssociado = new ArrayList<>();
        alunoAssociado = new ArrayList<>();
    }

    public int adicionaProfessor() {
        if(professorAssociado.size() < qtdMaxProfessores) {
            professorAssociado.add(Professor p)
            return 99; //implementar msg dizendo "professor adicionado"
        } else {
            return -1; //msg de erro dizendo "nao foi possivel add professor"
        }
    }

    public List<Professor> getProfessorAssociado() {
        return professorAssociado;
    }

    public String getCodigoTurma() {
        return codigoTurma;
    }

    public String getSala() {
        return sala;
    }

    public int adicionaAluno() {
        if(alunoAssociado.size() < qtdMaxAlunos) {
            alunoAssociado.add(Aluno a)
            return 98; //implementar msg dizendo "aluno add"
        } else {
            return -2; //implementar msg dizendo "nao foi possivel add aluno"
        }
    }

    public List<Aluno> getAlunoAssociado() {
        return alunoAssociado;
    }

    public int getQtdMaxAlunos() {
        return qtdMaxAlunos;
    }

    public int getQtdMaxProfessores() {
        return qtdMaxProfessores;
    }

    public Disciplina getDisciplina() {
        return materia;
    }
    
    @Override //por causa dos "toString" de professor, aluno e disciplina
    public String toString() {
        return  "TURMA: " + getCodigoTurma() + '\n' +
                "SALA: " + getSala() + '\n' +
                "DISCIPLINA: " + getDisciplina() + '\n' +
                "PROFESSOR(ES): " + getProfessorAssociado().toString() + '\n' +
                "ALUNO(S): " + getAlunoAssociado().toString() + '\n' +
                "QUANTIDADE MAXIMA DE PROFESSORES: " + getQtdMaxProfessores() + '\n' +
                "QUANTIDADE MAXIMA DE ALUNOS: " + getQtdMaxAlunos() + '\n';
    }
}