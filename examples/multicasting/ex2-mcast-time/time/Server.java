package time;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * Server thread that listens for responses from the client If it receives any
 * multicast packets, it displays the date on the console.
 * 
 * @author palderson, amit
 *
 */
public class Server extends Thread
{
    public void run() {
        // join a Multicast group and send the group salutations

        InetAddress addr;
        final int MULTICAST_PORT = 5800;

        try {
            addr = InetAddress.getByName("230.230.230.230");
            SocketAddress group = new InetSocketAddress(addr, MULTICAST_PORT);
            @SuppressWarnings("resource")
			MulticastSocket s = new MulticastSocket(MULTICAST_PORT);			

            s.joinGroup(group, null);
            s.setSoTimeout(60000);

            // Loop and get the responses
            while (true) {

                byte[] buf = new byte[1500];
                DatagramPacket recv = new DatagramPacket(buf, buf.length);
                s.receive(recv); // Block until we get responses
                buf = recv.getData();
                Long clientDate = (Long) Helpers.getObject(buf);
                System.out.println(
                        "Client " + recv.getAddress().toString() + " thinks the time is " + clientDate.longValue());
            }
        } catch (UnknownHostException e) {
            System.err.println(e);
        } catch (SocketTimeoutException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        } 
    }
}
