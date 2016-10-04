

import com.mysql.jdbc.*;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConToServer {

    private String passwd = null;//keno password gia thn metatroph tou pass tou xrhsth se string gia ton elenxo
    private String username;//to username to xrhsth
    private char password[];//to pass tou xrhsth opws to pernw apo thn eisagwgh tou kai prin thn metatroph se string gia ton elenxo
    boolean connect;
    Connection con;
    Statement statement;

    ConToServer() {
    }
    ResultSet rs, rs2;

    ConToServer(String username, char password[], Connection con, Statement statement, String passwd) {
        this.password = password;
        this.username = username;
        this.con = con;
        this.statement = statement;
        this.passwd = passwd;

    }

    public void setusername(String username) {
        this.username = username;
    }

    public void setpassword(char password[]) {
        this.password = password;
    }

    public String getusername() {
        return username;
    }

    public char[] getpassword() {
        return password;
    }

    public void setpasswd(String passwd) {
        this.passwd = passwd;

    }

    public String getpasswd() {
        return passwd;

    }

    public boolean connection() throws SQLException {
        try {
            passwd = String.valueOf(password);//apo char se string gia ton elexno tou password

            if (statement.execute("select username from users where username='" + getusername() + "'")) {

                rs = statement.getResultSet();
                if (rs.next()) {

                    if (statement.execute("select password from users where password='" + getpasswd() + "'")) {

                        rs2 = statement.getResultSet();

                        if (rs2.next()) {
                            connect = true;
                            statement.execute("select username from users where username='" + getusername() + "'");
                            rs = statement.getResultSet();

                        }
                    }

                }

            } else {
                connect = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConToServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex) {
            System.out.println(ex.getMessage());
        }
        return connect;
    }
}
