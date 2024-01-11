package com.galo.minhasfinancas.sources;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FilesUtil {

  public static String pathUsuarios = "src/main/resources/static/img/usuarios/";

  public static void saveFile(String path, byte[] file){

    File saveFile = new File(path);
    try {
      FileUtils.writeByteArrayToFile(saveFile, file);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void deletarFile(String path){
    try{
      Files.delete(Paths.get(path));
    }catch (IOException e){
      e.printStackTrace();
    }
  }

}
