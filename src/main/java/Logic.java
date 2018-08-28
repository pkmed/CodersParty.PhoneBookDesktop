import java.sql.ResultSet;
import java.sql.SQLException;

public class Logic {
    private static Window win;
    public static void main(String[] args){
        setUpDBConnection();
        win = new Window();
        win.setVisible(true);
        try {
            win.updateList(getAllElements());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    static Window getCurrentWin(){
        return win;
    }
    private static void setUpDBConnection(){
        JDBC_mysql_connector.importJDBC();
        ConnectionForm regForm = new ConnectionForm();
        regForm.setVisible(true);
        while(regForm.getWindowState()!=WindowState.DISPOSED){
            System.out.println("");
        }
    }
    static void addNumber(Long number, String fullName, String address){
        try {
            JDBC_mysql_connector.execUpdate("INSERT INTO people " +
                    "(phoneNumber, fullName, address) " +
                    "VALUES ("+number+", '"+fullName+"', '"+address+"');");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    static void editNumber(Long number, String fullName, String address, Integer oldNumber){
        try {
            ResultSet row = JDBC_mysql_connector.execQuery("SELECT * FROM people WHERE phoneNumber="+oldNumber+";");
            int oldRowId=-1;
            while(row.next()){
                oldRowId = row.getInt(1);
            }
            JDBC_mysql_connector.execUpdate("UPDATE people "+
                    "SET phoneNumber="+number+", fullName='"+fullName+"', address='"+address+"' " +
                    "WHERE id="+oldRowId+";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    static ResultSet getAllElements(){
        try {
            return JDBC_mysql_connector.execQuery("SELECT * FROM people;");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    static String getElementByNumber(long number) {
        try {
            ResultSet searchResult = JDBC_mysql_connector.execQuery("SELECT * " +
                    "FROM people WHERE phoneNumber="+number+";");
            String result="";
            while(searchResult.next()){
                result = searchResult.getString(2)+";";
                result += searchResult.getString(3)+";";
                result += searchResult.getString(4);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    static String getElementByFullName(String fullName) {
        try {
            ResultSet searchResult =  JDBC_mysql_connector.execQuery("SELECT * " +
                    "FROM people WHERE fullName='"+fullName+"' ORDER BY phoneNumber;");
            String result="";
            while(searchResult.next()){
                result += searchResult.getString(2)+";";
                result += searchResult.getString(3)+";";
                result += searchResult.getString(4)+"\n";
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    static String getElementByAddress(String address) {
        try {
            ResultSet searchResult =  JDBC_mysql_connector.execQuery("SELECT * " +
                    "FROM people WHERE address='"+address+"';");
            String result="";
            while(searchResult.next()){
                result = searchResult.getString(2)+";";
                result += searchResult.getString(3)+";";
                result += searchResult.getString(4);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    static void deleteElement(int number){
        try {
            JDBC_mysql_connector.execUpdate("DELETE FROM people " +
                    "WHERE phoneNumber='"+number+"';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    static ResultSet sortBy(String sortType) {
        try {
            return JDBC_mysql_connector.execQuery("SELECT * FROM people ORDER BY "+sortType+";");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
