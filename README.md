# нужно из корневой папки уйти в папку deploy
cd deploy/

# меня были ошибки вида:
# failed to list *v1.ConfigMap: configmaps "extension-apiserver-authentication" is forbidden: User "system:kube-scheduler" cannot list resource "configmaps" in API group "" in the namespace "kube-system"
# Что бы пофиксить нужно выполнить следующее:
kubectl create clusterrolebinding \
-n kube-system my-scheduler-extension-apiserver-authentication \
--clusterrole=extension-apiserver-authentication-reader \
--serviceaccount=kube-system:my-scheduler

# Ставим postgres
helm install postgress postgres-chart

# ждем пока поставятся деплои
kubectl get all

# заполним бд
kubectl apply -f initdb.yaml

# создадим ингресс контроллер
kubectl create namespace m
helm install nginx ingress-nginx/ingress-nginx --namespace m -f nginx-ingress.yaml

# задеплоим приложение
helm install otus app-chart -f app-chart/values-amd64.yaml
и ждем пока задеплоится 

# накатим ингресс
kubectl apply -f ingress.yaml

# если есть ошибка вида: failed to call webhook: Post "https://nginx-ingress-nginx-controller-admission.m.svc:443/networking/v1/ingresses?timeout=10s"
1. kubectl get validatingwebhookconfigurations
2. kubectl delete -A ValidatingWebhookConfiguration <сюда name из validatingwebhookconfigurations>
3. kubectl apply -f ingress.yaml

# получить айпишник из куба и засунуть его в хосты 
1. minikube service otus-service --url
2. sudo nano /etc/hosts

# запустить коллекцию 
newman run homework.postman_collection.json
