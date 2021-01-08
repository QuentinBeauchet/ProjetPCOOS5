package Controller;

import View.StartView;

import java.io.File;

public class HomeController {

    /**
     * Classe du controller qui appele la vue de depart du programme.
     */
    public HomeController() {
        new File("data").mkdir();
        new StartView();
    }

}
