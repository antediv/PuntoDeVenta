import java.util.Scanner;

public class PuntoDeVenta {
    public Usuarios[] lista = new Usuarios[100];
    public Productos[] listaProductos = new Productos[100];
    public int totalUsuarios = 0;
    public int totalProductos = 0;
    Scanner leer = new Scanner(System.in);

    public static void main(String[] args) {
        PuntoDeVenta tienda = new PuntoDeVenta();
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- PUNTO DE VENTA: ---");
            System.out.println("1. Registrar Usuario");
            System.out.println("2. Registrar Producto");
            System.out.println("3. Comprar y Facturar Producto");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    tienda.RegistrarUsuario();
                    break;
                case 2:
                    tienda.RegistrarProducto();
                    break;
                case 3:
                    tienda.FacturarProducto();
                    break;
                case 4:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 4);
    }

    public void RegistrarUsuario() {
        if (totalUsuarios < 100) {
            System.out.println("\n--- Nuevo Usuario ---");
            System.out.print("Nombre: ");
            String username = leer.next();
            System.out.print("Password: ");
            String password = leer.next();

            lista[totalUsuarios] = new Usuarios(username, password);
            totalUsuarios++;
            System.out.println("¡Usuario #" + totalUsuarios + " registrado!");
        } else {
            System.out.println("Error: Límite de usuarios alcanzado.");
        }
    }

    public void RegistrarProducto() {
        System.out.println("\n *** Inicia sesión con algún usuario registrado ***");
        System.out.print("Usuario: ");
        String userCheck = leer.next();
        System.out.print("Password: ");
        String passCheck = leer.next();

        boolean validado = false;
        for (int i = 0; i < totalUsuarios; i++) {
            if (lista[i].getUsername().equals(userCheck) && lista[i].getPassword().equals(passCheck)) {
                validado = true;
                break;
            }
        }

        if (validado) {
            if (totalProductos < 100) {
                System.out.print("Nombre del producto: ");
                String nombre = leer.next();
                System.out.print("Descripción: ");
                String desc = leer.next();
                System.out.print("Precio: ");
                double precio = leer.nextDouble();
                System.out.print("Stock inicial: ");
                int stock = leer.nextInt();

                listaProductos[totalProductos] = new Productos(nombre, desc, precio, stock);
                totalProductos++;
                System.out.println("Producto registrado exitosamente.");
            } else {
                System.out.println("Error: Límite de productos alcanzado.");
            }
        } else {
            System.out.println("ACCESO DENEGADO: Usuario o contraseña incorrectos.");
        }
    }

    public void FacturarProducto() {
        if (totalProductos == 0) {
            System.out.println("No hay productos registrados en el sistema.");
            return;
        }

        double totalFactura = 0;
        String detalleFactura = "";
        String continuar;

        do {
            System.out.print("\nIngrese nombre del producto para agregar al carrito: ");
            String busqueda = leer.next();
            boolean encontrado = false;

            for (int i = 0; i < totalProductos; i++) {
                if (listaProductos[i].getNombre().equalsIgnoreCase(busqueda)) {
                    encontrado = true;
                    if (listaProductos[i].getStock() > 0) {
                        // 3. AGREGAR PRODUCTO (Restar stock y sumar al total)
                        listaProductos[i].setStock(listaProductos[i].getStock() - 1);
                        totalFactura += listaProductos[i].getPrecio();
                        detalleFactura += "- " + listaProductos[i].getNombre() + " ($" + listaProductos[i].getPrecio() + ")\n";

                        System.out.println("¡" + listaProductos[i].getNombre() + " agregado al carrito!");
                    } else {
                        System.out.println("Lo sentimos, no hay stock de: " + busqueda);
                    }
                    break;
                }
            }

            if (!encontrado) {
                System.out.println("Producto no encontrado.");
            }

            System.out.print("¿Desea agregar otro producto al carrito? (si/no): ");
            continuar = leer.next();

        } while (continuar.equalsIgnoreCase("si"));

        if (totalFactura > 0) {
            System.out.println("\n--- CARRITO ACTUAL ---");
            System.out.print(detalleFactura);
            System.out.println("TOTAL ACUMULADO: $" + totalFactura);

            System.out.print("\n¿Desea facturar esta compra? (si/no): ");
            String confirmar = leer.next();

            if (confirmar.equalsIgnoreCase("si")) {
                System.out.println("\n========================");
                System.out.println("      FACTURA FINAL     ");
                System.out.println("========================");
                System.out.print(detalleFactura);
                System.out.println("------------------------");
                System.out.println("TOTAL PAGADO: $" + totalFactura);
                System.out.println("========================");
                System.out.println("¡Venta realizada con éxito!");
            } else {
                System.out.println("Compra cancelada. Los productos se quedaron en el carrito (simulado).");
            }
        } else {
            System.out.println("El carrito está vacío.");
        }
    }
}