# demo 1

docker-compose up from  directory spring-boot/
docker ps
Connect to mysql docker image exec -i -t 32e5458d2923  /bin/bash

```sql
CREATE TABLE `contact` (`id` INT NOT NULL,   `name` VARCHAR(255) NULL,   `email` VARCHAR(255) NULL,   `phone` VARCHAR(45) NULL,   PRIMARY KEY (`id`));

ALTER TABLE `contact` CHANGE COLUMN `id` `id` INT(11) NOT NULL AUTO_INCREMENT ,ADD UNIQUE INDEX `id_UNIQUE` (`id` ASC);

INSERT INTO contact VALUES(1,'ahmed','dev.zaouiahmed@gmail.com','122222');
```
# demo 2

 mvn thorntail:run
 
 java -jar target/demo-thorntail.jar



# demo 3 

#### before to start: (with minikube) : https://kubernetes.io/docs/tasks/tools/install-minikube/

1. Install an Hypervison : VirtualBox
2. Install Minikube
3. Install kubectl
4. minikube start

###### Check your installation :

```shell
kubectl version -o json
```
```json

{
  "clientVersion": {
    "major": "1",
    "minor": "14",
    "gitVersion": "v1.14.3",
    "gitCommit": "5e53fd6bc17c0dec8434817e69b04a25d8ae0ff0",
    "gitTreeState": "clean",
    "buildDate": "2019-06-06T01:44:30Z",
    "goVersion": "go1.12.5",
    "compiler": "gc",
    "platform": "linux/amd64"
  },
  "serverVersion": {
    "major": "1",
    "minor": "14",
    "gitVersion": "v1.14.1",
    "gitCommit": "b7394102d6ef778017f2ca4046abbaa23b88c290",
    "gitTreeState": "clean",
    "buildDate": "2019-04-08T17:02:58Z",
    "goVersion": "go1.12.1",
    "compiler": "gc",
    "platform": "linux/amd64"
  }
}

```
```shell
minikube version
minikube version: v1.0.1
```


 


#### Create a secret object

```shell
echo -n "Zaoui_1234" | base64 -

echo -n "zaoui" | base64 -
```



```yaml
apiVersion: v1
kind: Secret
metadata:
  name: mongodb
type: Opaque #means that from kubernetes this Secret is unstructured it can contain arbitrary key-value pairs.
data:
  database-password: WmFvdWlfMTIzNA==
  database-user: emFvdWk=

```

kubectl create -f mongodb-secret.yaml

#### Create ConfigMap

ConfigMap resources allow to decouple configuration artifacts from image content
to keep containerized apllication portable.

```yaml
apiVersion: v1
kind: ConfigMap
metadata:
  name: mongodb
data:
  database-name: admin

```

kubectl create -f mongodb-configmap.yaml




