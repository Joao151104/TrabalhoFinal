package app;

public class Aluno extends PessoaFisica {
    private String matricula;
    private String curso;

    public Aluno(String nome, String cpf, String email, String matricula, String curso) {
        super(nome, cpf, email);
        this.matricula = matricula;
        this.curso = curso;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getCurso() {
        return curso;
    }


   

    @Override
    public String toString() {
        return "NOME: " + getNome() + '\n' +
               "CPF: " + getCpf() + '\n' +
               "EMAIL: " + getEmail() + '\n' +
               "MATRICULA: " + matricula + '\n' +
               "CURSO: " + curso + '\n';
    }
}
