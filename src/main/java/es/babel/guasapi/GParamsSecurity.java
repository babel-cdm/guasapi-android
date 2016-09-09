package es.babel.guasapi;

/**
 * Created by BABEL Sistemas de Información.
 */

public class GParamsSecurity {

    boolean trustAllCert = false;
    String []enabledProtocols = new String[]{"TLSv1.1", "TLSv1.2"};
    String sslContextInstace = "SSL";
    GCertificatePinner certificatePinner = null;

    public GParamsSecurity setEnabledProtocols(String[] enabledProtocols) {
        this.enabledProtocols = enabledProtocols;
        return this;
    }

    public GParamsSecurity setSslContextInstace(String sslContextInstace) {
        this.sslContextInstace = sslContextInstace;
        return this;
    }

    public GParamsSecurity setTrustAllCert(boolean trustAllCert) {
        this.trustAllCert = trustAllCert;
        return this;
    }

    public GParamsSecurity setCertificates(GCertificatePinner certificatePinner) {
        this.certificatePinner = certificatePinner;
        return this;
    }
}
