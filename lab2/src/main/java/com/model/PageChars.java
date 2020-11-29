package com.model;

import java.util.Objects;

public class PageChars {
    private boolean emailAvailability;
    private boolean newsAvailability;
    private boolean archiveAvailability;
    private PollsAvailabilityType pollsAvailability;

    public PageChars(boolean emailAvailability, boolean newsAvailability, boolean archiveAvailability, PollsAvailabilityType pollsAvailability) {
        this.emailAvailability = emailAvailability;
        this.newsAvailability = newsAvailability;
        this.archiveAvailability = archiveAvailability;
        this.pollsAvailability = pollsAvailability;
    }

    public boolean isEmailAvailability() {
        return emailAvailability;
    }

    public void setEmailAvailability(boolean emailAvailability) {
        this.emailAvailability = emailAvailability;
    }

    public boolean isNewsAvailability() {
        return newsAvailability;
    }

    public void setNewsAvailability(boolean newsAvailability) {
        this.newsAvailability = newsAvailability;
    }

    public boolean isArchiveAvailability() {
        return archiveAvailability;
    }

    public void setArchiveAvailability(boolean archiveAvailability) {
        this.archiveAvailability = archiveAvailability;
    }

    public PollsAvailabilityType getPollsAvailability() {
        return pollsAvailability;
    }

    public void setPollsAvailability(PollsAvailabilityType pollsAvailability) {
        this.pollsAvailability = pollsAvailability;
    }

    @Override
    public String toString() {
        return "PageChars{" +
                "emailAvailability=" + emailAvailability +
                ", newsAvailability=" + newsAvailability +
                ", archiveAvailability=" + archiveAvailability +
                ", pollsAvailability=" + pollsAvailability +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PageChars pageChars = (PageChars) o;
        return emailAvailability == pageChars.emailAvailability &&
                newsAvailability == pageChars.newsAvailability &&
                archiveAvailability == pageChars.archiveAvailability &&
                pollsAvailability == pageChars.pollsAvailability;
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailAvailability, newsAvailability, archiveAvailability, pollsAvailability);
    }
}
