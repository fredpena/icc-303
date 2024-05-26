## Instalación de MPJ Express

MPJ Express es una implementación de MPI para Java. Sigue estos pasos para instalarlo y configurarlo:

1. Descargar MPJ Express: Visita **[MPJ Express](http://mpjexpress.org/download.php)** y descarga la versión más
   reciente.

2. Descomprimir el archivo descargado:

```shell
tar -xvf mpj-v0_44.tar.gz
```

o

```shell
unzip mpj-v0_44.zip
```

3. Configurar las variables de entorno:

```shell
export MPJ_HOME=${PWD}
export PATH=$MPJ_HOME/bin:$PATH
```

3. Verificación de las variables de entorno:

```shell
echo $MPJ_HOME  
```

## Usar un build tool como Maven

Si prefieres usar un build tool como Maven para gestionar las dependencias, puedes crear un archivo pom.xml y definir la
dependencia manualmente si no está disponible en un repositorio central. Para esto, puedes instalar mpj.jar en tu
repositorio local de Maven y luego incluirlo en tu pom.xml.

### Instalación en el repositorio local de Maven

1. Instala `mpj.jar` en tu repositorio local de Maven:

```shell
mvn install:install-file -Dfile=$MPJ_HOME/lib/mpj.jar -DgroupId=mpj -DartifactId=mpj -Dversion=0.44 -Dpackaging=jar
```

2. Configuración de pom.xml

```xml

<dependency>
    <groupId>mpj</groupId>
    <artifactId>mpj</artifactId>
    <version>0.44</version>
</dependency>
```

## Ejecución

1. Instalar Java 8

```shell
sdk install java 8.0.412-librca
```

2. Set Java 8

```shell
sdk use java 8.0.412-librca
```

3. Compilar el código

```shell
javac -cp $MPJ_HOME/lib/mpj.jar HelloWorld.java
```

4. Compilar el código

```shell
$MPJ_HOME/bin/mpjrun.sh -np 4 HelloWorld
```
