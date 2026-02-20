package presenter;

import interfaces.ModelInterface;
import interfaces.PresenterInterface;
import interfaces.ViewInterface;

public class Presenter implements PresenterInterface {
    private ModelInterface model;
    private ViewInterface view;

    @Override
    public void setModel(ModelInterface model) {
        this.model = model;
    }

    @Override
    public void setView(ViewInterface view) {
        this.view = view;
    }

    @Override
    public void menu() {
        int option = 0;
        //do{
            option = view.showMenu();
            switch (option) {
                case 1:
                    searchFile();
                    break;
                case 2:
                    view.showMessage(model.showFileLength());
                    break;
                case 3:
                    view.showMessage(model.showDirectory());
                    break;
                case 4:
                    deleteFile();
                    break;
                default:
                    break;
        }
        //}while(option != 5);
    }

    public void searchFile(){
        String file = view.askString("Ingrese el nombre del archivo a buscar : ");
        view.showMessage(model.searchDirectory(file));
    }

    public void deleteFile(){
        String file = view.askString("Ingrese el nombre del archivo a eliminar: ");
        if(model.searchDirectory(file).equals("Archivo no encontrado.")){
            view.showMessage("Archivo no se encuentra en este directorio");
        }else{
            int answer = view.confirmationMessaeg();
            switch (answer) {
                case 1:
                    model.deleteFile(file);
                    break;
                case 2:
                    view.showMessage("Operación cancelada. ");
                    break;
                default:
                    break;
            }
        }
    }

}
