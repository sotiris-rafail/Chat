import com.mysql.jdbc.Statement;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTextPane;

public class HelloThread extends Thread {

    DataInputStream msgrecieve;
    JTextPane jTextPane1;
    String apanthsh = "a";
    ServerSocket ss;
    HelloThread( JTextPane jTextPane1,ServerSocket ss) {
       
        this.jTextPane1 = jTextPane1;
        this.ss=ss;
    }

    @Override
    public void run() {
        System.out.println("meta th run");
        try {
            Socket sockets = ss.accept();
            System.out.println(ss.toString());
            msgrecieve = new DataInputStream(sockets.getInputStream());
            System.out.println(ss.isBound());
            System.out.println(ss.toString());
        } catch (IOException ex) {
            Logger.getLogger(HelloThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        do {
            if (apanthsh.equalsIgnoreCase(null)) {
                try {
                    apanthsh = msgrecieve.readUTF();
                } catch (IOException ex) {
                    Logger.getLogger(HelloThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("meta to read" + apanthsh);
                if (apanthsh != null) {
                    System.out.println("meta to if" + apanthsh);
                    try {
                        jTextPane1.setText(msgrecieve.readUTF());
                    } catch (NullPointerException ex) {
                        //System.out.println(ex.getMessage());
                    } catch (IOException ex) {
                        Logger.getLogger(HelloThread.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                
                try {
                    
                    apanthsh = msgrecieve.readUTF();
                    System.out.println("meta to if" + apanthsh);
                    jTextPane1.setText(apanthsh);
                } catch (NullPointerException ex) {
                    //System.out.println(ex.getMessage());
                } catch (IOException ex) {
                    Logger.getLogger(HelloThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } while (true);
    }

}
