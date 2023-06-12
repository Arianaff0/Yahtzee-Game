public class Dice {
    private int value;
    public Dice() {
        value = -1;}
    public Dice(int value){
        this.value = value;}
    public void roll() {
        value = RandomNumber.getRandomNumber(1, 6);}

    public int getValue(){
        return value;
    }
}







