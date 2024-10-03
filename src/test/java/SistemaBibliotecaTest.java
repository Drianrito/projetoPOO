import br.dcx.ufpb.bibioteca.SistemaBiblioteca;
import br.dcx.ufpb.bibioteca.exception.LivroJaExisteException;
import br.dcx.ufpb.bibioteca.exception.UsuarioJaExisteException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

/*public class SistemaBibliotecaTest {

   private  SistemaBiblioteca sistemaBiblioteca;

    @BeforeEach
    public void setUp() {
       sistemaBiblioteca  = new SistemaBiblioteca();
        try {
            sistemaBiblioteca.cadastrarUsuario("João", "1234", "xdrianxd@gmail.com");
            sistemaBiblioteca.cadastrarLivro("O Senhor dos Anéis", "J. R. R. Tolkien", "1234");
            sistemaBiblioteca.realizarEmprestimo("1234", "O Senhor dos Anéis", "01/01/2021", "01/02/2021");
            int tamanho = sistemaBiblioteca.getEmprestimos().size();
            assertEquals(tamanho,1);
            sistemaBiblioteca.gravarEmprestimos();
        } catch (UsuarioJaExisteException | LivroJaExisteException e) {
            fail("Exceção não esperada: " + e.getMessage());
        }
    }

    @Test
    public void testDados(){
        File file = new File(sistemaBiblioteca.getGravador().getFilePath());
        assertTrue(file.exists(), "O arquivo de empréstimos não foi criado.");
    }




}
*/
