public interface MessageHandler {
    public void handleMessage(Connection conn, Message msg);
}