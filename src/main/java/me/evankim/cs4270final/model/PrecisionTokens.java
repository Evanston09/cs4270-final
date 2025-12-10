package me.evankim.cs4270final.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Represents the precision tokens scoring component.
 * Tracks remaining precision tokens (0-6) and calculates score based on tokens available.
 * Scoring: 0 tokens = 0 pts, 1 = 10 pts, 2 = 15 pts, 3 = 25 pts, 4 = 35 pts, 5-6 = 50 pts
 */
public class PrecisionTokens extends ScoringComponent {
    private final IntegerProperty tokensRemaining;

    /**
     * Creates a PrecisionTokens component starting with 6 tokens (maximum).
     */
    public PrecisionTokens() {
        this.tokensRemaining = new SimpleIntegerProperty(6); // Start with max tokens
        this.score = new ReadOnlyIntegerWrapper(0);
        this.score.bind(tokensRemaining.map(n -> switch (n.intValue()) {
            case 0 -> 0;
            case 1 -> 10;
            case 2 -> 15;
            case 3 -> 25;
            case 4 -> 35;
            case 5, 6 -> 50;
            default -> 0;
        }));
    }

    /**
     * Gets the number of precision tokens remaining.
     *
     * @return the number of tokens (0-6)
     */
    public int getTokensRemaining() {
        return tokensRemaining.get();
    }

    /**
     * Gets the tokens remaining as a property for JavaFX bindings.
     *
     * @return the tokens remaining property
     */
    public IntegerProperty tokensRemainingProperty() {
        return tokensRemaining;
    }

    /**
     * Sets the number of precision tokens remaining.
     *
     * @param tokensRemaining the number of tokens (0-6)
     * @throws IllegalArgumentException if tokens not in range [0, 6]
     */
    public void setTokensRemaining(int tokensRemaining) {
        if (tokensRemaining < 0 || tokensRemaining > 6) {
            throw new IllegalArgumentException("Tokens must be between 0 and 6");
        }
        this.tokensRemaining.set(tokensRemaining);
    }
}
