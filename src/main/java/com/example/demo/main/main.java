package com.example.demo.main;

import java.util.Scanner;

public class main {

        private Scanner entrada = new Scanner(System.in);
        private final String ENDERECO = "";

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
                    listaAutoresVivos();
                    break;
                case 5:
                    listarAutoresPeloIdioma();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção Inválida");
            }






        }


















}
