package com.flayger;

import java.util.*;

public class PokerHand implements Comparable<PokerHand> {

    private HashSet<Card> cards;
    private EnumMap<ValidRank, Integer> rankMap;
    public int[] priorityCardPower = new int[]{0, 0, 0, 0, 0};
    private Combinations combination = Combinations.NONE;


    PokerHand(String cardsString) throws Exception {
        HashSet<Card> card = new HashSet<>();

        String[] splitCards = cardsString.split(" ");

        for (String tempCard : splitCards) {
            card.add(new Card(tempCard));
        }

        if (card.size() != 5)
            throw new Exception("Неправильное количество карт в руке");

        this.setCards(card);

        this.setRankMap(createRankMap());

        //вычислям комбинацию карт в руке
        setCombination(checkCombination());

        //после вычисления комбинации могли остаться карты вне комбинации,
        // их располагаем после карт из комбинаций (для сравнения совпадающих комбинаций)
        fillLeftCards();
    }

    public EnumMap<ValidRank, Integer> createRankMap() {
        EnumMap<ValidRank, Integer> rankMap = new EnumMap<>(ValidRank.class);
        for (Card card : this.getCards()) {
            rankMap.put(card.getRank(), rankMap.getOrDefault(card.getRank(), 0) + 1);
        }
        return rankMap;
    }


    //проверить, какая комбинация из карт получается в руке и присвоить модификатор, разделяющий комбинации по силе.
    public Combinations checkCombination() {

        Combinations comb = Combinations.NONE;

        comb = isFour(comb);

        comb = isFullHouse(comb);

        comb = isStraight(comb);

        comb = isThree(comb);

        comb = isTwoTwo(comb);

        if (comb == Combinations.NONE)
            if (isFlush()) {
                comb = Combinations.FLUSH;
            }

        return comb;
    }

    private boolean isFlush() {
        ValidSuit check = cards.iterator().next().getSuit();
        boolean isFlush = true;
        for (Card entry : cards) {
            if (!entry.getSuit().equals(check)) {
                isFlush = false;
                break;
            }
        }
        return isFlush;
    }

    //чтобы удалять Card.Rank, задействованные в комбинации, из RankMap...
    public ValidRank getMapKey(Map<ValidRank, Integer> map, Integer value) {
        for (EnumMap.Entry<ValidRank, Integer> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }


    //при определении комбинации сохраняем их силу в порядке использования, чтобы выполнять сравнение после.
    public Combinations isFullHouse(Combinations comb) {
        if (rankMap.containsValue(3)) {
            priorityCardPower[0] = getMapKey(rankMap, 3).ordinal() + 1;
            if (rankMap.containsValue(2)) {
                priorityCardPower[1] = getMapKey(rankMap, 2).ordinal() + 1;
                rankMap.remove(getMapKey(rankMap, 3));
                rankMap.remove(getMapKey(rankMap, 2));
                comb = Combinations.FULL_HOUSE;
            }
        }
        return comb;
    }

    public Combinations isFour(Combinations comb) {
        if (rankMap.containsValue(4)) {
            priorityCardPower[0] = getMapKey(rankMap, 4).ordinal() + 1;
            rankMap.remove(getMapKey(rankMap, 4));
            comb = Combinations.FOUR;
        }
        return comb;
    }


    public Combinations isThree(Combinations comb) {
        if (rankMap.containsValue(3)) {
            priorityCardPower[0] = getMapKey(rankMap, 3).ordinal() + 1;
            rankMap.remove(getMapKey(rankMap, 3));
            comb = Combinations.THREE;
        }
        return comb;
    }

    public Combinations isTwoTwo(Combinations comb) {
        if (rankMap.containsValue(2)) {
            priorityCardPower[0] = getMapKey(rankMap, 2).ordinal() + 1;
            rankMap.remove(getMapKey(rankMap, 2));
            comb = Combinations.TWO;
            if (rankMap.containsValue(2)) {
                int rankSecond = getMapKey(rankMap, 2).ordinal() + 1;
                if (priorityCardPower[0] > rankSecond) {
                    priorityCardPower[1] = getMapKey(rankMap, 2).ordinal() + 1;
                } else {
                    priorityCardPower[1] = priorityCardPower[0];
                    priorityCardPower[0] = getMapKey(rankMap, 2).ordinal() + 1;
                }
                rankMap.remove(getMapKey(rankMap, 2));
                comb = Combinations.TWOTWO;
            }
        }
        return comb;
    }


    public Combinations isStraight(Combinations comb) {
        int straightRun = 1;
        int maxStraightRun = 1;
        int previousCardRank = -1; //т.к. значения ordinal начинаются с 0 в enum, то предыдущая карта должна быть меньше. и по заданию туз не учитывается перед 2 в комбинации.
        int count = 4;

        for (ValidRank rank : rankMap.keySet()) {
            if (rank.ordinal() - previousCardRank == 1) {
                straightRun += 1;
                //если первый TWO, то это все еще одна карта в комбинации
                if (rank.ordinal() == 0)
                    straightRun = 1;
            } else {
                maxStraightRun = Math.max(maxStraightRun, straightRun);
                straightRun = 1;
            }
            previousCardRank = rank.ordinal();
        }
        maxStraightRun = Math.max(maxStraightRun, straightRun);

        if (maxStraightRun == 5) {
            if (isFlush()) {
                comb = Combinations.STRAIGHT_FLUSH;
                //если с 10 до туза, то royal
                if (isRoyal()) {
                    comb = Combinations.ROYAL_FLUSH;
                }
            }
            for (ValidRank rank : rankMap.keySet()) {
                priorityCardPower[count--] = rank.ordinal() + 1;
                rankMap.remove(rank);
            }
            if (comb == Combinations.NONE)
                comb = Combinations.STRAIGHT;
        }

        return comb;
    }

    private boolean isRoyal() {
       return cards.stream().anyMatch(o -> o.getRank().equals(ValidRank.ACE));
    }

    //RankMap после определения комбинации содержит только карты вне комбинаций
    //и их нужно расположить в порядке убывания силы в массиве приоритета карт
    public void fillLeftCards() {
        for (int i = 0; i < priorityCardPower.length - 1; i++) {
            if (priorityCardPower[i] == 0) {
                Iterator<Map.Entry<ValidRank, Integer>> iterator = rankMap.entrySet().iterator();
                if (iterator.hasNext()) {
                    Map.Entry<ValidRank, Integer> entry = iterator.next();
                    priorityCardPower[i] = entry.getKey().ordinal() + 1;
                    rankMap.remove(entry.getKey());
                }
            }

        }
    }

    public HashSet<Card> getCards() {
        return cards;
    }

    public void setCards(HashSet<Card> cards) {
        this.cards = cards;
    }

    public void setRankMap(EnumMap<ValidRank, Integer> rankMap) {
        this.rankMap = rankMap;
    }

    public int[] getPriorityCardPower() {
        return priorityCardPower;
    }

    public Combinations getCombination() {
        return combination;
    }

    public void setCombination(Combinations combination) {
        this.combination = combination;
    }

    @Override
    public int compareTo(PokerHand otherHand) {

        int[] priorityCurrentHand = this.getPriorityCardPower();
        int[] otherPriorityCurrentHand = otherHand.getPriorityCardPower();

        int result;

        //первым делом сравниваем комбинации
        result = otherHand.getCombination().ordinal() - this.getCombination().ordinal();

        //если комбинации совпали, начинают играть роль значения силы карт - сначала в комбинации, затем вне комбинаций.
        if (result == 0) {
            result = checkCardsWithNoCombination(priorityCurrentHand, otherPriorityCurrentHand);
        }
        return result;
    }

    public int checkCardsWithNoCombination(int[] priorityCardPower, int[] otherPriorityCurrentHand) {
        for (int i = 0; i < priorityCardPower.length - 1; i++) {
            if (priorityCardPower[i] != otherPriorityCurrentHand[i]) {
                return otherPriorityCurrentHand[i] - priorityCardPower[i];
            }
        }
        return 0;
    }


}
