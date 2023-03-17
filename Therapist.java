import java.util.Scanner;

public class Therapist {

    public static Scanner scanner = new Scanner(System.in);
    public static Utils utils = new Utils();

    public static String[] questions = utils.getQuestions();

    public static void main(String[] args) {

        displayGreeting();

        String response = getInitialResponse();
        Boolean wantsToContinue = therapySession(response);

        while (wantsToContinue) {
            System.out.print("What else are you feeling today? (happy, sad, anxious, excited) ");
            response = utils.normalizeResponse(scanner.nextLine());
            wantsToContinue = therapySession(response);
        }

    }

    public static void displayGreeting() {
        System.out.println("Hello! This is Dr. Starks and we are going to start our session today.");
    }

    public static String getInitialResponse() {
        System.out.print("How are you feeling today? (happy, sad, anxious, excited) ");
        String response = getUserResponse(false);
        return utils.normalizeResponse(response);
    }

    public static Boolean therapySession(String response) {

        int i = 0;
        int x = 0;
        while (!response.equals("no") && !response.contains("goodbye")) {


            String currentQuestion = questions[i].replace("replace", response);
            System.out.print(currentQuestion + " ");
            response = getUserResponse(i == 1);
            if(i == 3 || i == 4) {
                //the last two questions, can only get a "yes" or a "no" for answer
                if(!response.equals("yes") && !response.equals("no")) {
                    System.out.println("Invalid response. Please enter either 'yes' or 'no'.");
                    continue;
                } else {
                    break;
                }        
            }
            if(i == 1) {
                //know if the follow up question is happy or sad
                x = utils.getCorrectFollowUpQuestion(response);
            }

            if (i == 2) {
                //skip the happy follow up question if necessary
                i = i + x;
            }
            i++;

            if (i >= questions.length) {
                break;
            }
        }
        
        return checkIfWantsToContinue(response);

    }

    public static Boolean checkIfWantsToContinue(String response) {
        while (!response.equals("no") && !response.equals("yes") && !response.equals("goodbye")) {
            System.out.println("I am sorry, did you want to continue with your session? (yes, no)");
            response = getUserResponse();
        }
    
        if (response.equals("no") || response.equals("goodbye")) {
            System.out.println("Okay then, this is the end of our session, have a great day!");
            return false;
        } else {
            return true;
        }
    }
    

    public static String getUserResponse(boolean forSecondQuestion) {
        String response = scanner.nextLine();
        while (!(forSecondQuestion ? utils.isValidResponseForSecondQuestion(response) : isValidResponse(response))) {
            System.out.println("Invalid response. Please try again.");
            response = scanner.nextLine();
        }
        return response;
    }
    

    public static boolean isValidResponse(String response) {
        response = utils.normalizeResponse(response);
        return response.equals("happy") || response.equals("sad") || response.equals("anxious") || response.equals("excited") || response.equals("no") || response.equals("yes") || response.equals("goodbye");
    }

}
