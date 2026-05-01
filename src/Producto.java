
import java.util.ArrayList;
import java.util.Random;

public class Producto {

    private static ArrayList<Producto> listaProductos = new ArrayList<>();
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;
    private int id;

    public Producto(String nombre, String descripcion, double precio, int stock, int id) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.id = id;
    }

    public Producto() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static void crearProductos() {
        int opcion = 1;

        while (opcion == 1) {
            System.out.println("Ingrese el nombre del producto:");
            String nombre = Main.sc.nextLine().toUpperCase();
            System.out.println("Ingrese la descripcin del producto:");
            String descripcion = Main.sc.nextLine().toUpperCase();
            System.out.println("Ingrese el precio del producto:");
            double precio = Double.parseDouble(Main.sc.nextLine());
            System.out.println("Ingrese el ID del producto:");
            int id = Integer.parseInt(Main.sc.nextLine());

            if (validarProducto(nombre, descripcion, precio, id)) {
                System.out.println("Producto creado con xito.");
            } else {
                System.out.println("Error: El producto con ese nombre o ID ya existe.");
            }

            System.out.println("*********************************************");
            System.out.println("¿Desea crear otro producto? (1: Si / 2: No)");
            System.out.println("*********************************************");

            if (Main.sc.hasNextInt()) {
                opcion = Main.sc.nextInt();
            } else {
                opcion = 2;
            }
            Main.sc.nextLine();
        }
    }

    public static boolean validarProducto(String nombre, String descripcion, double precio, int id) {
        int tamano = listaProductos.size();

        for (int i = 0; i < tamano; i++) {
            Producto p = listaProductos.get(i);
            if (p.getNombre().equals(nombre) || p.getId() == id) {
                return false;
            }
        }

        Random random = new Random();
        int stockAleatorio = random.nextInt(91) + 10;

        Producto nuevoProducto = new Producto(nombre, descripcion, precio, stockAleatorio, id);
        listaProductos.add(nuevoProducto);
        return true;
    }

    public static void listarProductos() {
        int cantidadProductos = listaProductos.size();
        System.out.println("\n--- LISTADO DE PRODUCTOS ---");
        System.out.println("ID\tNombre\t\tDescripcion\t\tPrecio\t\tStock");
        System.out.println("---------------------------------------------------------------------------");
        for (int i = 0; i < cantidadProductos; i++) {
            Producto p = listaProductos.get(i);
            System.out.println(p.getId() + "\t" + p.getNombre() + "\t\t" + p.getDescripcion() + "\t\t" + p.getPrecio() + "\t\t" + p.getStock());
        }
    }

    public static Producto buscarProducto(int id) {
        int tamano = listaProductos.size();
        for (int i = 0; i < tamano; i++) {
            Producto prod = listaProductos.get(i);
            if (prod.getId() == id) {
                return prod;
            }
        }
        return null;
    }

    public static void editarProducto() {
        System.out.println("Ingrese el ID del producto a editar:");
        int id = Integer.parseInt(Main.sc.nextLine());

        Producto prod = buscarProducto(id);
        if (prod == null) {
            System.out.println("Producto no encontrado.");
            return;
        }

        System.out.println("Ingrese nuevo nombre (actual: " + prod.getNombre() + "):");
        String nuevoNombre = Main.sc.nextLine().toUpperCase();

        if (!nuevoNombre.equals(prod.getNombre())) {
            int tamano = listaProductos.size();
            for (int i = 0; i < tamano; i++) {
                if (listaProductos.get(i).getNombre().equals(nuevoNombre)) {
                    System.out.println("Error: Ya existe otro producto con ese nombre.");
                    return;
                }
            }
        }

        System.out.println("Ingrese nueva descripcin:");
        String nuevaDesc = Main.sc.nextLine().toUpperCase();
        System.out.println("Ingrese nuevo precio:");
        double nuevoPrecio = Double.parseDouble(Main.sc.nextLine());
        System.out.println("Ingrese nuevo stock:");
        int nuevoStock = Integer.parseInt(Main.sc.nextLine());

        prod.setNombre(nuevoNombre);
        prod.setDescripcion(nuevaDesc);
        prod.setPrecio(nuevoPrecio);
        prod.setStock(nuevoStock);
        System.out.println("Producto actualizado.");
    }

    public static void eliminarProducto() {
        System.out.println("Ingrese el ID del producto a eliminar:");
        int id = Integer.parseInt(Main.sc.nextLine());

        int tamano = listaProductos.size();
        for (int i = 0; i < tamano; i++) {
            if (listaProductos.get(i).getId() == id) {
                listaProductos.remove(i);
                System.out.println("Producto eliminado con xito.");
                return;
            }
        }
        System.out.println("Producto no encontrado.");
    }

    public static ArrayList<Producto> getListaProductos() {
        return listaProductos;
    }
}
