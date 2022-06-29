# REST with Spring Boot

Developing your first Spring Boot REST API is fun.

### Survey Questionnaire Data

```
Question question1 = new Question("Question1",
        "Most Popular Cloud Platform Today", "AWS", Arrays.asList(
                "AWS", "Azure", "Google Cloud", "Oracle Cloud"));
Question question2 = new Question("Question2",
        "Fastest Growing Cloud Platform", "Google Cloud", Arrays.asList(
                "AWS", "Azure", "Google Cloud", "Oracle Cloud"));
Question question3 = new Question("Question3",
        "Most Popular DevOps Tool", "Kubernetes", Arrays.asList(
                "Kubernetes", "Docker", "Terraform", "Azure DevOps"));

List<Question> questions = new ArrayList<>(Arrays.asList(question1,
        question2, question3));

Survey survey = new Survey("Survey1", "My Favorite Survey",
        "Description of the Survey", questions);

surveys.add(survey);

```
