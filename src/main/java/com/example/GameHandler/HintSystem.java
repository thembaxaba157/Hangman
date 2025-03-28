package com.example.GameHandler;

public class HintSystem {
    
    private static int REAVEAL_RANDOM_WORD = 5;
    private static int REVEAL_FACT_ABOUT_WORD = 3;
    private static int REMOVE_WRONG_LETTER_ATTEMPT = 4;


    public static boolean canRevealLetter(int availablePoints){
        return availablePoints >= REAVEAL_RANDOM_WORD;
    }

    public static boolean canRevealWordFact(int availablePoints){
        return availablePoints >= REVEAL_FACT_ABOUT_WORD;

    }
    public static boolean canRemoveWrongLetter(int availablePoints){
        return availablePoints >= REMOVE_WRONG_LETTER_ATTEMPT;
    }

    public static int getRevealRandomWordValue(){
        return REAVEAL_RANDOM_WORD;
    }
    public static int getRevealWordFactValue(){
        return REVEAL_FACT_ABOUT_WORD;
        }
        public static int getRemoveWrongLetterValue(){
            return REMOVE_WRONG_LETTER_ATTEMPT;
            }
            }



