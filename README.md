# нужно из корневой папки уйти в папку с манифестами
cd deploy/manifests

# создадим неймспейс
kubectl create namespace m 

# проапдейтить ингресс контроллер
helm install nginx ingress-nginx/ingress-nginx --namespace m -f nginx-ingress.yaml

# зааплаить все манифесты 
kubectl apply -f deployment.yml -f service.yml -f ingress.yaml

# если есть ошибка вида: failed to call webhook: Post "https://nginx-ingress-nginx-controller-admission.m.svc:443/networking/v1/ingresses?timeout=10s"
1. kubectl get validatingwebhookconfigurations
2. kubectl delete -A ValidatingWebhookConfiguration <сюда name из validatingwebhookconfigurations>
3. kubectl apply -f deployment.yml -f service.yml -f ingress.yaml

# получить айпишник из куба и засунуть его в хосты 
1. minikube service otus-service --url
2. sudo nano /etc/hosts

# вызвать сервис
1. curl http://arch.homework/otus
2. curl http://arch.homework/otus/health
