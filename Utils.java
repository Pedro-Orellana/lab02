/**
 * The Utils class provides a collection of utility methods for processing user
 * input during a conversation.
 * These methods include validating the user's responses to specific questions
 * and normalizing their input to improve accuracy.
 * The class also contains an array of pre-defined questions to ask the user
 * during the conversation.
 * 
 * @author Matt Magnaye and Pedro Orellana
 * @since 03/13/2023
 */

public class Utils {

    /**
     * An array of questions used to prompt the user during the conversation.
     */

    private String[] questions = {
            "What's making you feel replace today? (I don't know, personal issue, personal achievement, something else?)",
            "Tell me more about it, what is this \"replace\" you are talking about (problem at work, family problem, promotion at work, success in school)",
            "Can you tell me more about this replace?",
            "That's great! I am happy for you! and besides replace, how else is this making you feel? (joyful, hopeful for the future, grateful to life, motivated)",
            "It's really good to feel replace, keep doing what you are doing and you will continue to get success in your life! is there anything else you would like to talk about today? (yes, no)",
            "Oh no, I'm sorry to hear that, I know it must be a hard situation, but besides replace, how else are you feeling about this situation? (anxious, unmotivated, tired of this situation, burned out)",
            "I understand, feeling replace it's not a nice feeling, but know that life has ups and downs, and this will also pass, stay strong and rely on your family and friends! is there anything else you would like to talk about? (yes, no)"
    };

    /**
     * Gets the array of questions used to prompt the user.
     * 
     * @return the array of questions
     */

    public String[] getQuestions() {
        return questions;
    }

    /**
     * An array of initial answers used to validate the user's response to the first
     * question.
     */

    private String[] initialAnswers = {
            "happy",
            "sad",
            "anxious",
            "excited"
    };

    /**
     * Checks if the user's initial response is valid.
     * 
     * @param answer the user's response to the initial question
     * @return true if the response is valid, false otherwise
     */

    public boolean isInitialQuestionValid(String answer) {
        for (String validAnswer : initialAnswers) {
            if (answer.equalsIgnoreCase(validAnswer)) {
                return true;
            }
        }
        return false;
    }

    /**
     * An array of valid responses to the second question.
     */

    private String[] answers0 = {
            "I don't know",
            "I do not know",
            "dunno",
            "I dont know",
            "personal issue",
            "personal achievement",
            "something else"
    };

    /**
     * Checks if the user's response to the second question is valid.
     * 
     * @param answer the user's response to the second question
     * @return true if the response is valid, false otherwise
     */

    private boolean isAnswer0Valid(String answer) {
        for (String validAnswer : answers0) {
            if (answer.equalsIgnoreCase(validAnswer)) {
                return true;
            }
        }
        return false;
    }

    /**
     * An array of valid responses to the third question.
     */

    private String[] answers1 = {
            "problem at work",
            "family problem",
            "promotion at work",
            "success in school"
    };

    /**
     * Checks if the user's response to the third question is valid.
     * 
     * @param answer the user's response to the third question
     * @return true if the response is valid, false otherwise
     */

    private boolean isAnswer1Valid(String answer) {
        for (String validAnswer : answers1) {
            if (answer.equalsIgnoreCase(validAnswer)) {
                return true;
            }
        }
        return false;
    }

    /**
     * An array of valid responses to the fourth question.
     */

    private String[] answers3 = {
            "joyful",
            "hopeful for the future",
            "grateful to life",
            "motivated"
    };

    /**
     * Checks if the user's response to the fourth question is valid.
     * 
     * @param answer the user's response to the fourth question
     * @return true if the response is valid, false otherwise
     */

    private boolean isAnswer3Valid(String answer) {
        for (String validAnswer : answers3) {
            if (answer.equalsIgnoreCase(validAnswer)) {
                return true;
            }
        }
        return false;
    }

    /**
     * An array of valid responses to the fifth question.
     */

    private String[] answer5 = {
            "anxious",
            "unmotivated",
            "tired of this situation",
            "burned out"
    };

    /**
     * Checks if the user's response to the fifth question is valid.
     * 
     * @param answer the user's response to the fifth question
     * @return true if the response is valid, false otherwise
     */

    private boolean isAnswer5Valid(String answer) {
        for (String validAnswer : answer5) {
            if (answer.equalsIgnoreCase(validAnswer)) {
                return true;
            }
        }
        return false;
    }

    public boolean validateAnswer(String answer, int questionIndex) {

        switch (questionIndex) {
            case 0:
                return isAnswer0Valid(answer);
            case 1:
                return isAnswer1Valid(answer);
            case 3:
                return isAnswer3Valid(answer);
            case 5:
                return isAnswer5Valid(answer);
            default:
                return true;

        }
    }

    /**
     * Normalizes the user's response by converting it to lowercase and trimming
     * whitespace.
     * 
     * @param response the user's response to normalize
     * @return the normalized response
     */

    public String normalizeResponse(String response) {
        // for the purposes of making the program recognize different variations of the
        // same answer
        // I will make all the answers lowercase and trim any accidental white spaces
        String normalized = response.trim().toLowerCase();
        String firstInitial = normalized.substring(0, 1);
        if (firstInitial.equals("i")) {
            normalized = normalized.substring(0, 1).toUpperCase() + normalized.substring(1);
        }
        return normalized;

    }

    /**
     * Checks if the user's response indicates that they do not know the answer.
     * 
     * @param response the user's response to check
     * @return true if the response indicates that the user does not know the
     *         answer, false otherwise
     */

    public Boolean checkIfUserDontKnow(String response) {
        if (response.equals("I don't know") || response.equals("dunno")
                || response.equals("i don't know")
                || response.equals("I dont know") || response.equals("I do not know")) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the user wants to continue the therapy session or end it.
     *
     * @param lastResponse a String representing the user's last response to a
     *                     question
     * @return true if the user wants to continue, false otherwise
     */

    public boolean checkIfWantsToContinue(String lastResponse) {

        if (lastResponse.equals("no") || lastResponse.contains("goodbye")) {
            System.out.println(
                    "Dr. Starks: Okay I understand, this is the end of our session, please know that if I can be of any assistance, you can always reach out to me anytime!");
            return false;
        }
        return true;
    }

    /**
     * Gets the index of the follow-up question to ask based on the user's response
     * to the second question.
     * 
     * @param answer the user's response to the second question
     * @return the index of the follow-up question to ask
     */

    public int getQuestionsToSkip(String answer) {

        switch (answer) {
            case "problem at work":
            case "family problem":
                return 2;
            case "promotion at work":
            case "success in school":
                return 0;
            default:
                return 0;
        }
    }

}
