package interfaces;

public interface PresenterInterface {
    void setModel(ModelInterface model);
    void setView(ViewInterface view);
    void menu();
    void searchFile();
    void deleteFile();

}
