import java.util.Scanner;

public class Therapist {

    public static Scanner scanner = new Scanner(System.in);
    public static Utils utils = new Utils();

    public static String[] questions = utils.getQuestions();

    public static void main(String[] args) {

        displayGreeting();

        String userResponse = getInitialResponse();
        userResponse = therapySession(userResponse);

        Boolean wantsToContinue = checkIfWantsToContinue(userResponse);

        while (wantsToContinue) {
            System.out.print("What else are you feeling today? (happy, sad, anxious, excited) ");
            userResponse = utils.normalizeResponse(scanner.nextLine());
            userResponse = therapySession(userResponse);
            wantsToContinue = checkIfWantsToContinue(userResponse);
        }

    }

    public static void displayGreeting() {
        System.out.println("Hello! This is Dr. Starks and we are going to start our session today.");
    }

    public static String getInitialResponse() {
        System.out.print("How are you feeling today? (happy, sad, anxious, excited) ");
        String response = scanner.nextLine();
        return utils.normalizeResponse(response);
    }

    public static String therapySession(String response) {

        int currentQuestionIndex = 0;
        int questionsToSkip = 0;
        String mood = response;

        while (!response.equals("no") || !response.contains("goodbye")) {

            String currentQuestion = questions[currentQuestionIndex].replace("replace", response);
            System.out.print(currentQuestion + " ");
            response = utils.normalizeResponse(scanner.nextLine());
            if (utils.checkIfUserDontKnow(response)) {
                System.out.println("I'm sure you know, let's talk about it");
                response = mood;
                continue;
            }
            if (currentQuestionIndex == questions.length - 2 || currentQuestionIndex == questions.length - 1) {
                // the last two questions, can only get a "yes" or a "no" for answer
                if (!response.equals("yes") && !response.equals("no")) {
                    System.out.println(
                            "If you want to talk about something else, please reply with a \"yes\", otherwise please reply with a \"no\"");
                    continue;
                } else {
                    break;
                }
            }
            if (currentQuestionIndex == 1) {
                // know if the follow up question is happy or sad
                questionsToSkip = utils.getCorrectFollowUpQuestion(response);
            }

            if (currentQuestionIndex == 2) {
                // skip the happy follow up question if necessary
                currentQuestionIndex += questionsToSkip;
            }
            currentQuestionIndex++;

            if (currentQuestionIndex >= questions.length) {
                break;
            }
        }

        return response;

    }

    public static Boolean checkIfWantsToContinue(String lastResponse) {
        if (lastResponse.equals("no") || lastResponse.equals("goodbye")) {
            System.out.println("Okay then, this is the end of our session, have a great day!");
            return false;
        } else if (lastResponse.equals("yes")) {
            return true;
        } else {
            System.out.println("I am sorry, did you want to continue with your session? (yes, no)");
            lastResponse = utils.normalizeResponse(scanner.nextLine());
            checkIfWantsToContinue(lastResponse);
            return true;
        }
    }

}
