package com.hit.aliasgameapp.ui.addedit;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\b\u0010.\u001a\u00020+H\u0002J\b\u0010/\u001a\u00020+H\u0002J\u0012\u00100\u001a\u00020+2\b\u00101\u001a\u0004\u0018\u000102H\u0016J$\u00103\u001a\u0002042\u0006\u00105\u001a\u0002062\b\u00107\u001a\u0004\u0018\u0001082\b\u00101\u001a\u0004\u0018\u000102H\u0016J\b\u00109\u001a\u00020+H\u0016J\u0010\u0010:\u001a\u00020+2\u0006\u0010;\u001a\u000202H\u0016J\u001a\u0010<\u001a\u00020+2\u0006\u0010=\u001a\u0002042\b\u00101\u001a\u0004\u0018\u000102H\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\f\u001a\u00020\r8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0014\u001a\u00020\u00158\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u001b\u001a\u0010\u0012\f\u0012\n \u001d*\u0004\u0018\u00010\t0\t0\u001cX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u001e\u001a\u00020\u001f8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001b\u0010$\u001a\u00020%8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b(\u0010)\u001a\u0004\b&\u0010\'\u00a8\u0006>"}, d2 = {"Lcom/hit/aliasgameapp/ui/addedit/AddEditFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/hit/aliasgameapp/databinding/FragmentAddEditBinding;", "binding", "getBinding", "()Lcom/hit/aliasgameapp/databinding/FragmentAddEditBinding;", "currentPhotoPath", "", "editingTeamId", "", "imageApi", "Lcom/hit/aliasgameapp/data/remote/ImageApi;", "getImageApi", "()Lcom/hit/aliasgameapp/data/remote/ImageApi;", "setImageApi", "(Lcom/hit/aliasgameapp/data/remote/ImageApi;)V", "isSaved", "", "nameApi", "Lcom/hit/aliasgameapp/data/remote/NameApi;", "getNameApi", "()Lcom/hit/aliasgameapp/data/remote/NameApi;", "setNameApi", "(Lcom/hit/aliasgameapp/data/remote/NameApi;)V", "originalImagePath", "pickImage", "Landroidx/activity/result/ActivityResultLauncher;", "kotlin.jvm.PlatformType", "randomWordApi", "Lcom/hit/aliasgameapp/data/remote/RandomWordApi;", "getRandomWordApi", "()Lcom/hit/aliasgameapp/data/remote/RandomWordApi;", "setRandomWordApi", "(Lcom/hit/aliasgameapp/data/remote/RandomWordApi;)V", "viewModel", "Lcom/hit/aliasgameapp/viewmodel/TeamViewModel;", "getViewModel", "()Lcom/hit/aliasgameapp/viewmodel/TeamViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "copyImageToInternalStorage", "", "uri", "Landroid/net/Uri;", "generateRandomImage", "generateRandomName", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroyView", "onSaveInstanceState", "outState", "onViewCreated", "view", "app_debug"})
public final class AddEditFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private com.hit.aliasgameapp.databinding.FragmentAddEditBinding _binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String currentPhotoPath;
    private int editingTeamId = -1;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String originalImagePath;
    private boolean isSaved = false;
    @javax.inject.Inject()
    public com.hit.aliasgameapp.data.remote.RandomWordApi randomWordApi;
    @javax.inject.Inject()
    public com.hit.aliasgameapp.data.remote.NameApi nameApi;
    @javax.inject.Inject()
    public com.hit.aliasgameapp.data.remote.ImageApi imageApi;
    @org.jetbrains.annotations.NotNull()
    private final androidx.activity.result.ActivityResultLauncher<java.lang.String> pickImage = null;
    
    public AddEditFragment() {
        super();
    }
    
    private final com.hit.aliasgameapp.databinding.FragmentAddEditBinding getBinding() {
        return null;
    }
    
    private final com.hit.aliasgameapp.viewmodel.TeamViewModel getViewModel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hit.aliasgameapp.data.remote.RandomWordApi getRandomWordApi() {
        return null;
    }
    
    public final void setRandomWordApi(@org.jetbrains.annotations.NotNull()
    com.hit.aliasgameapp.data.remote.RandomWordApi p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hit.aliasgameapp.data.remote.NameApi getNameApi() {
        return null;
    }
    
    public final void setNameApi(@org.jetbrains.annotations.NotNull()
    com.hit.aliasgameapp.data.remote.NameApi p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hit.aliasgameapp.data.remote.ImageApi getImageApi() {
        return null;
    }
    
    public final void setImageApi(@org.jetbrains.annotations.NotNull()
    com.hit.aliasgameapp.data.remote.ImageApi p0) {
    }
    
    @java.lang.Override()
    public void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void generateRandomImage() {
    }
    
    private final void generateRandomName() {
    }
    
    private final void copyImageToInternalStorage(android.net.Uri uri) {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
    
    @java.lang.Override()
    public void onSaveInstanceState(@org.jetbrains.annotations.NotNull()
    android.os.Bundle outState) {
    }
}