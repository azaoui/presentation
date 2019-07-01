https://www.youtube.com/watch?v=D0OtWlMkBLI


# demo 1

docker build -t azaoui/hibernatex .
docker run azaoui/hibernatex

docker-compose up from  directory spring-boot/
docker ps
Connect to mysql docker image exec -i -t 32e5458d2923  /bin/bash
docker exec -i -t 4d30da675b81  /bin/bash

```sql
CREATE TABLE `contact` (`id` INT NOT NULL,   `name` VARCHAR(255) NULL,   `email` VARCHAR(255) NULL,   `phone` VARCHAR(45) NULL,   PRIMARY KEY (`id`));

ALTER TABLE `contact` CHANGE COLUMN `id` `id` INT(11) NOT NULL AUTO_INCREMENT ,ADD UNIQUE INDEX `id_UNIQUE` (`id` ASC);

INSERT INTO contact VALUES(1,'ahmed','dev.zaouiahmed@gmail.com','122222');
```
# demo 2

 mvn thorntail:run
 
 java -jar target/demo-thorntail.jar



# demo 3 

#### before to start: (with minikube) :

1. Install an Hypervison : VirtualBox
2. Install Minikube : https://kubernetes.io/docs/tasks/tools/install-minikube/
3. Install kubectl  : https://kubernetes.io/docs/tasks/tools/install-kubectl/
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


#### Create Deployment

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: mongodb
  labels:
    app: mongodb
spec:
  replicas: 1
  selector:
    matchLabels:
      app: mongodb
  template:
    metadata:
      labels:
        app: mongodb
    spec:
      containers:
      - name: mongodb
        image: mongo:latest
        ports:
        - containerPort: 27017
        env:
        - name: MONGO_INITDB_DATABASE
          valueFrom:
            configMapKeyRef:
              name: mongodb
              key: database-name
        - name: MONGO_INITDB_ROOT_USERNAME
          valueFrom:
            secretKeyRef:
              name: mongodb
              key: database-user
        - name: MONGO_INITDB_ROOT_PASSWORD
          valueFrom:
            secretKeyRef:
              name: mongodb
              key: database-password
```

#### Create Service

```yaml
apiVersion: v1
kind: Service
metadata:
  name: mongodb
  labels:
    app: mongodb
spec:
  ports:
  - port: 27017
    protocol: TCP
  selector:
    app: mongodb
```

https://kubernetes.io/docs/concepts/services-networking/service/

A Service is an abstraction which defines a logical set of Pods and a policy by which to access them.

This specification will create a new Service object named “mongodb” which targets TCP port 27017 on any Pod with the "app=mongodb" label.
Note: A Service can map any incoming port to a targetPort. By default, and for convenience, the targetPort will be set to the same value as the port field.





#### Create sample microservice : employees:


#### Create Deployment and service for the employee microservice:

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: employee
  labels:
    app: employee
spec:
  replicas: 1
  selector:
    matchLabels:
      app: employee
  template:
    metadata:
      labels:
        app: employee
    spec:
      containers:
      - name: employee
        image: piomin/employee:1.0
        ports:
        - containerPort: 8080
        env:
        - name: MONGO_DATABASE
          valueFrom:
            configMapKeyRef:
              name: mongodb
              key: database-name
        - name: MONGO_USERNAME
          valueFrom:
            secretKeyRef:
              name: mongodb
              key: database-user
        - name: MONGO_PASSWORD
          valueFrom:
            secretKeyRef:
              name: mongodb
              key: database-password
---
apiVersion: v1
kind: Service
metadata:
  name: employee
  labels:
    app: employee
spec:
  ports:
  - protocol: TCP
    port: 8080
  type: NodePort
  selector:
    app: employee
```

If you set the type field to NodePort, the Kubernetes control plane will allocate a port from a range specified by --service-node-port-range flag (default: 30000-32767). Each node will proxy that port each (the same port number on every Node) into your Service. Your service will report that allocated port in its .spec.ports[*].nodePort field.

NodePort: Exposes the service on each Node’s IP at a static port (the NodePort). A ClusterIP service, to which the NodePort service will route, is automatically created. You’ll be able to contact the NodePort service, from outside the cluster, by requesting <NodeIP>:<NodePort>.

