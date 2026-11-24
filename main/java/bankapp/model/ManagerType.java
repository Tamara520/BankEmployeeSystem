/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankapp.model;

/**
 * ManagerType - the valid manager types in the bank, from most to least senior.
 * The rank is used to build the employee hierarchy (most senior at the top).
 */
public enum ManagerType {
    HEAD_MANAGER("Head Manager", 1),
    SENIOR_MANAGER("Senior Manager", 2),
    MANAGER("Manager", 3),
    ASSISTANT_MANAGER("Assistant Manager", 4),
    TEAM_LEAD("Team Lead", 5),
    STAFF("Staff", 6);

    private final String label;
    private final int rank;

    ManagerType(String label, int rank) {
        this.label = label;
        this.rank = rank;
    }

    public String getLabel() { return label; }
    public int getRank()     { return rank; }

    /**
     * Maps a job title from the file to a manager type.
     * Titles that are not a manager title (e.g. "Bookkeeper") become STAFF.
     *
     * @param jobTitle job title text from the file
     * @return the matching manager type
     */
    public static ManagerType fromJobTitle(String jobTitle) {
        if (jobTitle != null) {
            for (ManagerType type : values()) {
                if (type.label.equalsIgnoreCase(jobTitle.trim())) {
                    return type;
                }
            }
        }
        return STAFF;
    }

    @Override
    public String toString() { return label; }
}

