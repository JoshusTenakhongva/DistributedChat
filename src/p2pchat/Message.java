import java.io.Serializable;

public class Message implements Serializable {

    //required when implementing Serializable, insignificant number
    private static final long serialVersionUID = -299482035708790407L;
    private int type;
    private String content;

    public Message(int type, String content) {
        this.type = type;
        this.content = content;
    }

    /**
     * @return int
     */
    public int getMessageType() {
        return type;
    }


    /**
     * @return String
     */
    public String getMessageContent() {
        return content;
    }


        /**
         * @return String
         */
        @Override
    public String toString() {
        return "Message reads:" + getMessageContent();
    }
}