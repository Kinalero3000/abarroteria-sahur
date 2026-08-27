package main.java.com.jgunzalesindustries.abarroteria.kinal.config;


public class Credentials {
       //final: constante
    //NUNCA DEJAR DATOS QUEMADOS AL HACER PUSH EN GIT!!!!!!!!!!!!
    //usar System.getenv("Variable de entorno")
    public static final String URL_DB = "jdbc:mysql://localhost:3306/abarroteria_kinal_in4bv";
    public static final String USER_DB = "IN4BV";
    public static final String PASS_DB = "%IndiVA4";
    
    private Credentials(){
    
    }
}
