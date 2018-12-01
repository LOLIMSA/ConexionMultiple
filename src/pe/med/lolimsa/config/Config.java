/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.med.lolimsa.config;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author Isuru
 */
public class Config {

  static Properties p;

  public static boolean isValidConfigurationFound() {
    p = new Properties();
    try {
      p.load(new FileInputStream("config.bin"));
      return p.containsKey("host");
    } catch (Exception ex) {
      return false;
    }
  }

  public static void createConfig() {
    try {
      File cf = new File("config.bin");
      if (!cf.exists()) {
        cf.createNewFile();
      }
      JOptionPane.showMessageDialog(null, "Configuracion no valida", "no valido.", JOptionPane.WARNING_MESSAGE);
    } catch (Exception e) {
    }
  }

  public static Properties getConfiguration() throws Exception {
    return p;
  }

  public static boolean setConfiguration(Properties prop) throws Exception {
    p = prop;
    File cf = new File("config.bin");
    if (!cf.exists()) {
      cf.createNewFile();
    }
    FileOutputStream fos = new FileOutputStream("config.bin");
    p.store(fos, "NO BORRAR ESTE ARCHIVO. (C) POR LOLIMSA\nCreado en: " + new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
    return true;
  }

  public static byte[] serializeToBytes(Object obj) throws Exception {
    ByteArrayOutputStream b = new ByteArrayOutputStream();
    ObjectOutputStream o = new ObjectOutputStream(b);
    o.writeObject(obj);
    return b.toByteArray();
  }

  public static Object deserializeFromBytes(byte[] bytes) throws Exception {
    ByteArrayInputStream b = new ByteArrayInputStream(bytes);
    ObjectInputStream o = new ObjectInputStream(b);
    return o.readObject();
  }

}
