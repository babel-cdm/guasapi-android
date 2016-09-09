package es.babel.guasapi;

/**
 * Created by BABEL Sistemas de Información.
 */

class GParamsInternalSecurity extends GParamsSecurity {

    public GParamsInternalSecurity(GParamsSecurity securityInputParams) {
        this.enabledProtocols = securityInputParams.enabledProtocols;
        this.sslContextInstace = securityInputParams.sslContextInstace;
    }

    public boolean trustAllCerts() {
        return trustAllCert;
    }

    public String[] getEnabledProtocols() {
        return enabledProtocols;
    }

    public String getSslContextInstace() {
        return sslContextInstace;
    }

    public GCertificatePinner getCertificatePinner() {
        return certificatePinner;
    }
}
