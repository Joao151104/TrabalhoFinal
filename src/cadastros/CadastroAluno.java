package cadastros;

import app.Aluno;
import java.util.ArrayList;
import java.util.List;

public class CadastroAluno {
    private List<Aluno> alunos;

    public CadastroAluno() {
        alunos = new ArrayList<>();
    }

    public int cadastrarAluno(Aluno a) {
        boolean cadastrou = alunos.add(a);
        return cadastrou ? alunos.size() : -1;
    }

    public Aluno pesquisarAluno(String matriculaAluno) {
        for (Aluno a : alunos) {
            if (a.getMatricula().equalsIgnoreCase(matriculaAluno)) {
                return a;
            }
        }
        return null;
    }

    public boolean removerAluno(Aluno a) {
        return alunos.remove(a);
    }

    public boolean atualizarAluno(String matricula, Aluno a) {
        Aluno remover = pesquisarAluno(matricula);
        if (remover != null) {
            alunos.remove(remover);
            return alunos.add(a);
        }
        return false;
    }

    public List<Aluno> listarTodosAlunos() {
        return new ArrayList<>(alunos);
    }
}
