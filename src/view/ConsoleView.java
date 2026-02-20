package view;

import java.util.Scanner;

import interfaces.PresenterInterface;
import interfaces.ViewInterface;

public class ConsoleView implements ViewInterface {
    private PresenterInterface presenter;
    private Scanner input = new Scanner(System.in);

    @Override
    public void setPresenter(PresenterInterface presenter) {
        this.presenter = presenter;
    }

    @Override
    public int showMenu() {
        System.out.println("EXPLORADOR DE ARCHIVOS");
        System.out.println("1. Buscar Archivo");
        System.out.println("2. Tamaño directorio");
        System.out.println("3. Listar carpeta específica");
        System.out.println("4. Borrar un archivo");
        System.out.println("5. Salir");
        System.out.flush();
        System.out.println("Opción: ");
        int opcion = Integer.parseInt(input.nextLine());
        return opcion;
    }

    @Override
    public String askString(String msg) {
        System.out.println(msg);
        return input.nextLine();
    }

    @Override
    public void showMessage(String msg) {
        System.out.println(msg);
    }

    @Override
    public void showError(String msg) {
        System.out.println("ERROR: " + msg);
    }

    @Override
    public int confirmationMessaeg() {
        System.out.println("Confirmar Acción:\nSi(1) --- No(2)");
        return Integer.parseInt(input.nextLine());
    }

}
