import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

  private final ServerSocket serverSocket;

  public Server(ServerSocket serverSocket) {
    this.serverSocket = serverSocket;
  }

  public void startServer(){

    try {

      while(!serverSocket.isClosed()){

        /*Fica travado o programa esperando a conexão com usuário acontecer,
        quando acontece retorna um objeto que pode ser usado para comunicação com o usuário*/
        Socket socket = serverSocket.accept();
        System.out.println("Usuario conectado!");
        ClientHandler clientHandler = new ClientHandler(socket);
        Thread thread = new Thread(clientHandler);
        thread.start();

      }
    }catch (IOException e ){
      e.printStackTrace();

    }
  }

  public void closeServerSide(){

    try {
      if(serverSocket!=null){
        serverSocket.close();
      }
    }catch (IOException e){
      e.printStackTrace();
    }
  }

  public static void main(String[] args) throws IOException {

    ServerSocket serverSocket = new ServerSocket(8080);
    Server server = new Server(serverSocket);
    server.startServer();
  }
}
