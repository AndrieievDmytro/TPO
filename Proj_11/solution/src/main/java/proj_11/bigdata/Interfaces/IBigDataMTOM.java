package proj_11.bigdata.Interfaces;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.soap.MTOM;
import proj_11.bigdata.Messages.Requests.DownloadRequest;
import proj_11.bigdata.Messages.Requests.QueryRequest;
import proj_11.bigdata.Messages.Requests.UploadRequest;
import proj_11.bigdata.Messages.Responses.DownloadResponse;
import proj_11.bigdata.Messages.Responses.QueryResponse;
import proj_11.bigdata.Messages.Responses.UploadResponse;

@MTOM
@WebService( //
		name = "IBigDataMTOM", //
		targetNamespace = "http://solution.dimas/big-data/mtom" //
)
public interface IBigDataMTOM {

	public static final String URI = "http://localhost:8080/big-data/mtom";

	@WebMethod(action = "http://solution.dimas/big-data/mtom/downloadFile")
	public DownloadResponse downloadFile(DownloadRequest request);

	@WebMethod(action = "http://solution.dimas/big-data/mtom/uploadFile")
	public UploadResponse uploadFile(UploadRequest request);

	@WebMethod(action = "http://solution.dimas/big-data/mtom/getFileQuery")
	public QueryResponse getFileQuery(QueryRequest request);
}