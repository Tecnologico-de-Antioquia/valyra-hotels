package src.main.java.com.tdea.consola;

public class CLI {
    /**
     * Colores para la consola
     **/

    private String RED = "\u001B[31m";
    private String GREEN = "\u001B[32m";
    private String YELLOW = "\u001B[33m";
    private String BLUE = "\u001B[34m";
    private String PURPLE = "\u001B[35m";
    private String CYAN = "\u001B[36m";
    private String RESET = "\u001B[0m";

    public void printTitle(String title, String color) {
        System.out.println(color + """
                ***************************
                %s
                ***************************
                """.formatted(title.toUpperCase()) + RESET);
    }

    public void printText(String texto, String color) {
        System.out.println(color + texto + RESET);
    }

    public void printText(String texto) {
        System.out.println(texto);
    }

    public void printMenu() {
        printTitle("Bienvenido a Valyra Hotels", BLUE);
    }
}
