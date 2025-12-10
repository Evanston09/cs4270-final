package me.evankim.cs4270final.model;

public class PrecisionTokens extends ScoringComponent {
    private int tokensRemaining;

    public PrecisionTokens() {
        this.tokensRemaining = 6; // Start with max tokens
    }

    public int getTokensRemaining() {
        return tokensRemaining;
    }

    public void setTokensRemaining(int tokensRemaining) {
        if (tokensRemaining < 0 || tokensRemaining > 6) {
            throw new IllegalArgumentException("Tokens must be between 0 and 6");
        }
        this.tokensRemaining = tokensRemaining;
    }

    @Override
    public int calculateScore() {
        return switch (tokensRemaining) {
            case 0 -> 0;
            case 1 -> 10;
            case 2 -> 15;
            case 3 -> 25;
            case 4 -> 35;
            case 5, 6 -> 50;
            default -> 0;
        };
    }

}
