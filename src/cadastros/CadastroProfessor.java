package cadastros;

import app.Professor;
import java.util.ArrayList;
import java.util.List;

public class CadastroProfessor {
    private List<Professor> professores;

    public CadastroProfessor() {
        professores = new ArrayList<>();
    }

    public int cadastrarProfessor(Professor p) {
        boolean cadastrou = professores.add(p);
        return cadastrou ? professores.size() : -1;
    }

    public Professor pesquisarProfessor(String matriculaFUB) {
        for (Professor p : professores) {
            if (p.getMatriculaFUB().equalsIgnoreCase(matriculaFUB)) {
                return p;
            }
        }
        return null;
    }

    public boolean removerProfessor(Professor p) {
        return professores.remove(p);
    }

    public boolean atualizarProfessor(String matriculaFUB, Professor p) {
        Professor remover = pesquisarProfessor(matriculaFUB);
        if (remover != null) {
            professores.remove(remover);
            return professores.add(p);
        }
        return false;
    }

    public List<Professor> listarTodosProfessores() {
        return new ArrayList<>(professores);
    }
}
