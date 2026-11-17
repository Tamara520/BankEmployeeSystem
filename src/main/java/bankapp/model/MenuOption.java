package bankapp.model;

/**
 * MenuOption - every option of the main menu, defined as an enum.
 * The controller and the view both use this enum, so the menu text,
 * the option numbers and the logic can never get out of step.
 */
public enum MenuOption {
    SORT(1, "SORT"),
    SEARCH(2, "SEARCH"),
    ADD_RECORDS(3, "ADD RECORDS"),
    CREATE_BINARY_TREE(4, "Create a binary tree"),
    EXIT(5, "EXIT");

    private final int number;
    private final String label;

    MenuOption(int number, String label) {
        this.number = number;
        this.label = label;
    }

    public int getNumber()   { return number; }
    public String getLabel() { return label; }

    /**
     * Finds the option for the number the user typed.
     *
     * @param number the number typed by the user
     * @return the matching option, or null if the number is not on the menu
     */
    public static MenuOption fromNumber(int number) {
        for (MenuOption option : values()) {
            if (option.number == number) {
                return option;
            }
        }
        return null;
    }
}
