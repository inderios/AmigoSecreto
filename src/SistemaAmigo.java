package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SistemaAmigo {
    private List<Mensagem> mensagemList = new ArrayList<>();
    private List<Amigo> amigosMap = new ArrayList<>();

    public List<Mensagem> getMensagemList() {
        return mensagemList;
    }

    public void setMensagemList(List<Mensagem> mensagemList) {
        this.mensagemList = mensagemList;
    }

    public List<Amigo> getAmigoList() {
        return amigosMap;
    }

    public void setAmigoList(List<Amigo> amigosMap) {
        this.amigosMap = amigosMap;
    }

    public void cadastraAmigo(String nomeAmigo, String emailAmigo) throws AmigoJaExisteException {
        Amigo amigo = new Amigo(nomeAmigo, emailAmigo, "");
        for (Amigo a: amigosMap) {
            if (a.equals(amigo)) {
                throw new AmigoJaExisteException("O amigo que você está tentando cadastra já existe");
            }
        }
        this.amigosMap.add(amigo);
    }
    public Amigo pesquisaAmigo(String emailAmigo) throws AmigoInexistenteException {
        for (Amigo a: amigosMap) {
            if (a.getEmail().equals(emailAmigo)) {
                return a;
            }
        }
        throw new AmigoInexistenteException("Não existe nenhum amigo com o email de " + emailAmigo);
    }
    public void enviarMensagemParaTodos(String texto, String emailRemetente, boolean ehAnonima) {
        MensagemParaTodos mensagem = new MensagemParaTodos(texto, emailRemetente, ehAnonima);
        mensagemList.add(mensagem);
    }
    public void enviarMensagemParaAlguem(String texto, String emailRemetente, String emailDestinatario, boolean ehAnonima) {
        MensagemParaAlguem mensagem = new MensagemParaAlguem(texto, emailRemetente, emailDestinatario, ehAnonima);
        mensagemList.add(mensagem);
    }
    public List<Mensagem> pesquisaMensagensAnonimas() {
        //return mensagemList.stream().filter(Mensagem::ehAnonima).collect(Collectors.toList());
        List<Mensagem> anonimas = new ArrayList<>();
        for (Mensagem m: mensagemList) {
            if (m.ehAnonima()) {
                anonimas.add(m);
            }
        }
        return anonimas;
    }
    public List<Mensagem> pesquisarTodasMensagens() {
        return mensagemList;
    }
    public void configuraAmigoSecretoDe(String emailDaPessoa, String emailAmigoSorteado)throws AmigoInexistenteException{
        Amigo amigo = pesquisaAmigo(emailDaPessoa);
        if (amigo == null) {
            throw new AmigoInexistenteException("Amigo com email " + emailDaPessoa + " não encontrado.");
        }
        amigo.setEmailAmigoSorteado(emailAmigoSorteado);
    }
    public String pesquisaAmigoSecretoDe(String emailDaPessoa) throws AmigoInexistenteException, AmigoNaoSorteadoException {
        Amigo amigo = pesquisaAmigo(emailDaPessoa);
        if (amigo == null) {
            throw new AmigoInexistenteException("Amigo com email " + emailDaPessoa + " não encontrado.");
        }
        String amigoSorteado = amigo.getEmailAmigoSorteado();
        if (amigoSorteado == null) {
            throw new AmigoNaoSorteadoException("O amigo com email " + emailDaPessoa + " não tem um amigo secreto sorteado.");
        }
        return amigo.getEmailAmigoSorteado();
    }
    public List<Mensagem> pesquisaTodasAsMensagens() {
        return mensagemList;
    }
    public List<Amigo> sortear() {
        List<Amigo> amigosParaSortear = new ArrayList<>(amigosMap);
        amigosMap.clear();
        amigosParaSortear = embaralharLista(amigosParaSortear);
        while (amigosParaSortear.size() >= 2) {
            Amigo amigoInicial = amigosParaSortear.getFirst();
            Amigo amigoFinal = amigosParaSortear.getLast();

            amigoInicial.setEmailAmigoSorteado(amigoFinal.getEmail());
            amigoFinal.setEmailAmigoSorteado(amigoInicial.getEmail());

            amigosParaSortear.removeFirst();
            amigosParaSortear.removeLast();
        }
        if (amigosParaSortear.size() % 2 != 0) {
            Amigo amigoFinal = amigosParaSortear.get(0);
            amigoFinal.setEmailAmigoSorteado(amigoFinal.getEmail());
        }
        // Atualiza a lista de amigos com os amigos sorteados
        return amigosParaSortear;
    }
    public List<Amigo> embaralharLista (List<Amigo> lista) {
        Random rand = new Random();
        int tamanho = lista.size();
        for (int i = 0; i < tamanho; i++) {
            int indiceAleatorio = rand.nextInt(tamanho);
            // Troque o elemento na posição 'i' com o elemento na posição 'indiceAleatorio'
            Amigo temp = lista.get(i);
            lista.set(i, lista.get(indiceAleatorio));
            lista.set(indiceAleatorio, temp);
        }
        return lista;
    }
}
