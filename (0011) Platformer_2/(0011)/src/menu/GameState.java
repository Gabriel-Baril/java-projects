package menu;

public final class GameState {
	public static final int MENU = 0;
	public static final int IN_GAME = 1;
	
	private static int CURRENT_STATE;
	public static void setCurrentState(int STATE) {GameState.CURRENT_STATE = STATE;}
	public static int getCurrentState() {return GameState.CURRENT_STATE;}
}
