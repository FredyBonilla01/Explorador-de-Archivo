import java.io.File;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println(args[0]);

        File directorio = new File(args[0]);

        if (directorio.exists() && directorio.isDirectory()) {
            System.out.println("--------------------------------------");
            System.out.println("DIRECTORIO: "+args[0]);
            System.out.println("--------------------------------------");

            File[] archivos = directorio.listFiles();
            System.out.println("NOMBRE DEL ARCHIVO                      | TAMAÑO(Bytes)");
            for (File archivo : archivos) {
                if(archivo.isDirectory()){
                    System.out.println("SUBCARPETA ->"+archivo.getName()+"|"+archivo.length()+" Bytes");
                }else{
                    System.out.println(archivo.getName()+" | "+archivo.length()+"(Bytes)");
                }
            }
        } else {
            System.out.println("La ruta no existe o no es un directorio.");
        }
    }
}
