package pe.med.lolimsa.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.med.lolimsa.config.Config;

/**
 *
 * @author Isuru
 */
public class Database {

  private static Connection cn;
  private static String host;
  private static String port;
  private static String database;
  private static String user;
  private static String password;
  private static String allowPublicKeyRetrieval;
  private static String verifyServerCertificate;
  private static String zeroDateTimeBehavior;
  private static String requireSSL;
  private static String useSSL;
  private static String autoReconnect;

  static Properties conf = null;

  static {
    try {
      Properties config = Config.getConfiguration();
      host = config.getProperty("host");
      port = config.getProperty("port");
      database = config.getProperty("database");
      user = config.getProperty("user");
      password = config.getProperty("password");
      allowPublicKeyRetrieval = config.getProperty("allowPublicKeyRetrieval");
      verifyServerCertificate = config.getProperty("verifyServerCertificate");
      zeroDateTimeBehavior = config.getProperty("zeroDateTimeBehavior");
      requireSSL = config.getProperty("requireSSL");
      useSSL = config.getProperty("useSSL");
      autoReconnect = config.getProperty("autoReconnect");

      conf = config;
    } catch (Exception ex) {
      Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public static Connection getConnection() throws Exception {
    String url = "";
    String driver = "";
    try {
      String condb = conf.getProperty("sgbd");
      switch (condb) {
        case "MySQL":
          driver = "com.mysql.jdbc.Driver";
          url = "jdbc:mysql://" + conf.getProperty("host") + ":" + conf.getProperty("port") + "/" + conf.getProperty("database");
          Class.forName(driver).newInstance();
          cn = DriverManager.getConnection(url, conf);
          System.out.println("llego a hacer la conexion, con " + conf.getProperty("sgbd"));
          break;
        case "SQL Server":
          driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
          url = "jdbc:sqlserver://" + conf.getProperty("host") + ":" + conf.getProperty("port") + ";databaseName=" + conf.getProperty("database");
          Class.forName(driver);
          cn = DriverManager.getConnection(url, conf.getProperty("user"), conf.getProperty("password"));
          System.out.println("llego a hacer la conexion, con " + conf.getProperty("sgbd"));
          break;
        case "Oracle":
          Class.forName("oracle.jdbc.driver.OracleDriver");
          url ="jdbc:oracle:thin:@" + conf.getProperty("host") + ":" + conf.getProperty("port") + ":"+ conf.getProperty("database");
          cn = DriverManager.getConnection(url,  conf.getProperty("user"), conf.getProperty("password"));
          System.out.println("llego a hacer la conexion, con " + conf.getProperty("sgbd"));
          break;
        case "PostgreSQL":
          driver = "org.postgresql.Driver";
          url = "jdbc:postgresql://" + conf.getProperty("host") + ":" + conf.getProperty("port") + "/" + conf.getProperty("database");
          Class.forName(driver).newInstance();
          cn = DriverManager.getConnection(url, conf.getProperty("user"), conf.getProperty("password"));
          System.out.println("llego a hacer la conexion, con " + conf.getProperty("sgbd"));
          break;
      }
    } catch (SQLException e) {
      // log.warn("SQLException error " + e.getMessage());
      throw e;
    } catch (ClassNotFoundException e) {
      //log.error("Error, no se encontro el driver");
      throw new SQLException("Error, no se encontro el driver");
    } catch (Exception e) {
      //log.error("Error, no se puede establecer la conexión.");
      throw new SQLException("ERROR, no se puede establecer la conexión.");
    }
    return cn;
  }

}
