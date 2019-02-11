package fight;

import game.Launcher;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import characters.Character;

public class FightWindowController {

	@FXML
    private ImageView playerImage;
	@FXML
    private ImageView opponentImage;
	@FXML
    private Label playerLabel;
    @FXML
    private Label opponentLabel;
    @FXML
    private ProgressBar healthBarPlayer;
    @FXML
    private ProgressBar manaBarPlayer;
    @FXML
    private ProgressBar staminaBarPlayer;
    @FXML
    private ProgressBar healthBarOpponent;
    @FXML
    private ProgressBar manaBarOpponent;
    @FXML
    private ProgressBar staminaBarOpponent;
    @FXML
    private Label healthLabelPlayer;
    @FXML
    private Label healthLabelOpponent;
    @FXML
    private Label manaLabelPlayer;
    @FXML
    private Label staminaLabelPlayer;
    @FXML
    private Label staminaLabelOpponent;
    @FXML
    private Label manaLabelOpponent;
	
	private Character player, opponent;
	
	public FightWindowController() {
	}
	
	@FXML
    void initialize() {
		/*Platform.runLater(() -> {
			playerImage.setImage(player.getFightImage());
			opponentImage.setImage(opponent.getFightImage());
	    });*/
	}
	
	public void setCharacters(Character player, Character opponent) {
		this.player = player;
		this.opponent = opponent;
		
		init();
	}
	
	public void init() {
		/*playerImage.setFitWidth(Character.DEFAULT_WIDTH);
		playerImage.setFitHeight(Character.DEFAULT_HEIGHT);
		opponentImage.setFitWidth(Character.DEFAULT_WIDTH);
		opponentImage.setFitHeight(Character.DEFAULT_HEIGHT);*/
		playerImage.setImage(player.getFightImage());	
		opponentImage.setImage(opponent.getFightImage());
		
		playerLabel.setText("Lv." + player.getLvl() + " " + player.getName());
		opponentLabel.setText("Lv." + opponent.getLvl() + " " + opponent.getName());
		
		updateBarsAndLabels();
	}
	
	private void updateBarsAndLabels() {
		healthLabelPlayer.setText(player.getCurrentHp() + " / " + player.getHp());
		healthBarPlayer.setProgress((double) player.getCurrentHp() / player.getHp());
		manaLabelPlayer.setText(player.getCurrentMp() + " / " + player.getMp());
		manaBarPlayer.setProgress((double) player.getCurrentMp() / player.getMp());
		staminaLabelPlayer.setText(player.getCurrentStamina() + " / " + player.getStamina());
		staminaBarPlayer.setProgress((double) player.getCurrentStamina() / player.getStamina());
		
		healthLabelOpponent.setText(opponent.getCurrentHp() + " / " + opponent.getHp());
		healthBarOpponent.setProgress((double) opponent.getCurrentHp() / opponent.getHp());
		manaLabelOpponent.setText(opponent.getCurrentMp() + " / " + opponent.getMp());
		manaBarOpponent.setProgress((double) opponent.getCurrentMp() / opponent.getMp());
		staminaLabelOpponent.setText(opponent.getCurrentStamina() + " / " + opponent.getStamina());
		staminaBarOpponent.setProgress((double)opponent.getCurrentStamina() / opponent.getStamina());
	}
	
	public void playerAttack() {
		opponent.setCurrentHp(opponent.getCurrentHp() - (player.getAttack() - opponent.getDefence()));
		player.setCurrentStamina(player.getCurrentStamina() - player.getEffort());
		updateBarsAndLabels();
	}
}
