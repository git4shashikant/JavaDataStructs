package com.practice;

import java.util.Stack;

/*
* Determine whether a given string of parentheses (multiple types) is properly nested.
* for example [{()}]
* */
public class Brackets {

    public static void main(String[] args) {
        System.out.println("({}) : " + checkExpression("({})"));
        System.out.println("[({})] : " + checkExpression("[({})]"));
        System.out.println("({})] : " + checkExpression("({})]"));
        System.out.println("({[}]) : " + checkExpression("({[}])"));
        System.out.println("(()(())()) : " + checkExpression("(()(())())"));
    }

    static enum BracketTypes {
        SQUARE_BRACKET_LEFT('['),
        SQUARE_BRACKET_RIGHT(']'),
        CURLY_BRACKET_LEFT('{'),
        CURLY_BRACKET_RIGHT('}'),
        ROUND_BRACKET_LEFT('('),
        ROUND_BRACKET_RIGHT(')');

        private char bracket;

        BracketTypes(char bracket) {
            this.bracket = bracket;
        }

        public char getBracket() {
            return bracket;
        }

        public static BracketTypes getBracketType(char bracket) {
            BracketTypes bracketTypeFound = null;
            for (BracketTypes bracketType : BracketTypes.values()) {
                if (bracketType.getBracket() == bracket) {
                    bracketTypeFound = bracketType;
                    break;
                }
            }

            return bracketTypeFound;
        }
    }

    public static boolean checkExpression(String s) {

        if (s.length()%2 != 0)
            return false;

        boolean isNested = true;
        char[] brackets = s.toCharArray();

        int index = 0;
        Stack stack = new Stack();
        while(index < brackets.length) {
            char bracket = brackets[index];
            switch (BracketTypes.getBracketType(bracket)) {
                case CURLY_BRACKET_LEFT:
                case ROUND_BRACKET_LEFT:
                case SQUARE_BRACKET_LEFT:
                    stack.add(brackets[index]);
                    break;
                case CURLY_BRACKET_RIGHT:
                    if (BracketTypes.getBracketType((char) stack.pop()) != BracketTypes.CURLY_BRACKET_LEFT) {
                        isNested = false;
                    }

                    break;
                case ROUND_BRACKET_RIGHT:
                    if (BracketTypes.getBracketType((char) stack.pop()) != BracketTypes.ROUND_BRACKET_LEFT) {
                        isNested = false;
                    }

                    break;
                case SQUARE_BRACKET_RIGHT:
                    if (BracketTypes.getBracketType((char) stack.pop()) != BracketTypes.SQUARE_BRACKET_LEFT) {
                        isNested = false;
                    }

                    break;
                default:
                    isNested = false;
            }

            if (!isNested)
                break;

            index++;
        }

        return isNested;
    }
}
