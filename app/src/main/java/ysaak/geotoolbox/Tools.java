package ysaak.geotoolbox;

import ysaak.geotoolbox.wordvalue.WordValueActivity;

public enum Tools {
    WORD_VALUE(WordValueActivity.class, R.string.word_value_title, R.string.word_value_description)
    ;

    public final Class<?> activityClass;
    public final int title;
    public final int description;

    Tools(Class<?> activityClass, int title, int description) {
        this.activityClass = activityClass;
        this.title = title;
        this.description = description;
    }
}
