import java.util.Scanner;

public class Therapist {

    public static Scanner scanner = new Scanner(System.in);
    public static Utils utils = new Utils();

    public static String[] questions = utils.getQuestions();

    public static void main(String[] args) {

        displayGreeting();

        String userResponse = getInitialResponse();
        boolean isValid = utils.isInitialQuestionValid(userResponse);
        while (!isValid) {
            System.out.println("I am sorry, can you please answer with one of the options?");
            userResponse = getInitialResponse();
            isValid = utils.isInitialQuestionValid(userResponse);
        }
        userResponse = therapySession(userResponse);

        Boolean wantsToContinue = checkIfWantsToContinue(userResponse);

        while (wantsToContinue) {
            System.out.print("What else are you feeling today? (happy, sad, anxious, excited) ");
            userResponse = utils.normalizeResponse(scanner.nextLine());
            isValid = utils.isInitialQuestionValid(userResponse);

            while (!isValid) {
                System.out.println("I am sorry, can you please answer with one of the options?");
                System.out.print("What else are you feeling today? (happy, sad, anxious, excited) ");
                userResponse = utils.normalizeResponse(scanner.nextLine());
                isValid = utils.isInitialQuestionValid(userResponse);
            }

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
        String lastResponse = response;

        while (!response.contains("goodbye")) {

            String currentQuestion = questions[currentQuestionIndex];

            String formattedQuestion = (currentQuestionIndex == 3 || currentQuestionIndex == 5)
                    ? currentQuestion.replace("replace", mood)
                    : currentQuestion.replace("replace", response);

            System.out.print(formattedQuestion + " ");
            response = utils.normalizeResponse(scanner.nextLine());
            boolean responseIsValid = true;

            switch (currentQuestionIndex) {
                case 0:
                    responseIsValid = utils.isAnswer0Valid(response);
                    break;
                case 1:
                    responseIsValid = utils.isAnswer1Valid(response);
                    break;
                case 3:
                    responseIsValid = utils.isAnswer3Valid(response);
                    break;
                case 5:
                    responseIsValid = utils.isAnswer5Valid(response);
                    break;
            }

            if (!responseIsValid) {
                System.out.println("Please respond the question with one of the suggested responses");
                response = lastResponse;
                continue;
            } else {
                lastResponse = response;
            }

            if (utils.checkIfUserDontKnow(response)) {
                System.out.println("I'm sure you know, let's talk about it");
                response = mood;
                continue;
            }

            if (currentQuestionIndex == questions.length - 3 || currentQuestionIndex == questions.length - 1) {
                // the 2 questions where we need to know if the user wants to keep talking or
                // not. Can only accept a "yes" or a "no"

                while (!response.equals("yes") && !response.equals("no")) {
                    System.out.print(
                            "If you want to talk about something else, please reply with a \"yes\", otherwise please reply with a \"no\" ");
                    response = utils.normalizeResponse(scanner.nextLine());
                }
                break;
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
        }
        return true;
    }

}