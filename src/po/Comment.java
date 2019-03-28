package po;

public class Comment
{
    private String commentId;
    private String userId;
    private String pId;
    private String content;
    private Character score;//只能是1 2 3 4 5

    public String getCommentId()
    {
        return commentId;
    }

    public void setCommentId(String commentId)
    {
        this.commentId = commentId;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getpId()
    {
        return pId;
    }

    public void setpId(String pId)
    {
        this.pId = pId;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public Character getScore()
    {
        return score;
    }

    public void setScore(Character score)
    {
        this.score = score;
    }

    public Comment(String commentId, String userId, String pId, String content, Character score)
    {
        this.commentId = commentId;
        this.userId = userId;
        this.pId = pId;
        this.content = content;
        this.score = score;
    }

    public Comment(String userId, String pId, String content, Character score)
    {
        this.userId = userId;
        this.pId = pId;
        this.content = content;
        this.score = score;
    }

    public Comment()
    {
    }

    public Comment(String commentId)
    {
        this.commentId = commentId;
    }

    @Override
    public String toString()
    {
        return commentId+" "+userId+" "+pId;
    }
}
