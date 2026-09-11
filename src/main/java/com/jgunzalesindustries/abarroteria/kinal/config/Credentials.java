package main.java.com.jgunzalesindustries.abarroteria.kinal.config;


public class Credentials {
       //final: constante
    //NUNCA DEJAR DATOS QUEMADOS AL HACER PUSH EN GIT!!!!!!!!!!!!
    //usar System.getenv("Variable de entorno")
    public static final String URL_DB = System.getenv("DB_URL");
    public static final String USER_DB = System.getenv("DB_USER");
    public static final String PASS_DB = System.getenv("DB_PASS");
    
    private Credentials(){
    
    }
}
