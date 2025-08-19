package com.example.demo.main;

import com.example.demo.dto.LivroDTO;
import com.example.demo.model.Autor;
import com.example.demo.model.DadosLivros;
import com.example.demo.model.Livro;
import com.example.demo.repository.AutorRepository;
import com.example.demo.repository.LivroRepository;
import com.example.demo.service.ConsumoService;
import com.example.demo.service.ConverteJsonClasse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class Principal {

        private Scanner entrada = new Scanner(System.in);
        private final String ENDERECO = "https://gutendex.com/books/?search=";

        private ConsumoService consumo = new ConsumoService();
        private ConverteJsonClasse conversor = new ConverteJsonClasse();

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;


    public void menu(){
            String menuPrincipal = """
                    Escolha o número da sua opção: 
                    1- Buscar livros pelo titulo.
                    2- Listar livros registrados.
                    3- Listar autores registrados.
                    4- Listar autores vivos em um determinado ano.
                    5- Listar livros em um determinado idioma.
                    0- Sair.                                                            
                    """;


            System.out.println(menuPrincipal);
            int opcao = entrada.nextInt();
            entrada.nextLine();


            switch (opcao) {
                case 1:
                    buscarLivros();
                    break;
                case 2:
                    listarLivros();
                    break;
                case 3:
                    listarAutores();
                    break;
                case 4:
                    listarAutoresVivos();
                    break;
                case 5:
                    listarLivrosPeloIdioma();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção Inválida");
            }


        }






    @Transactional
    public List<Livro> buscarLivros() {
        System.out.println("Digite o nome do livro:");
        String nomeLivro = entrada.nextLine();

        List<Livro> livros = new ArrayList<>();
        try {
            String url = ENDERECO + URLEncoder.encode(nomeLivro, StandardCharsets.UTF_8);
            var json = consumo.obterDados(url);
            DadosLivros wrapper = conversor.obterDados(json, DadosLivros.class);

            for (var dto : wrapper.resultados()) {
                Livro livro = new Livro();
                livro.setTitulo(dto.titulo());
                livro.setIdioma((dto.idiomas() != null && !dto.idiomas().isEmpty()) ? dto.idiomas().get(0) : "");
                livro.setNumDownloads(dto.numDownloads() != null ? dto.numDownloads().doubleValue() : 0.0);

                if (dto.autores() != null && !dto.autores().isEmpty()) {
                    String nomeAutor = dto.autores().get(0).nome();

                    var dtoAutor = dto.autores().get(0);


                    Integer anoNascimento = dtoAutor.anoNascimento() != null ? dtoAutor.anoNascimento() : null;
                    Integer anoFalecimento = dtoAutor.anoFalecimento() != null ? dtoAutor.anoFalecimento() : null;


                    Autor autor = autorRepository.findByNome(nomeAutor)
                            .orElseGet(() -> {
                                Autor novoAutor = new Autor(nomeAutor);
                                novoAutor.setAnoNascimento(anoNascimento);
                                novoAutor.setAnoFalecimento(anoFalecimento);
                                autorRepository.save(novoAutor);
                                return novoAutor;
                            });


                    livro.setAutor(autor);


                    livroRepository.save(livro);


                    livros.add(livro);


                    System.out.println("Título: " + livro.getTitulo());
                    System.out.println("Idioma: " + livro.getIdioma());
                    System.out.println("Downloads: " + livro.getNumDownloads());
                    System.out.println("Autor: " + autor.getNome());
                    System.out.println("----------------------------");
                }
            }

        } catch(Exception e){
            e.printStackTrace();
        }

        return livros;
    }


    private void listarLivros() {

        List<Livro> livros = livroRepository.findAllWithAutores();
        livros.forEach(livro -> {
            System.out.println("Título: " + livro.getTitulo());
            Autor autor = livro.getAutor();
            if (autor != null) {
                System.out.println("   Autor: " + autor.getNome());
            } else {
                System.out.println("   Autor: [sem autor cadastrado]");
            }
        });
    }


    private void listarAutores() {
        List<Autor> autores = autorRepository.findAll();
        autores.forEach(autor -> {
            System.out.println("Nome: " + autor.getNome());
            System.out.println("Ano Nascimento: " + (autor.getAnoNascimento() != null ? autor.getAnoNascimento() : "[desconhecido]"));
            System.out.println("Ano Falecimento: " + (autor.getAnoFalecimento() != null ? autor.getAnoFalecimento() : "[vivo]"));
            System.out.println("----------------------------");
        });
    }


    private void listarAutoresVivos() {
        System.out.println("Digite o ano para verificar autores vivos:");
        int ano = entrada.nextInt();
        entrada.nextLine();

        List<Autor> autores = autorRepository.findByAnoFalecimentoAfterOrAnoFalecimentoIsNull(ano);
        autores.forEach(autor -> {
            System.out.println("Nome: " + autor.getNome());
            System.out.println("Ano Nascimento: " + (autor.getAnoNascimento() != null ? autor.getAnoNascimento() : "[desconhecido]"));
            System.out.println("Ano Falecimento: " + (autor.getAnoFalecimento() != null ? autor.getAnoFalecimento() : "[vivo]"));
            System.out.println("----------------------------");
        });
    }


    private void listarLivrosPeloIdioma() {
        System.out.println("Digite o idioma dos livros:");
        String idioma = entrada.nextLine();

        List<Livro> livros = livroRepository.findByIdiomaWithAutor(idioma);
        livros.forEach(livro -> {
            System.out.println("Título: " + livro.getTitulo());
            Autor autor = livro.getAutor();
            System.out.println("   Autor: " + (autor != null ? autor.getNome() : "[sem autor]"));
            System.out.println("   Idioma: " + livro.getIdioma());
            System.out.println("----------------------------");
        });
    }

}







































