package interfaces;

public interface ModelInterface {
    void setDirectory(String path);
    void setPresenter(PresenterInterface presenter);
    String searchDirectory( String pattern);
    String searchFolder(String Folder, String pattern);
    String showDirectory();
    String showFileLength();
    String deleteFile(String name);
}
