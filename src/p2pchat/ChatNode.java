import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.UUID;

public class ChatNode {

    private class PeerHandler extends Thread {
        private Socket socket;

        public PeerHandler(Socket socket) {
            this.socket = socket;
        }

        public PeerHandler(String host, int port) throws IOException {
            this.socket = new Socket(host, port);
        }

        public void run() {
            Connection conn = new Connection(null, socket);
            Message msg = conn.receiveMessage();
            if (msgHandlers.containsKey(msg.getMessageType())) {
                msgHandlers.get(msg.getMessageType()).handleMessage(conn, msg);
            }
            else {
                System.out.println("That message type is not supported. Please try again.");
            }
            conn.close();
        }
    }

    private class Message_listener extends Thread {

        public void run() {

            // Listen for a message from another node in the network

            // Handle the message sent
        }

        public Socket listen_for_message() throws IOException {

            // Create a server socket for this node
            ServerSocket server_socket = makeNodeServer( 80 );

            // Wait and listen for a message from another node
            // Once we get a connection, return the socket 
            return server_socket.accept();
        }
    }

    // hash of all peers connected
    private Hashtable<String, PeerData> peers;

    // hash of all the message handlers
    private Hashtable<String, MessageHandler> msgHandlers;

    // this nodes peer data
    private PeerData currData;

    // node status
    private boolean isRunning;

    /*
    * Constructor method
     */
    public ChatNode(PeerData data) {

        // Check if the Peer data input has its host data initialized
        if (data.getHost() == null) {

            // If not, give it a Google host name
            data.setHost(getHostName());
        }

        // Check if the peer data input has an ID
        if (data.getId() == null) {

            // If not, set its id as the hostname and its port
            data.setId(data.getHost() + ":" + data.getPort());
        }

        // Save the data for this node
        this.currData = data;

        // Save the peer data of all of the other nodes in the network
        this.peers = new Hashtable<String, PeerData>();

        // Save the threads that handle receiving messages from each node
        this.msgHandlers = new Hashtable<String, MessageHandler>();

        // Set this node as running
        this.isRunning = true;
    }

    // Create a Chat node using the other constructor using only the port
    public ChatNode(int port) {
        this(new PeerData(port));
    }

    /*
    * Method that gets a hostname from Google
     */
    private String getHostName() {
        String host = "";
        try {

            // Connect a socket to Google
            Socket socket = new Socket("www.google.com", 80);

            // Get Google's address, then get its host address.
            host = socket.getLocalAddress().getHostAddress();
        } catch (Exception e) {
            System.out.println("Error getting host name. See:\n" + e);
        }
        return host;
    }

    /*
    * Create a server object and return it
     */
    public ServerSocket makeNodeServer(int port) throws IOException {
        ServerSocket server = new ServerSocket(port);
        server.setReuseAddress(true);
        return server;
    }

    public void sendMessage(String toId, String msgType, String msgContent) {


    }
}