public class PeerData {
    private String id;
    private String host;
    private int port;

    /**
     * This constructor is for first node being created and serves as a base
     * @param id
     * @param host
     * @param port
     */
    public PeerData(String id, String host, int port) {
        this.id = id;
        this.host = host;
        this.port = port;
    }

    /**
     *
     * @param host
     * @param port
     */
    public PeerData(String host, int port) {

        this(host + ":" + port, host, port);
    }

    /**
     *
     * @param port
     */
    public PeerData(int port) {
        this(null, port);
    }


    /**
     * @return int
     */
    public String getId() {
        return id;
    }


    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }


    /**
     * @return String
     */
    public String getHost() {
        return host;
    }


    /**
     * @param host
     */
    public void setHost(String host) {
        this.host = host;
    }


    /**
     * @return int
     */
    public int getPort() {
        return port;
    }


    /**
     * @param port
     */
    public void setPort(int port) {
        this.port = port;
    }


    /**
     * @return String
     */
    public String toString() {
        return id + " (" + host + ":" + port + ")";
    }
}