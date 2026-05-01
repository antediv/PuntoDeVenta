
import java.util.ArrayList;

public class Usuario {

    private String userName;
    private String password;

    private static ArrayList<Usuario> listaUsuarios = new ArrayList<>();

    public Usuario(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public Usuario() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static void crearUsuarios() {
        int opcion = 1;

        while (opcion == 1) {
            System.out.println("Ingrese nombre de usuario:");
            String username = Main.sc.nextLine().toUpperCase();
            System.out.println("Ingrese contraseña:");
            String password = Main.sc.nextLine().toUpperCase();

            if (validarUsuario(username, password)) {
                System.out.println("Usuario registrado con exito.");
            } else {
                System.out.println("Error: El usuario ya existe.");
            }

            System.out.println("******************************************");
            System.out.println("¿Desea crear otro usuario? (1: Si / 2: No)");
            System.out.println("******************************************");

            if (Main.sc.hasNextInt()) {
                opcion = Main.sc.nextInt();
            } else {
                System.out.println("Opcion no valida, saliendo...");
                opcion = 2;
            }
            Main.sc.nextLine();
        }
    }

    public static boolean validarUsuario(String username, String password) {
        int totalUsuarios = listaUsuarios.size();
        for (int i = 0; i < totalUsuarios; i++) {
            Usuario u = listaUsuarios.get(i);
            if (u.getUserName().equals(username)) {
                return false;
            }
        }
        listaUsuarios.add(new Usuario(username, password));
        return true;
    }

    public static Usuario login(String username, String password) {
        int totalUsuarios = listaUsuarios.size();
        for (int i = 0; i < totalUsuarios; i++) {
            Usuario u = listaUsuarios.get(i);
            if (u.getUserName().equals(username) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }
}
