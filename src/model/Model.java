package model;

import java.io.File;

import interfaces.ModelInterface;
import interfaces.PresenterInterface;
import util.Util;

public class Model implements ModelInterface {

    private PresenterInterface presenter;
    private File directory;

    @Override
    public void setPresenter(PresenterInterface presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setDirectory(String path) {
        this.directory = new File(path);
    }

    @Override
    public String searchDirectory(String pattern) {
        StringBuilder answer = new StringBuilder();
        pattern = Util.regex(pattern);

        File[] listaArchivos = directory.listFiles();

        for (File f : listaArchivos) {
            if (f.isDirectory()) {
                answer.append(searchFolder(f.getPath(), pattern));
            } else {
                if (f.getName().matches(pattern)) {
                    answer.append(f.getName())
                            .append("\nUbicación: ").append(f.getPath())
                            .append("\nTamaño: ").append(f.length()).append(" Bytes\n");
                }
            }
        }

        return answer.length() == 0 ? "Archivo no encontrado." : answer.toString();
    }

    @Override
    public String searchFolder(String folder, String pattern) {
        StringBuilder answer = new StringBuilder();
        File newDirectory = new File(folder);
        File[] subdirectorio = newDirectory.listFiles();

        if (subdirectorio == null)
            return "";

        for (File f : subdirectorio) {
            if (f.isDirectory()) {
                answer.append(searchFolder(f.getPath(), pattern));
            } else {
                if (f.getName().matches(pattern)) {
                    answer.append(f.getName()).append("\nUbicación: ").append(f.getPath())
                            .append("\nTamaño: ").append(f.length()).append(" Bytes\n");
                }
            }
        }

        return answer.toString();
    }

    @Override
    public String showDirectory() {
        StringBuilder txt = new StringBuilder();
        txt.append("Ruta: " + directory.getPath());
        File[] listaArchivos = directory.listFiles();
        for (File f : listaArchivos) {
            txt.append(f + "     " + f.length());
        }
        return txt.toString();
    }

    @Override
    public String deleteFile(String pattern) {
        StringBuilder answer = new StringBuilder();
        pattern = Util.regex(pattern);

        File[] listaArchivos = directory.listFiles();
        if (listaArchivos == null)
            return "No se pudo acceder al directorio.";

        for (File f : listaArchivos) {
            if (f.isDirectory()) {
                answer.append(deleteInFolder(f.getPath(), pattern));
            } else {
                if (f.getName().matches(pattern)) {
                    if (f.delete()) {
                        answer.append("Archivo eliminado: ").append(f.getName())
                                .append("\nUbicación: ").append(f.getPath()).append("\n");
                    } else {
                        answer.append("No se pudo eliminar: ").append(f.getName()).append("\n");
                    }
                }
            }
        }

        String resultado = answer.toString();
        return resultado.isEmpty() ? "Archivo no encontrado." : resultado;
    }

    private String deleteInFolder(String folder, String pattern) {
        StringBuilder answer = new StringBuilder();
        File newDirectory = new File(folder);
        File[] subdirectorio = newDirectory.listFiles();

        if (subdirectorio == null)
            return "";

        for (File f : subdirectorio) {
            if (f.isDirectory()) {
                answer.append(deleteInFolder(f.getPath(), pattern));
            } else {
                if (f.getName().matches(pattern)) {
                    if (f.delete()) {
                        answer.append("Archivo eliminado: ").append(f.getName())
                                .append("\nUbicación: ").append(f.getPath()).append("\n");
                    } else {
                        answer.append("No se pudo eliminar: ").append(f.getName()).append("\n");
                    }
                }
            }
        }

        return answer.toString();
    }

    @Override
    public String showFileLength() {
        return "El tamaño del directorio <" + directory.getName() + "> es: \n" + directory.length() + " Bytes";
    }

}
