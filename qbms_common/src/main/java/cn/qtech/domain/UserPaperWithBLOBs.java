package cn.qtech.domain;

public class UserPaperWithBLOBs extends UserPaper {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_paper.content
     *
     * @mbggenerated Tue Apr 25 13:52:14 CST 2017
     */
    private String content;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_paper.answer
     *
     * @mbggenerated Tue Apr 25 13:52:14 CST 2017
     */
    private String answer;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_paper.content
     *
     * @return the value of user_paper.content
     *
     * @mbggenerated Tue Apr 25 13:52:14 CST 2017
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_paper.content
     *
     * @param content the value for user_paper.content
     *
     * @mbggenerated Tue Apr 25 13:52:14 CST 2017
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_paper.answer
     *
     * @return the value of user_paper.answer
     *
     * @mbggenerated Tue Apr 25 13:52:14 CST 2017
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_paper.answer
     *
     * @param answer the value for user_paper.answer
     *
     * @mbggenerated Tue Apr 25 13:52:14 CST 2017
     */
    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }
}