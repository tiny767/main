package seedu.address.ui;

import java.net.URL;
import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.web.WebView;
import seedu.address.MainApp;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.ui.BrowserUrlChangedEvent;
import seedu.address.commons.events.ui.PersonPanelSelectionChangedEvent;
import seedu.address.logic.commands.FacebookLoginCommand;
import seedu.address.logic.commands.FacebookPostCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.Person;


/**
 * The Browser Panel of the App.
 */
public class BrowserPanel extends UiPart<Region> {

    public static final String DEFAULT_PAGE = "default.html";
    public static final String SEARCH_PAGE_URL =
            "https://www.google.com.sg/";

    private static final String FXML = "BrowserPanel.fxml";
    private static String processType;

    private final Logger logger = LogsCenter.getLogger(this.getClass());

    @FXML
    private WebView browser;

    private Label location;


    public BrowserPanel() {
        super(FXML);

        // To prevent triggering events for typing inside the loaded Web page.
        getRoot().setOnKeyPressed(Event::consume);

        facebookInit();

        loadDefaultPage();
        registerAsAnEventHandler(this);
    }
    /**
     * Sets the browser up to use Facebook functionality
     */
    private void facebookInit() {
        FacebookLoginCommand.setWebEngine(browser.getEngine());
        location = new Label();
        location.textProperty().bind(browser.getEngine().locationProperty());
        setEventHandlerForBrowserUrlChangedEvent();
    }

    /**
     * Loading the personal page with the given url
     * If @param person is null. Return default page.
    */
    private void loadPersonPage(Person person) {
        if (person.getLink() == null) {
            loadPage(SEARCH_PAGE_URL);
        } else {
            loadPage(person.getLink().value);
        }
    }

    public void loadPage(String url) {
        Platform.runLater(() -> browser.getEngine().load(url));
    }

    /**
     * Loads a default HTML file with a background that matches the general theme.
     */
    private void loadDefaultPage() {
        URL defaultPage = MainApp.class.getResource(FXML_FILE_FOLDER + DEFAULT_PAGE);
        loadPage(defaultPage.toExternalForm());
    }

    /**
     * Frees resources allocated to the browser.
     */
    public void freeResources() {
        browser = null;
    }

    @Subscribe
    private void handlePersonPanelSelectionChangedEvent(PersonPanelSelectionChangedEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        loadPersonPage(event.getNewSelection().person);
    }

    private void setEventHandlerForBrowserUrlChangedEvent() {
        location.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.contains("access_token")) {
                switch (processType) {
                case FacebookLoginCommand.COMMAND_WORD:
                case FacebookLoginCommand.COMMAND_ALIAS:
                    logger.fine("browser url changed to : '" + newValue + "'");
                    raise(new BrowserUrlChangedEvent(FacebookLoginCommand.COMMAND_WORD));
                    break;

                case FacebookPostCommand.COMMAND_WORD:
                case FacebookPostCommand.COMMAND_ALIAS:
                    logger.fine("browser url changed to : '" + newValue + "'");
                    raise(new BrowserUrlChangedEvent(FacebookPostCommand.COMMAND_WORD));
                    break;

                default: break;
                }
            }
        });
    }
    @Subscribe
    private void handleBrowserUrlChangedEvent(BrowserUrlChangedEvent event) throws CommandException {
        switch (event.getProcessType()) {
        case FacebookLoginCommand.COMMAND_WORD:
        case FacebookLoginCommand.COMMAND_ALIAS:
            logger.info(LogsCenter.getEventHandlingLogMessage(event));
            FacebookLoginCommand.completeAuth(browser.getEngine().getLocation());
            break;

        case FacebookPostCommand.COMMAND_WORD:
        case FacebookPostCommand.COMMAND_ALIAS:
            logger.info(LogsCenter.getEventHandlingLogMessage(event));

            FacebookLoginCommand.completeAuth(browser.getEngine().getLocation());
            break;

        default:
            break;
        }
    }
    public static void setProcessType(String pType) {
        processType = pType;
    }
}
