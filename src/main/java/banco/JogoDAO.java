package banco;

import entidades.Jogo;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JogoDAO {
    private static final String FILE_NAME = "jogos.txt";

    public void salvar(Jogo jogo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(jogo.getId() + "," + jogo.getNomeJogador() + "," + jogo.getNumeroAposta() + "," + jogo.getNumeroSecreto() + "," + jogo.getResultado() + "," + jogo.getData().getTime());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Jogo> listar() {
        List<Jogo> jogos = new ArrayList<>();
        File file = new File(FILE_NAME);
        
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("Arquivo criado: " + FILE_NAME);
                System.out.println("Diretório de trabalho: " + System.getProperty("user.dir"));
            } catch (IOException e) {
                System.out.println("Erro ao criar o arquivo: " + e.getMessage());
                return jogos; 
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) { 
                    Jogo jogo = new Jogo();
                    try {
                        jogo.setId(Integer.parseInt(parts[0].trim()));
                        jogo.setNomeJogador(parts[1].trim());
                        jogo.setNumeroAposta(Integer.parseInt(parts[2].trim()));
                        jogo.setNumeroSecreto(Integer.parseInt(parts[3].trim()));
                        jogo.setResultado(parts[4].trim());
                        jogo.setData(new Date(Long.parseLong(parts[5].trim())));
                        jogos.add(jogo);
                    } catch (NumberFormatException e) {
                        System.out.println("Erro ao converter número na linha: " + line + " - " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Erro ao processar a linha: " + line + " - " + e.getMessage());
                    }
                } else {
                    System.out.println("Formato de linha inválido: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        return jogos;
    }

    public void excluir(Jogo jogo) {
        List<Jogo> jogos = listar(); 
        jogos.removeIf(j -> j.getId().equals(jogo.getId())); 

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Jogo j : jogos) {
                writer.write(j.getId() + "," + j.getNomeJogador() + "," + j.getNumeroAposta() + "," +
                             j.getNumeroSecreto() + "," + j.getResultado() + "," + j.getData().getTime());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
