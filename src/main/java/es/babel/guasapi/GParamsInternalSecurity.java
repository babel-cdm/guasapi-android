package es.babel.guasapi;

/**
 * Created by BABEL Sistemas de Información.
 */

class GParamsInternalSecurity extends GParamsSecurity {

    public GParamsInternalSecurity(GParamsSecurity securityInputParams) {
        this.enabledProtocols = securityInputParams == null ? null : securityInputParams.enabledProtocols;
        this.sslContextInstace = securityInputParams == null ? null : securityInputParams.sslContextInstace;
        this.trustAllCert = securityInputParams == null ? false : securityInputParams.trustAllCert;
        this.certificatePinner = securityInputParams == null ? null : securityInputParams.certificatePinner;
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
