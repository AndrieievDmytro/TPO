package proj_11;

import org.junit.Assert;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import proj_11.bigdata.Interfaces.IBigDataMTOM;
import proj_11.bigdata.Interfaces.IService;
import proj_11.bigdata.Messages.Requests.DownloadRequest;
import proj_11.bigdata.Messages.Requests.QueryRequest;
import proj_11.bigdata.Messages.Requests.UploadRequest;
import proj_11.bigdata.Messages.Responses.DownloadResponse;
import proj_11.bigdata.Messages.Responses.QueryResponse;
import proj_11.bigdata.Messages.Responses.UploadResponse;
import proj_11.bigdata.Models.BigData;
import org.junit.Before;
import org.junit.Test;


public class AppTest 
{
    private static final Logger logger = Logger.getAnonymousLogger();
    private IBigDataMTOM proxy;

    @Before
    public void before() throws MalformedURLException{
        URL wsdl = new URL(proj_11.bigdata.Interfaces.IBigDataMTOM.URI+ IService.WSDL_SUFFIX);
        QName qname = new QName("http://solution.dimas/big-data/mtom", "BigDataMTOMImplService");
        Service service = Service.create(wsdl,qname);
        this.proxy = service.getPort(IBigDataMTOM.class);
    }


    @Test
    public void downloadFile(){
        String fileName = "fileName2";
        DownloadResponse downloadResponse = this.proxy.downloadFile(new DownloadRequest(fileName));
        Assert.assertNotNull(downloadResponse);
        Assert.assertEquals(downloadResponse.fileName , fileName);
        logger.info("File Name " + downloadResponse.fileName + " Content: " + downloadResponse.content.toString());
    }

    @Test
    public void getFileQuery(){
        String keyWord = "keyWord";
        String fileName = "fileName0";
        QueryResponse queryResponse = this.proxy.getFileQuery(new QueryRequest(keyWord));
        Assert.assertNotNull(queryResponse);
        Assert.assertEquals(queryResponse.fileNames.size(), 4);
        Assert.assertEquals(queryResponse.fileNames.get(0), fileName);
        // logger.info("File Name " + downloadResponse.fileName + " Content: " + downloadResponse.content.toString());
    }

    @Test
    public void uploadFile(){
        String check = "Uploaded";
        BigData newData = BigData.generate();

        UploadResponse uploadResponse = this.proxy.uploadFile(new UploadRequest(newData.fileName, newData.rawData , newData.keyWord));

        Assert.assertNotNull(uploadResponse);
        Assert.assertEquals(check, uploadResponse.response);
        
    }
}
