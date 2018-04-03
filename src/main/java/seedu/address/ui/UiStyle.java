package seedu.address.ui;

import javafx.scene.Scene;

/**
 * stores the styles that can be used for the interface
 */
public class UiStyle {
    private static final String LIGHT_THEME_STYLE = "view/LightTheme.css";
    private static final String DARK_THEME_STYLE = "view/DarkTheme.css";
    private static final String MORNING_THEME_STYLE = "view/MorningTheme.css";
    private static Scene scene = null;
    private static UiStyle instance = null;

    public static void setScene(Scene s) {
        scene = s;
        setDefaultTheme();
    }

    private static void setDefaultTheme() {
        scene.getStylesheets().add(MORNING_THEME_STYLE);
    }

    public static void setToLightTheme() {
        scene.getStylesheets().setAll(LIGHT_THEME_STYLE);
    }

    public static void setToDarkTheme() {
        scene.getStylesheets().setAll(DARK_THEME_STYLE);
    }

    public static void setToMorningTheme() {
        scene.getStylesheets().add(MORNING_THEME_STYLE);
    }

    public static UiStyle getInstance() {
        if (instance == null) {
            instance = new UiStyle();
        }

        return instance;
    }
}
