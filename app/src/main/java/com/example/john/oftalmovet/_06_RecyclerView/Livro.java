package com.example.john.oftalmovet._06_RecyclerView;

/**
 * Created by John on 04/04/2018.
 */

public class Livro {

    private final String nomeLivro;
    private final String nomeAutor;
    private final String descricao;
    private final Double preco;

    public Livro(String nomeLivro, String nomeAutor,
                 String descricao, Double preco) {

        this.nomeLivro = nomeLivro;
        this.nomeAutor = nomeAutor;
        this.descricao = descricao;
        this.preco = preco;
    }

    public String getNomeLivro() {
        return nomeLivro;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getPreco() {
        return preco;
    }
}
