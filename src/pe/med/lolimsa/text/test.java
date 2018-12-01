package pe.med.lolimsa.text;

import java.sql.Connection;
import pe.med.lolimsa.config.Config;
import pe.med.lolimsa.db.Database;

/**
 * @Class: test
 * @email: elitgamaliel@gmail.com
 * @author: Tarazona Marrujo Elí Gamaliel
 * @Copyright: "Lolimsa - System JAADE S.A.C."
 */
public class test {

  public static void main(String[] args) throws Exception {
    Config.isValidConfigurationFound();
    Database bd = new Database();
    Connection cn = null;
    cn = bd.getConnection();
    if (cn != null) {
      System.out.println("hay conexion");
    }
  }

}
