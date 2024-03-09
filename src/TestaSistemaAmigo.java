package src;


import java.util.List;

public class TestaSistemaAmigo {


    public static void main(String [] args) throws AmigoJaExisteException {

        SistemaAmigo sistema = new SistemaAmigo();

        sistema.cadastraAmigo("José", "jose@jose.com");

        sistema.cadastraAmigo("Maria", "maria@maria.com");

        try {

            sistema.configuraAmigoSecretoDe("jose@jose.com", "maria@maria.com");

            sistema.configuraAmigoSecretoDe("maria@maria.com", "jose@jose.com");

            sistema.enviarMensagemParaAlguem("oi José", "maria@maria.com", "jose@jose.com", true);

            sistema.enviarMensagemParaTodos("oi povo", "maria@maria.com", true);

            List<Mensagem> mensagensAnonimas = sistema.pesquisaMensagensAnonimas();

            for (Mensagem m: mensagensAnonimas){

                System.out.println(m.getTextoCompletoAExibir());

            }

            String emailAmigoDeJose = sistema.pesquisaAmigoSecretoDe("jose@jose.com");

            if (emailAmigoDeJose.equals("maria@maria.com")){

                System.out.println("OK");

            }

        } catch (AmigoInexistenteException e){

            System.out.println("Exceção estranha foi lançada");

            e.printStackTrace();



        } catch (AmigoNaoSorteadoException e){

            System.out.println("Exceção estranha foi lançada");

            e.printStackTrace();

        }
    }
}