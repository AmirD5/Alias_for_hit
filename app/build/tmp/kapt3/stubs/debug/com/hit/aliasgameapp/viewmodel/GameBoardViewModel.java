package com.hit.aliasgameapp.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\fJ\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00190\u00072\u0006\u0010\u001a\u001a\u00020\fJ\b\u0010\u001c\u001a\u00020\u001dH\u0002J\u0016\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020\u000bJ\u000e\u0010!\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u000bJ\b\u0010\"\u001a\u00020\u001dH\u0002J\u0016\u0010#\u001a\u00020\u001d2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0007H\u0002R\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\t\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00070\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001d\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R#\u0010\u0016\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0011\u00a8\u0006%"}, d2 = {"Lcom/hit/aliasgameapp/viewmodel/GameBoardViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "_boardSpaces", "Landroidx/lifecycle/MutableLiveData;", "", "Lcom/hit/aliasgameapp/data/model/BoardSpace;", "_teamPositions", "", "", "Lcom/hit/aliasgameapp/data/model/TeamPosition;", "allTeams", "Landroidx/lifecycle/LiveData;", "Lcom/hit/aliasgameapp/data/model/Team;", "getAllTeams", "()Landroidx/lifecycle/LiveData;", "boardSpaces", "getBoardSpaces", "repository", "Lcom/hit/aliasgameapp/repository/TeamRepository;", "teamPositions", "getTeamPositions", "getCurrentPlayer", "", "teamPosition", "getMembersList", "initializeBoardSpaces", "", "moveTeam", "teamId", "spaces", "nextPlayer", "observeTeams", "updateTeamPositions", "teams", "app_debug"})
public final class GameBoardViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.hit.aliasgameapp.repository.TeamRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.hit.aliasgameapp.data.model.Team>> allTeams = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.Map<java.lang.Integer, com.hit.aliasgameapp.data.model.TeamPosition>> _teamPositions = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.Map<java.lang.Integer, com.hit.aliasgameapp.data.model.TeamPosition>> teamPositions = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.hit.aliasgameapp.data.model.BoardSpace>> _boardSpaces = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.hit.aliasgameapp.data.model.BoardSpace>> boardSpaces = null;
    
    public GameBoardViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.hit.aliasgameapp.data.model.Team>> getAllTeams() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.Map<java.lang.Integer, com.hit.aliasgameapp.data.model.TeamPosition>> getTeamPositions() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.hit.aliasgameapp.data.model.BoardSpace>> getBoardSpaces() {
        return null;
    }
    
    private final void initializeBoardSpaces() {
    }
    
    private final void observeTeams() {
    }
    
    private final void updateTeamPositions(java.util.List<com.hit.aliasgameapp.data.model.Team> teams) {
    }
    
    public final void moveTeam(int teamId, int spaces) {
    }
    
    public final void nextPlayer(int teamId) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getMembersList(@org.jetbrains.annotations.NotNull()
    com.hit.aliasgameapp.data.model.TeamPosition teamPosition) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getCurrentPlayer(@org.jetbrains.annotations.NotNull()
    com.hit.aliasgameapp.data.model.TeamPosition teamPosition) {
        return null;
    }
}