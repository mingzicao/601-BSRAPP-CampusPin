//package com.tutorialspoint;

import java.io.*;

public class RuntimeDemo {

   public static void main(String[] args) {
      String line;
      try {
      Process process = Runtime.getRuntime().exec("python building_class.py /Users/zhiyuwang/tf_files/template_img.jpg", null, null);
      InputStream is = process.getInputStream();
      InputStreamReader isr = new InputStreamReader(is);
      BufferedReader br = new BufferedReader(isr);
      while ((line = br.readLine()) != null) {
         System.out.println(line);
            }
         } 
      catch (Exception ex) 
      {
         ex.printStackTrace();
      }
   }
}
