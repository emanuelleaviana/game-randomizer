package bean;

import entidades.Jogo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import banco.JogoDAO;

@ManagedBean
@ViewScoped
public class JogoBean {
    private List<Jogo> jogos = new ArrayList<>();
    private String nomeJogador;
    private Integer numeroAposta;
    private static Integer contadorId = 1;
    private JogoDAO jogoDAO = new JogoDAO();
    
    public JogoBean() {
        this.jogos = jogoDAO.listar(); 
    }

    public void adicionar() {
        if (nomeJogador != null && numeroAposta != null) {
            Jogo jogo = new Jogo();
            
            jogo.setId(contadorId++); 
            jogo.setNomeJogador(nomeJogador.trim());
            jogo.setNumeroAposta(numeroAposta);
            jogo.setData(new Date());
            
            Random random = new Random();
            int numeroSecreto = random.nextInt(5) + 1; 
            jogo.setNumeroSecreto(numeroSecreto);
            jogo.setResultado(numeroAposta.equals(numeroSecreto) ? "acertou" : "não acertou");
            
            jogos.add(jogo);
            
            jogoDAO.salvar(jogo);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Jogo adicionado com sucesso!"));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Total de Jogos: " + quantidadeJogos()));
            
            nomeJogador = null;
            numeroAposta = null;
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Os campos Nome do Jogador e Número da Aposta são obrigatórios."));
        }
    }

    public void excluirJogo(Jogo jogo) {
        jogos.remove(jogo); 
        jogoDAO.excluir(jogo); 
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Jogo excluído com sucesso!"));
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Total de Jogos: " + quantidadeJogos()));
    }
    
    public int quantidadeJogos() {
        return jogos.size();
    }

    public List<Jogo> getJogos() {
        return jogos;
    }

    public String getNomeJogador() {
        return nomeJogador;
    }

    public void setNomeJogador(String nomeJogador) {
        this.nomeJogador = nomeJogador;
    }

    public Integer getNumeroAposta() {
        return numeroAposta;
    }

    public void setNumeroAposta(Integer numeroAposta) {
        this.numeroAposta = numeroAposta;
    }
}
