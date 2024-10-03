package br.dcx.ufpb.bibioteca.gravador;

import br.dcx.ufpb.bibioteca.Emprestimo;

import java.io.*;
import java.util.List;

public class GravaDados {
    public List<Emprestimo> leEmprestimos() throws IOException {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream("emprestimos.txt"));
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
             out = new ObjectOutputStream(new FileOutputStream("emprestimos.txt"));
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
}
