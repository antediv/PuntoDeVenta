
import java.util.Scanner;

public class Main {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean salirPrograma = false;

        while (!salirPrograma) {
            System.out.println("\n========== PUNTO DE VENTA ==========");
            System.out.println("1. Login");
            System.out.println("2. Registrar usuario");
            System.out.println("3. Salir");
            System.out.println("====================================");
            System.out.print("Seleccione una opcin: ");

            String entrada = sc.nextLine();
            int opcion;
            try {
                opcion = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un nmero vlido.");
                continue;
            }

            switch (opcion) {
                case 1:
                    System.out.print("Nombre de usuario: ");
                    String user = sc.nextLine().toUpperCase();
                    System.out.print("Contraseña: ");
                    String pass = sc.nextLine().toUpperCase();

                    Usuario usuarioLogueado = Usuario.login(user, pass);
                    if (usuarioLogueado != null) {
                        menuAutenticado(usuarioLogueado);
                    } else {
                        System.out.println("Error: El usuario no existe o est mal escrito.");
                    }
                    break;
                case 2:
                    Usuario.crearUsuarios();
                    break;
                case 3:
                    salirPrograma = true;
                    System.out.println("Cerrando el sistema... ¡Hasta pronto!");
                    break;
                default:
                    System.out.println("Opcin no vlida.");
            }
        }
    }

    private static void menuAutenticado(Usuario user) {
        boolean cerrarSesion = false;

        while (!cerrarSesion) {
            System.out.println("\n******************************************");
            System.out.println("BIENVENIDO " + user.getUserName().toUpperCase());
            System.out.println("******************************************");
            System.out.println("1. Registrar producto");
            System.out.println("2. Buscar producto");
            System.out.println("3. Eliminar producto");
            System.out.println("4. Actualizar producto");
            System.out.println("5. Mostrar todos los productos");
            System.out.println("6. Comprar productos");
            System.out.println("7. Cerrar sesin");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opcin: ");

            String entrada = sc.nextLine();
            int opcion;
            try {
                opcion = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Opcin no vlida.");
                continue;
            }

            switch (opcion) {
                case 1:
                    Producto.crearProductos();
                    break;
                case 2:
                    System.out.print("Ingrese el ID del producto: ");
                    try {
                        int id = Integer.parseInt(sc.nextLine());
                        Producto prod = Producto.buscarProducto(id);
                        if (prod != null) {
                            System.out.println("Producto encontrado: " + prod.getNombre() + " | Precio: " + prod.getPrecio() + " | Stock: " + prod.getStock());
                        } else {
                            System.out.println("Producto no encontrado.");
                        }
                    } catch (Exception e) {
                        System.out.println("ID invlido.");
                    }
                    break;
                case 3:
                    Producto.eliminarProducto();
                    break;
                case 4:
                    Producto.editarProducto();
                    break;
                case 5:
                    Producto.listarProductos();
                    break;
                case 6:
                    menuCesta(user);
                    break;
                case 7:
                    cerrarSesion = true;
                    System.out.println("Cerrando sesin de " + user.getUserName());
                    break;
                case 8:
                    System.out.println("Saliendo del sistema...");
                    cerrarSesion = true;
                    break;
                default:
                    System.out.println("Opcin no vlida.");
            }
        }
    }

    private static void menuCesta(Usuario u) {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- CARRITO DE COMPRAS ---");
            System.out.println("1. Agregar producto a la cesta");
            System.out.println("2. Ver mi cesta");
            System.out.println("3. Quitar producto de la cesta");
            System.out.println("4. Pagar (Comprar)");
            System.out.println("5. Generar Factura (si ya pag)");
            System.out.println("6. Volver al men principal");
            System.out.print("Opcin: ");

            try {
                int op = Integer.parseInt(sc.nextLine());
                switch (op) {
                    case 1:
                        Cesta.agregarACesta();
                        break;
                    case 2:
                        Cesta.listarCesta();
                        break;
                    case 3:
                        Cesta.eliminarProductoDeLaCesta();
                        break;
                    case 4:
                        Cesta.comprar();
                        break;
                    case 5:
                        Factura.generarFactura(u);
                        break;
                    case 6:
                        volver = true;
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error en la entrada.");
            }
        }
    }
}
