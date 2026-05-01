
import java.util.ArrayList;
import java.util.Date;

public class Factura extends Cesta {

    public static void generarFactura(Usuario user) {
        ArrayList<Cesta> listaCompra = Cesta.getListaCesta();

        if (!listaCompra.isEmpty()) {

            double total = 0;
            Date fecha = new Date();

            System.out.println("\n" + fecha);
            System.out.println("\n\t\tArticulos");
            System.out.println("Piezas\tProducto\tTotal");

            int cantidadProductos = 0;
            for (int i = 0; i < listaCompra.size(); i++) {
                Cesta item = listaCompra.get(i);
                int piezas = item.getStock();
                double precio = item.getPrecio();

                double totalProducto = piezas * precio;
                total += totalProducto;
                cantidadProductos += piezas;

                System.out.println("  " + piezas + "\t\t" + item.getNombre()
                        + "\t\t" + totalProducto);
            }

            double iva = total * 0.16;
            double subtotal = total - iva;

            System.out.println("\n\tSubtotal = \t" + subtotal);
            System.out.println("\tI.V.A    = \t" + iva);
            System.out.println("\tTotal    = \t" + total);

            System.out.println("\n# Productos = " + cantidadProductos);
            System.out.println("Usuario     = " + user.getUserName());

            Cesta.getListaCesta().clear();
            System.out.println("\nCompra finalizada y cesta vaciada.");

        } else {
            System.out.println("El carrito esta vacio.");
        }
    }
}
