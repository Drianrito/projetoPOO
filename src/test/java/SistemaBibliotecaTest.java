import br.dcx.ufpb.bibioteca.objects.GeneroLivro;
import br.dcx.ufpb.bibioteca.objects.PeriodoEmprestimo;
import br.dcx.ufpb.bibioteca.SistemaBiblioteca;
import br.dcx.ufpb.bibioteca.exception.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
public class SistemaBibliotecaTest {

    private  SistemaBiblioteca sistemaBiblioteca;

    @Test
    public void TestaCadastro_Emprestimo(){
        sistemaBiblioteca  = new SistemaBiblioteca();
        try{
            sistemaBiblioteca.cadastrarUsuario("samuel","5203","marvel@gmail.com");
            sistemaBiblioteca.cadastrarLivro("O rei", GeneroLivro.FANTASIA, "J. R. R. Tolkien","100");
            LocalDate dataEmpertimo= LocalDate.of(2024,10,9);
            LocalDate dateDevolucao= LocalDate.of(2024,11,9);
            PeriodoEmprestimo periodoEmprestimo= new PeriodoEmprestimo(dataEmpertimo,dateDevolucao);
            sistemaBiblioteca.realizarEmprestimo("5203","100",periodoEmprestimo);
            assertEquals(sistemaBiblioteca.buscarEmprestimosRealizadosNoMes(10).size(),1);
        }catch (UsuarioJaExisteException | LivroJaExisteException | MesInformadoNaoExisteException |
                MatriculaNaoEncontradaException | LivroNaoExisteException e){
            fail(" Essa execção não deveria acontecer" + e.getMessage());
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
            sistemaBiblioteca.cadastrarLivro("Computação simplificada",GeneroLivro.BIOGRAFIA,"Rebeca","2002");
            assertEquals(sistemaBiblioteca.buscarLivrosPorGenero(GeneroLivro.BIOGRAFIA).size(),2);
            assertFalse(sistemaBiblioteca.buscarLivrosPorGenero(GeneroLivro.AUTOAJUDA).size()==3);
        }catch ( LivroJaExisteException e ) {
            fail("Este Teste não deveria lançar essa execção");
        }}

            @Test
            public void TestaCadastro_BuscaDeEmprestimosRealizadosNoMes(){
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
                        sistemaBiblioteca.realizarEmprestimo("202401","2099",periodoEmprestimo1);
                        sistemaBiblioteca.realizarEmprestimo("202402","1457",periodoEmprestimo2);
                        assertTrue(sistemaBiblioteca.buscarEmprestimosRealizadosNoMes(9).size()==2);
                    }
                catch (LivroJaExisteException|UsuarioJaExisteException|MesInformadoNaoExisteException e){
                        fail("Este Teste não deveria lançar essa exeção");
                } catch (MatriculaNaoEncontradaException | LivroNaoExisteException e) {
                    fail("Este teste não deveria lançar essa exeção");
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
