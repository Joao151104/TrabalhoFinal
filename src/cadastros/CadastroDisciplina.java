package cadastros;

import app.Disciplina;
import java.util.ArrayList;
import java.util.List;


public class CadastroDisciplina {
    
    private List<Disciplina> disciplinas;

    public CadastroDisciplina(){
        disciplinas = new ArrayList<>();
    }

    public int cadastrarDisciplina(Disciplina d){
         boolean cadastrou = disciplinas.add(d);
        return cadastrou ? disciplinas.size() : -1;
    }

    public Disciplina pesquisarDisciplina(String nomeDisciplina, String codigoDisciplina) {
        for (Disciplina d : disciplinas) {
            if (d.getNomeDisciplina().equalsIgnoreCase(nomeDisciplina) && d.getCodigoDisciplina().equalsIgnoreCase(codigoDisciplina)) {
                return d;
            }
        }
        return null;
    }

    public boolean removerDisciplina(Disciplina d){

        return disciplinas.remove(d);
    }

    public boolean atualizarDisciplina(String nomeDisciplina, String codigoDisciplina, Disciplina d){

        Disciplina remover = pesquisarDisciplina(nomeDisciplina, codigoDisciplina);
        if(remover != null){
            disciplinas.remove(remover);
            return disciplinas.add(d);
        }
        return false;

    }

    public List<Disciplina> listarTodasDisciplinas(){

        return new ArrayList<>(disciplinas);
    }
}
