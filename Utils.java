public class Utils {

    private String[] questions = {
            "What's making you feel replace today? (you don't know, personal issue, personal achievement, something else?)",
            "Tell me more about it, what is this \"replace\" you are talking about (problem at work, family problem, a promotion at work, success in school)",
            "Can you tell me more about this replace?",
            "That's great! I am happy for you! is there anything else you want to talk about? (yes, no)",
            "Oh no, I'm sorry to hear that, but things will eventually get better again!, is there anything else you want to talk about?"
    };

    public String[] getQuestions() {
        return questions;
    }

    private String[] answers1 = {
            "i don't know",
            "i do not know",
            "dunno",
            "personal issue",
            "personal achievement",
            "something else"
    };

    private String[] answers2 = {
            "problem at work",
            "family problem",
            "promotion at work",
            "success in school"
    };

    

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

}
