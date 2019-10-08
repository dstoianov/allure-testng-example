package se.techinsight;

/**
 * @author Boris Serdyuk just-boris@yandex-team.ru
 *         Date: 12/9/13
 */
public class Behaviors {

    public class Feature {
        public static final String BROWSER = "Browser Tests";
    }

    public class Story {
        public static final String title = "Simple Stories";
        public static final String BROWSER = "Browser";
        public static final String SUCCESS_STORY = "Success Story";
        public static final String FAILED_STORY = "Failed Story";
        public static final String SIMPLE_STORY = "Simple Story Description";
        public static final String PARAMETRIZED_STORY = "Parametrized Story";
    }

    public class Screenshot {
        public static final String title = "Screenshot";
        public static final String WEBDRIVER_SCREENSHOT = "Webdriver Screenshot";
        public static final String OTHER_SCREENSHOT = "Other Screenshot";
    }

    public class Priority {
        public static final String title = "Priority Feature";
        public static final String STORY = "Story description for this feature";
        public static final String FAILED_STORY = "Failed Story";
        public static final String SIMPLE_STORY = "Simple Story Description";
    }
}
