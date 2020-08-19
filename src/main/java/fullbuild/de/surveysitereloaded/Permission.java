package fullbuild.de.surveysitereloaded;

public enum Permission {

    WILDCARD,
    READ_BASIC,
    CREATE_THREAD_BASIC;


    //
    @Override
    public String toString() {
        return super.toString().toLowerCase().replaceAll("_", ".");
    }
}
