package src;

public class MensagemParaTodos extends Mensagem{
    public MensagemParaTodos(String texto, String emailRemetente, boolean anonima) {
        super(texto, emailRemetente, anonima);
    }

    @Override
    public String getTextoCompletoAExibir() {
        String texto;
        if(this.ehAnonima()) {
            texto = "Mensagem para todos Texto: " + this.getTexto();
        }else {
            texto = "Mensagem de " + this.getEmailRemetente() + "para todos. \nTexto: " + this.getTexto();
        }
        return texto;
    }
}
