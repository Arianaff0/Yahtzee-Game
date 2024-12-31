import java.util.Arrays;

/**
 * Developed by: Ariana F
 * Date: Jun 12, 2023
 * Yahtzee game with Dice and Yahtzee class, user rolls 5 dice and there are
 * 13 scoring options.
 */
public class Yahtzee {
    private Dice[] dice;


    public Yahtzee() {
        dice = new Dice[5];
        /**
         * initializes the 'dice' array w/ 5 elements
         * and assigns random values to each element by creating a new 'Dice'
         * object
         */
        for (int i = 0; i < dice.length; i++) {
            Dice DiceFace = new Dice();
            /** 'DiceFace is a variable of type Dice,
            ^this line creates a new instance variable of the 'Dice' class and assigns
            to the 'DiceFace' variable*/
            DiceFace.roll();
            dice[i] = DiceFace;
            /**storing the rolled dice values in 'dice' array*/
        }
    }
    /** rolling 5 dice, not referencing to num of sides in a dice*/
    public Yahtzee(Dice[] diceArray) {
        dice = new Dice[] {diceArray[0], diceArray[1], diceArray[2],
                diceArray[3], diceArray[4]};
    }

    /**
     * Formats the Dice class into a readable string
     */
    public String toString() {
        return "Dice: {" + dice[0].getValue() + ", " + dice[1].getValue() + ", " + dice[2].getValue() + ", " + dice[3].getValue() + ", " + dice[4].getValue() + "}";
    }
    public int[] returnDice() {
        return new int[] {dice[0].getValue(), dice[1].getValue(), dice[2].getValue(), dice[3].getValue(), dice[4].getValue()};
    }

    /**counter, count how many of that num is rolled
     * //to make the int array-->0,..5 so 6 possible faces
     */
    public int[] getValueCount() {
        int[] emptyIntArray = returnDice();
        int one = 0;
        int two = 0;
        int three = 0;
        int four = 0;
        int five = 0;
        int six = 0;

        for (int i = 0; i < emptyIntArray.length; i++) {
            if (emptyIntArray[i] == 1) {
                one++;
            } else if (emptyIntArray[i] == 2) {
                two++;
            } else if (emptyIntArray[i] == 3) {
                three++;
            } else if (emptyIntArray[i] == 4) {
                four++;
            } else if (emptyIntArray[i] == 5) {
                five++;
            } else if (emptyIntArray[i] == 6) {
                six++;
            }

        }
        return new int[] {one, two, three, four, five, six};


    }

    public int[] getScoreOptions() {
        int[] rollsValues = getValueCount();
        int[] myIntArray = returnDice();
        int aces = sumOfDiceShowing(1, rollsValues[0]);
        int twos = sumOfDiceShowing(2, rollsValues[1]);
        int threes = sumOfDiceShowing(3, rollsValues[2]);
        int fours = sumOfDiceShowing(4, rollsValues[3]);
        int fives = sumOfDiceShowing(5, rollsValues[4]);
        int sixes = sumOfDiceShowing(6, rollsValues[5]);
        int threeOfaKind = 0;
        if (sumOfAllFive(rollsValues, 3)== true) {
            for (int i=0; i<myIntArray.length; i++) {
                threeOfaKind += myIntArray[i];
            }
        }
        int fourOfaKind = 0;
        if (sumOfAllFive(rollsValues, 4)== true) {
            for (int i=0; i<myIntArray.length; i++) {
                fourOfaKind += myIntArray[i];
            }
        }
        int fullHouse = 0;
        if ((FullHouse(rollsValues, 3)== true) && (FullHouse(rollsValues, 2)==true)) {
            fullHouse += 25;
        }

        int SmallStraight = 0;
        Arrays.sort(myIntArray);
        boolean hasConsecutiveFour = false;
        for (int i = 0; i < myIntArray.length - 3; i++) {
            if ((myIntArray[i] == myIntArray[i+1] - 1 || myIntArray[i] == myIntArray[i+1]) &&
                    (myIntArray[i+1] == myIntArray[i+2] - 1 || myIntArray[i+1] == myIntArray[i+2]) &&
                    (myIntArray[i+2] == myIntArray[i+3] - 1 || myIntArray[i+2] == myIntArray[i+3]) &&
                    (myIntArray[0] == myIntArray[4] - 3)) {
                hasConsecutiveFour = true;
                break;
            }
        }

        if (hasConsecutiveFour) {
            SmallStraight += 30;
        } else {
            SmallStraight = 0;
        }


        int LargeStraight = 0;
        boolean hasConsecutiveFive = false;
        for (int i = 0; i < myIntArray.length-4; i++) {
            if (myIntArray[i] == myIntArray[i + 1] - 1 &&
                    myIntArray[i + 1] == myIntArray[i + 2] - 1 &&
                    myIntArray[i + 2] == myIntArray[i + 3] - 1 &&
                    myIntArray[i + 3] == myIntArray[i + 4] - 1) {
                hasConsecutiveFive = true;
                break;
            }
        }

        if (hasConsecutiveFive== true) {
            LargeStraight +=40;
        } else {
            LargeStraight = 0;
        }


        int yahtzee = 0;
        if (sumOfAllFive(rollsValues, 5)== true) {
            yahtzee +=50;
        }

        int Chance = 0;
        for (int i = 0; i < myIntArray.length; i++) {
            Chance += myIntArray[i];
        }
        int[] possibleScores = {aces, twos, threes, fours, fives, sixes, threeOfaKind, fourOfaKind, fullHouse, SmallStraight, LargeStraight, yahtzee, Chance};
        return possibleScores;
    }


    public int sumOfDiceShowing (int num, int occurences) {
        return num * occurences;
    }


    public boolean sumOfAllFive(int[] rollsValues, int occurences) {
        int sum = 0;
        for (int i = 0; i < rollsValues.length; i++) {
            if (occurences<= rollsValues[i]) {
                return true;
                }
            }
        return false;
        }

    public boolean FullHouse(int[] rollsValues, int occurences) {
        int sum = 0;
        for (int i = 0; i < rollsValues.length; i++) {
            if (occurences== rollsValues[i]) {
                return true;
            }
        }
        return false;
    }
    public int[] score() {
        int[] possibleScores2= this.getScoreOptions();  // getScoreOptions() returns an array called possibleScores,
        int max = possibleScores2[0];
        int maxIndex = 0;
        for (int i = 0; i < possibleScores2.length; i++) {
            if (possibleScores2[i] > max) {
                max = possibleScores2[i];
                maxIndex = i;
            }
        }
        int[] maxAndIndex = {max, maxIndex};
        return maxAndIndex;
    }

    public boolean equals(Yahtzee game) {
        int[] game1 = this.returnDice();
        int[] game2 = game.returnDice();

        Arrays.sort(game1);
        Arrays.sort(game2);
        return Arrays.equals(game1, game2);
    }

}


