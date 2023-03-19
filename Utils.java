public class Utils {

    private String[] questions = {
            "What's making you feel replace today? (you don't know, personal issue, personal achievement, something else?)",
            "Tell me more about it, what is this \"replace\" you are talking about (problem at work, family problem, a promotion at work, success in school)",
            "Can you tell me more about this replace?",
            "That's great! I am happy for you! is there anything else you want to talk about? (yes, no)",
            "Oh no, I'm sorry to hear that, but things will eventually get better again!, is there anything else you want to talk about? (yes, no)"
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
        // TODO: loop through the initialAnswers array, and if answer is not in the
        // array, return false, otherwise return true
        return true;
    }

    private String[] answers1 = {
            "I don't know",
            "I do not know",
            "dunno",
            "personal issue",
            "personal achievement",
            "something else"
    };

    public Boolean isAnswer1Valid(String answer) {
        // TODO: loop through the answers1 array, and if the answer is none of the
        // answers in the array, return false, otherwise return true
        return true;
    }

    private String[] answers2 = {
            "problem at work",
            "family problem",
            "promotion at work",
            "success in school"
    };

    public Boolean isAnswer2Valid(String answer) {
        // same as previous method but for anwers2 array
        return true;
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
                return 1;
            case "promotion at work":
            case "success in school":
                return 0;
            default:
                return 0;
        }
    }

}
