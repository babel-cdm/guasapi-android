# Guasapi

## Descripción
Librería interna del Centro de Modvilidad de BABEL SI, usada para realizar peticiones http/s. Basada eb OkHttp3.

## Constantes

#### Type:
* GET
* POST
* PUT
* DELETE

Como usar: 

```
.setType(GConstants.Type.GET)
```
#### MediaType:
* JSON: "application/json; charset=utf-8"
* XML: "text/xml; charset=utf-8"
* PNG: "image/png"

Como usar:

```
.setMediaType(GConstants.GMediaType.JSON)
```

Usar un MediaType personalizado:

```
.setMediaType(MediaType.parse("{Indicar el string}"))
```

#### SSLContext
* SSL
* TLS
* TLSv1 (TLSv1.1)
* TLSv2 (TLSv1.2)

Como usar:

```
.setSecurity(new GParamsSecurity()
    .setSslContextInstace(GConstants.GSSLContext.SSL)
    .setEnabledProtocols(new String[]{
	    GConstants.GSSLContext.TLSv1.1, 
	    GConstants.GSSLContext.TLSv1.2
    })
)
```

Usar SSLContext personalizado:

```
.setSecurity(new GParamsSecurity()
    .setSslContextInstace("SSL")
    .setEnabledProtocols(new String[]{"TLSv1.1", "TLSv1.2"})
)
```

## Como usar
```
String body = '{"key1": "value1","key2": "value2"}'

GHeader gHeader = new GHeader()
    .add("Content-Type", "application/json;charset=UTF-8")
    .add("Content-Length", Long.toString(body.length()));

new Guasapi().builder()
    .setId("1234567")
    .setUrl("http://desarrollo.babel.es/api/empleados")
    .setType(GConstants.Type.POST)
    .setBody(body)
    .setMediaType(GConstants.GMediaType.JSON)
    .setHeader(gHeader)
    .setCallback(this)
    .doCall();
```

### Indicar certificados para SSL-Pining

```
String body = '{"key1": "value1","key2": "value2"}'

GHeader gHeader = new GHeader()
    .add("Content-Type", "text/xml;charset=UTF-8")
    .add("Content-Length", Long.toString(body.length()));

GCertificatePinner gCertificates = new GCertificatePinner()
    .add("hostname", "sha1/AAAAAAAAAAAAAAAAAAAAAAAAA")
    .add("hostname", "sha256/BBBBBBBBBBBBBBBBBBBBBBBBB");

new Guasapi().builder()
    .setId("123456")
    .setUrl("http://desarrollo.babel.es/api/empleados")
    .setType(GConstants.Type.POST)
    .setBody(body)
    .setMediaType(GConstants.GMediaType.XML)
    .setHeader(gHeader)
    .setCallback(this)
    .setSecurity(new GParamsSecurity()
        .setCertificates(gCertificates)
        .setSslContextInstace("SSL")
        .setEnabledProtocols(new String[]{"TLSv1.1", "TLSv1.2"})
    )
    .doCall();
```

### Permitir todos los certificados
```
String body = '{"key1": "value1","key2": "value2"}'

GHeader gHeader = new GHeader()
    .add("Content-Type", "text/xml;charset=UTF-8")
    .add("Content-Length", Long.toString(body.length()));

GCertificatePinner gCertificates = new GCertificatePinner()
    .add("hostname", "sha1/AAAAAAAAAAAAAAAAAAAAAAAAA")
    .add("hostname", "sha256/BBBBBBBBBBBBBBBBBBBBBBBBB");

new Guasapi().builder()
    .setId("123456")
    .setUrl("http://desarrollo.babel.es/api/empleados")
    .setType(GConstants.Type.POST)
    .setBody(body)
    .setMediaType(GConstants.GMediaType.XML)
    .setHeader(gHeader)
    .setCallback(this)
    .setSecurity(new GParamsSecurity()
        .setTrustAllCert(true)
        .setSslContextInstace("SSL")
        .setEnabledProtocols(new String[]{"TLSv1.1", "TLSv1.2"})
    )
    .doCall();
```

## Amplicaciones

Si necesitas alguna funcionalidad que no contemple la libraría, puedes ponerte en contacto con:

* ismael.gonzalez@babel.es
* borja.velasco@babel.es
* imanol.bracero@babel.es