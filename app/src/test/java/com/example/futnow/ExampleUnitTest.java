package com.example.futnow;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.example.futnow.model.Comentario;
import com.example.futnow.model.Quadra;
import com.example.futnow.model.Usuario;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

     Usuario usuario;
     Quadra quadra;
     Comentario comentario;

    @Before
    public void CreateClasss() {
        usuario = new Usuario(
                "wil",
                "wilhelmsteins@gmail.com",
                "123456",
                "00987654321"
        );



    }
    @Before
    public void quadra() {
        quadra = new Quadra(
                "titulo da quadra",
                "descricao"
        );

    }
    @Before
    public void comentario(){
        comentario = new Comentario(
            "titulo da comentario",
            "descricao"
        );
    }

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testUsuarioNull() {
        assertNotNull(usuario);
    }

    @Test
    public void testNomeUsuario() {
        String nome = "Wilhelm";
        usuario.setNome(nome);
        assertEquals("Verifica se o metodo setNome e getNome está funcionando",
                nome, usuario.getNome());
    }

    @Test
    public void testSenhaUsuario(){
        String senha = "123456";
        assertEquals("Verifica se o metodo setSenha e getSenha está funcionando",
                senha, usuario.getSenha());
    }

    @Test
    public void testEmailUsuario(){
        String email = "wilhelmsteins@gmail.com";
        assertEquals("Verifica se o metodo setEmail e getEmail está funcionando",
                email, usuario.getEmail());
    }

    @Test
    public void testCpfUsuario() {
        String cpf = "00987654321";
        assertEquals("Verifica se o metodo setCPF e getCPF está funcionando",
                cpf, usuario.getCPF());
    }

    @Test
    public void testTitleQuadra() {
        String title = "titulo";
        quadra.setTitle(title);
        assertEquals("Verifica se o metodo setTitle e getTitle está funcionando",
                title, quadra.getTitle());
    }

    @Test
    public void testDescricaoQuadra() {
        String descricao = "descricao";
        assertEquals("Verifica se o metodo setDescricao e getDescricao está funcionando",
                descricao, quadra.getDescricao());
    }

    @Test
    public void testTitleComentario() {
        String title = "titulo da comentario";

        assertEquals("Verifica se o metodo setTitle e getTitle está funcionando",
                title, comentario.getTitle());
    }

    @Test
    public void testDescricaoComentario() {
        String descricao = "descricao";
        comentario.setDescricao(descricao);
        assertEquals("Verifica se o metodo setDescricao e getDescricao está funcionando",
                descricao, comentario.getDescricao());
    }
}