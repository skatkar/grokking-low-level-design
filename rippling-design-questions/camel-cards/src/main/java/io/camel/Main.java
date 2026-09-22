package io.camel;

public class Main {
    /**
     * Implement a simplified poker-like game called Camel Cards to determine which of two players has the stronger hand.
     *
     * Each hand has exactly 4 cards and is represented as a string such as "2332" or "9998".
     *
     * Card ranks are ordered from high to low as:
     *
     * 9, 8, 7, 6, 5, 4, 3, 2, 1
     * Each hand belongs to exactly one of these types, from strongest to weakest:
     *
     * Four of a kind: all four cards are the same, for example "9999"
     * Two pair: two cards of one rank and two cards of another rank, for example "2332"
     * Three of a kind: three cards of one rank and one different card, for example "9998"
     * One pair: two cards of one rank and two distinct other cards, for example "5233"
     * High card: all four cards are distinct, for example "2345"
     * Implement:
     *
     * evaluate(hand1: string, hand2: string) -> string
     * Return:
     *
     * "HAND_1" if hand1 wins
     * "HAND_2" if hand2 wins
     * "TIE" if both hands are exactly tied
     * Comparison rules:
     *
     * Compare hand type first
     * If both hands have the same type, compare cards from right to left
     * Do not sort the cards before tie-breaking
     * The first differing position determines the winner
     * If all four positions match, the result is a tie
     *
     * Design the solution so adding more hand types later is straightforward.
     * @param args
     */
    public static void main(String[] args) {
        CamelCards camelCards = new CamelCards();
        System.out.println(camelCards.evaluate("9999", "8888")); // HAND_1
        System.out.println(camelCards.evaluate("2332", "9998")); // HAND_1
        System.out.println(camelCards.evaluate("9998", "8887")); // HAND_1
        System.out.println(camelCards.evaluate("5233", "5122")); // HAND_1
        System.out.println(camelCards.evaluate("2345", "2344")); // HAND_1

        System.out.println(camelCards.evaluate("1234", "1234")); // TIE

        // Same type, compare right to left
        System.out.println(camelCards.evaluate("1234", "1235")); // HAND_2
        System.out.println(camelCards.evaluate("1234", "1233")); // HAND_1
    }
}