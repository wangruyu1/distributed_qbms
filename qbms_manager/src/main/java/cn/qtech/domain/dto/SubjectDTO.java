package cn.qtech.domain.dto;

/**
 * @author wangruyu
 * @since 2017/4/1-16:35
 */
public class SubjectDTO {
    private String subjectId;
    private String paperId;
    private String name;
    private String userId;
    private String categoryId;
    private String subjectCategoryId;
    private String categoryName;
    private String difficultyId;
    private String subjectDifficultyId;
    private String difficultyName;
    private int score;
    private String content;
    private String answer;
    private boolean firstSubject;
    private int littleSubjectNum;

    public String getSubjectCategoryId() {
        return subjectCategoryId;
    }

    public void setSubjectCategoryId(String subjectCategoryId) {
        this.subjectCategoryId = subjectCategoryId;
    }

    public String getSubjectDifficultyId() {
        return subjectDifficultyId;
    }

    public void setSubjectDifficultyId(String subjectDifficultyId) {
        this.subjectDifficultyId = subjectDifficultyId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDifficultyName() {
        return difficultyName;
    }

    public void setDifficultyName(String difficultyName) {
        this.difficultyName = difficultyName;
    }

    public int getLittleSubjectNum() {
        return littleSubjectNum;
    }

    public void setLittleSubjectNum(int littleSubjectNum) {
        this.littleSubjectNum = littleSubjectNum;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public boolean isFirstSubject() {
        return firstSubject;
    }

    public void setFirstSubject(boolean firstSubject) {
        this.firstSubject = firstSubject;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getDifficultyId() {
        return difficultyId;
    }

    public void setDifficultyId(String difficultyId) {
        this.difficultyId = difficultyId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
