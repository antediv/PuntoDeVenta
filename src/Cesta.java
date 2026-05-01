
import java.util.ArrayList;

public class Cesta extends Producto {

    private static ArrayList<Cesta> listaCesta = new ArrayList<>();

    public Cesta(String nombre, String descripcion, double precio, int cantidad, int id) {
        super(nombre, descripcion, precio, cantidad, id);
    }

    public Cesta() {
    }

    public static void agregarACesta() {
        System.out.println("Ingrese el ID del producto que desea agregar:");
        int id = 0;
        try {
            id = Integer.parseInt(Main.sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID no valido.");
            return;
        }

        ArrayList<Producto> inventario = Producto.getListaProductos();
        Producto encontrado = null;
        int tamanoInventario = inventario.size();
        for (int i = 0; i < tamanoInventario; i++) {
            Producto prod = inventario.get(i);
            if (prod.getId() == id) {
                encontrado = prod;
                break;
            }
        }

        if (encontrado == null) {
            System.out.println("Producto no encontrado.");
            return;
        }

        System.out.println("Ingrese la cantidad:");
        int cantidad = 0;
        try {
            cantidad = Integer.parseInt(Main.sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Cantidad no valida.");
            return;
        }

        if (validarStock(id, cantidad)) {
            listaCesta.add(new Cesta(encontrado.getNombre(), encontrado.getDescripcion(), encontrado.getPrecio(), cantidad, id));
            System.out.println("Agregado a la cesta.");
        } else {
            System.out.println("Stock insuficiente.");
        }
    }

    public static boolean validarStock(int id, int cantidad) {
        ArrayList<Producto> inventario = Producto.getListaProductos();
        int tamanoInventario = inventario.size();
        for (int i = 0; i < tamanoInventario; i++) {
            Producto prod = inventario.get(i);
            if (prod.getId() == id) {
                return prod.getStock() >= cantidad;
            }
        }
        return false;
    }

    public static void listarCesta() {
        if (listaCesta.isEmpty()) {
            System.out.println("La cesta esta vacia.");
            return;
        }
        System.out.println("\n--- PRODUCTOS EN LA CESTA ---");
        System.out.println("ID\tNombre\t\tPrecio\t\tCantidad");
        System.out.println("---------------------------------------------------------");
        int tamanoCesta = listaCesta.size();
        for (int i = 0; i < tamanoCesta; i++) {
            Cesta carrito = listaCesta.get(i);
            System.out.println(carrito.getId() + "\t" + carrito.getNombre() + "\t\t" + carrito.getPrecio() + "\t\t" + carrito.getStock());
        }
    }

    public static void eliminarProductoDeLaCesta() {
        System.out.println("Ingrese el ID del producto a eliminar:");
        int id = 0;
        try {
            id = Integer.parseInt(Main.sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID no valido.");
            return;
        }

        for (int i = 0; i < listaCesta.size(); i++) {
            if (listaCesta.get(i).getId() == id) {
                listaCesta.remove(i);
                System.out.println("Producto eliminado.");
                return;
            }
        }
        System.out.println("Producto no encontrado en la cesta.");
    }

    public static void comprar() {
        if (listaCesta.isEmpty()) {
            System.out.println("La cesta esta vacia.");
            return;
        }

        int tamanoCesta = listaCesta.size();
        ArrayList<Producto> inventario = Producto.getListaProductos();
        int tamanoInventario = inventario.size();

        for (int i = 0; i < tamanoCesta; i++) {
            Cesta carrito = listaCesta.get(i);
            for (int j = 0; j < tamanoInventario; j++) {
                Producto prod = inventario.get(j);
                if (prod.getId() == carrito.getId()) {
                    prod.setStock(prod.getStock() - carrito.getStock());
                }
            }
        }
        System.out.println("Compra realizada con exito.");

        System.out.println("******************************************");
        System.out.println("¿Desea facturar? (1: Si / 2: No)");
        System.out.println("******************************************");
        int opcion = 0;
        try {
            opcion = Integer.parseInt(Main.sc.nextLine());
        } catch (NumberFormatException e) {
            opcion = 2;
        }

        if (opcion == 2) {
            listaCesta.clear();
            System.out.println("Cesta vaciada.");
        } else {
            System.out.println("Cesta mantenida para facturacion.");
        }
    }

    public static ArrayList<Cesta> getListaCesta() {
        return listaCesta;
    }
}
