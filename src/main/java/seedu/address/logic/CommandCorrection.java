package seedu.address.logic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.HistoryCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ListJobsCommand;
import seedu.address.logic.commands.MatchJobCommand;
import seedu.address.logic.commands.PostJobCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.RemarkCommand;
import seedu.address.logic.commands.ReportCommand;
import seedu.address.logic.commands.SelectCommand;
import seedu.address.logic.commands.ThemeCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.commands.ViewCommand;

/***
 * TODO: Add Javadoc comment here.
 */
public class CommandCorrection {
    private static Set<String> commandDictionary;
    private static String commandParameters;
    private static int tabCounter = 0;
    private static String commandInput;
    private static ArrayList<String> latestSuggestionsList;
    private static final int NUMBER_ALPHABET = 26;
    private static final int START_INDEX = 0;
    private static boolean isFirstCall = true;

    public CommandCorrection() {
        createDictionary();
        latestSuggestionsList = new ArrayList<String>();
    }

    public static int getTabCounter() {
        return tabCounter;
    }

    public static void setUpCommandCorrection() {
        createDictionary();
    }

    public static void setUpCommandCompletion() {
        if (isFirstCall) {
            commandInput = "";
            isFirstCall = false;
        }
        createDictionary();
    }

    /***
     * Creates a dictionary of words that are included in the command words.
     */
    public static void createDictionary() {
        commandDictionary = new HashSet<>();
        commandDictionary.add(AddCommand.COMMAND_WORD);
        commandDictionary.add(ClearCommand.COMMAND_WORD);
        commandDictionary.add(DeleteCommand.COMMAND_WORD);
        commandDictionary.add(EditCommand.COMMAND_WORD);
        commandDictionary.add(FindCommand.COMMAND_WORD);
        commandDictionary.add(HelpCommand.COMMAND_WORD);
        commandDictionary.add(HistoryCommand.COMMAND_WORD);
        commandDictionary.add(ListCommand.COMMAND_WORD);
        commandDictionary.add(ListJobsCommand.COMMAND_WORD);
        commandDictionary.add(MatchJobCommand.COMMAND_WORD);
        commandDictionary.add(PostJobCommand.COMMAND_WORD);
        commandDictionary.add(RedoCommand.COMMAND_WORD);
        commandDictionary.add(RemarkCommand.COMMAND_WORD);
        commandDictionary.add(ReportCommand.COMMAND_WORD);
        commandDictionary.add(SelectCommand.COMMAND_WORD);
        commandDictionary.add(ThemeCommand.COMMAND_WORD);
        commandDictionary.add(UndoCommand.COMMAND_WORD);
        commandDictionary.add(ViewCommand.COMMAND_WORD);

    }

    /***
     * Extracts the command word and corrects only the command word and stores the rest in {@code commandParameters}
     */
    public static String extractCommandWord(String commandText) {
        String trimmedCommandText = commandText.trim();
        String[] wordsInCommandText = trimmedCommandText.split(" ");
        commandParameters = commandText;
        commandParameters = commandParameters.replace(wordsInCommandText[0], "");
        return wordsInCommandText[0];
    }

    /***
     * Checks if the commandText that contains the command word is already a correct command.
     * @param commandText
     * @return boolean indicating whether it is a correct command.
     */
    public static boolean isCorrectCommand(String commandText) {
        return commandDictionary.contains(commandText);
    }

    /***
     * Finds the nearest correction that is found. If no correction is found the text is returned as is.
     * @param commandText
     * @returns corrected String according the rules.
     */
    public static String nearestCorrection(String commandText) {

        if (isCorrectCommand(commandText)) {
            return commandText;
        }

        String extractedcommandText = extractCommandWord(commandText);

        String nearestCorrectedCommand = removeOneCharacter(extractedcommandText);
        if (nearestCorrectedCommand != null) {
            return nearestCorrectedCommand.concat(commandParameters);
        }
        nearestCorrectedCommand = addOneCharacter(extractedcommandText);
        if (nearestCorrectedCommand != null) {
            return nearestCorrectedCommand.concat(commandParameters);
        }
        nearestCorrectedCommand = swapTwoCharacters(extractedcommandText);
        if (nearestCorrectedCommand != null) {
            return nearestCorrectedCommand.concat(commandParameters);
        }

        return commandText;
    }

    /***
     * TODO: Write javadoc comment here.
     * @param commandText
     * @return
     */
    public static String removeOneCharacter(String commandText) {
        for (int index = 0; index < commandText.length(); index++) {
            StringBuilder stringBuilder = new StringBuilder(commandText);
            stringBuilder.deleteCharAt(index);

            if (isCorrectCommand(stringBuilder.toString())) {
                return stringBuilder.toString();
            }
        }

        return null;
    }

    /***
     * TODO: Write javadoc ocmments here.
     * @param commandText
     * @return
     */
    public static String addOneCharacter(String commandText) {
        String alphabetString = new String("abcdefghijklmnopqrstuvwxyz");

        for (int i = 0; i < NUMBER_ALPHABET; i++) {
            char alphabet = alphabetString.charAt(i);
            for (int position = 0; position <= commandText.length(); position++) {
                StringBuilder stringBuilder = new StringBuilder(commandText);
                stringBuilder.insert(position, alphabet);
                String modifiedCommand = stringBuilder.toString();

                if (isCorrectCommand(modifiedCommand)) {
                    return modifiedCommand;
                }
            }
        }
        return null;
    }

    /***
     * Returns correct word when two consecutive alphabets are swapped.
     * @param commandText
     * @returns a corrected String, if available. Else returns the same string.
     */
    public static String swapTwoCharacters(String commandText) {

        for (int position = 0; position < commandText.length() - 1; position++) {
            StringBuilder stringBuilder = new StringBuilder(commandText);
            char temp = stringBuilder.charAt(position);
            stringBuilder.deleteCharAt(position);
            stringBuilder.insert(position + 1, temp);
            String modifiedCommand = stringBuilder.toString();

            if (isCorrectCommand(modifiedCommand)) {
                return modifiedCommand;
            }
        }
        return null;
    }

    /***
     * TODO: Write a javadoc comment
     */
    public static void updateSuggestionsList(String commandText) {
        if (commandText.compareTo(commandInput) == 0) {
            latestSuggestionsList.clear();
            tabCounter++;
        } else {
            latestSuggestionsList = new ArrayList<String>();
            tabCounter = 0;
        }
    }

    /***
     * Function attempts to complete command that is consistent with the text already typed.
     * @param commandText
     * @return
     */
    public static List<String> completeCommand(String commandText) {
        //updateSuggestionsList(commandText);
        commandInput = commandText;

        if (isCorrectCommand(commandText)) {
            latestSuggestionsList.add(commandText.concat(" "));
            return latestSuggestionsList;
        }

        int commandTextLength = commandText.length();
        Iterator<String> iterator = commandDictionary.iterator();

        while (iterator.hasNext()) {
            String nextCommand = iterator.next();
            int nextCommandLength = nextCommand.length();
            if (nextCommandLength > commandTextLength) {
                String nextCommandSnippet = nextCommand.substring(START_INDEX, commandTextLength);
                if (nextCommandSnippet.compareTo(commandText) == 0) {
                    latestSuggestionsList.add(nextCommand.concat(" "));
                }
            }
        }
        return latestSuggestionsList;
    }
}
