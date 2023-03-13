import java.util.Scanner;

public class Therapist {

    public static Scanner scanner = new Scanner(System.in);

    public static String[] questions = {
            "What's making you feel replace today? (you don't know, personal issue, personal achievement, something else?)",
            "Tell me more about it, what is this \"replace\" you are talking about (problem at work, family problem, a promotion at work, succeeding in school)",
            "That's great! I am happy for you! is there anything else you want to talk about? (yes, no)"
    };

    public static String[] answers1 = {
        "I don't know",
        "personal issue",
        "personal achievement"
    };

    public static String[] answers2 = {
        "problem at work"
    };

    public static void main(String[] args) {

        String response = displayGreeting();
        Boolean wantsToContinue = therapySession(response);

        while (wantsToContinue) {
            System.out.print("What else are you feeling today? (happy, sad, anxious, excited) ");
            response = scanner.nextLine();
            wantsToContinue = therapySession(response);
        }

    }

    public static String normalizeResponse(String response) {

        // TODO: finish this method
        return response;
    }

    public static String displayGreeting() {
        System.out.println("Hello! This is Dr. Starks and we are going to start our session today.");
        System.out.print("How are you feeling today? (happy, sad, anxious, excited) ");
        String response = scanner.nextLine();
        return normalizeResponse(response);
    }

    public static Boolean therapySession(String response) {

        int i = 0;
        while (!response.equals("no")) {
            String currentQuestion = questions[i].replace("replace", response);
            System.out.print(currentQuestion + " ");
            response = scanner.nextLine();
            i++;
            if (i == questions.length) {
                break;
            }
        }
        // TODO: check if the user really wants to continue or if they input an invalid
        //TODO: This is another change that I just made
        // option

        if (response.equals("no") || response.equals("goodbye")) {
            System.out.println("Okay then, this is the end of our session, have a great day!");
            return false;
        }

        return true;

    }

}