package leitor;

import java.time.LocalDate;
import java.util.Scanner;

public class Leitor {
    public boolean isNumeric(String s) {  
        return s != null && s.matches("[0-9]+");  
    }  

    public boolean isDate(String s) {
        return s != null && s.matches("^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$");
    }

    public int optionReader(Scanner reader, String textInput, int startValue, int endValue) {
        while (true) {
            System.out.print(textInput);

            String input = reader.nextLine();

            if (isNumeric(input)) {
                int number = Integer.parseInt(input);
                if (number >= startValue && number <= endValue) {
                    return number;
                } else {
                    System.out.println("O número deve ser igual ou estar entre " + startValue + " e " + endValue + "!");
                }
            } else {
                System.out.println("Digite apenas números inteiros de " + startValue + " até " + endValue + "!");
            }
        }
    }

    public LocalDate dateReader(Scanner reader, String textInput) {
        while (true) {
            System.out.print(textInput);

            String input = reader.nextLine();

            if (isDate(input)) {
                String[] inputSplit = input.split("/");

                return LocalDate.of(Integer.parseInt(inputSplit[2]), Integer.parseInt(inputSplit[1]), Integer.parseInt(inputSplit[0]));
            } else {
                System.out.println("Formato de data incorreto!");
            }
        }
    }

    public String regexValidatorReader(Scanner reader, String textInput, String regex) {
        while (true) {
            System.out.print(textInput);

            String input = reader.nextLine();

            if (input != null && input.matches(regex)) {
                return input;
            } else {
                System.out.println("O que foi digitado está fora do formato correto!");
            }
        }
    }

    public String stringReader(Scanner reader, String textInput) {
        while (true) {
            System.out.print(textInput);

            String input = reader.nextLine();

            if (input.length() > 0) {
                return input;
            } else {
                System.out.println("O texto digitado deve conter pelo menos um caractere!");
            }
        }
    }

    public boolean stringBoolReader(Scanner reader, String textInput) {
        while (true) {
            System.out.print(textInput);

            String input = reader.nextLine();

            if (input.equalsIgnoreCase("sim")) {
                return true;
            } else if (input.equalsIgnoreCase("não")) {
                return false;
            } else {
                System.out.println("Digite apenas 'sim' ou 'não'!");
            }
        }
    }
}
