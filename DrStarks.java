import java.util.Scanner;

/**
 * The Therapist class (Dr. Starks) simulates a conversation with a user to
 * provide therapy.
 * Dr. Starks gets his name from "The Analyst", a novel by John Katzenbach.
 * The class prompts the user for their emotional state and responds with a
 * series of follow-up questions based on the user's responses.
 * The class uses an instance of the Utils class to validate user input and
 * normalize responses.
 * 
 * @author Matt Magnaye and Pedro Orellana
 * @since 03/13/2023
 */

public class DrStarks {

    /**
     * Scanner object for reading user input
     */

    public static Scanner scanner = new Scanner(System.in);

    /**
     * An instance of the Utils class for validating user input and normalizing
     * responses
     */

    public static Utils utils = new Utils();

    /**
     * An array of pre-defined questions to ask the user during the conversation
     */

    public static String[] questions = utils.getQuestions();

    /**
     * The main method for the Therapist program. Displays a greeting message,
     * prompts the user for their emotional state, and begins the therapy session.
     *
     * @param args the command-line arguments for the program (not used)
     */

    public static void main(String[] args) {

        displayGreeting();

        String userResponse = getInitialResponse();
        boolean isValid = utils.isInitialQuestionValid(userResponse);

        while (!isValid) {
            System.out.println("Dr. Starks: I am sorry, can you please answer with one of the options?");
            userResponse = getInitialResponse();
            isValid = utils.isInitialQuestionValid(userResponse);
        }
        userResponse = therapySession(userResponse);

        Boolean wantsToContinue = utils.checkIfWantsToContinue(userResponse);

        while (wantsToContinue) {
            System.out.println("Dr. Starks: What else are you feeling today? (happy, sad, anxious, excited) ");
            System.out.print("you: ");
            userResponse = utils.normalizeResponse(scanner.nextLine());
            isValid = utils.isInitialQuestionValid(userResponse);

            while (!isValid) {
                System.out.println("Dr. Starks: I am sorry, can you please answer with one of the options?");
                System.out.print("Dr. Starks: What else are you feeling today? (happy, sad, anxious, excited) ");
                userResponse = utils.normalizeResponse(scanner.nextLine());
                isValid = utils.isInitialQuestionValid(userResponse);
            }

            userResponse = therapySession(userResponse);
            wantsToContinue = utils.checkIfWantsToContinue(userResponse);
        }

    }

    /**
     * Displays a greeting message to the user.
     */

    public static void displayGreeting() {
        System.out.println();
        System.out.println("Dr. Starks:  Greetings, my name is Dr. Frederick Starks, it is nice to meet you!");
        System.out.printf("%82s", "Thank you for joining me here today, let's begin our virtual session\n");
        System.out.println();
    }

    /**
     * Prompts the user for their emotional state and normalizes their response.
     *
     * @return a normalized String representing the user's emotional state
     */

    public static String getInitialResponse() {
        System.out.println();
        System.out.println("Dr. Starks:  How are you feeling today? (happy, sad, anxious, excited) ");
        System.out.print("you: ");
        String response = scanner.nextLine();
        return utils.normalizeResponse(response);
    }

    /**
     * Handles the main logic for the therapy session. Asks the user a series of
     * follow-up questions based on their response to the initial question.
     *
     * @param response a String representing the user's response to the initial
     *                 question
     * @return a String representing the user's final response to the session (e.g.,
     *         "goodbye")
     */

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

            System.out.println("Dr. Starks: " + formattedQuestion + " ");
            System.out.print("you: ");
            response = utils.normalizeResponse(scanner.nextLine());

            boolean responseIsValid = utils.validateAnswer(response, currentQuestionIndex);

            if (!responseIsValid) {
                if (response.contains("goodbye")) {
                    break;
                }
                System.out.println();
                System.out.println("Dr.Starks: Please respond the question with one of the suggested responses");
                response = lastResponse;
                continue;
            } else {
                lastResponse = response;
            }

            if (utils.checkIfUserDontKnow(response)) {
                System.out.println();
                System.out.println("Dr. Starks: I'm sure you know, let's talk about it");
                response = mood;
                continue;
            }

            if (currentQuestionIndex == questions.length - 3 || currentQuestionIndex == questions.length - 1) {
                // the 2 questions where we need to know if the user wants to keep talking or
                // not. Can only accept a "yes" or a "no"

                while (!response.equals("yes") && !response.equals("no")) {
                    System.out.print(
                            "Dr. Starks: If you want to talk about something else, please reply with a \"yes\", otherwise please reply with a \"no\" ");
                    response = utils.normalizeResponse(scanner.nextLine());
                }
                break;
            }

            if (currentQuestionIndex == 1) {
                // know if the follow up question is happy or sad
                questionsToSkip = utils.getQuestionsToSkip(response);
            }

            if (currentQuestionIndex == 2) {
                // skip the happy follow up question if necessary
                currentQuestionIndex += questionsToSkip;
            }

            currentQuestionIndex++;

        }

        return response;

    }

}
