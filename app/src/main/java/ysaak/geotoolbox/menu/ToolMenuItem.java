package ysaak.geotoolbox.menu;

import java.util.Objects;

public final class ToolMenuItem {
    public final Class<?> activityClass;
    public final String title;
    public final String description;

    public ToolMenuItem(Class<?> activityClass, String title, String descriptionKeyId) {
        this.activityClass = activityClass;
        this.title = title;
        this.description = descriptionKeyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToolMenuItem that = (ToolMenuItem) o;
        return Objects.equals(activityClass, that.activityClass) &&
                Objects.equals(title, that.title) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(activityClass, title, description);
    }

    @Override
    public String toString() {
        return "ToolMenuItem{" +
                "activityClass=" + activityClass +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
