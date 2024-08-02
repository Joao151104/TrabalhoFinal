package cadastros;

import app.Professor;

public class CadastroProfessor {
    private int numProfessores;
    private Professor[] professores;

    public CadastroProfessor() {
        numProfessores = 0;
        professores = new Professor[0];
    }

    public int cadastrarProfessor(Professor p) {
        Professor[] temporaria = new Professor[numProfessores + 1];
        for(int i = 0; i < professores.length; i++) {
            temporaria[i] = professores[i];
        }
        temporaria[temporaria.length - 1] = p;
        professores = temporaria;
        numProfessores++;
        return numProfessores;
    }

    public Professor pesquisarProfessor(String matriculaFUB) {
        Professor resposta = null;
        for(int i = 0; i < professores.length; i++) {
            Professor p = professores[i];
            String matFUB = p.getMatriculaFUB();
            if(matFUB.equalsIgnoreCase(matriculaFUB)) {
                return professores[i];
            }
        }
        return resposta;
    }

    public boolean removerProfessor(Professor p) {
        Professor remover = null;
        if (p != null) {
            remover = pesquisarProfessor(p.getMatriculaFUB());
        }
        if (remover == null) {
            return false;
        }

        Professor[] temporaria = new Professor[numProfessores - 1];
        int contador = 0;
        for (int i = 0; i < numProfessores; i++) {
            if (professores[i] != remover) {
                temporaria[contador] = professores[i];
                contador++;
            } else {
                professores[i] = null;
            }
        }
        professores = temporaria;
        numProfessores--;
        return true;
    }

    public boolean atualizarProfessor (String matriculaFUB, Professor p) {
        int contador;
        for(contador = 0; contador < 11; contador++) {
            if (professores[contador].getMatriculaFUB().equalsIgnoreCase(matriculaFUB)) {
                break;
            }
        }
        if (contador > numProfessores) {
            return false;
        } else {
            professores[contador] = p;
        }
        return true;
    }
}