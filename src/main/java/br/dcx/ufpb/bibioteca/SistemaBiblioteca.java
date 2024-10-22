package br.dcx.ufpb.bibioteca;


import br.dcx.ufpb.bibioteca.exception.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import br.dcx.ufpb.bibioteca.exception.LivroJaExisteException;
import br.dcx.ufpb.bibioteca.exception.UsuarioJaExisteException;
import br.dcx.ufpb.bibioteca.gravador.GravaDados;
import br.dcx.ufpb.bibioteca.objects.*;

public class SistemaBiblioteca implements SistemaInterfaceBiblioteca {
    private HashMap<String, Livro> livros = new HashMap<>();
    private List<Usuario> usuarios = new ArrayList<>();
    private List<Emprestimo> emprestimos = new ArrayList<>();
    private GravaDados gravador = new GravaDados();

    /**
     * Esse método irá funcionar para  adicionar livros no estoque da biblioteca para serem emprestados
     * @param titulo
     * @param generoLivro
     * @param autor
     * @param codLivro
     * @return ele retorna um valor verdadeiro(true) caso o cadastro de um livro foi realizado com
     * sucesso
     * @throws LivroJaExisteException essa exeção sera lançada casa o livro que desejamos cadastrar já exista na
     * bilbioteca
     */
    public boolean cadastrarLivro(String titulo, GeneroLivro generoLivro, String autor, String codLivro) throws LivroJaExisteException {
        if (!this.livros.containsKey(codLivro)) {
            Livro novoLivro = new Livro(titulo, generoLivro, autor, codLivro);
            livros.put(codLivro, novoLivro);
            return true;
        } else {
            throw new LivroJaExisteException("Já existe esse livro no sistema.");
        }
    }

    /**
     * Método que realiza o cadastro de um usuário no sistema
     * @param nome
     * @param matricula
     * @param email
     * @return  retorna um True para sinalizar que o usuário foi cadastrado no sistema
     * @throws UsuarioJaExisteException essa exeção sera lançada casa o usuario que desejamos cadastrar já exista no
     * sistema da bilbioteca
     */
    public boolean cadastrarUsuario(String nome, String matricula, String email) throws UsuarioJaExisteException {
        Usuario novoUsuario = new Usuario(nome, matricula, email);
        if (!this.usuarios.contains(novoUsuario)) {
            this.usuarios.add(novoUsuario);
            return true;
        } else {
            throw new UsuarioJaExisteException("Usuário já cadastrado no sistema.");
        }
    }

    /**
     * Método que percorre o map dos livros que foram cadastrados, e irá colocar todos os
     * livros que tenham o mesmo título que foi passado como parâmetro e coloca-los em uma Coleção
     * @param titulo
     * @return  irá retornar uma coleção de todos os livros cadastrados com o título deles sendo iqual
     * ao titulo que foi passado como parâmetro
     */
    public Collection<Livro> buscarLivroPorTitulo(String titulo) {
        return this.livros.values().stream().filter(livro -> livro.getTitulo().startsWith(titulo)).toList();
    }

    /**
     * ele vai percocorer o map que contém o conjunto dos livros que foram cadastrados, e irá colocar todos os
     * livros que tenham o mesmo gênero que foi passado como parâmetro e coloca-los em uma Coleção
     * @param generoLivro
     * @return  irá retornar uma coleção de todos os livros cadastrados com o gênero deles sendo iqual
     * ao tipo de gênero  que foi passado como parâmetro
     */
    public Collection<Livro> buscarLivrosPorGenero(GeneroLivro generoLivro) {
        Collection<Livro> livrosPorGenero = new ArrayList<>();
        for (Livro livro: this.livros.values()) {
            if (livro.getGeneroLivro() == generoLivro) {
                livrosPorGenero.add(livro);
            }
        } return livrosPorGenero;
    }

    /**
     * esse método vai receber como parâmetro o código de um livro que com ele vai pesquisar no map de livros
     * até achar o livro que tenha o código e o achando ele vai remove-lo do map
     * @param codLivro
     * @return vai retornar uma expreção boleana true que serve para sinalizar que a operação de remoção foi
     * efetuada com êxito
     * @throws LivroNaoExisteException essa exeção vai ser disparada se o código que foi passado como parâmetro
     * não tiver nenhum livro que tenha um código dele iqual ao parâmetro
     */
    public boolean removerLivro(String codLivro) throws LivroNaoExisteException {
        if (this.livros.containsKey(codLivro)) {
            this.livros.remove(codLivro);
            return true;
        } else {
            throw new LivroNaoExisteException("Não existe livro no sistema com o código informado.");
        }
    }

    /**
     * o método realiza a função de empréstimo de um livro para um usuário cadastrado no sistema da biblioteca
     * para que isso acontece ele passa a matrícula para identificar o usuário que vai estar pegando o livro,
     * o titulo do livro para ser informado qual livro foi emprestado e o período que o usuário pode ficar com o livro
     * @param matricula
     * @param codigoLivro
     * @param periodoEmprestimo
     */
    @Override
    public boolean realizarEmprestimo(String matricula, String codigoLivro, PeriodoEmprestimo periodoEmprestimo) throws LivroNaoExisteException, MatriculaNaoEncontradaException{
        if(livros.containsKey(codigoLivro)){
            for(Usuario u: usuarios){
                if(u.getMatricula().equals(matricula)){
                    Emprestimo emprestimo = new Emprestimo(matricula, codigoLivro, periodoEmprestimo);
                    this.emprestimos.add(emprestimo);
                    return true;
                }
            }
            throw new MatriculaNaoEncontradaException("Matrícula não cadastrada no sistema.");
        } else{
            throw new LivroNaoExisteException("Livro não cadastrado no sistema.");
        }
    }

    /**
     * esse método vai receber como parâmetro a matrícula de um usuário de uma biblioteca que quer pesquisar  o
     * emprestimo que um usuário da matrícula realizou
     * @param matricula
     * @return ele vai retornar uma coleção com o empréstimo que foi realizado pelo usuario com a matrícula
     * iqual a passada como parâmetro
     * @throws MatriculaNaoEncontradaException essa exeção irá acontecer quando não existir um usuário
     * cadastrado no sistema com a matrícula iqual a passada como parâmetro
     */
    public Emprestimo buscarEmprestimoPorMatricula(String matricula)throws MatriculaNaoEncontradaException{
        return this.emprestimos.stream().filter(emprestimo -> emprestimo.getMatricula().equals(matricula)).findFirst().orElseThrow(() -> new MatriculaNaoEncontradaException("Matrícula não encontrada."));
    }

    /**
     * Esse método vai receber um dado como parâmetro do tipo int que vai ser o mês, então com esse parâmetro iremos
     * percorrer o map de empréstimos para achar todos os emprestimos que foram realizados no mês que foi passado como
     * parâmetro e achando cada empréstimo que foi realizado neste mês iremos colocá-lo em uma collection.
     * @param mes
     * @return ele vai retornar uma coleção que contém os empréstimos que aconteceram durante o mês passado como parâmetro
     * @throws MesInformadoNaoExisteException essa exeção irá acontecer quando o mês passado como parâmetro for menor que
     * 1 e maior que 12, pois não existem mês no calendário que corespondam com essea números
     */
    public Collection<Emprestimo> buscarEmprestimosRealizadosNoMes(int mes) throws MesInformadoNaoExisteException {
        Collection<Emprestimo> emprestimosRealizadosNoMes = new ArrayList<>();
        if (mes < 1 || mes > 12) {
            throw new MesInformadoNaoExisteException("O mês digitado não existe.");
        }
        for (Emprestimo emp: this.emprestimos) {
            if (emp.getPeriodoEmprestimo().getDataEmprestimo().getMonthValue() == mes) {
                emprestimosRealizadosNoMes.add(emp);
            }
        } return emprestimosRealizadosNoMes;
    }


// GRAVA DADOS

    /**
     *  método que faz a leitura dos empréstimos salvos ao iniciar o sistema
     */
    public void lerDados(){
        try {
            this.emprestimos = gravador.leEmprestimos();
            this.livros = gravador.leLivros();
            this.usuarios = gravador.leUsuarios();
        }catch (IOException e){
            this.emprestimos = new ArrayList<>();
            this.livros = new HashMap<>();
            this.usuarios = new ArrayList<>();
        }
    }

    /**
     * Método para salvar os empréstimos feitos durante a operação do sistema quando o programa for finalizado
     */
    public void  gravardados(){
        try {
            gravador.gravarEmprestimos(this.emprestimos);
            gravador.gravarLivros(this.livros);
            gravador.gravarUsuario(this.usuarios);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

}




