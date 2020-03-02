import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/*
* Object that contains the information and socket to connect to another node in the network
 */
public class Connection {

    private PeerData peerData;
    private Socket socket;

    public Connection(PeerData peerData, Socket socket) {
        this.peerData = peerData;
        this.socket = socket;
    }


    /**
     * @param msg
     */
    public void sendMessage(Message msg) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(msg);
        } catch (Exception e) {
            System.out.println("Error sending message. See:\n" + e);
        }
    }


    /**
     * @return Message
     */
    public Message receiveMessage() {
        try {
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            Message msg = (Message) in.readObject();
            return msg;
        } catch (Exception e) {
            System.out.println("Error receiving message, see:\n" + e);
            return null;
        }
    }

    public void close() {
        if (socket != null) {
            try {
                socket.close();
            } catch (Exception e) {
                System.out.println("Error closing connecton, see:\n" + e);
            }
        }
    }

    public PeerData getPeerData() {
        return peerData;
    }
}