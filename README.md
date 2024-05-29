# Chat Terminal

Este projeto surgiu do estudo sobre networking e sockets, recomendado [neste roadmap](https://roadmap.sh/java). O objetivo é entender e implementar um chat simples utilizando Java Sockets.

## Funcionalidades

- Envio e recebimento de mensagens utilizando `socket.getInputStream()` e `socket.getOutputStream()`.
- Conexão dos usuários ao servidor através da classe intermediária `ClientHandler`.
- Comunicação de múltiplos usuários simultâneos com threads e sockets individuais para cada usuário.

## Como Executar

### Requisitos

- Java instalado (JDK 8 ou superior).

### Passos

1. Clone o repositório:
   ```bash
   git clone https://github.com/Julio-Pupim/chatterminal.git
   cd chatterminal
   ```

2. Compile o servidor e os clientes:
   ```bash
   javac Server.java Client.java ClientHandler.java
   ```

3. Inicie o servidor:
   ```bash
   java Server
   ```

4. Inicie os clientes em terminais separados:
   ```bash
   java Client
   ```

## Utilização do Hamachi

Para utilizar o chat com mais pessoas em diferentes redes:

1. Baixe e instale o Hamachi: [Hamachi Download](https://www.vpn.net/).
2. Crie uma nova rede no Hamachi e compartilhe o ID da rede com seus amigos.
3. Todos devem se conectar à mesma rede Hamachi.
4. Inicie o servidor usando o endereço IP fornecido pelo Hamachi.
   ```java
   Socket socket = new Socket("IP_do_Hamachi", 8080);
   ```
5. Inicie os clientes utilizando o mesmo IP.

## Conclusão

Com este projeto, consegui entender e trabalhar conceitos de sockets e threads em Java. Compreendedo a comunicação entre cliente-servidor e a sincronização de mensagens em um chat multiusuário. Não bloqueando mensagens de usuário entre si!

---
