package analysis;

public class Analytics {
    private static Analytics analytics_instance = null;

    // TODO: decide exactly what attributes to include

    private Analytics() {
        // TODO
    }

    public static Analytics getInstance()
    {
        if (analytics_instance == null)
            analytics_instance = new Analytics();

        return analytics_instance;
    }

    public void getStaticAttrbPlaceholder() {
        // TODO
        // used in MainFrame when complete
    }

    public void getDynamicAttrbPlaceholder() {
        // TODO
        // used in MainFrame when complete
    }

    public void setStaticAttrbPlaceholder() {
        // TODO
    }

    public void setDynamicAttrbPlaceholder() {
        // TODO
    }

}
