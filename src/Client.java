import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;


public class Client {

  private Socket socket;
  private BufferedWriter bufferedWriter;
  private BufferedReader bufferedReader;
  private String nome;


  public Client(Socket socket, String nome) {
    try {
      this.socket = socket;
      this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); //mandando dados para o socket
      this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream())); // recebendo dados do socket
      this.nome = nome;
    }catch (IOException e ){
      closeEverthing(socket, bufferedReader, bufferedWriter);

    }
  }
  public void sendMessage(){
    try{
      bufferedWriter.write(nome);
      bufferedWriter.newLine();
      bufferedWriter.flush();

      Scanner scanner = new Scanner(System.in);
      while(socket.isConnected()){
        String messageToSend = scanner.nextLine();
        bufferedWriter.write(nome+ ": " + messageToSend);
        bufferedWriter.newLine();
        bufferedWriter.flush();
      }
    } catch (IOException e) {
      closeEverthing(socket, bufferedReader, bufferedWriter);
    }
  }

  public void listenForMessage(){
    new Thread(() -> {
      String msgFromChat;

      while(socket.isConnected()){
        try {
          msgFromChat = bufferedReader.readLine();
          System.out.println(msgFromChat);
        } catch (IOException e) {
          closeEverthing(socket, bufferedReader, bufferedWriter);
        }
      }
    }).start();
  }
  public void closeEverthing(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter){
    try {
      if(bufferedReader!=null){
        bufferedReader.close();
      }
      if(bufferedWriter!=null){
        bufferedWriter.close();
      }
      if(socket!=null){
        socket.close();
      }
    }catch(IOException e){
      e.printStackTrace();
    }
  }

  public static void main(String[] args) throws IOException {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Digite seu nome para o chat: ");
    String nome = scanner.nextLine();
    Socket socket = new Socket("25.0.193.12",8080);
    Client client = new Client(socket, nome);
    client.listenForMessage();
    client.sendMessage();
  }
}
