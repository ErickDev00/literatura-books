package com.example.demo.main;

import com.example.demo.dto.LivroDTO;
import com.example.demo.model.Autor;
import com.example.demo.model.DadosLivros;
import com.example.demo.model.Livro;
import com.example.demo.repository.LivroRepository;
import com.example.demo.service.ConsumoService;
import com.example.demo.service.ConverteJsonClasse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

@Component
public class Principal {

        private Scanner entrada = new Scanner(System.in);
        private final String ENDERECO = "https://gutendex.com/books/?search=";

        private ConsumoService consumo = new ConsumoService();
        private ConverteJsonClasse conversor = new ConverteJsonClasse();

    @Autowired
    private LivroRepository livroRepository;


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
                    //listarLivros();
                    break;
                case 3:
                    //listarAutores();
                    break;
                case 4:
                    //listaAutoresVivos();
                    break;
                case 5:
                    //listarAutoresPeloIdioma();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção Inválida");
            }


        }






        private List<Livro> buscarLivros(){

            System.out.println("Digite o nome do livro:");
            String nomeLivro = entrada.nextLine();

            try{
                String url = ENDERECO + URLEncoder.encode(nomeLivro, StandardCharsets.UTF_8);
                var json = consumo.obterDados(url);

                DadosLivros wrapper = conversor.obterDados(json, DadosLivros.class);


                //List<LivroDTO> livros = wrapper.resultados();


                // Converte cada LivroDTO para Livro
                return wrapper.resultados().stream()
                        .map(dto -> {
                            Livro livro = new Livro();
                            livro.setTitulo(dto.titulo());

                            if (dto.idiomas() != null && !dto.idiomas().isEmpty()) {
                                livro.setIdioma(dto.idiomas().get(0));
                            } else {
                                livro.setIdioma("");
                            }

                            if (dto.numDownloads() != null) {
                                livro.setNumDownloads(dto.numDownloads().doubleValue());
                            } else {
                                livro.setNumDownloads(0.0);
                            }

                            //convertendo autores
                            List<Autor> autores = dto.autores().stream()
                                    .map(a -> {
                                        Autor autor = new Autor();
                                        autor.setNome(a.nome());
                                        autor.setLivro(livro); // associa ao livro
                                        return autor;
                                    })
                                    .toList();

                            livro.setAutor(autores);


                            livroRepository.save(livro);


                            //exibindo o livro
                            System.out.println("Título: " + livro.getTitulo());
                            System.out.println("Idioma: " + livro.getIdioma());
                            System.out.println("Downloads: " + livro.getNumDownloads());
                            System.out.println("----------------------------");


                            return livro;

                        })
                        .toList();

                        } catch (Exception e) {
                        e.printStackTrace();
                        return List.of(); // retorna lista vazia em caso de erro
                    }

        }




































}
