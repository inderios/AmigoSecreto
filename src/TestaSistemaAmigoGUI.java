package src;


import javax.swing.*;

public class TestaSistemaAmigoGUI {

    public static void main(String [] args) throws AmigoInexistenteException, AmigoJaExisteException {

        SistemaAmigo sistema = new SistemaAmigo();

        int numAmigos = Integer.parseInt(JOptionPane.showInputDialog("Quantos amigos vão brincar?"));

        for (int k=0; k<numAmigos; k++){

            String nome = JOptionPane.showInputDialog("Qual o nome?");

            String email = JOptionPane.showInputDialog("Qual o e-mail?");

            sistema.cadastraAmigo(nome, email);

        }


        for (int k=0; k<numAmigos; k++){

            String emailAmigo = JOptionPane.showInputDialog("Você quer dizer o amigo secreto de quem? (diga o email)");

            String emailAmigoSorteado = JOptionPane.showInputDialog("Diga o e-mail do amigo sorteado para "+emailAmigo);

            sistema.configuraAmigoSecretoDe(emailAmigo, emailAmigoSorteado);

        }



        String remetente = JOptionPane.showInputDialog("A mensagem é de quem?");

        String texto = JOptionPane.showInputDialog("Qual o texto da mensagem a enviar");

        String ehAnonima = JOptionPane.showInputDialog("A mensagem é anônima? Sim (S) ou Não (N)");

        //boolean anonima = false;

        //if (ehAnonima.equals("S")){

        //        anonima = true;

//}


        boolean anonima = (ehAnonima.equals("S"))? true: false;

        sistema.enviarMensagemParaTodos(remetente, texto, anonima);



    }

}
