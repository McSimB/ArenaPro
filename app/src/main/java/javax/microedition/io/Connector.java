package javax.microedition.io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.microedition.io.Connection;

public class Connector
{

 private Connector()
 {
 }

 public static Connection open(String s)
     throws IOException
 {
     return null;
 }

 public static Connection open(String s, int i)
     throws IOException
 {
     return null;
 }

 public static Connection open(String s, int i, boolean flag)
     throws IOException
 {
     return null;
 }

 public static DataInputStream openDataInputStream(String s)
     throws IOException
 {
     return null;
 }

 public static DataOutputStream openDataOutputStream(String s)
     throws IOException
 {
     return null;
 }

 public static InputStream openInputStream(String s)
     throws IOException
 {
     return null;
 }

 public static OutputStream openOutputStream(String s)
     throws IOException
 {
     return null;
 }

 public static final int READ = 1;
 public static final int WRITE = 2;
 public static final int READ_WRITE = 3;
}