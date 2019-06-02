import java.net.*;
import java.net.Socket;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
 
class Server
{
    public static void main(String args[]) throws IOException
    {
 
        ServerConnection conn;
        String pno=args[0];                 // enter port number
        System.out.println("\n"+pno+"\n");
        conn = new ServerConnection(Integer.parseInt(pno));
    }
}
 
class ServerConnection implements Runnable          // this class connects clients and server
{
    private ServerSocket ss;
    private int port;
    private MessageList messList;
    private ClientList clientList;
    private MessageTransmit messTransmit;
 
    
    public ServerConnection(int aport) throws IOException
    {
        port = aport;
        messList = new MessageList();
        clientList = new ClientList();
        messTransmit = new MessageTransmit(messList, clientList);
 
        ss = new ServerSocket(port);
     
        Thread t=new Thread(this);
        t.start();
    }
     
    public void getConnection() throws IOException   // whenever new client comes
    {
        NewClient client1=new NewClient(ss.accept(), messList);
        clientList.add(client1);                                    // add client to ClientList
    }
 
 
    public void run()                   // run until server is open
    {
        while(!ss.isClosed())               // until server is open
        {
            try
            {
                getConnection();    // check for any new connection from clients
            }
            catch(IOException e)
            {
                System.out.println("IOException throws");
            }
        }
    }
 
}
 
/*
        list of all clients
 */
 
class ClientList
{
    private ArrayList<NewClient> clients ;
     
    public ClientList(){
        clients = new ArrayList<NewClient>();
    }
 
    public synchronized NewClient removeFirst(){             // remove client
        return clients.remove(0);
    }
 
    public synchronized void add(Object o){                // add client
        clients.add((NewClient)o);
    }
 
    public synchronized boolean isEmpty (){   // check whether list is empty or not
        return clients.isEmpty();
    }
 
    public synchronized void remove(NewClient aclient){   // remove specific client
        if(clients.contains(aclient))
            clients.remove(aclient);
    }
 
    synchronized void transmitMessage(Message message){ // transmit message to all other clients
        for(NewClient client : clients)
        {
            if(!client.isClosed())
            {
                if(!message.senderEquals(client))
                {
                    client.sendMessage(message.getText());
                }
            }
            else
            {
                clients.remove(client);
            }
        }
    }
 
 
}
 
/*
 stoored all messages come from client
 */
 
class MessageList
{
    private ArrayList<Message> messages;
     
    public MessageList(){
    messages = new ArrayList<Message>();
    }
 
    public synchronized Message removeFirst(){ // remove first message from ArrayList
        if(!messages.isEmpty())
        {
           return messages.remove(0);
        }
        else
            return null;
    }
 
    public synchronized void add(Object o){                  // add into ArrayList
        messages.add((Message) o);
    }
 
    public synchronized boolean isEmpty (){   // check whether list is empty or not
        return messages.isEmpty();
    }
 
}
 
 
 
class Message
{
    private NewClient client;
    private String text;
 
    public Message(NewClient aclient, String atext){
        client = aclient;
        text = atext;
    }
 
    public String getWrittenText(){                      // get the text written by client
        return text;
    }
 
    public String getText(){       // return clientname along with his message
        //System.out.println("inside get message :"+text);
        return client.getClientName()+ ": " + text;
    }
 
    public boolean senderEquals(NewClient aclient){          // check for sender client
        return client.equals(aclient);
    }
 
    public String toString(){
        String result = client + "Text: " + text;
        return result;
    }
 
}
 
/*
 *  transmit message to all clients those are in ClientList
 */
 
class MessageTransmit implements Runnable
{
 
    private MessageList messList;
    private ClientList clientList;
 
    MessageTransmit(MessageList messageList, ClientList aclientList){
        messList = messageList;
        clientList = aclientList;
        Thread t = new Thread(this);
        t.start();
    }
 
    public void run(){          //every time take first message from MessageList
        while(true)
        {
            Message message = (Message)messList.removeFirst();
            if(message!=null)
            {
                clientList.transmitMessage(message);
                message = (Message)messList.removeFirst();
            }
        }
    }
 
}
 
/*
 * for new Client
 */
 
class NewClient implements Runnable
{
    private String clientName;
    private Socket socket;
    private PrintWriter pw;
    private Scanner scan;
    private MessageList messList;
 
    public NewClient(Socket asocket, MessageList messageList) throws IOException{
        socket = asocket;
 
        if(messList == null)    // System.out.println("Senthil");
            messList = messageList;
 
        pw = new PrintWriter(socket.getOutputStream());
        scan = new Scanner(socket.getInputStream());
        clientName = scan.nextLine();
        Thread t  = new Thread(this);
        t.start();
    }
 
    public String getClientName(){
        return clientName;
    }
 
    
    private void newMessage(){
        if(scan.hasNextLine())
        {
            Message message1=new Message(this,scan.nextLine());
            messList.add(message1);
        }
    }
 
    public void sendMessage(String message){
        pw.println(message);
        pw.flush();
    }
 
    public boolean isClosed(){
        return socket.isClosed();
    }
     
    public void run(){               //every time checks for mew messages
        while(true)
        {
            newMessage();
        }
    }
 
    public String toString(){
        String result = "Client Name: " + clientName + " " +  socket;
        return result;
    }
 
}