package fullbuild.de.surveysitereloaded;

public enum Permission {

    WILDCARD;


    //
    @Override
    public String toString() {
        return super.toString().toLowerCase().replaceAll("_", ".");
    }
}
