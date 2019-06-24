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


