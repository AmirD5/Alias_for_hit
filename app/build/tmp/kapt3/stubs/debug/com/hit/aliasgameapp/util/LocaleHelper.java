package com.hit.aliasgameapp.util;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bJ\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u0004H\u0002J\u0016\u0010\f\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u0004J\u000e\u0010\r\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bJ\u0018\u0010\u000e\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/hit/aliasgameapp/util/LocaleHelper;", "", "()V", "KEY_LANGUAGE", "", "PREFS_NAME", "getLanguage", "context", "Landroid/content/Context;", "saveLanguage", "", "languageCode", "setLocale", "toggleLanguage", "updateResources", "app_debug"})
public final class LocaleHelper {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String PREFS_NAME = "language_prefs";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_LANGUAGE = "selected_language";
    @org.jetbrains.annotations.NotNull()
    public static final com.hit.aliasgameapp.util.LocaleHelper INSTANCE = null;
    
    private LocaleHelper() {
        super();
    }
    
    /**
     * Set the app locale
     */
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context setLocale(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String languageCode) {
        return null;
    }
    
    /**
     * Get the saved language preference, or detect system language if not set
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLanguage(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    /**
     * Toggle between supported languages (English and Hebrew)
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String toggleLanguage(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    /**
     * Save language preference
     */
    private final void saveLanguage(android.content.Context context, java.lang.String languageCode) {
    }
    
    /**
     * Update the app resources with the selected locale
     */
    private final android.content.Context updateResources(android.content.Context context, java.lang.String languageCode) {
        return null;
    }
}