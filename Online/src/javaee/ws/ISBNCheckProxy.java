package javaee.ws;

public class ISBNCheckProxy implements javaee.ws.ISBNCheck {
  private String _endpoint = null;
  private javaee.ws.ISBNCheck iSBNCheck = null;
  
  public ISBNCheckProxy() {
    _initISBNCheckProxy();
  }
  
  public ISBNCheckProxy(String endpoint) {
    _endpoint = endpoint;
    _initISBNCheckProxy();
  }
  
  private void _initISBNCheckProxy() {
    try {
      iSBNCheck = (new javaee.ws.ISBNCheckServiceLocator()).getISBNCheck();
      if (iSBNCheck != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)iSBNCheck)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)iSBNCheck)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (iSBNCheck != null)
      ((javax.xml.rpc.Stub)iSBNCheck)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public javaee.ws.ISBNCheck getISBNCheck() {
    if (iSBNCheck == null)
      _initISBNCheckProxy();
    return iSBNCheck;
  }
  
  public java.lang.String normalize(java.lang.String booknum) throws java.rmi.RemoteException{
    if (iSBNCheck == null)
      _initISBNCheckProxy();
    return iSBNCheck.normalize(booknum);
  }
  
  public boolean check(java.lang.String booknum) throws java.rmi.RemoteException{
    if (iSBNCheck == null)
      _initISBNCheckProxy();
    return iSBNCheck.check(booknum);
  }
  
  
}