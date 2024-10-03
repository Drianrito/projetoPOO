import br.dcx.ufpb.bibioteca.Emprestimo;
import br.dcx.ufpb.bibioteca.SistemaBiblioteca;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.*;

public class SistemaBibliotecaTest {

   private  SistemaBiblioteca sistemaBiblioteca;

    @BeforeEach
    public void setUp() {
       sistemaBiblioteca  = new SistemaBiblioteca();
    }

    @Test
    public void testapesquisa() {
        sistemaBiblioteca.lerEmprestimos();
       Emprestimo item = sistemaBiblioteca.pesquisarEmprestimo("O Senhor dos Anéis");
       assertEquals("O Senhor dos Anéis", item.getLivro());

    }




}

