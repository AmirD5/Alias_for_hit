package com.hit.aliasgameapp.ui.gameboard;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u001a\u0010\u0010\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0\n0\tH\u0002J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\rH\u0002J\u0018\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0013\u001a\u00020\rH\u0002J\b\u0010\u0018\u001a\u00020\u0019H\u0002J\u001c\u0010\u001a\u001a\u00020\u00192\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00170\u001cH\u0002J\u000e\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\r0\tH\u0002J\u0010\u0010\u001e\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020 H\u0002J\u0018\u0010!\u001a\u00020\u00192\u0006\u0010\"\u001a\u00020 2\u0006\u0010#\u001a\u00020\rH\u0002J$\u0010$\u001a\u00020\u00122\u0006\u0010%\u001a\u00020&2\b\u0010\'\u001a\u0004\u0018\u00010(2\b\u0010)\u001a\u0004\u0018\u00010*H\u0016J\b\u0010+\u001a\u00020\u0019H\u0016J\u001a\u0010,\u001a\u00020\u00192\u0006\u0010-\u001a\u00020\u00122\b\u0010)\u001a\u0004\u0018\u00010*H\u0016J\u0018\u0010.\u001a\u00020\u00192\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0013\u001a\u00020\rH\u0002J\u0018\u0010/\u001a\u00020\u00192\u0006\u0010\"\u001a\u00020 2\u0006\u0010#\u001a\u00020\rH\u0002J\b\u00100\u001a\u00020\u0019H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\"\u0010\b\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0\n\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u00061"}, d2 = {"Lcom/hit/aliasgameapp/ui/gameboard/GameBoardFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/hit/aliasgameapp/databinding/FragmentGameBoardBinding;", "binding", "getBinding", "()Lcom/hit/aliasgameapp/databinding/FragmentGameBoardBinding;", "savedBoardPositions", "", "Lkotlin/Pair;", "", "savedRandomNumbers", "", "viewModel", "Lcom/hit/aliasgameapp/viewmodel/GameBoardViewModel;", "calculatePositions", "createBoardSpace", "Landroid/view/View;", "position", "number", "createPawn", "team", "Lcom/hit/aliasgameapp/data/model/TeamPosition;", "drawBoard", "", "drawPawns", "positions", "", "generateRandomNumbers", "getTeamColor", "colorName", "", "navigateToResult", "winnerName", "score", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onViewCreated", "view", "showTeamTooltip", "showWinDialog", "updatePawnsFromScore", "app_debug"})
public final class GameBoardFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private com.hit.aliasgameapp.databinding.FragmentGameBoardBinding _binding;
    private com.hit.aliasgameapp.viewmodel.GameBoardViewModel viewModel;
    @org.jetbrains.annotations.Nullable()
    private java.util.List<java.lang.Integer> savedRandomNumbers;
    @org.jetbrains.annotations.Nullable()
    private java.util.List<kotlin.Pair<java.lang.Float, java.lang.Float>> savedBoardPositions;
    
    public GameBoardFragment() {
        super();
    }
    
    private final com.hit.aliasgameapp.databinding.FragmentGameBoardBinding getBinding() {
        return null;
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
    
    private final void updatePawnsFromScore() {
    }
    
    private final void drawBoard() {
    }
    
    private final java.util.List<java.lang.Integer> generateRandomNumbers() {
        return null;
    }
    
    private final java.util.List<kotlin.Pair<java.lang.Float, java.lang.Float>> calculatePositions() {
        return null;
    }
    
    private final android.view.View createBoardSpace(int position, int number) {
        return null;
    }
    
    private final void drawPawns(java.util.Map<java.lang.Integer, com.hit.aliasgameapp.data.model.TeamPosition> positions) {
    }
    
    private final android.view.View createPawn(com.hit.aliasgameapp.data.model.TeamPosition team, int position) {
        return null;
    }
    
    private final void showTeamTooltip(com.hit.aliasgameapp.data.model.TeamPosition team, int position) {
    }
    
    private final int getTeamColor(java.lang.String colorName) {
        return 0;
    }
    
    private final void navigateToResult(java.lang.String winnerName, int score) {
    }
    
    private final void showWinDialog(java.lang.String winnerName, int score) {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
}