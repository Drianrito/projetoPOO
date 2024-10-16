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

import java.io.IOException;
import java.util.*;

public class SistemaBiblioteca implements SistemaInterfaceBiblioteca {
    private HashMap<String, Livro> livros = new HashMap<>();
    private List<Usuario> usuarios = new ArrayList<>();
    private List<Emprestimo> emprestimos = new ArrayList<>();
    private GravaDados gravador = new GravaDados();

    /**
     * Esse metodo ira funcionar para que se posam adicionar livros no estoque da biblioteca para serem emprestados
     * @param titulo
     * @param generoLivro
     * @param autor
     * @param codLivro
     * @return ele retorna um valor verdadeiro(true) caso o livro o novo cadastro de um livro foi realizadp com
     * sucesso
     * @throws LivroJaExisteException essa exeção sera lançada casa o livro que desejamos cadastrar ja exista na
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
     *Esse metodo vai colocar nos arquivos do sistema de uma biblioteca o cadastro de um usuario para que ele possa
     * pegar os realisar o impertio de livros da biblioteca
     * @param nome
     * @param matricula
     * @param email
     * @return ele vai retorna um True que funciona com um sinal de que não tinha o ususario cadastrado antes no sistema da
     * biblioteca,e ao memso tempo confirma que os cadastro do novo usuario foi realizado com suceso
     * @throws UsuarioJaExisteException essa exeção sera lançada casa o usuario que desejamos cadastrar ja exista no
     * sistema da bilbioteca
     */
    public boolean cadastrarUsuario(String nome, String matricula, String email) throws UsuarioJaExisteException {
        Usuario novoUsuario = new Usuario(nome, matricula, email);
        if (!this.usuarios.contains(novoUsuario)) {
            this.usuarios.add(novoUsuario);
            return true;
        } else {
            throw new UsuarioJaExisteException("Já existe esse usuário no sistema.");
        }
    }

    /**
     * ele vai percocorer o map que contem o conjunto dos livros que foram cadastrados, e ira colocar todos os
     * livros que tenham o mesmo titulo que foi pasado como parametro e colocalos em uma Coleção
     * @param titulo
     * @return ele ira retornar uma coleção de todos os livros cadastrados com o titulo deles sendo iqual
     * a o titulo que foi passado como parametro
     */
    public Collection<Livro> buscarLivroPorTitulo(String titulo) {
        return this.livros.values().stream().filter(livro -> livro.getTitulo().startsWith(titulo)).toList();
    }

    /**
     * ele vai percocorer o map que contem o conjunto dos livros que foram cadastrados, e ira colocar todos os
     * livros que tenham o mesmo genero que foi pasado como parametro e colocalos em uma Coleção
     * @param generoLivro
     * @return ele ira retornar uma coleção de todos os livros cadastrados com o genero deles sendo iqual
     * a o tipo de genero  que foi passado como parametro
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
     * esse metodo vai receber como parametro o codigo de um livro que com ele vai pesquisar no map de livros
     * ate achar o livro que tenha o codigo e o achando ele vai removelo do map ou seja dos arquivos
     * @param codLivro
     * @return vai retornar uma expreção boleana true que serve para intentificar que a operação de remoção foi
     * efetuada com exito
     * @throws LivroNaoExisteException essa exeção fai ser disparada se o codigo que foi passado com parametro
     * não tiver nenum livro que tenha um codigo dele iqual a o parametro ou seja não existe nos arquivos
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
     * o metodo realizar a função de imprertimo de um livro para um ususario cadastrado no sistema da biblioteca
     * para que isso acontece ele pasa a matricula para intentificar o usuario que vai estar pegando o livro,
     * o titulo do livro para ser informado qual livro foi impertado e o periodo que o usuario pode ficar com o livro
     * @param matricula
     * @param tituloLivro
     * @param periodoEmprestimo
     */
    public void realizarEmprestimo(String matricula, String tituloLivro, PeriodoEmprestimo periodoEmprestimo) {
        Emprestimo emprestimo = new Emprestimo(matricula, tituloLivro, periodoEmprestimo);
        this.emprestimos.add(emprestimo);
    }

    /**
     * esse metodo vai receber como parametro a matricula de um aluno/usuario de uma biblioteca que quer pesquisar todos os
     * impertimos que um susuario da matricula que foi pasada realizou
     * @param matricula
     * @return ele vai retornar uma coleçãocom totos os impertimos que foram realizados pelo usuario com a matricula
     * iqual a pasada como parametro
     * @throws MatriculaNaoEncontradaException essa exeção ira acontecer quando não existir um ususario
     * cadastrada no sistema com a matricual iqual a pasada como parametro
     */
    public Emprestimo buscarEmprestimoPorMatricula(String matricula)throws MatriculaNaoEncontradaException{ // TODO: atualizar método para retornar uma lista
        return this.emprestimos.stream().filter(emprestimo -> emprestimo.getMatricula().equals(matricula)).findFirst().orElse(null);
    }

    /**
     * Esse metodo vai receber um dado como parametro do tipo int que vai ser o mes, então com esse parametro irremos
     * percorer o map de empertimos para achar todos os emprestimos que foram realizados no mes que foi pasado como
     * parametro e achando cada empertimo que foi realizaod neste mes iremos colocalo em uma collectio.
     * @param mes
     * @return ele vai retonar uma coleção qeu contem dos os empertimos que aconteceram duram o mes pasado como parametro
     * @throws MesInformadoNaoExisteException essa exeção ira acontecer quanto o mes que foi passado como parametro for menor que
     * 1 e maior que 12 pois não existem mes no calendario que corespondão com esse numeros
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
     * Esse metodo ira ter a função de ler os empertimos que aconteceram em todas as operações de empertimo com
     * isso ele posibilita saber quem efetuol cada empertimo de livros que estão cadastrados na biblioteca e qual
     * dos usuarios o pegou empertado
     */
    public void lerEmprestimos(){
        try {
            this.emprestimos = gravador.leEmprestimos();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Esse metodo vai gravar quando cada operação de empertimo e  realizada por um usuario cadastrado no sistema
     * da biblioteca que livro ele pegou e qual o praso que devolução deste livro com isso ele tem a importante
     * função de arquivar as informações de empertimos realizados
     */
    public void  gravarEmprestimos(){
        try {
            gravador.gravarEmprestimos(this.emprestimos);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

}




