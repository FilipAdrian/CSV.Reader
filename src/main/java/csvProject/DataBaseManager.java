package csvProject;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class DataBaseManager {

    private static String dataBaseName = "Records.db";
    private static String path = "./data/";
    private static String url = "jdbc:sqlite:" + path + dataBaseName;

    public static Connection makeConnection() {
        Connection connection = null;
        try {
            File file = new File (path + dataBaseName);
            if (!file.exists ( )) {
                createNewDataBase (dataBaseName);
                createTableX ( );
            }
            connection = DriverManager.getConnection (url);
            System.out.println ("Connection to SQLite has been established");
        } catch (SQLException e) {
            e.printStackTrace ( );
        } finally {
            if (connection == null) {
                try {
                    connection.close ( );
                } catch (SQLException e) {
                    System.out.println (e.getMessage ( ));
                    e.printStackTrace ( );
                }
            }
        }
        return connection;
    }

    private static void createNewDataBase(String fileName) {
        String url = "jdbc:sqlite:"+path + fileName;
        try {
            Class.forName ("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection (url);
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData ( );
                System.out.println ("The driver name is " + meta.getDriverName ( ));
                System.out.println ("A new database has been created.");
            }
            conn.close ( );
        } catch (Exception e) {
            e.printStackTrace ( );
        }

    }

    public static void createTableX() {
        String sql = "create table if not exists X\n" +
                "(\n" +
                "  ID INTEGER primary key  AUTOINCREMENT,\n" +
                "  A  TEXT,\n" +
                "  B  TEXT,\n" +
                "  C  TEXT,\n" +
                "  D  TEXT,\n" +
                "  E  TEXT,\n" +
                "  F  TEXT,\n" +
                "  G  TEXT,\n" +
                "  H  TEXT,\n" +
                "  I  TEXT,\n" +
                "  J  TEXT )";
        Connection connection = makeConnection ( );
        try {
            Statement statement = connection.createStatement ( );
            statement.execute (sql);
            connection.close ( );
        } catch (SQLException e) {
            e.printStackTrace ( );
        }
    }

    public static void dropTableX() {
        String sql = "drop table if exists X  ";
        Connection connection = makeConnection ( );
        try {
            Statement statement = connection.createStatement ( );
            statement.execute (sql);
        } catch (SQLException e) {
            e.printStackTrace ( );
        }

    }

    public static void insertRecordsIntoTableX(List <String[]> records) {
        Connection connToDataBase = makeConnection ();
        String sql = "Insert into  X(A,B,C,D,E,F,G,H,I,J)" +
                " values (?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = connToDataBase.prepareStatement (sql);
            ArrayList <String[]> badData = new ArrayList <> ( );
            Logger logger = LoggerWrite.writeLogFile ( );
            for (int i = 0; i < records.size ( ) - 1; i++) {
                String[] line = records.get (i);
                if (line.length > 10) {
                    badData.add (line);
                } else {
                    int check = 0;
                    for (int j = 0; j < line.length; j++) {
                        System.out.println (line[j] );
                        if (line[j].isEmpty ( )) {
                            badData.add (line);
                            check = 1;
                            break;
                        } else {
                            if (line[j].contains (",")) {
                                line[j] = "\"" + line[j] + "\"";
                            }
                            stmt.setString (j + 1, line[j]);
                        }
                    }
                    if (check == 0) {
                        stmt.executeUpdate ( );
                    }
                }

            }
            stmt.close ();
            logger.info ("Received Records = " + records.size ());
            logger.info ("Successful Records = " + (records.size () - badData.size ( )));
            logger.info ("Failed Records = " + badData.size ( ));
            CsvFileManager.write (badData);
        } catch (SQLException e) {
            e.printStackTrace ( );
        }
    }

}
