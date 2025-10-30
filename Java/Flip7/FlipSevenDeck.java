public class FlipSevenDeck {
   private Integer[] deck;
   public FlipSevenDeck() {
       this.deck = new Integer[79];
       int count = 0;
       this.deck[count++] = Integer.valueOf(0); // One zero card
       for (int i = 0; i < 13; i++) {
        for (int j = 0; j < i; j++) {
            this.deck[count++] = Integer.valueOf(i);
        }
       }
   } 

    public Integer[] getDeck() {
         return this.deck;
    }
}
