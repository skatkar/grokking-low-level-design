package io.camel;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class CamelCards {
    private static final String HAND_1 = "HAND_1";
    private static final String HAND_2 = "HAND_2";
    private final Map<Predicate, HandType> map = new HashMap<>();
    public String evaluate(String hand1, String hand2) {
        HandType type1 = getHandType(hand1);
        HandType type2 = getHandType(hand2);

        // HAND 1 case
        if(type1.getStrength() > type2.getStrength()){
            return HAND_1;
        }

        // HAND 2 case
        if(type1.getStrength() < type2.getStrength()){
            return HAND_2;
        }

        // both are of equal strength / rank
        for(int i=hand1.length() - 1; i >= 0; i--){
            char card1 = hand1.charAt(i);
            char card2 = hand2.charAt(i);

            if(card1 > card2)
                return HAND_1;

            if(card1 < card2)
                return HAND_2;
        }
        return "TIE";
    }

    private HandType getHandType(String hand) {
        Map<Character, Integer> frequency = new HashMap<>();
        for(char ch : hand.toCharArray()){
            frequency.put(ch, frequency.getOrDefault(ch, 0) + 1);
        }

        boolean hasCountFour = false;
        boolean hasCountThree = false;
        int pairCount = 0;

        for(int count : frequency.values()) {
            if(count == 4) {
                hasCountFour = true;
            } else if (count == 3) {
                hasCountThree = true;
            } else if(count == 2){
                pairCount++;
            }
        }

        if(hasCountFour)
            return HandType.FOUR_OF_A_KIND;

        if(hasCountThree)
            return HandType.THREE_OF_A_KIND;

        if(pairCount == 2)
            return HandType.TWO_PAIR;
        if(pairCount == 1)
            return HandType.ONE_PAIR;

        return HandType.HIGH_CARD;
    }
}
