package au.com.codycodes.tpk.tamizhpallikoodam;

/**
 * {@link Word} represents a vocabulary word that the user wants to learn.
 * It contains a default translation, a Tamil translation, and an image for that word.
 */
public class Word {

    /** Default translation for the word */
    private String mDefaultTranslation;

    /** Tamil translation for the word */
    private String mTamilTranslation;

    /** Audio resource ID for the word */
    private int mAudioResourceId;

    /** Image resource ID for the word */
    private int mImageResourceId = NO_IMAGE_PROVIDED;

    /** Constant value that represents no image was provided for this word */
    private static final int NO_IMAGE_PROVIDED = -1;

    /**
     * Create a new Word object.
     *
     * @param defaultTranslation is the word in a language that the user is already familiar with
     *                           (such as English)
     * @param tamilTranslation is the word in the Tamil language
     * @param audioResourceId is the resource ID for the audio file associated with this word
     */
    public Word(String defaultTranslation, String tamilTranslation, int audioResourceId) {
        mDefaultTranslation = defaultTranslation;
        mTamilTranslation = tamilTranslation;
        mAudioResourceId = audioResourceId;
    }

    /**
     * Create a new Word object.
     *
     * @param defaultTranslation is the word in a language that the user is already familiar with
     *                           (such as English)
     * @param tamilTranslation is the word in the Miwok language
     * @param imageResourceId is the drawable resource ID for the image associated with the word
     * @param audioResourceId is the resource ID for the audio file associated with this word
     */
    public Word(String defaultTranslation, String tamilTranslation, int imageResourceId,
                int audioResourceId) {
        mDefaultTranslation = defaultTranslation;
        mTamilTranslation = tamilTranslation;
        mImageResourceId = imageResourceId;
        mAudioResourceId = audioResourceId;
    }

    /**
     * Get the default translation of the word.
     */
    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    /**
     * Get the Miwok translation of the word.
     */
    public String getTamilTranslation() {
        return mTamilTranslation;
    }

    /**
     * Return the image resource ID of the word.
     */
    public int getImageResourceId() {
        return mImageResourceId;
    }

    /**
     * Returns whether or not there is an image for this word.
     */
    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    /**
     * Return the audio resource ID of the word.
     */
    public int getAudioResourceId() {
        return mAudioResourceId;
    }
}