/**
 * ISBNCheck.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package javaee.ws;

public interface ISBNCheck extends java.rmi.Remote {
    public java.lang.String normalize(java.lang.String booknum) throws java.rmi.RemoteException;
    public boolean check(java.lang.String booknum) throws java.rmi.RemoteException;
}
