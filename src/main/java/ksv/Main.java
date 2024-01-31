package ksv;

import ksv.Model.Registry;
import ksv.Presenter.Presenter;
import ksv.View.View;

public class Main {
    public static void main(String[] args) {
        View view = new View();
        Registry registry = new Registry();
        Presenter presenter = new Presenter(view, registry);

        // Запускаем презентер
        presenter.start();
    }
}