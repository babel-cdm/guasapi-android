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
.setMediaType("{Indicar en formato string}")
.setMediaType("application/json; charset=utf-8")
```

### MultipartBodyType:

* ALTERNATIVE
> El tipo "multipart/alternative" es sintácticamente idéntico a "multipart/mixed", pero la semántica son diferentes. En particular, cada una de las partes del cuerpo es una versión "alternativa" de la misma información.

* DIGEST
> Este tipo es sintácticamente idéntico a "multipart/mixed", pero la semántica son diferentes. En particular, en un digest, el valor de tipo de contenido predeterminado para una parte del cuerpo se cambia de "text/plain" a "mensaje/rfc822".

* FORM
> El media-type multipart/form-data sigue las reglas de todos los flujos de datos MIME de varias partes como se describe en el RFC 2046. En las formas, hay una serie de campos a ser suministrados por el usuario que rellena el formulario. Cada campo tiene un nombre. Dentro de una determinada forma, los nombres son únicos.

* MIXED
> El subtipo "mixed" de "multipart" es para uso cuando las partes del cuerpo son independientes y deben ser agrupados en un orden determinado. Cualquier subtipos "multipart" que una implementación no reconoce deben ser tratados como de subtipo "mixed".

* PARALLEL
> Este tipo es sintácticamente idéntico a "multipart/mixed", pero la semántica son diferentes. En particular, en una entidad paralelo, el orden de las partes del cuerpo no es significativo.

Como usar:

```
.setMultipartForm(new GMultipartFormData()
    .setType(GConstants.GMultipartBody.FORM)
    .add("title", "Logo", false /* No incluir body */)
    .add("image", "logo.png", true /* Incluir body */) 
)
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

### Enviar fichero 

```
File file = new File("README.md");

GHeader gHeader = new GHeader()
    .add("Content-Type", "application/json;charset=UTF-8")
    .add("Content-Length", Long.toString(body.length()));

new Guasapi().builder()
    .setId("1234567")
    .setUrl("http://desarrollo.babel.es/api/empleados")
    .setType(GConstants.Type.POST)
    .setFile(file)
    .setMediaType(GConstants.GMediaType.JSON)
    .setHeader(gHeader)
    .setCallback(this)
    .doCall();
```

### Enviar FormBody 

```
GHeader gHeader = new GHeader()
    .add("Content-Type", "text/xml;charset=UTF-8")
    .add("Content-Length", Long.toString(body.length()));

GFormBody gFormBody = new GFormBody()
    .add("key1", "value1")
    .add("key2", "value2");

new Guasapi().builder()
    .setId(SAUKConstant.ID_LOGIN_CHECKID)
    .setUrl(SAUKConstant.URL_SAUK_LOGIN)
    .setType(GConstants.Type.POST)
    .setFormBody(gFormBody)
    .setMediaType(GConstants.GMediaType.XML)
    .setHeader(gHeader)
    .setCallback(this)
    .doCall();
```

### Usar MultipartForm

```
GHeader gHeader = new GHeader()
    .add("Content-Type", "text/xml;charset=UTF-8")
    .add("Content-Length", Long.toString(body.length()));

GFormBody gFormBody = new GFormBody()
    .add("key1", "value1")
    .add("key2", "value2");

new Guasapi().builder()
    .setId(SAUKConstant.ID_LOGIN_CHECKID)
    .setUrl(SAUKConstant.URL_SAUK_LOGIN)
    .setType(GConstants.Type.POST)
    .setFormBody(gFormBody)
    .setMultipartForm(new GMultipartFormData()
        .setType(GConstants.GMultipartBody.FORM)    
        .add("title", "Logo", false /* No incluir body */)
        .add("image", "logo.png", true /* Incluir body */) 
    )
    .setMediaType(GConstants.GMediaType.XML)
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

## Callback
Las respuesta de de las peticiones se reciben todas en el **UIThread**. La clase que desee recibir al el response de la respueta debe de implementar la iterfaz **GCallback**

```
public class MyClass implements GCallback {
	...
}
```

Dicha interfaz contiene dos métodos que tendrán que ser sobreescribidos:

```
@Override
public void onError(GResponse response) {
    // TODO ...
}

@Override
public void onSuccess(GResponse response) {
	// TODO ...
}
```

La estructura de GResponse sería la suiguiente:

```
id (String): Identificador del la petición.
code (int): Código http de la respuesta.
result (String): Resutlado obtenido.
originUrl (String): URL que realizo la llamada.
```

Métodos de uso:

```
getId(): String
getCode(): int
getResult(): String
getOriginUrl(): String
successResponse(): boolean {Indica si el http code esta entre 200 y 300}
```

## Cache
Utilizar una cache local para los response; Hay dos parámetros configurables:

* Directory ```.setDirectory(File)```
> Archivo que almacenará la cache. Hay que tener en cuenta que la aplicación debe de disponer de permisos para realzar la escritura de la cache.
 
* Size: ```.setSize(int)```
> Tamaño maximo de la cache, indicado en MegasBytes.

Como usar:

```
new Guasapi().builder()
    .setId(GConstantTest.ID)
    .setUrl(GConstantTest.URL)
    .setType(GConstants.Type.POST)
    .setMediaType(GConstants.GMediaType.JSON)
    .setBody(anyString())
    .setCache(new GCache()
            .setDirectory(new File("/storage/extSdCard/guasapi-cache"))
            .setSize(10)
    )
    .setCallback(callback)
    .doCall();
```

## Depuración

Habilitar depuración interna de la libraria:

```
.setDebug(true)
```

Ejemplo de salida de consola (log):

```
I/Guasapi: Sending request http://desarrollo.babel.es/api/empleados
       POST
       null
       Content-Type: text/xml;charset=UTF-8
       Content-Length: 425
       
I/Guasapi: Received response for http://desarrollo.babel.es/api/empleados in 1837,3ms
       http/1.1
       Date: Tue, 13 Sep 2016 15:37:45 GMT
       X-Frame-Options: SAMEORIGIN
       X-Powered-By: Servlet/3.0
       Vary: Accept-Encoding,User-Agent
       X-Content-Type-Options: nosniff
       X-XSS-Protection: 1; mode=block
       Strict-Transport-Security: max-age=15768000; includeSubDomains
       Connection: close
       Transfer-Encoding: chunked
       Content-Type: text/xml;charset=UTF-8
       Content-Language: en-GB
       Message: Internal Server Error
```

## Amplicaciones

Si necesitas alguna funcionalidad que no contemple la libraría, puedes ponerte en contacto con:

* ismael.gonzalez@babel.es
* borja.velasco@babel.es
* imanol.bracero@babel.es