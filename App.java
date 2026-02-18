import java.io.File;
import java.io.FileFilter;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int opcion;

        File rutaInicial = new File("C:\\Users\\sala L310\\Desktop\\trabajogrupo");

        do {
            System.out.println("\nMENU");
            System.out.println("1. Buscar archivo");
            System.out.println("2. Mostrar tamano total de la carpeta inicial");
            System.out.println("3. Listar contenido de un directorio");
            System.out.println("4. Borrar archivo");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opcion: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese nombre o patron (*.txt, ejemplo*): ");
                    String patron = scanner.nextLine();
                    buscarArchivo(rutaInicial, patron);
                    break;

                case 2:
                    long tamano = calcularTamano(rutaInicial);
                    System.out.println("Tamano total: " + tamano + " bytes");
                    break;

                case 3:
                    System.out.print("Ingrese nombre del directorio: ");
                    String dir = scanner.nextLine();
                    listarDirectorio(new File(rutaInicial, dir));
                    break;

                case 4:
                    System.out.print("Ingrese nombre del archivo a borrar: ");
                    String archivo = scanner.nextLine();
                    borrarArchivo(rutaInicial, archivo);
                    break;

                case 0:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opcion invalida.");
            }

        } while (opcion != 0);
    }

    public static void buscarArchivo(File directorio, String patron) {
        File[] archivos = directorio.listFiles();
        if (archivos != null) {
            for (File f : archivos) {
                if (f.isDirectory()) {
                    buscarArchivo(f, patron);
                } else {
                    if (coincide(f.getName(), patron)) {
                        System.out.println("Encontrado:");
                        System.out.println("Ubicacion: " + f.getAbsolutePath());
                        System.out.println("Tamano: " + f.length() + " bytes\n");
                    }
                }
            }
        }
    }

    public static boolean coincide(String nombre, String patron) {
        patron = patron.replace("*", ".*");
        return nombre.matches(patron);
    }

    public static long calcularTamano(File directorio) {

        long tamano = 0;

        File[] archivos = directorio.listFiles();

        if (archivos != null) {
            for (File f : archivos) {
                if (f.isDirectory()) {
                    tamano += calcularTamano(f);
                } else {
                    tamano += f.length();
                }
            }
        }
        return tamano;
    }

    public static void listarDirectorio(File directorio) {

        if (!directorio.exists() || !directorio.isDirectory()) {
            System.out.println("Directorio invalido.");
            return;
        }

        File[] archivos = directorio.listFiles();

        if (archivos != null) {
            for (File f : archivos) {
                if (f.isDirectory()) {
                    System.out.println("[DIR] " + f.getName());
                } else {
                    System.out.println("[FILE] " + f.getName() + " - " + f.length() + " bytes");
                }
            }
        }
    }

    public static void borrarArchivo(File directorio, String nombreArchivo) {

        File[] archivos = directorio.listFiles();

        if (archivos != null) {
            for (File f : archivos) {

                if (f.isDirectory()) {
                    borrarArchivo(f, nombreArchivo);
                } else {
                    if (f.getName().equals(nombreArchivo)) {
                        if (f.delete()) {
                            System.out.println("Archivo eliminado: " + f.getAbsolutePath());
                        } else {
                            System.out.println("No se pudo eliminar: " + f.getAbsolutePath());
                        }
                    }
                }
            }
        }
    }
}
