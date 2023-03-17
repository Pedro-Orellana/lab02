public class Utils {

    private String[] questions = {
            "What's making you feel replace today? (you don't know, personal issue, personal achievement, something else?)",
            "Tell me more about it, what is this \"replace\" you are talking about (problem at work, family problem, a promotion at work, success in school)",
            "Can you tell me more about this replace?",
            "That's great! I am happy for you! is there anything else you want to talk about? (yes, no)",
            "Oh no, I'm sorry to hear that, but things will eventually get better again!, is there anything else you want to talk about? (yes, no)"
    };

    private String[] validResponses = {
        "happy",
        "sad",
        "anxious",
        "excited",
        "no",
        "yes",
        "goodbye",
        "you don't know",
        "personal issue",
        "personal achievement",
        "something else",
        "problem at work",
        "family problem",
        "promotion at work",
        "success in school",
        "you don't know",
        "personal issue",
        "personal achievement",
        "something else"
};


    public String[] getQuestions() {
        return questions;
    }

    public String normalizeResponse(String response) {
        //for the purposes of making the program recognize different variations of the same answer
        //I will make all the answers lowercase and trim any accidental white spaces
        String normalized = response.trim().toLowerCase();
        return normalized;
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

    public boolean isValidResponse(String response) {
        response = normalizeResponse(response);
        for (String validResponse : validResponses) {
            if (response.equals(validResponse)) {
                return true;
            }
        }
        return false;
    }

    public boolean isValidResponseForSecondQuestion(String response) {
        response = normalizeResponse(response);
        String[] validResponsesForSecondQuestion = {
                "you don't know",
                "personal issue",
                "personal achievement",
                "something else"
        };
        for (String validResponse : validResponsesForSecondQuestion) {
            if (response.equals(validResponse)) {
                return true;
            }
        }
        return false;
    }

}
