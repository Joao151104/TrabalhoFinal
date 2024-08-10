package app;

import java.util.ArrayList;
import java.util.List;

import app.Aluno;
import app.Professor;
import app.Disciplina;

public class Turma {
    private String codigoTurma, sala;
    private String professor;
    private String materia;
    private int qtdMaxAlunos;
    private List <Aluno> alunoAssociado;
    private Aluno aluno;

    public Turma(String cT, String s, String matriculaFUB, String codDisc, int qtdMA) {
        codigoTurma = cT;
        sala = s;
        professor = matriculaFUB;
        materia = codDisc;
        qtdMaxAlunos = qtdMA;
        alunoAssociado = new ArrayList<>();
    }

    public String getCodigoTurma() {
        return codigoTurma;
    }

    public String getSala() {
        return sala;
    }

    public String getProfessorAssociado() {
        return professor;
    }

    public String getDisciplina() {
        return materia;
    }

    public int getQtdMaxAlunos() {
        return qtdMaxAlunos;
    }

    public int adicionaAluno() { //implementar sistema para evitar duplicatas
        if(alunoAssociado.size() < qtdMaxAlunos) {
            alunoAssociado.add(aluno);
            return 99; //msg sucesso "Aluno adicionado"
        } else {
            return -1; //msg de erro "Nao foi possivel adicionar aluno"
        }
    }

    /* public int removeAluno() {
        ...
    } */

    public List<Aluno> getAlunoAssociado() {
        return alunoAssociado;
    }
    
    @Override //por causa dos "toString" de professor, aluno e disciplina
    public String toString() {
        return  "Código da turma: " + getCodigoTurma() + '\n' +
                "Sala: " + getSala() + '\n' +
                "Código da disciplina: " + getDisciplina() + '\n' +
                "Matrícula FUB do professor: " + getProfessorAssociado() + '\n' +
                "Quantidade máxima de alunos: " + getQtdMaxAlunos() + '\n' +
                "Alunos: " + getAlunoAssociado().toString() + '\n';
    }
}