package proj_11.bigdata.Messages.Requests;

public class UploadRequest {

    public String fileName;
    public byte[] content;
    public String keyWord;

    public UploadRequest(){
    }

    public UploadRequest(String fileName, byte[] content, String keyWord){
        this.fileName = fileName;
        this.content = content;
        this.keyWord = keyWord;
    }
}
