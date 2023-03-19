public class Utils {

    private String[] questions = {
            "What's making you feel replace today? (you don't know, personal issue, personal achievement, something else?)",
            "Tell me more about it, what is this \"replace\" you are talking about (problem at work, family problem, promotion at work, success in school)",
            "Can you tell me more about this replace?",
            "That's great! I am happy for you! and besides replace, how else is this making you feel? (joyful, hopeful for the future, grateful to life, motivated)",
            "It's really good to feel replace, keep doing what you are doing and you will continue to get success in your life! is there anything else you would like to talk about today? (yes, no)",
            "Oh no, I'm sorry to hear that, I know it must be a hard situation, but besides replace, how else are you feeling about this situation? (anxious, unmotivated, tired of this situation, burned out)",
            "I understand, feeling replace it's not a nice feeling, but know that life has ups and downs, and this will also pass, stay strong and rely on your family and friends! is there anything else you would like to talk about? (yes, no)"
    };

    public String[] getQuestions() {
        return questions;
    }

    private String[] initialAnswers = {
            "happy",
            "sad",
            "anxious",
            "excited"
    };

    public Boolean isInitialQuestionValid(String answer) {
        for (String validAnswer : initialAnswers) {
            if (answer.equalsIgnoreCase(validAnswer)) {
                return true;
            }
        }
        return false;
    }
    

    private String[] answers0 = {
            "I don't know",
            "I do not know",
            "dunno",
            "personal issue",
            "personal achievement",
            "something else"
    };

    public boolean isAnswer0Valid(String answer) {
        for (String validAnswer : answers0) {
            if (answer.equalsIgnoreCase(validAnswer)) {
                return true;
            }
        }
        return false;
    }
    

    private String[] answers1 = {
            "problem at work",
            "family problem",
            "promotion at work",
            "success in school"
    };

    public boolean isAnswer1Valid(String answer) {
        for (String validAnswer : answers1) {
            if (answer.equalsIgnoreCase(validAnswer)) {
                return true;
            }
        }
        return false;
    }
    

    private String[] answers3 = {
            "joyful",
            "hopeful for the future",
            "grateful to life",
            "motivated"
    };

    public boolean isAnswer3Valid(String answer) {
        for (String validAnswer : answers3) {
            if (answer.equalsIgnoreCase(validAnswer)) {
                return true;
            }
        }
        return false;
    }
    

    private String[] answer5 = {
            "anxious",
            "unmotivated",
            "tired of this situation",
            "burned out"
    };

    public boolean isAnswer5Valid(String answer) {
        for (String validAnswer : answer5) {
            if (answer.equalsIgnoreCase(validAnswer)) {
                return true;
            }
        }
        return false;
    }
    

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

    public Boolean checkIfUserDontKnow(String response) {
        if (response.equals("I don't know") || response.equals("dunno")
                || response.equals("i don't know") || response.equals("i dont know")
                || response.equals("I dont know")) {
            return true;
        }
        return false;
    }

    public int getCorrectFollowUpQuestion(String answer) {

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
