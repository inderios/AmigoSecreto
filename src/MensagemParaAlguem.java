package src;

public class MensagemParaAlguem extends Mensagem{
    private String emailDestinatario;
    public MensagemParaAlguem(String texto, String emailRemetente, String emailDestinatario,boolean anonima) {
        super(texto, emailRemetente, anonima);
        this.emailDestinatario = emailDestinatario;
    }

    public String getEmailDestinatario() {
        return emailDestinatario;
    }

    public void setEmailDestinatario(String emailDestinatario) {
        this.emailDestinatario = emailDestinatario;
    }

    @Override
    public String getTextoCompletoAExibir() {
        String texto;
        if(this.ehAnonima()) {
            texto = "Mensagem  para: " + this.getEmailDestinatario()+  "\nTexto: " + this.getTexto();
        }else {
            texto = "Mensagem de: " + this.getEmailRemetente() + "\nPara " + this.getEmailDestinatario() +"\nTexto: " + this.getTexto();
        }
        return texto;
    }

}
