import java.util.ArrayList;
import java.util.List;

public class BowlingGame {

	private static final String EXCEPTION_NEGATIVE_ROLL = "Negative roll is invalid";
	private static final String EXCEPTION_TOO_MANY_PINS = "Pin count exceeds pins on the lane";
	private static final String EXCEPTION_GAME_OVER = "Cannot roll after game is over";
	private static final String EXCEPTION_GAME_NOT_OVER = "Score cannot be taken until the end of the game";

	private final List<Integer> rolls;
	private boolean isFirstRoll;
	private boolean isLastRoll;
	private boolean isBonusRoll;
	private boolean isGameOver;
	private int score;
	private int frameNumber;
	private int frameScore;
	private int pinsUp;

	public BowlingGame() {
		rolls = new ArrayList<>();
		isFirstRoll = true;
		isLastRoll = false;
		isBonusRoll = false;
		isGameOver = false;
		score = 0;
		frameNumber = 1;
		frameScore = 0;
		pinsUp = 10;
	}

	public int score() {
		if (!isGameOver) {
			throw new IllegalStateException(EXCEPTION_GAME_NOT_OVER);
		}

		isFirstRoll = true;
		for (int i = 0; i < rolls.size(); i++) {
			frameScore += rolls.get(i);
			if (frameScore == 10) {
				if (isFirstRoll) {
					frameScore += rolls.get(i + 1) + rolls.get(i + 2);
					if (i == rolls.size() - 3) {
						addFrameScore();
						break;
					}
				} else {
					frameScore += rolls.get(i + 1);
					if (i == rolls.size() - 2) {
						addFrameScore();
						break;
					}
				}
				addFrameScore();
			} else if (!isFirstRoll) {
				addFrameScore();
			} else {
				isFirstRoll = false;
			}
		}

		return score;
	}

	public void roll(final int pins) {
		validateThrow(pins);
		
		rolls.add(pins);
		pinsUp -= pins;

		if (frameNumber == 10) {
			isBonusRoll |= pinsUp == 0;
			isGameOver = isLastRoll || !isBonusRoll && !isFirstRoll;
			pinsUp = (pinsUp + 9) % 10 + 1;
			isLastRoll = !isFirstRoll;
			isFirstRoll = false;
		} else {
			if (pinsUp == 0 || !isFirstRoll) {
				endFrame();
			} else {
				isFirstRoll = false;
			}
		}
	}

	private void validateThrow(final int pins) {
		if (pins < 0) {
			throw new IllegalStateException(EXCEPTION_NEGATIVE_ROLL);
		}

		if (isGameOver) {
			throw new IllegalStateException(EXCEPTION_GAME_OVER);
		}

		if (pinsUp - pins < 0) {
			throw new IllegalStateException(EXCEPTION_TOO_MANY_PINS);
		}
	}

	private void addFrameScore() {
		score += frameScore;
		frameScore = 0;
		isFirstRoll = true;
	}

	private void endFrame() {
		frameNumber++;
		pinsUp = 10;
		isFirstRoll = true;
	}

}
