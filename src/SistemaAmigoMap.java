package src;


import java.util.*;

public class SistemaAmigoMap {
    private List<Mensagem> mensagemList = new ArrayList<>();
    private HashMap<String, Amigo> amigosMap = new HashMap<>();

    public List<Mensagem> getMensagemList() {
        return mensagemList;
    }

    public void setMensagemList(List<Mensagem> mensagemList) {
        this.mensagemList = mensagemList;
    }

    public HashMap<String, Amigo> getAmigosMap() {
        return amigosMap;
    }

    public void setAmigosMap(HashMap<String, Amigo> amigosMap) {
        this.amigosMap = amigosMap;
    }

    public void cadastraAmigo(String nomeAmigo, String emailAmigo) throws AmigoJaExisteException {
        Amigo amigoAdd = new Amigo(nomeAmigo, emailAmigo, "");
        Amigo amigoMap = amigosMap.get(amigoAdd.getEmail());
        if(amigoMap == null) {
            this.amigosMap.put(amigoAdd.getEmail(), amigoAdd);
        }else {
            throw new AmigoJaExisteException("O amigo que você está tentando cadastrar já existe.");
        }
    }
    public Amigo pesquisaAmigo(String emailAmigo) throws AmigoInexistenteException {
        Amigo a = amigosMap.get(emailAmigo);
        if(a == null){
            throw new AmigoInexistenteException("Não existe nenhum amigo com o email de " + emailAmigo);
        }
        return a;
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
    public HashMap<String, Amigo> sortear() {
        List<Amigo> amigoList = new ArrayList<>(amigosMap.values());
        HashMap<String, Amigo> amigosSorteados = new HashMap<>();
        int tamanhoAmigoList = amigoList.size();
        for (int i = 0; i < tamanhoAmigoList; i++) {
            double aleatorio = Math.random();
            int indexAleatorio = 0;
            do{
                indexAleatorio = (int) (aleatorio*tamanhoAmigoList);
            }while (indexAleatorio == i);
            Amigo amigoAtual = amigoList.get(i);
            Amigo amigoAleatorio = amigoList.get(indexAleatorio);
            amigoAtual.setEmailAmigoSorteado(amigoAleatorio.getEmail());
            amigosSorteados.put(amigoAtual.getEmail(), amigoAtual);
        }
        return amigosSorteados;
    }
}