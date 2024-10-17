package br.dcx.ufpb.bibioteca.gravador;

import br.dcx.ufpb.bibioteca.Emprestimo;
import br.dcx.ufpb.bibioteca.Livro;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GravaDados {

    public static final String ARQUIVO_EMPRESTIMO = "emprestimos.txt";
    public static final String ARQUIVO_LIVRO = "livros.txt";

    public List<Emprestimo> leEmprestimos() throws IOException {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream(ARQUIVO_EMPRESTIMO));
            return (List<Emprestimo>) in.readObject();
        }catch (FileNotFoundException e){
            throw new IOException("Arquivo não encontrado");
        }catch (IOException e){
            throw e;
        }catch (ClassNotFoundException e){
            throw new IOException("Classe não encontrada");
        }finally {
            if (in != null){
                in.close();
            }
        }
    }
    public void gravarEmprestimos(List<Emprestimo> emprestimos) throws IOException {
         ObjectOutputStream out = null;
         try {
             out = new ObjectOutputStream(new FileOutputStream(ARQUIVO_EMPRESTIMO));
             out.writeObject(emprestimos);
         }catch (FileNotFoundException e){
                throw new IOException("Arquivo não encontrado");
         }catch (IOException e){
             throw e;
         }finally {
             if (out != null){
                 out.close();
             }
         }
    }
    public void gravarLivros(HashMap<String,Livro> livros) throws IOException {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(ARQUIVO_LIVRO));
            out.writeObject(livros);
        }catch (FileNotFoundException e){
            throw new IOException("Arquivo não encontrado");
        }catch (IOException e){
            throw e;
        }finally {
            if (out != null){
                out.close();
            }
        }
    }
    public HashMap<String,Livro> leLivros() throws IOException {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream(ARQUIVO_LIVRO));
            return (HashMap<String, Livro>) in.readObject();
        }catch (FileNotFoundException e){
            throw new IOException("Arquivo não encontrado");
        }catch (IOException e){
            throw e;
        }catch (ClassNotFoundException e){
            throw new IOException("Classe não encontrada");
        }finally {
            if (in != null){
                in.close();
            }
        }
    }
}
