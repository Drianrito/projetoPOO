import br.dcx.ufpb.bibioteca.Emprestimo;
import br.dcx.ufpb.bibioteca.GeneroLivro;
import br.dcx.ufpb.bibioteca.PeriodoEmprestimo;
import br.dcx.ufpb.bibioteca.SistemaBiblioteca;
import br.dcx.ufpb.bibioteca.exception.LivroJaExisteException;
import br.dcx.ufpb.bibioteca.exception.LivroNaoExisteException;
import br.dcx.ufpb.bibioteca.exception.MesInformadoNaoExisteException;
import br.dcx.ufpb.bibioteca.exception.UsuarioJaExisteException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SistemaBibliotecaTest {

   private  SistemaBiblioteca sistemaBiblioteca;

    @Test
    public void TestaCadastro_Imprestimo(){
        sistemaBiblioteca  = new SistemaBiblioteca();
        try{
            sistemaBiblioteca.cadastrarUsuario("samuel","5203","marvel@gmail.com");
            sistemaBiblioteca.cadastrarLivro("O rei", GeneroLivro.FANTASIA, "J. R. R. Tolkien","100");
            LocalDate dataEmpertimo= LocalDate.of(2024,10,9);
            LocalDate dateDevolucao= LocalDate.of(2024,11,9);
            PeriodoEmprestimo periodoEmprestimo= new PeriodoEmprestimo(dataEmpertimo,dateDevolucao);
            sistemaBiblioteca.realizarEmprestimo("5203","O rei",periodoEmprestimo);
            assertEquals(sistemaBiblioteca.buscarEmprestimosRealizadosNoMes(10).size(),1);

        }catch (UsuarioJaExisteException | LivroJaExisteException | MesInformadoNaoExisteException e){
            fail(" essa exeção não deveria acontecer" + e.getMessage());
        }
    }

    @Test
    public void TestaCadastro_Remocao(){
        sistemaBiblioteca  = new SistemaBiblioteca();
        try{
            sistemaBiblioteca.cadastrarLivro("A vida nas Estrelas", GeneroLivro.ROMANCE, "Jessica","8943");
            assertTrue(sistemaBiblioteca.removerLivro("8943"));
        }catch (LivroNaoExisteException | LivroJaExisteException e ){
            fail("Este Teste não deveria lançar essa exeção");
        }
    }

    @Test
    public void TestaCadastro_PesquisaPORGeneroDosLivros(){
        sistemaBiblioteca  = new SistemaBiblioteca();
        try{
            sistemaBiblioteca.cadastrarLivro("Culinaria vegana",GeneroLivro.AUTOAJUDA,"Antonio","1962");
            sistemaBiblioteca.cadastrarLivro("Desvendando a mente Autista",GeneroLivro.AUTOAJUDA,"Michele","1981");
            sistemaBiblioteca.cadastrarLivro("TDAH",GeneroLivro.BIOGRAFIA,"Samuel","2004");
            sistemaBiblioteca.cadastrarLivro("Combutaçõa simplificada",GeneroLivro.BIOGRAFIA,"Rebeca","2002");
            assertEquals(sistemaBiblioteca.buscarLivrosPorGenero(GeneroLivro.BIOGRAFIA).size(),2);
            assertFalse(sistemaBiblioteca.buscarLivrosPorGenero(GeneroLivro.AUTOAJUDA).size()==3);
        }catch ( LivroJaExisteException e ){
            fail("Este Teste não deveria lançar essa exeção");
        }
    }

    @Test
    public void TestaCadastro_BuscaDeImpertimosRealizadosNoMes(){
        sistemaBiblioteca  = new SistemaBiblioteca();
        try {
            sistemaBiblioteca.cadastrarLivro("Imperio do sol",GeneroLivro.FANTASIA,"Arthur","2099");
            sistemaBiblioteca.cadastrarLivro("Star Wars",GeneroLivro.FICCAO_CIENTIFICA,"Lucas","1457");

            sistemaBiblioteca.cadastrarUsuario("Matheus","202401","Brazil@gmail.com");
            sistemaBiblioteca.cadastrarUsuario("Victor","202402","Europa@gmail.com");

            LocalDate dataEmpertimo1= LocalDate.of(2024,9,11);
            LocalDate dateDevolucao1= LocalDate.of(2024,10,18);
            PeriodoEmprestimo periodoEmprestimo1= new PeriodoEmprestimo(dataEmpertimo1,dateDevolucao1);

            LocalDate dataEmpertimo2= LocalDate.of(2024,9,15);
            LocalDate dateDevolucao2= LocalDate.of(2024,12,10);
            PeriodoEmprestimo periodoEmprestimo2= new PeriodoEmprestimo(dataEmpertimo2,dateDevolucao2);

            sistemaBiblioteca.realizarEmprestimo("202401","Imperio do sol",periodoEmprestimo1);
            sistemaBiblioteca.realizarEmprestimo("202402","Star wars",periodoEmprestimo2);

            assertTrue(sistemaBiblioteca.buscarEmprestimosRealizadosNoMes(9).size()==2);

        }catch (LivroJaExisteException|UsuarioJaExisteException|MesInformadoNaoExisteException e){
            fail("Este Teste não deveria lançar essa exeção");

        }
    }

    @Test
    public void TestaCadastro_BuscaPorTitulo(){
        sistemaBiblioteca  = new SistemaBiblioteca();
        try{
            sistemaBiblioteca.cadastrarLivro("O senhor dos aneis:A furia de mordor",GeneroLivro.FANTASIA,"tokin","2018");
            sistemaBiblioteca.cadastrarLivro("O senhor dos aneis: o um anel",GeneroLivro.FANTASIA,"tokin","1942");

            assertEquals(sistemaBiblioteca.buscarLivroPorTitulo("O senhor dos aneis").size(),2);
        }catch(LivroJaExisteException e ){
            fail("Este Teste não deveria lançar essa exeção");
        }

        }

    }


